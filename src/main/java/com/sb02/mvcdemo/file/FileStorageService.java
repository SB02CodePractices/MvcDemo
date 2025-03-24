package com.sb02.mvcdemo.file;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadPath;

    public void saveFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        if (!isAllowedExtension(originalName)) {
            throw new RuntimeException("File extension not allowed");
        }
        String uuidName = UUID.randomUUID().toString() + "_" + originalName;

        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File dest = new File(uploadPath, uuidName);
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isAllowedExtension(String originalFilename) {
        String[] allowedExtensions = {".jpg", ".jpeg", ".png", ".gif" , ".webp"};
        if (originalFilename == null) {
            return false;
        }

        String lowercaseName = originalFilename.toLowerCase();
        for (String extension : allowedExtensions) {
            if (originalFilename.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
