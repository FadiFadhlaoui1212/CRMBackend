package com.example.jwtAuth.model.entity;

import com.example.jwtAuth.model.enums.ActivityEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private ActivityEnum activity;

    @ManyToMany
    @JoinTable(
            name = "activity_participations",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private List<Contact> participants;

    private String subject;

    private String Note;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents;


}
