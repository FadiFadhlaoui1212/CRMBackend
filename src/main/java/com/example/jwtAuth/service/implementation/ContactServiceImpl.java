package com.example.jwtAuth.service.implementation;

import com.example.jwtAuth.dto.ContactUpdateDTO;
import com.example.jwtAuth.model.entity.Contact;
import com.example.jwtAuth.repository.ContactRepository;
import com.example.jwtAuth.repository.UserRepository;
import com.example.jwtAuth.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ContactServiceImpl implements ContactService {


    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;


    public Contact createContact(Contact contact){
        contactRepository.save(contact);
        return contactRepository.findContactWithMaxId();
    }

    public List<Contact> getAllContacts(){
        return contactRepository.findAllContacts();
    }

    public Contact getContactById(Long contactId){
        Optional<Contact> contact = contactRepository.findById(contactId);
        return contact.get();
    }


    public void deleteContact(Long contactId){
        contactRepository.deleteById(contactId);
    }

    public void updateContact(Long id, ContactUpdateDTO contact){

        Contact contactToUpdate = getContactById(id);

        contactToUpdate.setFirstName(contact.getFirstName());
        contactToUpdate.setLastName(contact.getLastName());
        contactToUpdate.setCompany(contact.getCompany());
        contactToUpdate.setPhoneNumber(contact.getPhoneNumber());
        contactToUpdate.setTitleEnum(contact.getTitleEnum());
        contactToUpdate.setAddress(contact.getAddress());
        contactToUpdate.setCountry(contact.getCountry());
        contactToUpdate.setCity(contact.getCity());
        contactToUpdate.setZipCode(contact.getZipCode());
        contactToUpdate.setState(contact.getState());

        contactRepository.save(contactToUpdate);
    }


}
