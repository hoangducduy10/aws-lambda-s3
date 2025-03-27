package com.example.awslambdas3.services;

import org.springframework.stereotype.Repository;

import java.io.IOException;

public interface IFileService {
    void fetchAndSaveFile(String bucketName, String fileName) throws IOException;
}
