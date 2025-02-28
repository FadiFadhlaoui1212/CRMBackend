package com.example.jwtAuth.service.implementation;

import com.example.jwtAuth.model.entity.Document;
import com.example.jwtAuth.repository.DocumentRepository;
import com.example.jwtAuth.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    public void createDocument(Document document){
        documentRepository.save(document);
    }

    public void deleteDocument(Long documentId){
        documentRepository.deleteById(documentId);
    }

    public List<Document> findDocumentsByActivityId(Long id){
        List<Document> documents = documentRepository.findDocumentsByActivityId(id);
        return documents;
    }
}
