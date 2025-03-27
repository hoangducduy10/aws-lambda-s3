package com.example.awslambdas3.services;

import java.io.IOException;

public interface IS3Service {
    String readFileFromS3(String bucketName, String fileName) throws IOException;
}
