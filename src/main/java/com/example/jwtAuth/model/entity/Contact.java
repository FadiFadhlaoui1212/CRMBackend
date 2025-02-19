package com.example.jwtAuth.model.entity;


import com.example.jwtAuth.model.enums.RoleEnum;
import com.example.jwtAuth.model.enums.TitleEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Lob
    private byte[] profilePicture;

    @Column(nullable = false)
    private String company;

    @ManyToOne
    @JoinColumn(name="contactOwner", nullable = false)
    private User user;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private TitleEnum titleEnum;

    private String address;

    private String country;

    private String city;

    private int zipCode;

    private String state;

    public void setProfilePicture(byte[] profilePicture){
        this.profilePicture = profilePicture;
    }

    public byte[] getProfilePicture(){
        return profilePicture;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public String getCompany(){
        return company;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

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
