package com.example.jwtAuth.controller;


import com.example.jwtAuth.dto.ParticipationRequest;
import com.example.jwtAuth.model.entity.Activity;
import com.example.jwtAuth.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/activity")
@CrossOrigin(origins = "http://localhost:4200")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createActivity(@RequestBody Activity activity){
        activityService.createActivity(activity);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Activity has been added successfully" );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActivityById(@PathVariable("id") Long id){
        activityService.deleteActivityById(id);
        return ResponseEntity.ok("Activity has been removed successfully");
    }

    @PostMapping("/addParticipant")
    public ResponseEntity<Map<String, String>> addParticipant(@RequestBody ParticipationRequest request){
        activityService.addParticipantToActivity(request.getActivityId(), request.getContactsIds());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Participants have been added successfully" );
        return ResponseEntity.ok(response);
    }




}
