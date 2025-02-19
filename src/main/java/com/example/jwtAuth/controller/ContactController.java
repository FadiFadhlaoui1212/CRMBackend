package com.example.jwtAuth.controller;


import com.example.jwtAuth.auth.JwtService;
import com.example.jwtAuth.dto.ContactUpdateDTO;
import com.example.jwtAuth.model.entity.Contact;
import com.example.jwtAuth.model.entity.User;
import com.example.jwtAuth.repository.ContactRepository;
import com.example.jwtAuth.service.implementation.ContactServiceImpl;
import com.example.jwtAuth.service.implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    @Autowired
    ContactServiceImpl contactServiceImpl;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    ContactRepository contactRepository;

    @PostMapping("/create")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact, HttpServletRequest request){

        String token = request.getHeader("Authorization").substring(7); //remove the Bearer
        String username = jwtService.extractUsername(token);
        User user =  (User) userDetailsService.loadUserByUsername(username);
        contact.setUser(user);
        Contact newContact = contactServiceImpl.createContact(contact);
        return ResponseEntity.ok(newContact);
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getAllContacts(){
        return ResponseEntity.ok(contactServiceImpl.getAllContacts());
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteContact(@PathVariable("id") Long id, HttpServletRequest request){

        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        Contact contact =  contactServiceImpl.getContactById(id);
        Map<String, String> response = new HashMap<>();
        if (Objects.equals(contact.getUser().getEmail(), username)){
            contactServiceImpl.deleteContact(id);
            response.put("message", "The contact has been deleted successfully !!!");
            return ResponseEntity.ok(response);
        }
        else {
            response.put("message", "You cannot delete this contact since you are not the owner !!!");
            return ResponseEntity.ok(response);
        }
    }

     @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateContact(@PathVariable("id") Long id, @RequestBody ContactUpdateDTO contact, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        Contact contactToUpdate = contactServiceImpl.getContactById(id);
         Map<String, String> response = new HashMap<>();
        if (Objects.equals(contactToUpdate.getUser().getEmail(), username)){
            contactServiceImpl.updateContact(id, contact);
            response.put("message", "The contact has been updated successfully !!!");
            return ResponseEntity.ok(response);
        }
        else {
            response.put("message","You cannot update this contact since you are not the owner !!!");
            return ResponseEntity.ok(response);
        }
     }

     @PostMapping("/{id}/upload-picture")
     public ResponseEntity<String> uploadPicture(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
         Contact contact = contactRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Contact not found"));

         contact.setProfilePicture(file.getBytes());
         contactRepository.save(contact);

         return ResponseEntity.ok("Profile picture uploaded successfully!");
     }



}
