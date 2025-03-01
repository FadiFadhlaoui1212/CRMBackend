package com.example.jwtAuth.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationRequest {

    private Long activityId;

    private List<Long> contactsIds;

    public Long getActivityId(){
        return activityId;
    }

    public List<Long> getContactsIds(){
        return contactsIds;
    }
}
