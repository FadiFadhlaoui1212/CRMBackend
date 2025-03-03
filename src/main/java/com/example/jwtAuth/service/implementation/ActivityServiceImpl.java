package com.example.jwtAuth.service.implementation;

import com.example.jwtAuth.dto.ActivityReducedDetails;
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

    public Activity createActivity(Activity activity){
        activityRepository.save(activity);
        Activity newActivity = activityRepository.findActivityWithMaxId();
        return newActivity;
    }

    public void deleteActivityById(Long id){
        activityRepository.deleteById(id);
    }

    public void deleteActivitiesByIds(List<Long> ids){
        activityRepository.deleteActivitiesByIds(ids);
    }

    public void updateActivityById(Long id, ActivityUpdateDTO dto){
        Activity activityToUpdate = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
        activityToUpdate.setDate(dto.getDate());
        activityToUpdate.setNote(dto.getNote());
        activityToUpdate.setSubject(dto.getSubject());
        activityRepository.save(activityToUpdate);
    }

    public Activity getActivityById(Long id){
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
        return activity;
    }

    public void addParticipantsToActivity(Long activityId, List<Long> contactsIds){
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        List<Contact> contacts = contactRepository.findContactsByIds(contactsIds);
        activity.getParticipants().clear();

        for (Contact contact: contacts){
            activity.getParticipants().add(contact);
            activityRepository.save(activity);
        }
    }

    public List<ActivityReducedDetails> getAllActivities(){
        List<ActivityReducedDetails> activities = activityRepository.findAllActivities();
        for (ActivityReducedDetails activity: activities){
            Activity entity = activityRepository.findById(activity.getId()).orElse(null);
            if (entity!=null){
                activity.setParticipants(entity.getParticipants());
            }
        }
        return activities;
    }




}
