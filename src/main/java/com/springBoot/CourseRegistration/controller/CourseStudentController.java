package com.springBoot.CourseRegistration.controller;

import com.springBoot.CourseRegistration.entities.Course;
import com.springBoot.CourseRegistration.entities.Student;
import com.springBoot.CourseRegistration.services.CourseService;
import com.springBoot.CourseRegistration.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student")
public class CourseStudentController {

    private CourseService courseService;
    private StudentService studentService;

    @Autowired
    public CourseStudentController(CourseService courseService, StudentService studentService){
        this.courseService=courseService;
        this.studentService = studentService;
    }

    @RequestMapping("/applyCourse")
    public String applyCourse(@RequestParam("courseId") int courseId){
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Course course = courseService.findById(courseId);
        Student student = studentService.findById(user.getUsername());
        studentService.addCourse(course,student);

        return "redirect:/courses/list?appliedCourse="+course.getTitle();
    }


    @RequestMapping("/getStudentCourses")
    public String getCourses( Model model){
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Student student = studentService.findById(user.getUsername());
        model.addAttribute("studentCourses",student.getCourses());

        return "student-courses";
    }

}
