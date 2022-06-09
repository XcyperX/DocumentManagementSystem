package com.DocumentManagementSystem.service.impl;

import com.DocumentManagementSystem.constants.FileErrors;
import com.DocumentManagementSystem.exception.FileSaveException;
import com.DocumentManagementSystem.model.FileModel;
import com.DocumentManagementSystem.repository.FileModelRepository;
import com.DocumentManagementSystem.service.FileModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileModelServiceImpl implements FileModelService {
    private final FileModelRepository fileModelRepository;

    @Override
    public FileModel saveFile(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (filename.contains("...")) {
                throw new FileSaveException(FileErrors.INVALID_FILE + filename);
            }
            FileModel model = new FileModel(filename, file.getContentType(), file.getBytes());
            return fileModelRepository.save(model);
        } catch (Exception e) {
            throw new FileSaveException(FileErrors.FILE_NOT_STORED, e);
        }
    }

    @Override
    public FileModel getFile(Long id) {
        return fileModelRepository.findById(id).orElseThrow(() -> new RuntimeException(FileErrors.FILE_NOT_FOUND));
    }

    @Override
    public List<FileModel> getAllFiles() {
        return fileModelRepository.findAll();
    }
}
