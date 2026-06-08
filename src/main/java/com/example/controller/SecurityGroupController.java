package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.security.CreateGroupRequestDto;
import com.example.dto.security.CreateGroupResponseDto;
import com.example.dto.security.GenerateScriptsResponseDto;
import com.example.dto.security.SecurityGroupDto;
import com.example.dto.security.CompanyDto;
import com.example.dto.security.PageInfoDto;
import com.example.dto.security.ButtonInfoDto;
import com.example.dto.security.WebServiceInfoDto;
import com.example.dto.security.InquiryScreenDto;
import com.example.dto.security.ProductDto;
import com.example.dto.security.TransactionInfoDto;
import com.example.dto.security.PlanDto;
import com.example.dto.security.SecurityGroupRequestDto;
import com.example.service.SecurityGroupService;

/**
 * REST controller for the OIPA Security Tool.
 * All endpoints are read-only against the secondarydev database.
 * The generate-scripts endpoint returns raw SQL — it never executes them.
 */
@RestController
@RequestMapping("/api")
public class SecurityGroupController {

    @Autowired
    private SecurityGroupService securityGroupService;

    /**
     * List all companies (GUID + name only).
     */
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companies = securityGroupService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    /**
     * List all pages (GUID + name only).
     */
    @GetMapping("/pages")
    public ResponseEntity<List<PageInfoDto>> getAllPages() {
        List<PageInfoDto> pages = securityGroupService.getAllPages();
        return ResponseEntity.ok(pages);
    }

    /**
     * List all buttons (GUID + name only).
     */
    @GetMapping("/buttons")
    public ResponseEntity<List<ButtonInfoDto>> getAllButtons() {
        List<ButtonInfoDto> buttons = securityGroupService.getAllButtons();
        return ResponseEntity.ok(buttons);
    }

    /**
     * List all webservices (GUID + name only).
     */
    @GetMapping("/webservices")
    public ResponseEntity<List<WebServiceInfoDto>> getAllWebServices() {
        List<WebServiceInfoDto> webServices = securityGroupService.getAllWebServices();
        return ResponseEntity.ok(webServices);
    }

    /**
     * List inquiry screens by company or plan.
     */
    @GetMapping("/inquiry-screens")
    public ResponseEntity<List<InquiryScreenDto>> getInquiryScreens(
            @RequestParam(required = false) String companyGuid,
            @RequestParam(required = false) String planGuid) {
        List<InquiryScreenDto> screens = securityGroupService.getInquiryScreens(companyGuid, planGuid);
        return ResponseEntity.ok(screens);
    }

    /**
     * List all products under a company.
     */
    @GetMapping("/companies/{companyGuid}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCompany(
            @PathVariable String companyGuid) {
        List<ProductDto> products = securityGroupService.getProductsByCompany(companyGuid);
        return ResponseEntity.ok(products);
    }

    /**
     * List all plans under a product and company.
     */
    @GetMapping("/companies/{companyGuid}/products/{productGuid}/plans")
    public ResponseEntity<List<PlanDto>> getPlansByCompanyAndProduct(
            @PathVariable String companyGuid,
            @PathVariable String productGuid) {
        List<PlanDto> plans = securityGroupService.getPlansByCompanyAndProduct(companyGuid, productGuid);
        return ResponseEntity.ok(plans);
    }

    /**
     * List all plans under a company.
     */
    @GetMapping("/companies/{companyGuid}/plans")
    public ResponseEntity<List<PlanDto>> getPlansByCompany(
            @PathVariable String companyGuid) {
        List<PlanDto> plans = securityGroupService.getPlansByCompany(companyGuid);
        return ResponseEntity.ok(plans);
    }

    /**
     * List all transactions under a product or plan.
     */
    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionInfoDto>> getTransactions(
            @RequestParam(required = false) String productGuid,
            @RequestParam(required = false) String planGuid) {
        List<TransactionInfoDto> transactions = securityGroupService.getTransactions(productGuid, planGuid);
        return ResponseEntity.ok(transactions);
    }

    /**
     * List all security groups (GUID + name only, no nested children).
     */
    @GetMapping("/security-groups")
    public ResponseEntity<List<SecurityGroupDto>> getAllSecurityGroups() {
        List<SecurityGroupDto> groups = securityGroupService.getAllSecurityGroups();
        return ResponseEntity.ok(groups);
    }

    /**
     * Create a new security group (dry-run).
     * Generates a GUID and returns the INSERT script without executing it.
     */
    @PostMapping("/security-groups/create")
    public ResponseEntity<CreateGroupResponseDto> createSecurityGroup(
            @RequestBody CreateGroupRequestDto request) {
        CreateGroupResponseDto response = securityGroupService.createSecurityGroup(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Fetch the full nested security configuration for a given group.
     * If the group doesn't exist yet, returns an empty shell structure.
     */
    @GetMapping({"/security-group/{securityGroupGuid}", "/security-groups/{securityGroupGuid}"})
    public ResponseEntity<SecurityGroupRequestDto> getSecurityConfiguration(
            @PathVariable String securityGroupGuid) {
        SecurityGroupDto config = securityGroupService.getSecurityConfiguration(securityGroupGuid);
        return ResponseEntity.ok(new SecurityGroupRequestDto(config));
    }

    /**
     * Generate delta SQL scripts by comparing the incoming JSON payload
     * against the current database state.
     * <p>
     * <b>NEVER executes</b> any INSERT/UPDATE/DELETE — only returns
     * the generated SQL as strings for manual review and execution.
     */
    @PostMapping({"/security-group/generate-scripts", "/security-groups/generate-scripts"})
    public ResponseEntity<GenerateScriptsResponseDto> generateScripts(
            @RequestBody SecurityGroupRequestDto request) {
        GenerateScriptsResponseDto response = securityGroupService.generateScripts(request.getSecurityGroup());
        return ResponseEntity.ok(response);
    }
}
