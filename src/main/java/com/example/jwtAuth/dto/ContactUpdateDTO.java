package com.example.jwtAuth.dto;


import com.example.jwtAuth.model.enums.TitleEnum;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactUpdateDTO {

    private String firstName;

    private String lastName;

    private String company;

    private String phoneNumber;

    private TitleEnum titleEnum;

    private String address;

    private String country;

    private String city;

    private int zipCode;

    private String state;

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getCompany(){return company;}

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public TitleEnum getTitleEnum(){
        return titleEnum;
    }

    public void setTitleEnum(TitleEnum titleEnum){
        this.titleEnum = titleEnum;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public int getZipCode(){
        return zipCode;
    }

    public void setZipCode(int zipCode){
        this.zipCode = zipCode;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

}
