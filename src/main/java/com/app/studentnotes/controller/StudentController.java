package com.app.studentnotes.controller;

import com.app.studentnotes.model.Note;
import com.app.studentnotes.model.Student;
import com.app.studentnotes.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * The type Student controller.
 */
@Controller
@Validated
public class StudentController {
    final private StudentService studentService;

    /**
     * Instantiates a new Student controller.
     *
     * @param studentService the student service
     */
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * View homepage string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/")
    public String viewHomepage(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("students",studentService.listAllStudents());
        return "index";
    }

    /**
     * Show new student form string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/save-student-form")
    public String showNewStudentForm(Model model) {
        // create model attribute to bind form data
        model.addAttribute("Student", new Student());
        return "new_student";
    }

    /**
     * Save student string.
     *
     * @param student the student
     * @return the string
     */
    @PostMapping("/save-student-form")
    public String saveStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            studentService.saveStudent(student);
            model.addAttribute("message", "Registration successfully...");
        }
        return "new_student";
    }

    /**
     * Show form for update string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {
        // get employee from the service
        Student student = studentService.getStudentById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("student", student);
        return "update_student";
    }

    /**
     * Delete employee string.
     *
     * @param id the id
     * @return the string
     */
    @GetMapping("/deleteStudent/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Integer id) {
        // call delete employee method
        this.studentService.deleteStudentById(id);
        return "redirect:/";
    }

    /**
     * Show new note form string.
     *
     * @param model the model
     * @param id    the id
     * @return the string
     */
    @GetMapping("/showNewNoteForm/{id}")
    public String showNewNoteForm(Model model,@PathVariable(value = "id") Integer id) {
        // create model attribute to bind form data
        Note note = new Note();
        model.addAttribute("note", note);
        model.addAttribute("student_id", id);
        return "new_note";
    }

    /**
     * Save note string.
     *
     * @param note the note
     * @param id   the id
     * @return the string
     */
    @PostMapping("/saveNote/{id}")
    public String saveNote(@ModelAttribute("note") Note note, @PathVariable(value = "id") Integer id) {
        // save student to database
        note.setId(null);
        studentService.addStudentNote(id, note);
        return "redirect:/";
    }

    /**
     * List of student notes string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/showListOfNotes/{id}")
    public String listOfStudentNotes(@PathVariable(value = "id") Integer id, Model model) {
/*        Student student = studentService.getStudentById(id);
        model.addAttribute("student",student);*/
        // get notes of a specific user
        model.addAttribute("notes",studentService.listOfStudentNotes(id));
        return "student_notes";
    }

    /**
     * Search string.
     *
     * @return the string
     */
    @GetMapping("/search")
    public String search() {
        return "search";
    }

    /**
     * Statistics string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/statistics")
    public String statistics(Model model) {
        model.addAttribute("total_students",studentService.numberOfStudents());
        model.addAttribute("average_notes", studentService.averageNotes());
        return "statistics";
    }

    /**
     * Search student string.
     *
     * @param model the model
     * @param id    the id
     * @return the string
     */
    @GetMapping("/searchStudentById")
    public String searchStudent(Model model, @RequestParam(value = "id") Integer id) {
        Student student = studentService.getStudentById(id);
/*        if(!student.isPresent()){
            ObjectError error = new ObjectError("globalError", "user is not present");
            result.addError(error);
        }
        if (result.hasErrors()) {
            return "search";
        }*/
        model.addAttribute("students", student);
        model.addAttribute("notes",studentService.listOfStudentNotes(id));
        // get notes of a specific user
        return "student_with_notes";
    }

    @PostMapping("/error")
    public String error() {
        return "redirect:/";
    }

    @GetMapping("/no-notes")
    public String viewNoNotesStudents(Model model){
        model.addAttribute("students",studentService.studentsWithNoNotes());
        return "no_notes_students";
    }

    @GetMapping("/more-than-4-notes")
    public String studentsMoreThan4Notes(Model model){
        model.addAttribute("students",studentService.studentsWithMoreThan4Notes());
        return "student_notes_more_than_four";
    }

    @GetMapping("/create-student")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "createstudent";
    }

    @PostMapping("/create-student")
    public String register(@Valid Student student, Errors errors, Model model,  SessionStatus status) {
        if (errors.hasErrors()) {
            return "createstudent";
        } else {
            studentService.saveStudent(student);

            model.addAttribute("students", studentService.listAllStudents());
            status.setComplete();
            return "index";
        }
    }





}
