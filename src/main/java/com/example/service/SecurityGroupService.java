package com.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.security.*;
import com.example.entity.security.*;
import com.example.entity.AsCompany;
import com.example.entity.AsAuthPage;
import com.example.entity.AsAuthButton;
import com.example.entity.AsAuthWebService;
import com.example.entity.AsInquiryScreen;
import com.example.entity.AsProduct;
import com.example.entity.AsTransaction;
import com.example.entity.AsPlan;
import com.example.secondaryDev.repository.*;

/**
 * Read-only service for the OIPA Security Tool.
 * <p>
 * All reads target the secondarydev database exclusively.
 * No INSERT / UPDATE / DELETE is ever executed — the generate-scripts
 * endpoint only <b>returns</b> raw SQL strings.
 */
@Service
public class SecurityGroupService {

    // ── Repositories (all wired to secondarydev) ────────────────────────
    @Autowired private AsSecurityGroupRepository securityGroupRepo;
    @Autowired private AsCompanySecondaryDevRepository companyRepo;
    @Autowired private AsAuthPageSecondaryDevRepository pageRepo;
    @Autowired private AsAuthButtonSecondaryDevRepository buttonRepo;
    @Autowired private AsAuthWebServiceSecondaryDevRepository webServiceRepo;
    @Autowired private AsAuthCompanyRepository authCompanyRepo;
    @Autowired private AsAuthCompanyPageRepository companyPageRepo;
    @Autowired private AsAuthCompanyPageButtonRepository companyPageButtonRepo;
    @Autowired private AsAuthCompanyInquiryRepository companyInquiryRepo;
    @Autowired private AsAuthCompanyWebServiceRepository companyWebServiceRepo;
    @Autowired private AsAuthPlanRepository authPlanRepo;
    @Autowired private AsAuthPlanPageRepository planPageRepo;
    @Autowired private AsAuthPlanPageButtonRepository planPageButtonRepo;
    @Autowired private AsAuthTransactionRepository transactionRepo;
    @Autowired private AsAuthPlanInquiryRepository planInquiryRepo;
    @Autowired private AsAuthProductRepository authProductRepo;
    @Autowired private AsAuthProductPageRepository productPageRepo;
    @Autowired private AsAuthProductPageButtonRepository productPageButtonRepo;
    @Autowired private AsAuthProductTransactionRepository productTransactionRepo;
    @Autowired private AsAuthTransactionButtonRepository transactionButtonRepo;
    @Autowired private AsAuthProductTransactionButtonRepository productTransactionButtonRepo;
    @Autowired private AsInquiryScreenRepository inquiryScreenRepo;
    @Autowired private AsProductSecondaryDevRepository productSecondaryDevRepo;
    @Autowired private AsTransactionSecondaryDevRepository transactionSecondaryDevRepo;
    @Autowired private AsPlanSecondaryDevRepository planSecondaryDevRepo;

    // ===================================================================
    // 0.  GET /api/companies  —  list all companies
    // ===================================================================
    @Transactional(value = "secondaryDevTransactionManager", readOnly = true)
    public List<CompanyDto> getAllCompanies() {
        return companyRepo.findAll().stream()
                .map(c -> new CompanyDto(c.getCOMPANYGUID(), c.getCOMPANYNAME()))
                .collect(Collectors.toList());
    }

    // ===================================================================
    // 0b. GET /api/pages  —  list all pages
    // ===================================================================
    @Transactional(value = "secondaryDevTransactionManager", readOnly = true)
    public List<PageInfoDto> getAllPages() {
        return pageRepo.findAll().stream()
                .map(p -> new PageInfoDto(p.getAUTHPAGEGUID(), p.getPAGENAME()))
                .collect(Collectors.toList());
    }

    // ===================================================================
    // 0c. GET /api/buttons  —  list all buttons
    // ===================================================================
    @Transactional(value = "secondaryDevTransactionManager", readOnly = true)
    public List<ButtonInfoDto> getAllButtons() {
        return buttonRepo.findAll().stream()
                .map(b -> new ButtonInfoDto(b.getAUTHBUTTONGUID(), b.getBUTTONNAME()))
                .collect(Collectors.toList());
    }

    // ===================================================================
    // 0d. GET /api/webservices  —  list all webservices
    // ===================================================================
    @Transactional(value = "secondaryDevTransactionManager", readOnly = true)
    public List<WebServiceInfoDto> getAllWebServices() {
        return webServiceRepo.findAll().stream()
                .map(w -> new WebServiceInfoDto(w.getAUTHWEBSERVICEGUID(), w.getWEBSERVICENAME()))
                .collect(Collectors.toList());
    }

    // ===================================================================
    // 0e. GET /api/inquiry-screens  —  list inquiry screens
    // ===================================================================
    @Transactional(value = "secondaryDevTransactionManager", readOnly = true)
    public List<InquiryScreenDto> getInquiryScreens(String companyGuid, String planGuid) {
        List<AsInquiryScreen> screens;
        if (companyGuid != null && !companyGuid.trim().isEmpty()) {
            screens = inquiryScreenRepo.findByCOMPANYGUID(companyGuid);
        } else if (planGuid != null && !planGuid.trim().isEmpty()) {
            screens = inquiryScreenRepo.findByPLANGUID(planGuid);
        } else {
            screens = inquiryScreenRepo.findAll();
        }
        return screens.stream()
                .map(s -> new InquiryScreenDto(
                        s.getINQUIRYSCREENNAMEGUID() != null && !s.getINQUIRYSCREENNAMEGUID().trim().isEmpty()
                                ? s.getINQUIRYSCREENNAMEGUID()
                                : s.getINQUIRYSCREENGUID(),
                        s.getSCREENNAME()))
                .collect(Collectors.toList());
    }

