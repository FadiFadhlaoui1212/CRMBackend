package com.example.jwtAuth.service;


import com.example.jwtAuth.dto.ActivityReducedDetails;
import com.example.jwtAuth.dto.ActivityUpdateDTO;
import com.example.jwtAuth.model.entity.Activity;
import com.example.jwtAuth.model.entity.Contact;
import com.example.jwtAuth.service.implementation.ActivityServiceImpl;

import java.util.List;

public interface ActivityService {

    Activity createActivity(Activity activity);

    void deleteActivityById(Long id);

    void deleteActivitiesByIds(List<Long> ids);

    Activity getActivityById(Long id);

    void updateActivityById(Long id, ActivityUpdateDTO dto);

    void addParticipantsToActivity(Long activityId, List<Long> contactsIds);

    List<ActivityReducedDetails> getAllActivities();

}
