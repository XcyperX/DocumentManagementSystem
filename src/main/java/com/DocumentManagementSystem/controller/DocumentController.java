package com.DocumentManagementSystem.controller;

import com.DocumentManagementSystem.dto.DocumentDto;
import com.DocumentManagementSystem.dto.FileModelDto;
import com.DocumentManagementSystem.model.Document;
import com.DocumentManagementSystem.model.Role;
import com.DocumentManagementSystem.model.User;
import com.DocumentManagementSystem.security.SecurityUtils;
import com.DocumentManagementSystem.service.DocumentService;
import com.DocumentManagementSystem.service.UpdateDocFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class DocumentController {
    private final MapperFacade mapper;
    private final DocumentService documentService;
    private final UpdateDocFileService updateDocFileService;

    @GetMapping("/requests/create")
    private String getCreateRequestPage() {
        return "createRequest";
    }

    @PostMapping(value = "/requests/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private String createRequest(DocumentDto documentDto) {
        try {
            FileModelDto fileModelDto = new FileModelDto();
            MultipartFile file = documentDto.getFile();
            fileModelDto.setFileName(file.getName());
            fileModelDto.setFileType(file.getContentType());
            fileModelDto.setFileData(file.getBytes());
            documentDto.setFileModel(fileModelDto);
            documentService.save(mapper.map(documentDto, Document.class));
        } catch (Exception e) {
            return "redirect:/requests/create?error";
        }
        return "createRequest";
    }

    @GetMapping("/requests")
    private String getListNewRequest(Model model) {
        User userFromContext = SecurityUtils.getUserFromContext();
        if (Objects.isNull(userFromContext)) {
            return "redirect:/login";
        }
        List<Document> documents = new ArrayList<>();
        if (userFromContext.getRole().equals(Role.MANAGER)) {
            documents = documentService.getAllNewDocument(Role.MANAGER);
            model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
            return "viewRequests";
        }
        if (userFromContext.getRole().equals(Role.ANALYST)) {
            documents = documentService.getAllNewDocument(Role.ANALYST);
            model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
            return "viewRequests";
        }
        if (userFromContext.getRole().equals(Role.DIRECTOR)) {
            documents = documentService.getAllNewDocument(Role.DIRECTOR);
            model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
            return "viewRequests";
        }
        if (userFromContext.getRole().equals(Role.ACCOUNTANT)) {
            documents = documentService.getAllNewDocument(Role.ACCOUNTANT);
            model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
            return "viewRequests";
        }
        model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
        return "viewRequests";
    }

    @GetMapping("/download/{documentId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long documentId) {
        Document model = documentService.getById(documentId);
        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + model.getDocumentName() + "\"")
                .contentType(MediaType.valueOf(model.getFileModel().getFileType()))
                .body(new ByteArrayResource(model.getFileModel().getFileData()));
    }

    @PostMapping("/requests/{id}/accepted")
    public String acceptedRequest(@PathVariable Long id) {
        Document document = documentService.getById(id);
        User userFromContext = SecurityUtils.getUserFromContext();
        if (Objects.isNull(userFromContext)) {
            return "redirect:/login";
        }
        if (Objects.isNull(document)) {
            return "redirect:/requests";
        }
        try {
            if (userFromContext.getRole().equals(Role.MANAGER)) {
                document.setApprovedManager(true);
                documentService.save(updateDocFileService.approveRequest(document, userFromContext));
            }
            if (userFromContext.getRole().equals(Role.ANALYST)) {
                document.setApprovedAnalyst(true);
                documentService.save(updateDocFileService.approveRequest(document, userFromContext));
            }
            if (userFromContext.getRole().equals(Role.DIRECTOR)) {
                document.setApprovedDirector(true);
                documentService.save(updateDocFileService.approveRequest(document, userFromContext));
            }
            if (userFromContext.getRole().equals(Role.ACCOUNTANT)) {
                document.setApprovedAccountant(true);
                documentService.save(updateDocFileService.approveRequest(document, userFromContext));
            }
        } catch (Exception e) {
            return "redirect:/requests?error";
        }
        return "redirect:/requests";
    }

    @PostMapping("/requests/{id}/rejected")
    public String rejectedRequest(@PathVariable Long id) {
        Document document = documentService.getById(id);
        User userFromContext = SecurityUtils.getUserFromContext();
        if (Objects.isNull(userFromContext)) {
            return "redirect:/login";
        }
        if (Objects.isNull(document)) {
            return "redirect:/requests";
        }
        try {
            if (userFromContext.getRole().equals(Role.MANAGER)) {
                document.setApprovedManager(false);
                documentService.save(updateDocFileService.rejectRequest(document, userFromContext));
            }
            if (userFromContext.getRole().equals(Role.ANALYST)) {
                document.setApprovedAnalyst(false);
                documentService.save(updateDocFileService.rejectRequest(document, userFromContext));
            }
            if (userFromContext.getRole().equals(Role.DIRECTOR)) {
                document.setApprovedDirector(false);
                documentService.save(updateDocFileService.rejectRequest(document, userFromContext));
            }
            if (userFromContext.getRole().equals(Role.ACCOUNTANT)) {
                document.setApprovedAccountant(false);
                documentService.save(updateDocFileService.rejectRequest(document, userFromContext));
            }
        } catch (Exception e) {
            return "redirect:/requests?error";
        }
        return "redirect:/requests";
    }

    @GetMapping("/requests/approved")
    public String getRequestsApprovedPage(Model model) {
        User userFromContext = SecurityUtils.getUserFromContext();
        if (Objects.isNull(userFromContext)) {
            return "redirect:/login";
        }
        List<Document> documents = new ArrayList<>();
        if (userFromContext.getRole().equals(Role.MANAGER)) {
            documents = documentService.getAllDocumentApprovedByRole(Role.MANAGER);
            model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
            return "viewRequestsAccepted";
        }
        if (userFromContext.getRole().equals(Role.ANALYST)) {
            documents = documentService.getAllDocumentApprovedByRole(Role.ANALYST);
            model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
            return "viewRequestsAccepted";
        }
        if (userFromContext.getRole().equals(Role.DIRECTOR)) {
            documents = documentService.getAllDocumentApprovedByRole(Role.DIRECTOR);
            model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
            return "viewRequestsAccepted";
        }
        if (userFromContext.getRole().equals(Role.ACCOUNTANT)) {
            documents = documentService.getAllDocumentApprovedByRole(Role.ACCOUNTANT);
            model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
            return "viewRequestsAccepted";
        }
        model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
        return "viewRequestsAccepted";
    }

    @GetMapping("/requests/rejected")
    public String getRequestsRejectedPage(Model model) {
        User userFromContext = SecurityUtils.getUserFromContext();
        if (Objects.isNull(userFromContext)) {
            return "redirect:/login";
        }
        List<Document> documents = new ArrayList<>();
        if (userFromContext.getRole().equals(Role.MANAGER)) {
            documents = documentService.getAllDocumentNotApprovedByRole(Role.MANAGER);
            model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
            return "viewRequestsRejected";
        }
        if (userFromContext.getRole().equals(Role.ANALYST)) {
            documents = documentService.getAllDocumentNotApprovedByRole(Role.ANALYST);
            model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
            return "viewRequestsRejected";
        }
        if (userFromContext.getRole().equals(Role.DIRECTOR)) {
            documents = documentService.getAllDocumentNotApprovedByRole(Role.DIRECTOR);
            model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
            return "viewRequestsRejected";
        }
        if (userFromContext.getRole().equals(Role.ACCOUNTANT)) {
            documents = documentService.getAllDocumentNotApprovedByRole(Role.ACCOUNTANT);
            model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
            return "viewRequestsRejected";
        }
        model.addAttribute("documents", mapper.mapAsList(documents, DocumentDto.class));
        return "viewRequestsRejected";
    }
}