    // ===================================================================
    // 0f. GET /api/companies/{companyGuid}/products  —  list products by company
    // ===================================================================
    @Transactional(value = "secondaryDevTransactionManager", readOnly = true)
    public List<ProductDto> getProductsByCompany(String companyGuid) {
        return productSecondaryDevRepo.findByCOMPANYGUID(companyGuid).stream()
                .map(p -> new ProductDto(p.getPRODUCTGUID(), p.getPRODUCTNAME(), p.getCOMPANYGUID()))
                .collect(Collectors.toList());
    }

    // ===================================================================
    // 0g. GET /api/transactions  —  list transactions by product or plan
    // ===================================================================
    @Transactional(value = "secondaryDevTransactionManager", readOnly = true)
    public List<TransactionInfoDto> getTransactions(String productGuid, String planGuid) {
        List<AsTransaction> transactions;
        if (planGuid != null && !planGuid.trim().isEmpty()) {
            transactions = transactionSecondaryDevRepo.findByPLANGUID(planGuid);
        } else if (productGuid != null && !productGuid.trim().isEmpty()) {
            transactions = transactionSecondaryDevRepo.findByPRODUCTGUID(productGuid);
        } else {
            transactions = Collections.emptyList();
        }
        return transactions.stream()
                .map(t -> new TransactionInfoDto(t.getTRANSACTIONGUID(), t.getTRANSACTIONNAME()))
                .collect(Collectors.toList());
    }

    // ===================================================================
    // 0h. GET /api/companies/{companyGuid}/products/{productGuid}/plans  —  list plans by company and product
    // ===================================================================
    @Transactional(value = "secondaryDevTransactionManager", readOnly = true)
    public List<PlanDto> getPlansByCompanyAndProduct(String companyGuid, String productGuid) {
        return planSecondaryDevRepo.findByCOMPANYGUIDAndPRODUCTGUID(companyGuid, productGuid).stream()
                .map(p -> new PlanDto(p.getPLANGUID(), p.getPLANNAME(), p.getCOMPANYGUID(), p.getPRODUCTGUID()))
                .collect(Collectors.toList());
    }

    // ===================================================================
    // 0i. GET /api/companies/{companyGuid}/plans  —  list plans by company
    // ===================================================================
    @Transactional(value = "secondaryDevTransactionManager", readOnly = true)
    public List<PlanDto> getPlansByCompany(String companyGuid) {
        return planSecondaryDevRepo.findByCOMPANYGUID(companyGuid).stream()
                .map(p -> new PlanDto(p.getPLANGUID(), p.getPLANNAME(), p.getCOMPANYGUID(), p.getPRODUCTGUID()))
                .collect(Collectors.toList());
    }

    // ===================================================================
    // 1.  GET /api/security-groups  —  list all groups
    // ===================================================================
    @Transactional(value = "secondaryDevTransactionManager", readOnly = true)
    public List<SecurityGroupDto> getAllSecurityGroups() {
        return securityGroupRepo.findAll().stream()
                .map(g -> new SecurityGroupDto(g.getSECURITYGROUPGUID(), g.getGROUPNAME()))
                .collect(Collectors.toList());
    }

    // ===================================================================
    // 1b. POST /api/security-groups/create  —  generate GUID + INSERT SQL
    // ===================================================================
    public CreateGroupResponseDto createSecurityGroup(CreateGroupRequestDto request) {
        String groupName = request.getGroupName();

        // Generate a GUID — SECURITYGROUPGUID is CHAR(144) in the DB
        String guid = java.util.UUID.randomUUID().toString().toUpperCase();

        // Build the INSERT script (never executed)
        String insertScript = String.format(
            "INSERT INTO ASSECURITYGROUP (SECURITYGROUPGUID, GROUPNAME) VALUES ('%s', '%s');",
            esc(guid), esc(groupName));

        return new CreateGroupResponseDto(guid, groupName, insertScript);
    }

