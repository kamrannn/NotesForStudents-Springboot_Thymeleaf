package com.app.studentnotes.service;

import com.app.studentnotes.model.Note;
import com.app.studentnotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Note service.
 */
@Service
public class NoteService {
    /**
     * The Note repository.
     */
    NoteRepository noteRepository;

    /**
     * Instantiates a new Note service.
     *
     * @param noteRepository the note repository
     */
    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * List all notes list.
     *
     * @return the list
     */
    public List<Note> listAllNotes(){
        return noteRepository.findAll();
    }

    /**
     * Save student.
     *
     * @param note the note
     */
    public void saveStudent(Note note){
        this.noteRepository.save(note);
    }

    /**
     * Get note by id note.
     *
     * @param id the id
     * @return the note
     */
    public Note getNoteById(Integer id){
        return noteRepository.findNoteById(id);
    }

    /**
     * Delete note by id.
     *
     * @param id the id
     */
    public void deleteNoteById(Integer id){
        Note note = noteRepository.findNoteById(id);
        noteRepository.delete(note);
    }
}
