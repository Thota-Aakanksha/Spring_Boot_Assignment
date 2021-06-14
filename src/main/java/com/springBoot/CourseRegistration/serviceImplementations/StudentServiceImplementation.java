package com.springBoot.CourseRegistration.serviceImplementations;

import com.springBoot.CourseRegistration.dao.StudentRepository;
import com.springBoot.CourseRegistration.entities.Course;
import com.springBoot.CourseRegistration.entities.Student;
import com.springBoot.CourseRegistration.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findById(String username) {
        Optional<Student> result= studentRepository.findById(username);
        return result.orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public void addCourse(Course course, Student student) {
        student.addCourse(course);
        studentRepository.save(student);
    }

}
