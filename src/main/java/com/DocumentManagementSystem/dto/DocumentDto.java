package com.DocumentManagementSystem.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DocumentDto {

    private Long id;

    private String userName;

    private String email;

    private String documentName;

    private MultipartFile file;

    private FileModelDto fileModel;

    private String description;

    private Boolean approvedManager;

    private Boolean approvedAnalyst;

    private Boolean approvedDirector;

    private Boolean approvedAccountant;
}
