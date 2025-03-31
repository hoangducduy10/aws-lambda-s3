package com.example.awslambdas3.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.awslambdas3.services.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class FetchFileLambdaHandler implements RequestHandler<Map<String, String>, String> {

    private final IFileStorageService fileService;

    @Autowired
    public FetchFileLambdaHandler(IFileStorageService fileService) {
        this.fileService = fileService;
    }

    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        String bucketName = event.get("bucketName");
        String fileName = event.get("fileName");

        if (bucketName == null || fileName == null) {
            return "Missing bucketName or fileName!";
        }

        try {
            fileService.fetchAndSaveFile(bucketName, fileName);
            return "File fetched and saved successfully!";
        } catch (IOException e) {
            return "Failed to fetch and save file: " + e.getMessage();
        }
    }
}
