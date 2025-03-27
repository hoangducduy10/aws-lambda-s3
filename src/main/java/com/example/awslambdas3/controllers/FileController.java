package com.example.awslambdas3.controllers;

import com.example.awslambdas3.services.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/files")
public class FileController {

    private final IFileService fileService;

    @PostMapping("/fetch")
    public String fetchAndSaveFile(@RequestParam String bucketName,
                                   @RequestParam String fileName){
        try {
            fileService.fetchAndSaveFile(bucketName, fileName);
            return "File fetched and saved successfully!";
        }catch (IOException e){
            return "Failed to fetch and save file: " + e.getMessage();
        }
    }

}
