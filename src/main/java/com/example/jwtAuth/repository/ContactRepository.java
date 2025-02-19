package com.example.jwtAuth.repository;

import com.example.jwtAuth.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT c FROM Contact c WHERE c.id = (SELECT MAX(c2.id) FROM Contact c2)")
    Contact findContactWithMaxId();

    @Query("SELECT c FROM Contact c")
    List<Contact> findAllContacts();

}
