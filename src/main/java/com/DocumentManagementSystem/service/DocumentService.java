package com.DocumentManagementSystem.service;

import com.DocumentManagementSystem.model.Document;
import com.DocumentManagementSystem.model.Role;

import java.util.List;

public interface DocumentService extends CRUDService<Document, Long> {
    List<Document> getAllNewDocument(Role role);

    List<Document> getAllDocumentApprovedByRole(Role role);
    List<Document> getAllDocumentNotApprovedByRole(Role role);
}
