package com.springBoot.CourseRegistration.exceptionHandling;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(String message) {
        super(message);
    }
}
