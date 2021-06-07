package com.springBoot.CourseRegistration.services;

import com.springBoot.CourseRegistration.entities.Course;
import com.springBoot.CourseRegistration.entities.Student;

public interface StudentService {
    public Student findById(String username);
    public void addCourse(Course course, Student student);
}
