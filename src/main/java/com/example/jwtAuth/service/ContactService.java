package com.example.jwtAuth.service;

import com.example.jwtAuth.model.entity.Contact;

import java.util.List;

public interface ContactService {
    Contact createContact(Contact contact);

    List<Contact> getAllContacts();

    void deleteContact(Long ContactId);



}
