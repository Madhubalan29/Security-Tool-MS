package com.example.dto;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.util.*;

public class ParsedExcel {
    private final Map<String, List<Object[]>> sheets = new HashMap<>();
    private final Map<String, Map<String, Integer>> sheetHeaders = new HashMap<>();

    public ParsedExcel(File file) {
        try (Workbook workbook = WorkbookFactory.create(file)) {
            for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
                Sheet sheet = workbook.getSheetAt(s);
                String sheetName = sheet.getSheetName();
                List<Object[]> rows = new ArrayList<>();
                Map<String, Integer> headers = new HashMap<>();

                Row headerRow = sheet.getRow(0);
                if (headerRow != null) {
                    for (Cell cell : headerRow) {
                        if (cell != null && cell.getCellType() == CellType.STRING) {
                            headers.put(cell.getStringCellValue(), cell.getColumnIndex());
                        }
                    }
                }
                
                int maxCol = headers.values().stream().max(Integer::compareTo).orElse(0);

                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        rows.add(null);
                        continue;
                    }
                    Object[] rowData = new Object[maxCol + 1];
                    for (int c = 0; c <= maxCol; c++) {
                        Cell cell = row.getCell(c);
                        if (cell != null) {
                            CellType type = cell.getCellType() == CellType.FORMULA ? cell.getCachedFormulaResultType() : cell.getCellType();
                            if (type == CellType.STRING) {
                                rowData[c] = cell.getStringCellValue();
                            } else if (type == CellType.NUMERIC) {
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    rowData[c] = cell.getDateCellValue();
                                } else {
                                    rowData[c] = cell.getNumericCellValue();
                                }
                            }
                        }
                    }
                    rows.add(rowData);
                }

                sheets.put(sheetName, rows);
                sheetHeaders.put(sheetName, headers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> getSheetRows(String sheetName) {
        return sheets.get(sheetName);
    }

    public Map<String, Integer> getSheetHeaders(String sheetName) {
        return sheetHeaders.get(sheetName);
    }
}
