package com.example.awslambdas3.repositories;

import com.example.awslambdas3.models.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
