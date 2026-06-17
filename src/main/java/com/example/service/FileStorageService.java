package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CompletableFuture;
import com.example.dto.ParsedExcel;

@Service
public class FileStorageService {

    private final Map<String, FileInfo> fileStorageMap = new ConcurrentHashMap<>();
    private final Map<String, CompletableFuture<ParsedExcel>> parsedStorageMap = new ConcurrentHashMap<>();
    private final String tempDir = System.getProperty("java.io.tmpdir");

    public String storeFile(MultipartFile file) throws IOException {
        String uploadSessionId = UUID.randomUUID().toString();
        File tempFile = new File(tempDir, uploadSessionId + "_" + file.getOriginalFilename());
        file.transferTo(tempFile);
        
        fileStorageMap.put(uploadSessionId, new FileInfo(tempFile, System.currentTimeMillis()));
        
        CompletableFuture<ParsedExcel> future = CompletableFuture.supplyAsync(() -> new ParsedExcel(tempFile));
        parsedStorageMap.put(uploadSessionId, future);

        return uploadSessionId;
    }

    public ParsedExcel getParsedExcel(String uploadSessionId) throws Exception {
        CompletableFuture<ParsedExcel> future = parsedStorageMap.get(uploadSessionId);
        if (future == null) {
            throw new IllegalArgumentException("File not found or has expired. Please re-upload.");
        }
        return future.get();
    }

    @Scheduled(fixedRate = 60000) // Run every 60 seconds
    public void cleanupOldFiles() {
        long now = System.currentTimeMillis();
        long maxAgeMillis = 15 * 60 * 1000; // 15 minutes

        fileStorageMap.entrySet().removeIf(entry -> {
            FileInfo info = entry.getValue();
            if (now - info.getReceivedTime() > maxAgeMillis) {
                if (info.getFile().exists()) {
                    boolean deleted = info.getFile().delete();
                    if (!deleted) {
                        System.err.println("Failed to delete expired file: " + info.getFile().getAbsolutePath());
                    }
                }
                parsedStorageMap.remove(entry.getKey());
                return true; // remove from map
            }
            return false;
        });
    }

    private static class FileInfo {
        private final File file;
        private final long receivedTime;

        public FileInfo(File file, long receivedTime) {
            this.file = file;
            this.receivedTime = receivedTime;
        }

        public File getFile() {
            return file;
        }

        public long getReceivedTime() {
            return receivedTime;
        }
    }
}
