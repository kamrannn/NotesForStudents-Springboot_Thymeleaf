package com.app.studentnotes.service;

import com.app.studentnotes.model.Note;
import com.app.studentnotes.model.Student;
import com.app.studentnotes.model.User;
import com.app.studentnotes.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Student service.
 */
@Service
public class StudentService {
    private StudentRepository studentRepository;

    /**
     * Instantiates a new Student service.
     *
     * @param studentRepository the student repository
     */
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * List all students list.
     *
     * @return the list
     */
    public List<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Save student.
     *
     * @param student the student
     */
    public void saveStudent(Student student) {
        this.studentRepository.save(student);
    }

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     */
    public Student getStudentById(Integer id) {
        Optional<Student> student = studentRepository.findStudentById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new RuntimeException("User is not present");
        }
    }

    /**
     * Delete student by id.
     *
     * @param id the id
     */
    public void deleteStudentById(Integer id) {
        Optional<Student> student = studentRepository.findStudentById(id);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
        } else {
            throw new RuntimeException("User is not present");
        }
    }

    /**
     * Add student note.
     *
     * @param student_id the student id
     * @param note       the note
     */
    public void addStudentNote(Integer student_id, Note note) {
        Student student = studentRepository.findStudentById(student_id).get();
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        List<Note> noteList = student.getStudentNotes();
        note.setDateOfCreation(date);
        noteList.add(note);
        studentRepository.save(student);
    }

    /**
     * List of student notes list.
     *
     * @param id the id
     * @return the list
     */
    public List<Note> listOfStudentNotes(Integer id) {
        Optional<Student> student = studentRepository.findStudentById(id);
        if (student.isPresent()) {
            return student.get().getStudentNotes();
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    /**
     * Number of students integer.
     *
     * @return the integer
     */
    public Integer numberOfStudents() {
        List<Student> studentList = studentRepository.findAll();
        Integer totalStudents = studentList.size();
        return totalStudents;
    }

    /**
     * Average notes double.
     *
     * @return the double
     */
    public double averageNotes() {
        List<Student> studentList = studentRepository.findAll();
        Integer totalStudents = studentList.size();
        int totalNotes = 0;
        for (Student student : studentList
        ) {
            List<Note> notes;
            notes = student.getStudentNotes();
            totalNotes = totalNotes + notes.size();
        }
        double result = totalNotes / totalStudents;
        return result;
    }

    public List<Student> studentsWithNoNotes(){
        List<Student> studentList = studentRepository.findAll();
        List<Student> noNotesStudents = new ArrayList<>();
        for (Student student:studentList
             ) {
            if(student.getStudentNotes().isEmpty()){
                noNotesStudents.add(student);
            }
        }
        return noNotesStudents;
    }

    public List<Student> studentsWithMoreThan4Notes(){
        List<Student> studentList = studentRepository.findAll();
        List<Student> studentsWithMoreThan4Notes = new ArrayList<>();
        for (Student student:studentList
        ) {
            if(student.getStudentNotes().size()>4){
                studentsWithMoreThan4Notes.add(student);
            }
        }
        return studentsWithMoreThan4Notes;
    }
}
