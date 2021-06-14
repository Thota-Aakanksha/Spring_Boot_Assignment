package com.springBoot.CourseRegistration.serviceImplementations;

import com.springBoot.CourseRegistration.dao.CourseRepository;
import com.springBoot.CourseRegistration.entities.Course;
import com.springBoot.CourseRegistration.exceptionHandling.CourseNotFoundException;
import com.springBoot.CourseRegistration.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;

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
        return course.orElseThrow(()->new CourseNotFoundException("Course with id : "+id+" not found" ));
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteById(int id) {
        if(courseRepository.findById(id).isEmpty())
            throw new CourseNotFoundException("Course with id : "+id+" not found" );
        courseRepository.deleteById(id);
    }
}
