package com.app.studentnotes.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({NoHandlerFoundException.class, RuntimeException.class})
    public String inputValidationException(Exception e) {
        return "redirect:/";
    }

    @ExceptionHandler({TemplateInputException.class,DataIntegrityViolationException.class, ConstraintViolationException.class})
    public String dataIntegrityViolation(Exception e) {
        e.printStackTrace();
        return "redirect:/";
    }
}