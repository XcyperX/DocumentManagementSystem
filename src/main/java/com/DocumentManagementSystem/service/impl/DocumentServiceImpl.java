package com.DocumentManagementSystem.service.impl;

import com.DocumentManagementSystem.model.Document;
import com.DocumentManagementSystem.model.FileModel;
import com.DocumentManagementSystem.model.Role;
import com.DocumentManagementSystem.repository.DocumentRepository;
import com.DocumentManagementSystem.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public Document getById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Заявка не найдена");
        }
        return documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));
    }

    @Override
    public Document save(Document document) {
        if (Objects.isNull(document)) {
            throw new RuntimeException("Заявка не заполнена");
        }
        return documentRepository.save(document);
    }

    @Override
    public Document update(Document document) {
        getById(document.getId());
        return documentRepository.save(document);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        documentRepository.deleteById(id);
    }

    @Override
    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    @Override
    public List<Document> getAllNewDocument(Role role) {
        List<Document> findDocuments = documentRepository.findAll();
        List<Document> result = new ArrayList<>();
        if (role.equals(Role.MANAGER)) {
            findDocuments.forEach(document -> {
                if (document.getApprovedManager() == null) {
                    document.getFileModel().setDocument(null);
                    result.add(document);
                }
            });
        }
        if (role.equals(Role.ANALYST)) {
            findDocuments.forEach(document -> {
                if (document.getApprovedManager() != null) {
                    if (document.getApprovedAnalyst() == null && document.getApprovedManager()) {
                        document.getFileModel().setDocument(null);
                        result.add(document);
                    }
                }

            });
        }
        if (role.equals(Role.DIRECTOR)) {
            findDocuments.forEach(document -> {
                if (document.getApprovedAnalyst() != null) {
                    if (document.getApprovedDirector() == null && document.getApprovedAnalyst()) {
                        document.getFileModel().setDocument(null);
                        result.add(document);
                    }
                }

            });
        }
        if (role.equals(Role.ACCOUNTANT)) {
            findDocuments.forEach(document -> {
                if (document.getApprovedDirector() != null) {
                    if (document.getApprovedAccountant() == null && document.getApprovedDirector()) {
                        document.getFileModel().setDocument(null);
                        result.add(document);
                    }
                }

            });
        }
        return result;
    }

    @Override
    public List<Document> getAllDocumentApprovedByRole(Role role) {
        List<Document> findDocuments = documentRepository.findAll();
        List<Document> result = new ArrayList<>();
        if (role.equals(Role.MANAGER)) {
            findDocuments.forEach(document -> {
                if (document.getApprovedManager() != null ) {
                    if (document.getApprovedManager()) {
                        document.getFileModel().setDocument(null);
                        result.add(document);
                    }
                }
            });
            return result;
        }
        if (role.equals(Role.ANALYST)) {
            findDocuments.forEach(document -> {
                if (document.getApprovedAnalyst() != null ) {
                    if (document.getApprovedAnalyst()) {
                        document.getFileModel().setDocument(null);
                        result.add(document);
                    }
                }
            });
            return result;
        }
        if (role.equals(Role.DIRECTOR)) {
            findDocuments.forEach(document -> {
                if (document.getApprovedDirector() != null ) {
                    if (document.getApprovedDirector()) {
                        document.getFileModel().setDocument(null);
                        result.add(document);
                    }
                }
            });
            return result;
        }
        if (role.equals(Role.ACCOUNTANT)) {
            findDocuments.forEach(document -> {
                if (document.getApprovedAccountant() != null ) {
                    if (document.getApprovedAccountant()) {
                        document.getFileModel().setDocument(null);
                        result.add(document);
                    }
                }

            });
            return result;
        }
        return result;
    }

    @Override
    public List<Document> getAllDocumentNotApprovedByRole(Role role) {
        List<Document> findDocuments = documentRepository.findAll();
        List<Document> result = new ArrayList<>();
        if (role.equals(Role.MANAGER)) {
            findDocuments.forEach(document -> {
                if (document.getApprovedManager() != null) {
                    if (!document.getApprovedManager()) {
                        document.getFileModel().setDocument(null);
                        result.add(document);
                    }
                }

            });
            return result;
        }
        if (role.equals(Role.ANALYST)) {
            findDocuments.forEach(document -> {
                if (document.getApprovedAnalyst() != null) {
                    if (!document.getApprovedAnalyst()) {
                        document.getFileModel().setDocument(null);
                        result.add(document);
                    }
                }
            });
            return result;
        }
        if (role.equals(Role.DIRECTOR)) {
            findDocuments.forEach(document -> {
                if (document.getApprovedDirector() != null) {
                    if (!document.getApprovedDirector()) {
                        document.getFileModel().setDocument(null);
                        result.add(document);
                    }
                }
            });
            return result;
        }
        if (role.equals(Role.ACCOUNTANT)) {
            findDocuments.forEach(document -> {
                if (document.getApprovedAccountant() != null) {
                    if (!document.getApprovedAccountant()) {
                        document.getFileModel().setDocument(null);
                        result.add(document);
                    }
                }
            });
            return result;
        }
        return result;
    }
}
