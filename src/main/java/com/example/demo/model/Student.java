package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "first_name", nullable = false)
    @Size(min = 3, message = " Name too short ")
    @Size(max = 50, message = " Name too long ")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 3, message = " Name too short ")
    @Size(max = 50, message = " Name too long ")
    private String lastName;

}
