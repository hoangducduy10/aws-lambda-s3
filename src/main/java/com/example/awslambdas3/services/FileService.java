package com.example.awslambdas3.services;

import com.example.awslambdas3.models.FileEntity;
import com.example.awslambdas3.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService implements IFileService{

    private final IS3Service s3Service;
    private final FileRepository fileRepository;

    @Override
    public void fetchAndSaveFile(String bucketName, String fileName) throws IOException {
        String content = s3Service.readFile(bucketName, fileName);

        FileEntity fileEntity = FileEntity.builder()
                .fileName(fileName)
                .content(content)
                .build();

        fileRepository.save(fileEntity);
    }
}
