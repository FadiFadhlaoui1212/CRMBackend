package com.example.jwtAuth.controller;

import com.example.jwtAuth.dto.DocumentName;
import com.example.jwtAuth.model.entity.Activity;
import com.example.jwtAuth.model.entity.Document;
import com.example.jwtAuth.service.implementation.ActivityServiceImpl;
import com.example.jwtAuth.service.implementation.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.jsonwebtoken.Jwts.header;

@RestController
@RequestMapping("/api/document")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentController {

    @Autowired
    DocumentServiceImpl documentService;

    @Autowired
    ActivityServiceImpl activityService;

    @PostMapping("/create/{activityId}")
    public ResponseEntity<Map<String, String>> createDocuments(@RequestParam("files") MultipartFile[] files, @PathVariable("activityId") Long activityId){
        try {
            Activity activity = activityService.getActivityById(activityId);
            for (MultipartFile file: files){
                Document document = new Document();
                document.setFileName(file.getOriginalFilename());
                document.setData(file.getBytes());
                document.setActivity(activity);
                documentService.createDocument(document);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file data", e);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Documents have been added successfully" );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable("id") Long id){
        documentService.deleteDocument(id);
        return ResponseEntity.ok("The Document has been removed successfully");
    }

    @DeleteMapping("/documents/delete")
    public ResponseEntity<Map<String, String>> deleteDocumentsByIds(@RequestBody List<Long> ids){
        documentService.deleteDocumentsByIds(ids);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Documents have been deleted successfully" );
        return ResponseEntity.ok(response);
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
