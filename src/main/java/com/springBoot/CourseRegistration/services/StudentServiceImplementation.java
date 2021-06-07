package com.springBoot.CourseRegistration.services;

import com.springBoot.CourseRegistration.dao.StudentRepository;
import com.springBoot.CourseRegistration.entities.Course;
import com.springBoot.CourseRegistration.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImplementation implements StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findById(String username) {
        Optional<Student> result= studentRepository.findById(username);
        if(result.isPresent())
            return result.get();
        else
            throw new RuntimeException("User not found");
    }

    @Override
    public void addCourse(Course course, Student student) {

        //check for duplicate
        List<Course> appliedCourses=student.getCourses();
        for(Course tempCourse:appliedCourses){
            if(tempCourse.getId()==course.getId())
                return;
        }
        student.addCourse(course);
        studentRepository.save(student);
    }

}
