package com.springBoot.CourseRegistration.exceptionHandling;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler
    public String handler(RuntimeException exception, Model model){
        model.addAttribute("errorMessage",exception.getMessage());
        return "exception-page";
    }
}
