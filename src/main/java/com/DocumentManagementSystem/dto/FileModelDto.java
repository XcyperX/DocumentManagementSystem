package com.DocumentManagementSystem.dto;

import lombok.Data;

@Data
public class FileModelDto {
    private Long id;
    private String fileName;
    private String fileType;
    private byte[] fileData;
}
