package com.example.jwtAuth.service.implementation;

import com.example.jwtAuth.dto.ActivityUpdateDTO;
import com.example.jwtAuth.model.entity.Activity;
import com.example.jwtAuth.model.entity.Contact;
import com.example.jwtAuth.repository.ActivityRepository;
import com.example.jwtAuth.repository.ContactRepository;
import com.example.jwtAuth.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ContactRepository contactRepository;

    public void createActivity(Activity activity){
        activityRepository.save(activity);
    }

    public void deleteActivityById(Long id){
        activityRepository.deleteById(id);
    }

    public void updateActivityById(Long id, ActivityUpdateDTO dto){
        Activity activityToUpdate = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
        activityToUpdate.setDate(dto.getDate());
        activityToUpdate.setParticipants(dto.getParticipants());
        activityToUpdate.setNote(dto.getNote());
        activityToUpdate.setSubject(dto.getSubject());
    }

    public void addParticipantToActivity(Long activityId, List<Long> contactsIds){
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        List<Contact> contacts = contactRepository.findContactsByIds(contactsIds);

        for (Contact contact: contacts){
            activity.getParticipants().add(contact);
        }
    }




}
