package com.example.jwtAuth.service;

import com.example.jwtAuth.model.entity.Document;

import java.util.List;

public interface DocumentService {

    void createDocument(Document document);

    void deleteDocument(Long DocumentId);

    List<Document> findDocumentsByActivityId(Long id);

    void deleteDocumentsByIds(List<Long> ids);


}