    // ===================================================================
    // 2.  GET /api/security-group/{guid}  —  full nested config
    // ===================================================================
    @Transactional(value = "secondaryDevTransactionManager", readOnly = true)
    public SecurityGroupDto getSecurityConfiguration(String securityGroupGuid) {

        // Fetch the group header (or build an empty shell for new groups)
        Optional<AsSecurityGroup> groupOpt = securityGroupRepo.findById(securityGroupGuid);
        SecurityGroupDto dto = new SecurityGroupDto();
        dto.setSecurityGroupGuid(securityGroupGuid);
        dto.setGroupName(groupOpt.map(AsSecurityGroup::getGROUPNAME).orElse(""));

        if (!groupOpt.isPresent()) {
            // New group — return empty shell
            return dto;
        }

        // ── Fetch all flat auth rows for this group in one pass ─────────
        List<AsAuthCompany> companies = authCompanyRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthCompanyPage> companyPages = companyPageRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthCompanyPageButton> companyPageButtons = companyPageButtonRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthCompanyInquiry> companyInquiries = companyInquiryRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthCompanyWebService> companyWebServices = companyWebServiceRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthPlan> plans = authPlanRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthPlanPage> planPages = planPageRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthPlanPageButton> planPageButtons = planPageButtonRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthTransaction> transactions = transactionRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthTransactionButton> transactionButtons = transactionButtonRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthPlanInquiry> planInquiries = planInquiryRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthProduct> products = authProductRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthProductPage> productPages = productPageRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthProductPageButton> productPageButtons = productPageButtonRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthProductTransaction> productTransactions = productTransactionRepo.findBySECURITYGROUPGUID(securityGroupGuid);
        List<AsAuthProductTransactionButton> productTransactionButtons = productTransactionButtonRepo.findBySECURITYGROUPGUID(securityGroupGuid);

        // ── Pre-group flat lists into maps to achieve O(1) lookups inside loops ──
        Map<String, List<AsAuthCompanyPage>> companyPagesMap = companyPages.stream()
                .filter(p -> p.getCOMPANYGUID() != null)
                .collect(Collectors.groupingBy(AsAuthCompanyPage::getCOMPANYGUID));

        Map<String, List<AsAuthCompanyPageButton>> companyPageButtonsMap = companyPageButtons.stream()
                .filter(b -> b.getCOMPANYGUID() != null)
                .collect(Collectors.groupingBy(AsAuthCompanyPageButton::getCOMPANYGUID));

        Map<String, List<AsAuthCompanyInquiry>> companyInquiriesMap = companyInquiries.stream()
                .filter(i -> i.getCOMPANYGUID() != null)
                .collect(Collectors.groupingBy(AsAuthCompanyInquiry::getCOMPANYGUID));

        Map<String, List<AsAuthCompanyWebService>> companyWebServicesMap = companyWebServices.stream()
                .filter(w -> w.getCOMPANYGUID() != null)
                .collect(Collectors.groupingBy(AsAuthCompanyWebService::getCOMPANYGUID));

        Map<String, List<AsAuthPlan>> plansMap = plans.stream()
                .filter(p -> p.getCOMPANYGUID() != null)
                .collect(Collectors.groupingBy(AsAuthPlan::getCOMPANYGUID));

        Map<String, List<AsAuthPlanPage>> planPagesMap = planPages.stream()
                .filter(pp -> pp.getCOMPANYGUID() != null && pp.getPLANGUID() != null)
                .collect(Collectors.groupingBy(pp -> pp.getCOMPANYGUID() + "|" + pp.getPLANGUID()));

        Map<String, List<AsAuthPlanPageButton>> planPageButtonsMap = planPageButtons.stream()
                .filter(pb -> pb.getCOMPANYGUID() != null && pb.getPLANGUID() != null)
                .collect(Collectors.groupingBy(pb -> pb.getCOMPANYGUID() + "|" + pb.getPLANGUID()));

        Map<String, List<AsAuthTransaction>> transactionsMap = transactions.stream()
                .filter(t -> t.getCOMPANYGUID() != null && t.getPLANGUID() != null)
                .collect(Collectors.groupingBy(t -> t.getCOMPANYGUID() + "|" + t.getPLANGUID()));

        Map<String, List<AsAuthTransactionButton>> transactionButtonsMap = transactionButtons.stream()
                .filter(b -> b.getAUTHTRANSACTIONGUID() != null)
                .collect(Collectors.groupingBy(AsAuthTransactionButton::getAUTHTRANSACTIONGUID));

        Map<String, List<AsAuthPlanInquiry>> planInquiriesMap = planInquiries.stream()
                .filter(pi -> pi.getCOMPANYGUID() != null && pi.getPLANGUID() != null)
                .collect(Collectors.groupingBy(pi -> pi.getCOMPANYGUID() + "|" + pi.getPLANGUID()));

        Map<String, List<AsAuthProduct>> productsMap = products.stream()
                .filter(pr -> pr.getCOMPANYGUID() != null)
                .collect(Collectors.groupingBy(AsAuthProduct::getCOMPANYGUID));

        Map<String, List<AsAuthProductPage>> productPagesMap = productPages.stream()
                .filter(pp -> pp.getCOMPANYGUID() != null && pp.getPRODUCTGUID() != null)
                .collect(Collectors.groupingBy(pp -> pp.getCOMPANYGUID() + "|" + pp.getPRODUCTGUID()));

        Map<String, List<AsAuthProductPageButton>> productPageButtonsMap = productPageButtons.stream()
                .filter(pb -> pb.getCOMPANYGUID() != null && pb.getPRODUCTGUID() != null)
                .collect(Collectors.groupingBy(pb -> pb.getCOMPANYGUID() + "|" + pb.getPRODUCTGUID()));

        Map<String, List<AsAuthProductTransaction>> productTransactionsMap = productTransactions.stream()
                .filter(t -> t.getCOMPANYGUID() != null && t.getPRODUCTGUID() != null)
                .collect(Collectors.groupingBy(t -> t.getCOMPANYGUID() + "|" + t.getPRODUCTGUID()));

        Map<String, List<AsAuthProductTransactionButton>> productTransactionButtonsMap = productTransactionButtons.stream()
                .filter(b -> b.getAUTHPRODUCTTRANSACTIONGUID() != null)
                .collect(Collectors.groupingBy(AsAuthProductTransactionButton::getAUTHPRODUCTTRANSACTIONGUID));

        // ── Assemble nested structure per company ───────────────────────
        List<CompanyAuthDto> companyDtos = new ArrayList<>();
        for (AsAuthCompany ac : companies) {
            String cGuid = ac.getCOMPANYGUID();
            CompanyAuthDto cDto = new CompanyAuthDto(cGuid);

            // Company Pages → Buttons
            Map<String, PageDto> pageMap = new LinkedHashMap<>();
            List<AsAuthCompanyPage> cPages = companyPagesMap.getOrDefault(cGuid, Collections.emptyList());
            for (AsAuthCompanyPage p : cPages) {
                pageMap.computeIfAbsent(p.getAUTHCOMPANYPAGEGUID(), k -> new PageDto(p.getAUTHPAGEGUID()));
            }
            List<AsAuthCompanyPageButton> cPageButtons = companyPageButtonsMap.getOrDefault(cGuid, Collections.emptyList());
            for (AsAuthCompanyPageButton b : cPageButtons) {
                PageDto page = pageMap.get(b.getAUTHCOMPANYPAGEGUID());
                if (page != null) {
                    page.getButtons().add(new ButtonDto(b.getAUTHBUTTONGUID()));
                }
            }
            cDto.setCompanyPages(new ArrayList<>(pageMap.values()));

            // Company Inquiries
            List<AsAuthCompanyInquiry> cInquiries = companyInquiriesMap.getOrDefault(cGuid, Collections.emptyList());
            cDto.setCompanyInquiries(
                cInquiries.stream()
                    .map(i -> new InquiryDto(i.getINQUIRYSCREENGUID()))
                    .collect(Collectors.toList())
            );

            // Company Web Services
            List<AsAuthCompanyWebService> cWebServices = companyWebServicesMap.getOrDefault(cGuid, Collections.emptyList());
            cDto.setCompanyWebServices(
                cWebServices.stream()
                    .map(w -> new WebServiceDto(w.getAUTHWEBSERVICEGUID()))
                    .collect(Collectors.toList())
            );

            // ── Plans ───────────────────────────────────────────────────
            List<PlanAuthDto> planDtos = new ArrayList<>();
            List<AsAuthPlan> cPlans = plansMap.getOrDefault(cGuid, Collections.emptyList());
            for (AsAuthPlan plan : cPlans) {
                String pGuid = plan.getPLANGUID();
                PlanAuthDto planDto = new PlanAuthDto(pGuid);
                String planKey = cGuid + "|" + pGuid;

                // Plan Pages → Buttons
                Map<String, PageDto> planPageMap = new LinkedHashMap<>();
                List<AsAuthPlanPage> pPages = planPagesMap.getOrDefault(planKey, Collections.emptyList());
                for (AsAuthPlanPage pp : pPages) {
                    planPageMap.computeIfAbsent(pp.getAUTHPLANPAGEGUID(), k -> new PageDto(pp.getAUTHPAGEGUID()));
                }
                List<AsAuthPlanPageButton> pPageButtons = planPageButtonsMap.getOrDefault(planKey, Collections.emptyList());
                for (AsAuthPlanPageButton pb : pPageButtons) {
                    PageDto page = planPageMap.get(pb.getAUTHPLANPAGEGUID());
                    if (page != null) {
                        page.getButtons().add(new ButtonDto(pb.getAUTHBUTTONGUID()));
                    }
                }
                planDto.setPlanPages(new ArrayList<>(planPageMap.values()));

                // Plan Transactions
                List<AsAuthTransaction> pTransactions = transactionsMap.getOrDefault(planKey, Collections.emptyList());
                List<TransactionDto> pTxnDtos = new ArrayList<>();
                for (AsAuthTransaction t : pTransactions) {
                    TransactionDto tDto = new TransactionDto(t.getTRANSACTIONGUID());
                    List<AsAuthTransactionButton> buttons = transactionButtonsMap.getOrDefault(t.getAUTHTRANSACTIONGUID(), Collections.emptyList());
                    tDto.setButtons(buttons.stream()
                        .map(b -> new ButtonDto(b.getAUTHBUTTONGUID()))
                        .collect(Collectors.toList())
                    );
                    pTxnDtos.add(tDto);
                }
                planDto.setPlanTransactions(pTxnDtos);

                // Plan Inquiries
                List<AsAuthPlanInquiry> pInquiries = planInquiriesMap.getOrDefault(planKey, Collections.emptyList());
                planDto.setPlanInquiries(
                    pInquiries.stream()
                        .map(pi -> new InquiryDto(pi.getINQUIRYSCREENGUID()))
                        .collect(Collectors.toList())
                );

                planDtos.add(planDto);
            }
            cDto.setPlans(planDtos);

            // ── Products ────────────────────────────────────────────────
            List<ProductAuthDto> productDtos = new ArrayList<>();
            List<AsAuthProduct> cProducts = productsMap.getOrDefault(cGuid, Collections.emptyList());
            for (AsAuthProduct prod : cProducts) {
                String prGuid = prod.getPRODUCTGUID();
                ProductAuthDto prodDto = new ProductAuthDto(prGuid);
                String productKey = cGuid + "|" + prGuid;

                // Product Pages → Buttons
                Map<String, PageDto> prodPageMap = new LinkedHashMap<>();
                List<AsAuthProductPage> prPages = productPagesMap.getOrDefault(productKey, Collections.emptyList());
                for (AsAuthProductPage pp : prPages) {
                    prodPageMap.computeIfAbsent(pp.getAUTHPRODUCTPAGEGUID(), k -> new PageDto(pp.getAUTHPAGEGUID()));
                }
                List<AsAuthProductPageButton> prPageButtons = productPageButtonsMap.getOrDefault(productKey, Collections.emptyList());
                for (AsAuthProductPageButton pb : prPageButtons) {
                    PageDto page = prodPageMap.get(pb.getAUTHPRODUCTPAGEGUID());
                    if (page != null) {
                        page.getButtons().add(new ButtonDto(pb.getAUTHBUTTONGUID()));
                    }
                }
                prodDto.setProductPages(new ArrayList<>(prodPageMap.values()));

                // Product Transactions
                List<AsAuthProductTransaction> prTransactions = productTransactionsMap.getOrDefault(productKey, Collections.emptyList());
                List<TransactionDto> prTxnDtos = new ArrayList<>();
                for (AsAuthProductTransaction t : prTransactions) {
                    TransactionDto tDto = new TransactionDto(t.getTRANSACTIONGUID());
                    List<AsAuthProductTransactionButton> buttons = productTransactionButtonsMap.getOrDefault(t.getAUTHPRODUCTTRANSACTIONGUID(), Collections.emptyList());
                    tDto.setButtons(buttons.stream()
                        .map(b -> new ButtonDto(b.getAUTHBUTTONGUID()))
                        .collect(Collectors.toList())
                    );
                    prTxnDtos.add(tDto);
                }
                prodDto.setProductTransactions(prTxnDtos);

                productDtos.add(prodDto);
            }
            cDto.setProducts(productDtos);

            companyDtos.add(cDto);
        }

        dto.setCompanies(companyDtos);
        return dto;
    }

