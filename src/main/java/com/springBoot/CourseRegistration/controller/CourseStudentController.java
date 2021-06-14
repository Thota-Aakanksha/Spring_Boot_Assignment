package com.springBoot.CourseRegistration.controller;

import com.springBoot.CourseRegistration.entities.Course;
import com.springBoot.CourseRegistration.services.CourseService;
import com.springBoot.CourseRegistration.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.stream.Stream;

@Controller
@RequestMapping("/student")
public class CourseStudentController {

    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public CourseStudentController(CourseService courseService, StudentService studentService){
        this.courseService=courseService;
        this.studentService = studentService;
    }

    @GetMapping("/applyCourse")
    public String applyCourse(@RequestParam("courseId") int courseId){
        var user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var course = courseService.findById(courseId);
        var student = studentService.findById(user.getUsername());

        //check for duplicate
        Stream<Course> appliedCourses=student.getCourses().stream();
        if(appliedCourses.anyMatch(tempCourse -> tempCourse.getId() == course.getId()))
                return "redirect:/courses/list?alreadyApplied";

        studentService.addCourse(course,student);
        return "redirect:/courses/list?appliedCourse="+course.getTitle();
    }


    @GetMapping("/getStudentCourses")
    public String getCourses( Model model){
        var user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var student = studentService.findById(user.getUsername());
        model.addAttribute("studentCourses",student.getCourses());

        return "student-courses";
    }

}
