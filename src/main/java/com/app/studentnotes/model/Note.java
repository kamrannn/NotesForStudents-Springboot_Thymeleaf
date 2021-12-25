package com.app.studentnotes.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

/**
 * The type Note.
 */
@Data
@Entity
@Table(name = "t_notes")
public class Note {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /**
     * The Text.
     */
    String text;
    /**
     * The Date of creation.
     */
    Date dateOfCreation;
}
