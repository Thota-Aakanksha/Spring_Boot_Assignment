package com.springBoot.CourseRegistration.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
public class Student {
    @Id
    @Column(name="username")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="enabled")
    private int enabled;

    @ManyToMany()
    @JoinTable(name="course_student",
            joinColumns=@JoinColumn(name="username"),
            inverseJoinColumns=@JoinColumn(name="course_id"))
    private List<Course> courses;


    public void addCourse(Course course) {
        if(courses==null)
            courses=new ArrayList<>();
        courses.add(course);
    }

}
