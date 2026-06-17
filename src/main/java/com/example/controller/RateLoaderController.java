package com.example.controller;

import com.example.dto.SheetMappingDto;
import com.example.entity.AsRateGroup;
import com.example.service.RateLoaderService;
import com.example.service.FileStorageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.dto.ParsedExcel;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/rates")
public class RateLoaderController {

    @Autowired
    private RateLoaderService rateLoaderService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/descriptions")
    public ResponseEntity<List<String>> getDescriptions() {
        return ResponseEntity.ok(rateLoaderService.getDistinctRateDescriptions());
    }

    @GetMapping("/asrate-descriptions")
    public ResponseEntity<List<String>> getAsRateDescriptions() {
        return ResponseEntity.ok(rateLoaderService.getDistinctAsRateDescriptions());
    }

    @GetMapping("/description-details")
    public ResponseEntity<?> getDescriptionDetails(@RequestParam String rateDesc) {
        try {
            AsRateGroup desc = rateLoaderService.getRateDescriptionDetails(rateDesc);
            if (desc == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(desc);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/descriptions")
    public ResponseEntity<?> createRateDescription(@RequestBody AsRateGroup description) {
        try {
            AsRateGroup created = rateLoaderService.createRateDescription(description);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/upload-file")
    public ResponseEntity<?> storeFile(@RequestParam("file") MultipartFile file) {
        try {
            String uploadSessionId = fileStorageService.storeFile(file);
            return ResponseEntity.ok(Collections.singletonMap("uploadSessionId", uploadSessionId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadRates(@RequestParam("mappings") String mappingsJson,
                                         @RequestParam("uploadSessionId") String uploadSessionId) {
        try {
            ParsedExcel parsedExcel = fileStorageService.getParsedExcel(uploadSessionId);
            List<SheetMappingDto> mappings = objectMapper.readValue(mappingsJson, new TypeReference<List<SheetMappingDto>>(){});
            
            // 1. Validate the entire file synchronously
            List<com.example.entity.AsRate> ratesToSave = rateLoaderService.validateAndExtractRates(parsedExcel, mappings, uploadSessionId);
            
            // 2. Execute parallelized multi-threaded DB bulk insert synchronously
            rateLoaderService.executeBatchInsert(ratesToSave);
            
            return ResponseEntity.ok("Rates uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/generate-script")
    public ResponseEntity<?> generateScript(@RequestParam("mappings") String mappingsJson,
                                            @RequestParam("uploadSessionId") String uploadSessionId) {
        try {
            ParsedExcel parsedExcel = fileStorageService.getParsedExcel(uploadSessionId);
            List<SheetMappingDto> mappings = objectMapper.readValue(mappingsJson, new TypeReference<List<SheetMappingDto>>(){});
            String script = rateLoaderService.generateScript(parsedExcel, mappings, uploadSessionId);
            return ResponseEntity.ok(script);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/export-script")
    public ResponseEntity<?> exportScript(@RequestBody List<String> rateDescriptions) {
        try {
            String script = rateLoaderService.exportScript(rateDescriptions);
            return ResponseEntity.ok(script);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/delete-rates")
    public ResponseEntity<?> deleteRates(@RequestBody List<String> rateDescriptions) {
        try {
            rateLoaderService.deleteRates(rateDescriptions);
            return ResponseEntity.ok("Successfully deleted rates for selected descriptions.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
