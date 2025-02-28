package com.example.jwtAuth.controller;


import com.example.jwtAuth.model.entity.Activity;
import com.example.jwtAuth.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activity")
@CrossOrigin(origins = "http://localhost:4200")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @PostMapping("/create")
    public ResponseEntity<String> createActivity(@RequestBody Activity activity){
        activityService.createActivity(activity);
        return ResponseEntity.ok("Activity has been added successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActivityById(@PathVariable("id") Long id){
        activityService.deleteActivityById(id);
        return ResponseEntity.ok("Activity has been removed successfully");
    }




}
