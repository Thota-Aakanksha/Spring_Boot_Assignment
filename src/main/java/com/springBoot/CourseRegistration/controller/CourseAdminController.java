package com.springBoot.CourseRegistration.controller;

import com.springBoot.CourseRegistration.dto.CourseDto;
import com.springBoot.CourseRegistration.entities.Course;
import com.springBoot.CourseRegistration.services.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Stream;

@Controller
@RequestMapping("admin")
public class CourseAdminController {
    private static final String COURSE_FORM="course-form";
    public static final String REDIRECT_HOME = "redirect:/courses/list";
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @Autowired
    CourseAdminController(CourseService courseService, ModelMapper modelMapper){
        this.courseService=courseService;
        this.modelMapper = modelMapper;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        var editor=new StringTrimmerEditor((true));
        dataBinder.registerCustomEditor(String.class,editor);
    }

    @GetMapping("/addCourseForm")
    public String addCourseForm(Model model){
        model.addAttribute("courseDto",new CourseDto());
        return COURSE_FORM;
    }

    @PostMapping("/addCourse")
    public String addCourse(@Valid @ModelAttribute("courseDto") CourseDto courseDto, BindingResult result){

         //validate for duplicates in add
        String courseTitle=courseDto.getTitle();
        Stream<Course> coursesAvailable = courseService.findAll().stream();
        if(coursesAvailable.anyMatch( tempCourse->  courseDto.getId()==0 && tempCourse.getTitle().equals(courseTitle))) {
            result.rejectValue("title", "", "Course " + courseTitle+ " already exists");
        }

        if(result.hasErrors())
            return COURSE_FORM;

        var course=modelMapper.map(courseDto,Course.class);
        courseService.save(course);
        return REDIRECT_HOME;
    }

    @GetMapping("/updateCourseForm")
    public String updateCourseForm(@RequestParam("courseId") int courseId, Model model){

        var course=courseService.findById(courseId);
        var courseDto=modelMapper.map(course,CourseDto.class);

        model.addAttribute("courseDto",courseDto);
        return COURSE_FORM;
    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") int courseId){
        courseService.deleteById(courseId);
        return REDIRECT_HOME;
    }

    @GetMapping("/addCourse")
    public String addCourseFormRedirect(){
        return "redirect:/admin/addCourseForm";
    }
}
