package com.example.jwtAuth.dto;


import lombok.*;

@Getter
@Setter
@Data
public class DocumentName {

    private Long id;

    private String fileName;

    public DocumentName(Long id, String fileName){
        this.id = id;
        this.fileName = fileName;
    }


}
