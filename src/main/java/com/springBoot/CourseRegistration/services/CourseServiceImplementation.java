package com.springBoot.CourseRegistration.services;

import com.springBoot.CourseRegistration.dao.CourseRepository;
import com.springBoot.CourseRegistration.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImplementation implements CourseService{

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImplementation(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(int id) {
        Optional<Course> course= courseRepository.findById(id);
        if(course.isPresent())
            return course.get();
        else
            throw new RuntimeException("Course not found");
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }
}
