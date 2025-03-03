package com.example.jwtAuth.controller;


import com.example.jwtAuth.dto.ActivityReducedDetails;
import com.example.jwtAuth.dto.ActivityUpdateDTO;
import com.example.jwtAuth.dto.ParticipationRequest;
import com.example.jwtAuth.model.entity.Activity;
import com.example.jwtAuth.model.entity.Contact;
import com.example.jwtAuth.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/activity")
@CrossOrigin(origins = "http://localhost:4200")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @PostMapping("/create")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity){
        Activity newActivity = activityService.createActivity(activity);
        return ResponseEntity.ok(newActivity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteActivityById(@PathVariable("id") Long id){
        activityService.deleteActivityById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Activity has been removed successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteActivitiesByIds(@RequestBody List<Long> ids){
        activityService.deleteActivitiesByIds(ids);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Activities have been removed successfully" );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addParticipants")
    public ResponseEntity<Map<String, String>> addParticipants(@RequestBody ParticipationRequest request){
        activityService.addParticipantsToActivity(request.getActivityId(), request.getContactsIds());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Participants have been added successfully" );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ActivityReducedDetails>> getAllActivities(){
        List<ActivityReducedDetails> activities = activityService.getAllActivities();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/participants/{id}")
    public ResponseEntity<List<Contact>> getParticipantsByActivityId(@PathVariable("id") Long id){
        Activity activity = activityService.getActivityById(id);
        return ResponseEntity.ok(activity.getParticipants());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateActivity(@PathVariable("id") Long id, @RequestBody ActivityUpdateDTO dto){
        activityService.updateActivityById(id, dto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Activity has been updated successfully" );
        return ResponseEntity.ok(response);

    }




}
