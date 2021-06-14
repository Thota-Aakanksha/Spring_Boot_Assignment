package com.springBoot.CourseRegistration.services;

import com.springBoot.CourseRegistration.entities.Course;
import com.springBoot.CourseRegistration.entities.Student;

public interface StudentService {
    Student findById(String username);
    void addCourse(Course course, Student student);
}
