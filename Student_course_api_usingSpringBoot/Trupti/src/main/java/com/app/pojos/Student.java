package com.app.pojos;

import lombok.*;

import javax.persistence.*;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private int score;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}

