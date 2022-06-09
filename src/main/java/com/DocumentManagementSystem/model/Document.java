package com.DocumentManagementSystem.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "document")
public class Document {
    private static final String DOCUMENT_ID_SEQ = "document_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = DOCUMENT_ID_SEQ)
    @SequenceGenerator(name = DOCUMENT_ID_SEQ, sequenceName = DOCUMENT_ID_SEQ, allocationSize = 1)
    private Long id;

    private String userName;

    private String email;

    private String documentName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private FileModel fileModel;

    private String description;

    private Boolean approvedManager = null;

    private Boolean approvedAnalyst = null;

    private Boolean approvedDirector = null;

    private Boolean approvedAccountant = null;
}
