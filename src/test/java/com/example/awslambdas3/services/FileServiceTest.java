package com.example.awslambdas3.services;

import com.example.awslambdas3.repositories.FileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @Mock
    private IS3Service s3Service;

    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileStorageService fileService;

    @BeforeEach
    void setUp() {
        reset(s3Service, fileRepository);
    }

    private final String bucketName = "my-test-bucket";
    private final String fileName = "test.txt";
    private final String fileContent = "Hello Duy M10!";

    @Test
    void fetchAndSaveFile_givenFileExists_whenFetching_thenFileIsSaved() throws IOException {
        when(s3Service.readFile(bucketName, fileName)).thenReturn(fileContent);

        fileService.fetchAndSaveFile(bucketName, fileName);

        verify(fileRepository, times(1)).save(argThat(fileEntity ->
                fileEntity.getFileName().equals(fileName) &&
                fileEntity.getContent().equals(fileContent)
        ));
    }

    @Test
    void fetchAndSaveFile_givenFileNotExists_whenFetching_thenThrowIOException() throws IOException {
        when(s3Service.readFile(bucketName, fileName)).thenThrow(new IOException("File not found!"));

        IOException thrown = assertThrows(IOException.class, () -> {
            fileService.fetchAndSaveFile(bucketName, fileName);
        });

        assertEquals("File not found!", thrown.getMessage());

        verify(fileRepository, never()).save(any());
    }

}
