package com.example.jwtAuth.controller;

import com.example.jwtAuth.model.entity.Document;
import com.example.jwtAuth.service.implementation.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Document>> findDocumentsByActivityId(@PathVariable("id") Long id){
        List<Document> documents = documentService.findDocumentsByActivityId(id);
        return ResponseEntity.ok(documents);
    }




}
