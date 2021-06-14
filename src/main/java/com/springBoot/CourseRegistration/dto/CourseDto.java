package com.springBoot.CourseRegistration.dto;

import com.springBoot.CourseRegistration.entities.Student;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CourseDto {
    private int id;

    @NotNull(message = "Title Required")
    @Size(min = 1,message = "Title Required")
    private String title;

    private String duration;
    private String description;
    private List<Student> students;
}
