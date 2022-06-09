package com.DocumentManagementSystem.service.impl;

import com.DocumentManagementSystem.model.Document;
import com.DocumentManagementSystem.model.User;
import com.DocumentManagementSystem.service.DocumentService;
import com.DocumentManagementSystem.service.UpdateDocFileService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UpdateDocFileServiceImpl implements UpdateDocFileService {

    @Override
    public Document approveRequest(Document document, User user) throws Exception {
        XWPFDocument documentDocx = new XWPFDocument(new ByteArrayInputStream(document.getFileModel().getFileData()));
        LocalDate date = LocalDate.now();
        XWPFParagraph paragraph = documentDocx.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(14);
        run.setText(user.getRole() + ": " + user.getUsername() + " " + "утвердил " + date);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        documentDocx.write(out);
        out.close();
        documentDocx.close();
        document.getFileModel().setFileData(out.toByteArray());
        return document;
    }

    @Override
    public Document rejectRequest(Document document, User user) throws Exception {
        XWPFDocument documentDocx = new XWPFDocument(new ByteArrayInputStream(document.getFileModel().getFileData()));
        LocalDate date = LocalDate.now();
        XWPFParagraph paragraph = documentDocx.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(14);
        run.setText(user.getRole() + ": " + user.getUsername() + " " + "отклонил " + date);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        documentDocx.write(out);
        out.close();
        documentDocx.close();
        document.getFileModel().setFileData(out.toByteArray());
        return document;
    }
}
