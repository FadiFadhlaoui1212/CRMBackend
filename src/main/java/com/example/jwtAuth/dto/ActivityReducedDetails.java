package com.example.jwtAuth.dto;


import com.example.jwtAuth.model.entity.Contact;
import com.example.jwtAuth.model.enums.ActivityEnum;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
public class ActivityReducedDetails {

    private Long id;
    private Date date;
    private ActivityEnum type;
    private String subject;
    private String note;
    private List<Contact> participants;

    public ActivityReducedDetails(Long id, Date date, ActivityEnum type, String subject, String note) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.subject = subject;
        this.note = note;
    }

    public Long getId(){
        return id;
    }

    public void setParticipants(List<Contact> participants){
        this.participants = participants;
    }




}
