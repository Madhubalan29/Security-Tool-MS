package com.example.service;

import com.example.dto.SheetMappingDto;
import com.example.dto.ParsedExcel;
import com.example.entity.AsRate;
import com.example.entity.AsRateGroup;
import com.example.secondaryDev.repository.AsRateGroupRepository;
import com.example.secondaryDev.repository.AsRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RateLoaderService {

    @Autowired
    private AsRateGroupRepository asRateGroupRepository;

    @Autowired
    private AsRateRepository asRateRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getDistinctRateDescriptions() {
        return asRateGroupRepository.findDistinctRateDescriptions();
    }

    public List<String> getDistinctAsRateDescriptions() {
        return asRateRepository.findDistinctRateDescriptions();
    }

    private String generateUniqueGuid(String uploadSessionId, String sheetName, int rowIndex) {
        String input = (uploadSessionId != null ? uploadSessionId : "default") + "_" + sheetName + "_" + rowIndex;
        return UUID.nameUUIDFromBytes(input.getBytes()).toString().toUpperCase();
    }

    public AsRateGroup getRateDescriptionDetails(String rateDesc) {
        return asRateGroupRepository.findByRateDescription(rateDesc);
    }

    private String getRateGroupGuidByDescription(String rateDesc) {
        AsRateGroup descEntity = asRateGroupRepository.findByRateDescription(rateDesc);
        if (descEntity == null) {
            throw new IllegalArgumentException("Rate description not found in AsRateGroup table: " + rateDesc);
        }
        return descEntity.getRateGroupGuid();
    }

    private Date extractDate(Object dateVal, int rowIndex, List<String> errors) {
        if (dateVal instanceof Date) {
            return (Date) dateVal;
        } else if (dateVal instanceof Double) {
            return org.apache.poi.ss.usermodel.DateUtil.getJavaDate((Double) dateVal);
        } else if (dateVal instanceof String) {
            String dateStr = ((String) dateVal).trim();
            if (dateStr.isEmpty()) return null;
            String[] formats = {
                "yyyy-MM-dd", "dd-MM-yyyy", "MM-dd-yyyy", 
                "yyyy/MM/dd", "dd/MM/yyyy", "MM/dd/yyyy",
                "dd-MMM-yyyy", "dd-MMM-yy", "MMM dd, yyyy",
                "dd.MM.yyyy", "MM.dd.yyyy",
                "dd-MM-yy", "MM-dd-yy"
            };
            for (String format : formats) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
                    sdf.setLenient(false);
                    return sdf.parse(dateStr);
                } catch (Exception e) {}
            }
            if (errors != null) {
                errors.add("Row " + rowIndex + ": Invalid date format '" + dateStr + "'");
            }
            return null;
        }
        return null;
    }

    public AsRateGroup createRateDescription(AsRateGroup description) {
        if (asRateGroupRepository.existsByRateDescription(description.getRateDescription())) {
            throw new IllegalArgumentException("Rate description already exists!");
        }

        if (description.getRateGroupGuid() == null || description.getRateGroupGuid().isEmpty()) {
            description.setRateGroupGuid(UUID.randomUUID().toString());
        }

        if (description.getStatusCode() == null) description.setStatusCode("01");
        if (description.getTypeCode() == null) description.setTypeCode("01");
        if (description.getIntegerCriteria() == null) description.setIntegerCriteria("1");
        
        return asRateGroupRepository.save(description);
    }

    public List<AsRate> validateAndExtractRates(ParsedExcel parsedExcel, List<SheetMappingDto> mappings, String uploadSessionId) throws Exception {
        List<String> dateErrors = new ArrayList<>();
        List<AsRate> allRatesToSave = new ArrayList<>();
        
        for (SheetMappingDto mapping : mappings) {
            List<Object[]> rows = parsedExcel.getSheetRows(mapping.getSheetName());
            if (rows == null) continue;

            Map<String, String> colMap = mapping.getColumnMappings();
            String rateDesc = colMap.get("RATEDESCRIPTION");
            String rateGroupGuid = getRateGroupGuidByDescription(rateDesc);

            Map<String, Integer> colIndexMap = parsedExcel.getSheetHeaders(mapping.getSheetName());

            List<AsRate> ratesToSave = new ArrayList<>();
            for (int i = 0; i < rows.size(); i++) {
                Object[] row = rows.get(i);
                if (row == null) continue;

                AsRate rate = new AsRate();
                rate.setRateGuid(generateUniqueGuid(uploadSessionId, mapping.getSheetName(), i + 1));
                rate.setRateGroupGuid(rateGroupGuid);
                rate.setRateDescription(rateDesc);

                rate.setRate(getBigDecimalValue(row, colIndexMap, colMap.get("RATE")));
                rate.setIntegerCriteria(getIntegerValue(row, colIndexMap, colMap.get("INTEGERCRITERIA")));

                String dateCol = colMap.get("DATECRITERIA");
                if (dateCol != null && colIndexMap.containsKey(dateCol)) {
                    Object dateVal = row[colIndexMap.get(dateCol)];
                    if (dateVal != null) {
                        Date d = extractDate(dateVal, i + 2, dateErrors);
                        if (d != null) {
                            rate.setDateCriteria(new Timestamp(d.getTime()));
                        }
                    }
                }

                rate.setCriteria1(getStringValue(row, colIndexMap, colMap.get("CRITERIA1")));
                rate.setCriteria2(getStringValue(row, colIndexMap, colMap.get("CRITERIA2")));
                rate.setCriteria3(getStringValue(row, colIndexMap, colMap.get("CRITERIA3")));
                rate.setCriteria4(getStringValue(row, colIndexMap, colMap.get("CRITERIA4")));
                rate.setCriteria5(getStringValue(row, colIndexMap, colMap.get("CRITERIA5")));
                rate.setCriteria6(getStringValue(row, colIndexMap, colMap.get("CRITERIA6")));
                rate.setCriteria7(getStringValue(row, colIndexMap, colMap.get("CRITERIA7")));
                rate.setCriteria8(getStringValue(row, colIndexMap, colMap.get("CRITERIA8")));
                rate.setCriteria9(getStringValue(row, colIndexMap, colMap.get("CRITERIA9")));
                rate.setCriteria10(getStringValue(row, colIndexMap, colMap.get("CRITERIA10")));

                allRatesToSave.add(rate);
            }
        }
        
        if (!dateErrors.isEmpty()) {
            String msg = String.join("\n", dateErrors.subList(0, Math.min(20, dateErrors.size())));
            if (dateErrors.size() > 20) {
                msg += "\n...and " + (dateErrors.size() - 20) + " more invalid rows.";
            }
            throw new IllegalArgumentException(msg);
        }
        
        return allRatesToSave;
    }

    public void executeBatchInsert(List<AsRate> ratesToSave) {
        String sql = "INSERT INTO ASRATE (RATEGUID, RATEGROUPGUID, RATEDESCRIPTION, RATE, INTEGERCRITERIA, DATECRITERIA, " +
                     "CRITERIA1, CRITERIA2, CRITERIA3, CRITERIA4, CRITERIA5, CRITERIA6, CRITERIA7, CRITERIA8, CRITERIA9, CRITERIA10) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                     
        int batchSize = 1000;
        List<List<AsRate>> chunks = new ArrayList<>();
        for (int i = 0; i < ratesToSave.size(); i += batchSize) {
            chunks.add(ratesToSave.subList(i, Math.min(i + batchSize, ratesToSave.size())));
        }

        // Execute chunks in blazing fast parallel threads
        chunks.parallelStream().forEach(chunk -> {
            jdbcTemplate.batchUpdate(sql, new org.springframework.jdbc.core.BatchPreparedStatementSetter() {
                @Override
                public void setValues(java.sql.PreparedStatement ps, int i) throws java.sql.SQLException {
                    AsRate rate = chunk.get(i);
                    ps.setString(1, rate.getRateGuid());
                    ps.setString(2, rate.getRateGroupGuid());
                    ps.setString(3, rate.getRateDescription());
                    if (rate.getRate() != null) ps.setBigDecimal(4, rate.getRate()); else ps.setNull(4, java.sql.Types.DECIMAL);
                    if (rate.getIntegerCriteria() != null) ps.setInt(5, rate.getIntegerCriteria()); else ps.setNull(5, java.sql.Types.INTEGER);
                    if (rate.getDateCriteria() != null) ps.setTimestamp(6, rate.getDateCriteria()); else ps.setNull(6, java.sql.Types.TIMESTAMP);
                    ps.setString(7, rate.getCriteria1());
                    ps.setString(8, rate.getCriteria2());
                    ps.setString(9, rate.getCriteria3());
                    ps.setString(10, rate.getCriteria4());
                    ps.setString(11, rate.getCriteria5());
                    ps.setString(12, rate.getCriteria6());
                    ps.setString(13, rate.getCriteria7());
                    ps.setString(14, rate.getCriteria8());
                    ps.setString(15, rate.getCriteria9());
                    ps.setString(16, rate.getCriteria10());
                }

                @Override
                public int getBatchSize() {
                    return chunk.size();
                }
            });
        });
    }

    public String generateScript(ParsedExcel parsedExcel, List<SheetMappingDto> mappings, String uploadSessionId) throws Exception {
        StringBuilder sql = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        List<String> dateErrors = new ArrayList<>();

        for (SheetMappingDto mapping : mappings) {
            List<Object[]> rows = parsedExcel.getSheetRows(mapping.getSheetName());
            if (rows == null) continue;

            Map<String, String> colMap = mapping.getColumnMappings();
            String rateDesc = colMap.get("RATEDESCRIPTION");
            String rateGroupGuid = getRateGroupGuidByDescription(rateDesc);

            Map<String, Integer> colIndexMap = parsedExcel.getSheetHeaders(mapping.getSheetName());

            for (int i = 0; i < rows.size(); i++) {
                Object[] row = rows.get(i);
                if (row == null) continue;

                String rateGuid = generateUniqueGuid(uploadSessionId, mapping.getSheetName(), i + 1);
                BigDecimal rateVal = getBigDecimalValue(row, colIndexMap, colMap.get("RATE"));
                Integer intCrit = getIntegerValue(row, colIndexMap, colMap.get("INTEGERCRITERIA"));

                String dateCriteriaSql = "NULL";
                String dateCol = colMap.get("DATECRITERIA");
                if (dateCol != null && colIndexMap.containsKey(dateCol)) {
                    Object dateVal = row[colIndexMap.get(dateCol)];
                    if (dateVal != null) {
                        Date d = extractDate(dateVal, i + 2, dateErrors);
                        if (d != null) {
                            String formattedDate = sdf.format(d);
                            dateCriteriaSql = "to_timestamp('" + formattedDate + " 12:00:00.000000000 AM','DD-MM-YYYY fmHH12:fmMI:SSXFF AM')";
                        }
                    }
                }

                String c1 = escapeSql(getStringValue(row, colIndexMap, colMap.get("CRITERIA1")));
                String c2 = escapeSql(getStringValue(row, colIndexMap, colMap.get("CRITERIA2")));
                String c3 = escapeSql(getStringValue(row, colIndexMap, colMap.get("CRITERIA3")));
                String c4 = escapeSql(getStringValue(row, colIndexMap, colMap.get("CRITERIA4")));
                String c5 = escapeSql(getStringValue(row, colIndexMap, colMap.get("CRITERIA5")));
                String c6 = escapeSql(getStringValue(row, colIndexMap, colMap.get("CRITERIA6")));
                String c7 = escapeSql(getStringValue(row, colIndexMap, colMap.get("CRITERIA7")));
                String c8 = escapeSql(getStringValue(row, colIndexMap, colMap.get("CRITERIA8")));
                String c9 = escapeSql(getStringValue(row, colIndexMap, colMap.get("CRITERIA9")));
                String c10 = escapeSql(getStringValue(row, colIndexMap, colMap.get("CRITERIA10")));

                sql.append("Insert into ASRATE (RATEGUID,RATEGROUPGUID,RATEDESCRIPTION,DATECRITERIA,")
                   .append("CRITERIA1,CRITERIA2,CRITERIA3,CRITERIA4,CRITERIA5,CRITERIA6,CRITERIA7,CRITERIA8,CRITERIA9,CRITERIA10,")
                   .append("INTEGERCRITERIA,RATE) values (")
                   .append("'").append(rateGuid).append("',")
                   .append("'").append(rateGroupGuid).append("',")
                   .append("'").append(rateDesc).append("',")
                   .append(dateCriteriaSql).append(",")
                   .append(c1).append(",").append(c2).append(",").append(c3).append(",").append(c4).append(",").append(c5).append(",")
                   .append(c6).append(",").append(c7).append(",").append(c8).append(",").append(c9).append(",").append(c10).append(",")
                   .append(intCrit == null ? "NULL" : intCrit).append(",")
                   .append(rateVal == null ? "NULL" : rateVal.stripTrailingZeros().toPlainString())
                   .append(");\n");
            }
        }
        
        if (!dateErrors.isEmpty()) {
            String msg = String.join("\n", dateErrors.subList(0, Math.min(20, dateErrors.size())));
            if (dateErrors.size() > 20) {
                msg += "\n...and " + (dateErrors.size() - 20) + " more invalid rows.";
            }
            throw new IllegalArgumentException(msg);
        }
        
        return sql.toString();
    }

    public String exportScript(List<String> rateDescriptions) {
        if (rateDescriptions == null || rateDescriptions.isEmpty()) return "";
        
        String inSql = String.join(",", java.util.Collections.nCopies(rateDescriptions.size(), "?"));
        String query = "SELECT RATEGUID, RATEGROUPGUID, RATEDESCRIPTION, DATECRITERIA, CRITERIA1, CRITERIA2, CRITERIA3, CRITERIA4, CRITERIA5, CRITERIA6, CRITERIA7, CRITERIA8, CRITERIA9, CRITERIA10, INTEGERCRITERIA, RATE FROM ASRATE WHERE RATEDESCRIPTION IN (" + inSql + ")";
        
        return jdbcTemplate.query(
            (java.sql.Connection con) -> {
                java.sql.PreparedStatement ps = con.prepareStatement(query);
                ps.setFetchSize(10000); // Crucial for Oracle bulk fetch performance
                for (int i = 0; i < rateDescriptions.size(); i++) {
                    ps.setString(i + 1, rateDescriptions.get(i));
                }
                return ps;
            },
            (java.sql.ResultSet rs) -> {
                StringBuilder sql = new StringBuilder(1024 * 1024); // 1MB initial buffer
                
                // Cache column indices for O(1) lookups instead of 300,000 string lookups
                int colRateGuid = rs.findColumn("RATEGUID");
                int colRateGroupGuid = rs.findColumn("RATEGROUPGUID");
                int colRateDesc = rs.findColumn("RATEDESCRIPTION");
                int colDate = rs.findColumn("DATECRITERIA");
                int colC1 = rs.findColumn("CRITERIA1");
                int colC2 = rs.findColumn("CRITERIA2");
                int colC3 = rs.findColumn("CRITERIA3");
                int colC4 = rs.findColumn("CRITERIA4");
                int colC5 = rs.findColumn("CRITERIA5");
                int colC6 = rs.findColumn("CRITERIA6");
                int colC7 = rs.findColumn("CRITERIA7");
                int colC8 = rs.findColumn("CRITERIA8");
                int colC9 = rs.findColumn("CRITERIA9");
                int colC10 = rs.findColumn("CRITERIA10");
                int colInt = rs.findColumn("INTEGERCRITERIA");
                int colRate = rs.findColumn("RATE");

                while (rs.next()) {
                    String dateCriteriaSql = "NULL";
                    java.sql.Timestamp ts = rs.getTimestamp(colDate);
                    if (ts != null) {
                        java.time.LocalDateTime ldt = ts.toLocalDateTime();
                        String formattedDate = String.format("%02d-%02d-%04d", ldt.getDayOfMonth(), ldt.getMonthValue(), ldt.getYear());
                        dateCriteriaSql = "to_timestamp('" + formattedDate + " 12:00:00.000000000 AM','DD-MM-YYYY fmHH12:fmMI:SSXFF AM')";
                    }

                    BigDecimal rateVal = rs.getBigDecimal(colRate);
                    String rateStr = rateVal == null ? "NULL" : rateVal.stripTrailingZeros().toPlainString();
                    
                    Object intCritVal = rs.getObject(colInt);
                    String intCrit = intCritVal == null ? "NULL" : intCritVal.toString();

                    String rgGuid = rs.getString(colRateGroupGuid);
                    rgGuid = rgGuid != null ? rgGuid : "";

                    sql.append("Insert into ASRATE (RATEGUID,RATEGROUPGUID,RATEDESCRIPTION,DATECRITERIA,")
                       .append("CRITERIA1,CRITERIA2,CRITERIA3,CRITERIA4,CRITERIA5,CRITERIA6,CRITERIA7,CRITERIA8,CRITERIA9,CRITERIA10,")
                       .append("INTEGERCRITERIA,RATE) values (")
                       .append("'").append(rs.getString(colRateGuid)).append("',")
                       .append("'").append(rgGuid).append("',")
                       .append("'").append(rs.getString(colRateDesc)).append("',")
                       .append(dateCriteriaSql).append(",")
                       .append(escapeSql(rs.getString(colC1))).append(",")
                       .append(escapeSql(rs.getString(colC2))).append(",")
                       .append(escapeSql(rs.getString(colC3))).append(",")
                       .append(escapeSql(rs.getString(colC4))).append(",")
                       .append(escapeSql(rs.getString(colC5))).append(",")
                       .append(escapeSql(rs.getString(colC6))).append(",")
                       .append(escapeSql(rs.getString(colC7))).append(",")
                       .append(escapeSql(rs.getString(colC8))).append(",")
                       .append(escapeSql(rs.getString(colC9))).append(",")
                       .append(escapeSql(rs.getString(colC10))).append(",")
                       .append(intCrit).append(",")
                       .append(rateStr)
                       .append(");\n");
                }
                return sql.toString();
            }
        );
    }

    @org.springframework.transaction.annotation.Transactional
    public void deleteRates(List<String> rateDescriptions) {
        if (rateDescriptions != null && !rateDescriptions.isEmpty()) {
            asRateRepository.bulkDeleteByRateDescriptionIn(rateDescriptions);
        }
    }

    private String getStringValue(Object[] row, Map<String, Integer> colIndexMap, String colName) {
        if (colName == null || !colIndexMap.containsKey(colName) || row == null) return null;
        int idx = colIndexMap.get(colName);
        if (idx >= row.length) return null;
        Object val = row[idx];
        if (val == null) return null;
        if (val instanceof String) return (String) val;
        if (val instanceof Double) return new BigDecimal(String.valueOf(val)).stripTrailingZeros().toPlainString();
        return null;
    }

    private BigDecimal getBigDecimalValue(Object[] row, Map<String, Integer> colIndexMap, String colName) {
        if (colName == null || !colIndexMap.containsKey(colName) || row == null) return null;
        int idx = colIndexMap.get(colName);
        if (idx >= row.length) return null;
        Object val = row[idx];
        if (val == null) return null;
        if (val instanceof Double) return BigDecimal.valueOf((Double) val);
        if (val instanceof String) {
            try { return new BigDecimal((String) val); } catch(Exception e) { return null; }
        }
        return null;
    }

    private Integer getIntegerValue(Object[] row, Map<String, Integer> colIndexMap, String colName) {
        if (colName == null || !colIndexMap.containsKey(colName) || row == null) return null;
        int idx = colIndexMap.get(colName);
        if (idx >= row.length) return null;
        Object val = row[idx];
        if (val == null) return null;
        if (val instanceof Double) return ((Double) val).intValue();
        if (val instanceof String) {
            try { return Integer.parseInt((String) val); } catch(Exception e) { return null; }
        }
        return null;
    }

    private String escapeSql(String val) {
        if (val == null) return "NULL";
        return "'" + val.replace("'", "''") + "'";
    }
}
