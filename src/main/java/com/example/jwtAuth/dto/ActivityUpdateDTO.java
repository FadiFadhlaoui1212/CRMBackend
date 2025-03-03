package com.example.jwtAuth.dto;

import com.example.jwtAuth.model.entity.Contact;
import com.example.jwtAuth.model.entity.Document;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityUpdateDTO {

    private Date date;

    private String subject;

    private String note;

    public Date getDate(){
        return date;
    }

    public String getSubject(){
        return subject;
    }

    public String getNote(){
        return note;
    }




}
