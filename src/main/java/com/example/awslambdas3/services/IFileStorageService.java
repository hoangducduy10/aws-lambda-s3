package com.example.awslambdas3.services;

import java.io.IOException;

public interface IFileStorageService {
    void fetchAndSaveFile(String bucketName, String fileName) throws IOException;
}
