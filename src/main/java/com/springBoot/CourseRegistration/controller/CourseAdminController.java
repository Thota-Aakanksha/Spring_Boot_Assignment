package com.springBoot.CourseRegistration.controller;

import com.springBoot.CourseRegistration.entities.Course;
import com.springBoot.CourseRegistration.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin")
public class CourseAdminController {

    private CourseService courseService;

    @Autowired
    CourseAdminController(CourseService courseService){
        this.courseService=courseService;
    }

    @GetMapping("/addCourseForm")
    public String addCourseForm(Model model){
        model.addAttribute("course",new Course());
        return "course-form";
    }

    @PostMapping("/addCourse")
    public String addCourse(@ModelAttribute("course") Course course){
        courseService.save(course);
        return "redirect:/courses/list";
    }

    @GetMapping("/updateCourseForm")
    public String updateCourseForm(@RequestParam("courseId") int courseId, Model model){
        Course course=courseService.findById(courseId);
        model.addAttribute("course",course);
        return "course-form";
    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") int courseId){
        courseService.deleteById(courseId);
        return "redirect:/courses/list";
    }
}
