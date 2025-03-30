package com.example.awslambdas3.services;

import java.io.IOException;

public interface IS3Service {
    String readFile(String bucketName, String fileName) throws IOException;
}