    // ===================================================================
    // 3.  POST /api/security-group/generate-scripts  —  delta generator
    // ===================================================================
    @Transactional(value = "secondaryDevTransactionManager", readOnly = true)
    public GenerateScriptsResponseDto generateScripts(SecurityGroupDto incoming) {

        String guid = incoming.getSecurityGroupGuid();
        String groupName = incoming.getGroupName();
        boolean isNewGroup = (guid == null || guid.trim().isEmpty());

        // Step A — Read existing state
        SecurityGroupDto existing;
        if (isNewGroup) {
            // For a new group we need to generate a GUID placeholder
            guid = java.util.UUID.randomUUID().toString().toUpperCase();
            incoming.setSecurityGroupGuid(guid);
            existing = new SecurityGroupDto(guid, groupName);
        } else {
            existing = getSecurityConfiguration(guid);
        }

        // Step B & C — Compare and generate SQL
        List<String> scripts = new ArrayList<>();

        // ── Security Group INSERT if new ────────────────────────────────
        if (isNewGroup) {
            scripts.add(String.format(
                "INSERT INTO ASSECURITYGROUP (SECURITYGROUPGUID, GROUPNAME) VALUES ('%s', '%s');",
                esc(guid), esc(groupName)));
        }

        // Flatten both trees into sets of composite keys, then diff
        Set<String> existingCompanyKeys = flattenCompanyKeys(existing);
        Set<String> incomingCompanyKeys = flattenCompanyKeys(incoming);

        Set<String> existingCompanyPageKeys = flattenCompanyPageKeys(existing);
        Set<String> incomingCompanyPageKeys = flattenCompanyPageKeys(incoming);

        Set<String> existingCompanyPageButtonKeys = flattenCompanyPageButtonKeys(existing);
        Set<String> incomingCompanyPageButtonKeys = flattenCompanyPageButtonKeys(incoming);

        Set<String> existingCompanyInquiryKeys = flattenCompanyInquiryKeys(existing);
        Set<String> incomingCompanyInquiryKeys = flattenCompanyInquiryKeys(incoming);

        Set<String> existingCompanyWebServiceKeys = flattenCompanyWebServiceKeys(existing);
        Set<String> incomingCompanyWebServiceKeys = flattenCompanyWebServiceKeys(incoming);

        Set<String> existingPlanKeys = flattenPlanKeys(existing);
        Set<String> incomingPlanKeys = flattenPlanKeys(incoming);

        Set<String> existingPlanPageKeys = flattenPlanPageKeys(existing);
        Set<String> incomingPlanPageKeys = flattenPlanPageKeys(incoming);

        Set<String> existingPlanPageButtonKeys = flattenPlanPageButtonKeys(existing);
        Set<String> incomingPlanPageButtonKeys = flattenPlanPageButtonKeys(incoming);

        Set<String> existingTransactionKeys = flattenTransactionKeys(existing);
        Set<String> incomingTransactionKeys = flattenTransactionKeys(incoming);

        Set<String> existingPlanInquiryKeys = flattenPlanInquiryKeys(existing);
        Set<String> incomingPlanInquiryKeys = flattenPlanInquiryKeys(incoming);

        Set<String> existingProductKeys = flattenProductKeys(existing);
        Set<String> incomingProductKeys = flattenProductKeys(incoming);

        Set<String> existingProductPageKeys = flattenProductPageKeys(existing);
        Set<String> incomingProductPageKeys = flattenProductPageKeys(incoming);

        Set<String> existingProductPageButtonKeys = flattenProductPageButtonKeys(existing);
        Set<String> incomingProductPageButtonKeys = flattenProductPageButtonKeys(incoming);

        Set<String> existingProductTransactionKeys = flattenProductTransactionKeys(existing);
        Set<String> incomingProductTransactionKeys = flattenProductTransactionKeys(incoming);

        Set<String> existingProductTransactionButtonKeys = flattenProductTransactionButtonKeys(existing);
        Set<String> incomingProductTransactionButtonKeys = flattenProductTransactionButtonKeys(incoming);

        Set<String> existingTransactionButtonKeys = flattenTransactionButtonKeys(existing);
        Set<String> incomingTransactionButtonKeys = flattenTransactionButtonKeys(incoming);

        // ── DELETES (bottom-up to respect FK constraints) ───────────────
        scripts.add("-- ====== DELETE SCRIPTS (removing revoked permissions) ======");

        // Product transaction buttons
        diff(existingProductTransactionButtonKeys, incomingProductTransactionButtonKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHPRODUCTTRANSACTIONBUTTON WHERE AUTHBUTTONGUID = '%s' AND AUTHPRODUCTTRANSACTIONGUID IN (SELECT AUTHPRODUCTTRANSACTIONGUID FROM ASAUTHPRODUCTTRANSACTION WHERE TRANSACTIONGUID = '%s' AND AUTHPRODUCTGUID IN (SELECT AUTHPRODUCTGUID FROM ASAUTHPRODUCT WHERE PRODUCTGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s')));",
                esc(p[4]), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Plan transaction buttons
        diff(existingTransactionButtonKeys, incomingTransactionButtonKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHTRANSACTIONBUTTON WHERE AUTHBUTTONGUID = '%s' AND AUTHTRANSACTIONGUID IN (SELECT AUTHTRANSACTIONGUID FROM ASAUTHTRANSACTION WHERE TRANSACTIONGUID = '%s' AND AUTHPLANGUID IN (SELECT AUTHPLANGUID FROM ASAUTHPLAN WHERE PLANGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s')));",
                esc(p[4]), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        
        // Product page buttons
        diff(existingProductPageButtonKeys, incomingProductPageButtonKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHPRODUCTPAGEBUTTON WHERE AUTHBUTTONGUID = '%s' AND AUTHPRODUCTPAGEGUID IN (SELECT AUTHPRODUCTPAGEGUID FROM ASAUTHPRODUCTPAGE WHERE AUTHPAGEGUID = '%s' AND AUTHPRODUCTGUID IN (SELECT AUTHPRODUCTGUID FROM ASAUTHPRODUCT WHERE PRODUCTGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s')));",
                esc(p[4]), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Product pages
        diff(existingProductPageKeys, incomingProductPageKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHPRODUCTPAGE WHERE AUTHPAGEGUID = '%s' AND AUTHPRODUCTGUID IN (SELECT AUTHPRODUCTGUID FROM ASAUTHPRODUCT WHERE PRODUCTGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s'));",
                esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Product transactions
        diff(existingProductTransactionKeys, incomingProductTransactionKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHPRODUCTTRANSACTION WHERE TRANSACTIONGUID = '%s' AND AUTHPRODUCTGUID IN (SELECT AUTHPRODUCTGUID FROM ASAUTHPRODUCT WHERE PRODUCTGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s'));",
                esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Products
        diff(existingProductKeys, incomingProductKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHPRODUCT WHERE PRODUCTGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s');",
                esc(p[2]), esc(p[1]), esc(p[0])));
        });

        // Plan page buttons
        diff(existingPlanPageButtonKeys, incomingPlanPageButtonKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHPLANPAGEBUTTON WHERE AUTHBUTTONGUID = '%s' AND AUTHPLANPAGEGUID IN (SELECT AUTHPLANPAGEGUID FROM ASAUTHPLANPAGE WHERE AUTHPAGEGUID = '%s' AND AUTHPLANGUID IN (SELECT AUTHPLANGUID FROM ASAUTHPLAN WHERE PLANGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s')));",
                esc(p[4]), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Plan pages
        diff(existingPlanPageKeys, incomingPlanPageKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHPLANPAGE WHERE AUTHPAGEGUID = '%s' AND AUTHPLANGUID IN (SELECT AUTHPLANGUID FROM ASAUTHPLAN WHERE PLANGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s'));",
                esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Plan transactions
        diff(existingTransactionKeys, incomingTransactionKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHTRANSACTION WHERE TRANSACTIONGUID = '%s' AND AUTHPLANGUID IN (SELECT AUTHPLANGUID FROM ASAUTHPLAN WHERE PLANGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s'));",
                esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Plan inquiries
        diff(existingPlanInquiryKeys, incomingPlanInquiryKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHPLANINQUIRY WHERE INQUIRYSCREENNAMEGUID = '%s' AND AUTHPLANGUID IN (SELECT AUTHPLANGUID FROM ASAUTHPLAN WHERE PLANGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s'));",
                esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Plans
        diff(existingPlanKeys, incomingPlanKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHPLAN WHERE PLANGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s');",
                esc(p[2]), esc(p[1]), esc(p[0])));
        });

        // Company page buttons
        diff(existingCompanyPageButtonKeys, incomingCompanyPageButtonKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHCOMPANYPAGEBUTTON WHERE AUTHBUTTONGUID = '%s' AND AUTHCOMPANYPAGEGUID IN (SELECT AUTHCOMPANYPAGEGUID FROM ASAUTHCOMPANYPAGE WHERE AUTHPAGEGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s'));",
                esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Company pages
        diff(existingCompanyPageKeys, incomingCompanyPageKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHCOMPANYPAGE WHERE AUTHPAGEGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s');",
                esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Company inquiries
        diff(existingCompanyInquiryKeys, incomingCompanyInquiryKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHCOMPANYINQUIRY WHERE INQUIRYSCREENNAMEGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s');",
                esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Company web services
        diff(existingCompanyWebServiceKeys, incomingCompanyWebServiceKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHCOMPANYWEBSERVICE WHERE AUTHWEBSERVICEGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s');",
                esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Companies
        diff(existingCompanyKeys, incomingCompanyKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "DELETE FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s';",
                esc(p[1]), esc(p[0])));
        });

        // Orphaned group cleanup
        if (!isNewGroup && incomingCompanyKeys.isEmpty() && !existingCompanyKeys.isEmpty()) {
            scripts.add(String.format(
                "DELETE FROM ASSECURITYGROUP WHERE SECURITYGROUPGUID = '%s';", esc(guid)));
        }

        // ── INSERTS (top-down to satisfy FK constraints) ───────────────
        scripts.add("-- ====== INSERT SCRIPTS (granting new permissions) ======");

        // Companies
        diff(incomingCompanyKeys, existingCompanyKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHCOMPANY (AUTHCOMPANYGUID, COMPANYGUID, SECURITYGROUPGUID) VALUES ('%s', '%s', '%s');",
                esc(newGuid), esc(p[1]), esc(p[0])));
        });
        // Company pages
        diff(incomingCompanyPageKeys, existingCompanyPageKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHCOMPANYPAGE (AUTHCOMPANYPAGEGUID, AUTHCOMPANYGUID, AUTHPAGEGUID) SELECT '%s', AUTHCOMPANYGUID, '%s' FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s';",
                esc(newGuid), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Company page buttons
        diff(incomingCompanyPageButtonKeys, existingCompanyPageButtonKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHCOMPANYPAGEBUTTON (AUTHCOMPANYPAGEBUTTONGUID, AUTHCOMPANYPAGEGUID, AUTHBUTTONGUID) SELECT '%s', AUTHCOMPANYPAGEGUID, '%s' FROM ASAUTHCOMPANYPAGE WHERE AUTHPAGEGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s');",
                esc(newGuid), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Company inquiries
        diff(incomingCompanyInquiryKeys, existingCompanyInquiryKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHCOMPANYINQUIRY (AUTHCOMPANYINQUIRYGUID, AUTHCOMPANYGUID, INQUIRYSCREENNAMEGUID) SELECT '%s', AUTHCOMPANYGUID, '%s' FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s';",
                esc(newGuid), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Company web services
        diff(incomingCompanyWebServiceKeys, existingCompanyWebServiceKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "INSERT INTO ASAUTHCOMPANYWEBSERVICE (AUTHWEBSERVICEGUID, AUTHCOMPANYGUID) SELECT '%s', AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s';",
                esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Plans
        diff(incomingPlanKeys, existingPlanKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHPLAN (AUTHPLANGUID, COMPANYGUID, PLANGUID) SELECT '%s', AUTHCOMPANYGUID, '%s' FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s';",
                esc(newGuid), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Plan pages
        diff(incomingPlanPageKeys, existingPlanPageKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHPLANPAGE (AUTHPLANPAGEGUID, AUTHPLANGUID, AUTHPAGEGUID) SELECT '%s', AUTHPLANGUID, '%s' FROM ASAUTHPLAN WHERE PLANGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s');",
                esc(newGuid), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Plan page buttons
        diff(incomingPlanPageButtonKeys, existingPlanPageButtonKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHPLANPAGEBUTTON (AUTHPLANPAGEBUTTONGUID, AUTHPLANPAGEGUID, AUTHBUTTONGUID) SELECT '%s', AUTHPLANPAGEGUID, '%s' FROM ASAUTHPLANPAGE WHERE AUTHPAGEGUID = '%s' AND AUTHPLANGUID IN (SELECT AUTHPLANGUID FROM ASAUTHPLAN WHERE PLANGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s'));",
                esc(newGuid), esc(p[4]), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Plan transactions
        diff(incomingTransactionKeys, existingTransactionKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHTRANSACTION (AUTHTRANSACTIONGUID, AUTHPLANGUID, TRANSACTIONGUID) SELECT '%s', AUTHPLANGUID, '%s' FROM ASAUTHPLAN WHERE PLANGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s');",
                esc(newGuid), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Plan inquiries
        diff(incomingPlanInquiryKeys, existingPlanInquiryKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHPLANINQUIRY (AUTHPLANINQUIRYGUID, AUTHPLANGUID, INQUIRYSCREENNAMEGUID) SELECT '%s', AUTHPLANGUID, '%s' FROM ASAUTHPLAN WHERE PLANGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s');",
                esc(newGuid), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Products
        diff(incomingProductKeys, existingProductKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHPRODUCT (AUTHPRODUCTGUID, AUTHCOMPANYGUID, PRODUCTGUID) SELECT '%s', AUTHCOMPANYGUID, '%s' FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s';",
                esc(newGuid), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Product pages
        diff(incomingProductPageKeys, existingProductPageKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHPRODUCTPAGE (AUTHPRODUCTPAGEGUID, AUTHPRODUCTGUID, AUTHPAGEGUID) SELECT '%s', AUTHPRODUCTGUID, '%s' FROM ASAUTHPRODUCT WHERE PRODUCTGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s');",
                esc(newGuid), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Product page buttons
        diff(incomingProductPageButtonKeys, existingProductPageButtonKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHPRODUCTPAGEBUTTON (AUTHPRODUCTPAGEBUTTONGUID, AUTHPRODUCTPAGEGUID, AUTHBUTTONGUID) SELECT '%s', AUTHPRODUCTPAGEGUID, '%s' FROM ASAUTHPRODUCTPAGE WHERE AUTHPAGEGUID = '%s' AND AUTHPRODUCTGUID IN (SELECT AUTHPRODUCTGUID FROM ASAUTHPRODUCT WHERE PRODUCTGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s'));",
                esc(newGuid), esc(p[4]), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });
        // Product transactions
        diff(incomingProductTransactionKeys, existingProductTransactionKeys).forEach(k -> {
            String[] p = k.split("\\|");
            String newGuid = java.util.UUID.randomUUID().toString().toUpperCase();
            scripts.add(String.format(
                "INSERT INTO ASAUTHPRODUCTTRANSACTION (AUTHPRODUCTTRANSACTIONGUID, AUTHPRODUCTGUID, TRANSACTIONGUID) SELECT '%s', AUTHPRODUCTGUID, '%s' FROM ASAUTHPRODUCT WHERE PRODUCTGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s');",
                esc(newGuid), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });

        // Product transaction buttons
        diff(incomingProductTransactionButtonKeys, existingProductTransactionButtonKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "INSERT INTO ASAUTHPRODUCTTRANSACTIONBUTTON (AUTHPRODUCTTRANSACTIONGUID, AUTHBUTTONGUID) SELECT AUTHPRODUCTTRANSACTIONGUID, '%s' FROM ASAUTHPRODUCTTRANSACTION WHERE TRANSACTIONGUID = '%s' AND AUTHPRODUCTGUID IN (SELECT AUTHPRODUCTGUID FROM ASAUTHPRODUCT WHERE PRODUCTGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s'));",
                esc(p[4]), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });

        // Plan transaction buttons
        diff(incomingTransactionButtonKeys, existingTransactionButtonKeys).forEach(k -> {
            String[] p = k.split("\\|");
            scripts.add(String.format(
                "INSERT INTO ASAUTHTRANSACTIONBUTTON (AUTHTRANSACTIONGUID, AUTHBUTTONGUID) SELECT AUTHTRANSACTIONGUID, '%s' FROM ASAUTHTRANSACTION WHERE TRANSACTIONGUID = '%s' AND AUTHPLANGUID IN (SELECT AUTHPLANGUID FROM ASAUTHPLAN WHERE PLANGUID = '%s' AND AUTHCOMPANYGUID IN (SELECT AUTHCOMPANYGUID FROM ASAUTHCOMPANY WHERE COMPANYGUID = '%s' AND SECURITYGROUPGUID = '%s'));",
                esc(p[4]), esc(p[3]), esc(p[2]), esc(p[1]), esc(p[0])));
        });

        return new GenerateScriptsResponseDto(guid, groupName, scripts);
    }

    // ===================================================================
    //  Flattening helpers — convert nested DTO trees to Sets of pipe-
    //  delimited composite keys for O(n) set-difference comparisons
    // ===================================================================

    private Set<String> flattenCompanyKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        return dto.getCompanies().stream()
                .map(c -> g + "|" + c.getCompanyGuid())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private Set<String> flattenCompanyPageKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (PageDto p : c.getCompanyPages()) {
                keys.add(g + "|" + c.getCompanyGuid() + "|" + p.getPageGuid());
            }
        }
        return keys;
    }

    private Set<String> flattenCompanyPageButtonKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (PageDto p : c.getCompanyPages()) {
                for (ButtonDto b : p.getButtons()) {
                    keys.add(g + "|" + c.getCompanyGuid() + "|" + p.getPageGuid() + "|" + b.getButtonGuid());
                }
            }
        }
        return keys;
    }

    private Set<String> flattenCompanyInquiryKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (InquiryDto i : c.getCompanyInquiries()) {
                keys.add(g + "|" + c.getCompanyGuid() + "|" + i.getInquiryScreenNameGuid());
            }
        }
        return keys;
    }

    private Set<String> flattenCompanyWebServiceKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (WebServiceDto w : c.getCompanyWebServices()) {
                keys.add(g + "|" + c.getCompanyGuid() + "|" + w.getWebServiceGuid());
            }
        }
        return keys;
    }

    private Set<String> flattenPlanKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (PlanAuthDto p : c.getPlans()) {
                keys.add(g + "|" + c.getCompanyGuid() + "|" + p.getPlanGuid());
            }
        }
        return keys;
    }

    private Set<String> flattenPlanPageKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (PlanAuthDto plan : c.getPlans()) {
                for (PageDto pg : plan.getPlanPages()) {
                    keys.add(g + "|" + c.getCompanyGuid() + "|" + plan.getPlanGuid() + "|" + pg.getPageGuid());
                }
            }
        }
        return keys;
    }

    private Set<String> flattenPlanPageButtonKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (PlanAuthDto plan : c.getPlans()) {
                for (PageDto pg : plan.getPlanPages()) {
                    for (ButtonDto b : pg.getButtons()) {
                        keys.add(g + "|" + c.getCompanyGuid() + "|" + plan.getPlanGuid() + "|" + pg.getPageGuid() + "|" + b.getButtonGuid());
                    }
                }
            }
        }
        return keys;
    }

    private Set<String> flattenTransactionKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (PlanAuthDto plan : c.getPlans()) {
                for (TransactionDto t : plan.getPlanTransactions()) {
                    keys.add(g + "|" + c.getCompanyGuid() + "|" + plan.getPlanGuid() + "|" + t.getTransactionGuid());
                }
            }
        }
        return keys;
    }

    private Set<String> flattenPlanInquiryKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (PlanAuthDto plan : c.getPlans()) {
                for (InquiryDto i : plan.getPlanInquiries()) {
                    keys.add(g + "|" + c.getCompanyGuid() + "|" + plan.getPlanGuid() + "|" + i.getInquiryScreenNameGuid());
                }
            }
        }
        return keys;
    }

    private Set<String> flattenProductKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (ProductAuthDto pr : c.getProducts()) {
                keys.add(g + "|" + c.getCompanyGuid() + "|" + pr.getProductGuid());
            }
        }
        return keys;
    }

    private Set<String> flattenProductPageKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (ProductAuthDto pr : c.getProducts()) {
                for (PageDto pg : pr.getProductPages()) {
                    keys.add(g + "|" + c.getCompanyGuid() + "|" + pr.getProductGuid() + "|" + pg.getPageGuid());
                }
            }
        }
        return keys;
    }

    private Set<String> flattenProductPageButtonKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (ProductAuthDto pr : c.getProducts()) {
                for (PageDto pg : pr.getProductPages()) {
                    for (ButtonDto b : pg.getButtons()) {
                        keys.add(g + "|" + c.getCompanyGuid() + "|" + pr.getProductGuid() + "|" + pg.getPageGuid() + "|" + b.getButtonGuid());
                    }
                }
            }
        }
        return keys;
    }

    private Set<String> flattenProductTransactionKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (ProductAuthDto pr : c.getProducts()) {
                for (TransactionDto t : pr.getProductTransactions()) {
                    keys.add(g + "|" + c.getCompanyGuid() + "|" + pr.getProductGuid() + "|" + t.getTransactionGuid());
                }
            }
        }
        return keys;
    }

    private Set<String> flattenProductTransactionButtonKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (ProductAuthDto pr : c.getProducts()) {
                for (TransactionDto t : pr.getProductTransactions()) {
                    for (ButtonDto b : t.getButtons()) {
                        keys.add(g + "|" + c.getCompanyGuid() + "|" + pr.getProductGuid() + "|" + t.getTransactionGuid() + "|" + b.getButtonGuid());
                    }
                }
            }
        }
        return keys;
    }

    private Set<String> flattenTransactionButtonKeys(SecurityGroupDto dto) {
        String g = dto.getSecurityGroupGuid();
        Set<String> keys = new LinkedHashSet<>();
        for (CompanyAuthDto c : dto.getCompanies()) {
            for (PlanAuthDto plan : c.getPlans()) {
                for (TransactionDto t : plan.getPlanTransactions()) {
                    for (ButtonDto b : t.getButtons()) {
                        keys.add(g + "|" + c.getCompanyGuid() + "|" + plan.getPlanGuid() + "|" + t.getTransactionGuid() + "|" + b.getButtonGuid());
                    }
                }
            }
        }
        return keys;
    }

    // ── Utility ─────────────────────────────────────────────────────────

    /** Set difference: elements in {@code a} that are NOT in {@code b}. */
    private List<String> diff(Set<String> a, Set<String> b) {
        return a.stream().filter(k -> !b.contains(k)).collect(Collectors.toList());
    }

    /** Escape single quotes for safe SQL string literals. */
    private String esc(String value) {
        if (value == null) return "";
        return value.replace("'", "''");
    }
}
