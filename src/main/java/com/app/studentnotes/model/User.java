package com.app.studentnotes.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * The type User.
 */
@Data
@Entity
@Table(name = "t_users")
public class User {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /**
     * The Username.
     */
    @NotBlank(message = "username is mandatory")
    @Column(unique = true)
    String username;
    /**
     * The Password.
     */
    String password;
    /**
     * The Role.
     */
    String role;
    /**
     * The First name.
     */
    String firstName;
    /**
     * The Last name.
     */
    String lastName;
}
