package com.springBoot.CourseRegistration.services;

import com.springBoot.CourseRegistration.entities.Course;

import java.util.List;

public interface CourseService {
    public List<Course> findAll();
    public Course findById(int id);
    public void save(Course course);
    public void deleteById(int id);
}
