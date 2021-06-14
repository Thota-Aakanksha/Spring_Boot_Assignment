package com.springBoot.CourseRegistration.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="course")
@NoArgsConstructor
@Getter @Setter
public class Course {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @NotNull(message = "Title Required")
    @Size(min = 1,message = "Title Required")
    @Column(name="title")
    private String title;

    @Column(name="duration")
    private String duration;

    @Column(name="description")
    private String description;

    @ManyToMany()
    @JoinTable(name="course_student",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="username"))
    private List<Student> students;

    public Course(int id, String title, String duration, String description) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.description = description;
    }
}
