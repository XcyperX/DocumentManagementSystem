package com.DocumentManagementSystem.service;

import com.DocumentManagementSystem.model.Document;
import com.DocumentManagementSystem.model.User;

public interface UpdateDocFileService {
    Document approveRequest(Document document, User user) throws Exception;
    Document rejectRequest(Document document, User user) throws Exception;
}
