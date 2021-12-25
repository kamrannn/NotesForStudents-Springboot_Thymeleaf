package com.app.studentnotes.repository;

import com.app.studentnotes.model.Note;
import com.app.studentnotes.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    Note findNoteById(Integer id);
}
