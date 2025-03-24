package com.sb02.mvcdemo.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private final FileStorageService fileStorageService;

    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/single")
    @ResponseBody
    public String uploadSingleFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty";
        }

        fileStorageService.saveFile(file);

        return "File uploaded successfully";
    }

    @PostMapping("/multiple")
    @ResponseBody
    public String uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files) {
        if (files.isEmpty()) {
            return "No files uploaded";
        }

        files.forEach(fileStorageService::saveFile);

        return "Files uploaded successfully";
    }
}
