package com.app.studentnotes.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Student.
 */
@Data
@Entity
@Table(name = "t_students")
public class Student {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /**
     * The Email.
     */
    @NotEmpty(message = "Email field should not be empty")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    @Column(unique = true)
    String email;
    /**
     * The First name.
     */
    @NotEmpty(message = "First Name is mandatory")
    String firstName;
    /**
     * The Sur name.
     */
    @NotEmpty(message = "Sur Name is mandatory")
    String surName;

    @OneToMany(targetEntity = Note.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private List<Note> studentNotes = new ArrayList<>();
}
