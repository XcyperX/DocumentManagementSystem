package com.DocumentManagementSystem.repository;

import com.DocumentManagementSystem.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileModelRepository extends JpaRepository<FileModel, Long> {
}
