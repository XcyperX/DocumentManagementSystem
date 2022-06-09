package com.DocumentManagementSystem.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "document_file")
public class FileModel {
    private static final String DOCUMENT_FILE_ID_SEQ = "document_file_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = DOCUMENT_FILE_ID_SEQ)
    @SequenceGenerator(name = DOCUMENT_FILE_ID_SEQ, sequenceName = DOCUMENT_FILE_ID_SEQ, allocationSize = 1)
    private Long id;
    private String fileName;
    private String fileType;
    private byte[] fileData;

    @OneToOne(mappedBy = "fileModel")
    private Document document;

    public FileModel() {

    }
    public FileModel(String fileName, String fileType) {
        this.fileName = fileName;
        this.fileType = fileType;

    }
    public FileModel(String fileName, String fileType, byte[] fileData)     {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
    }
}
