package com.example.jwtAuth.repository;

import com.example.jwtAuth.model.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("SELECT d FROM Document d WHERE d.activity_id =: id")
    List<Document> findDocumentsByActivityId(@Param("id") Long id);
}
