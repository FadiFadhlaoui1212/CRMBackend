package com.example.jwtAuth.controller;

import com.example.jwtAuth.dto.DocumentName;
import com.example.jwtAuth.model.entity.Document;
import com.example.jwtAuth.service.implementation.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static io.jsonwebtoken.Jwts.header;

@RestController
@RequestMapping("/api/document")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentController {

    @Autowired
    DocumentServiceImpl documentService;

    @PostMapping("/create")
    public ResponseEntity<String> createDocuments(@RequestBody List<Document> documents){
        for (Document document: documents){
            documentService.createDocument(document);
        }
        return ResponseEntity.ok("The Documents have been added successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable("id") Long id){
        documentService.deleteDocument(id);
        return ResponseEntity.ok("The Document has been removed successfully");
    }

    @DeleteMapping("/documents/delete")
    public ResponseEntity<String> deleteDocumentsByIds(@RequestBody List<Long> ids){
        documentService.deleteDocumentsByIds(ids);
        return ResponseEntity.ok("The Documents have been removed successfully");
    }

    @GetMapping("/documents/{id}")
    public ResponseEntity<List<DocumentName>> findDocumentsByActivityId(@PathVariable("id") Long id){
        List<Document> documents = documentService.findDocumentsByActivityId(id);
        List<DocumentName> docsnames = new ArrayList<>();
        for (Document document: documents){
            DocumentName docname = new DocumentName(document.getId(), document.getFileName());
            docsnames.add(docname);
        }
        return ResponseEntity.ok(docsnames);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable("id") Long id){
        Document document = documentService.getDocumentById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + document.getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(document.getData());
    }





}
