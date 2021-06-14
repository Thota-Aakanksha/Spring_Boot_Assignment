package com.springBoot.CourseRegistration.services;

import com.springBoot.CourseRegistration.entities.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Course findById(int id);
    void save(Course course);
    void deleteById(int id);
}
