package com.springBoot.CourseRegistration.controller;

import com.springBoot.CourseRegistration.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseHomeController {
    private final CourseService courseService;

    @Autowired
    public CourseHomeController(CourseService courseService){
        this.courseService=courseService;
    }

    @GetMapping("/list")
    public String getCourses(Model model){
        model.addAttribute("courses",courseService.findAll());
        return "list";
    }

}
