package com.DocumentManagementSystem.service;

import com.DocumentManagementSystem.model.FileModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileModelService {
    FileModel saveFile(MultipartFile file);
    FileModel getFile(Long id);
    List<FileModel> getAllFiles();
}
