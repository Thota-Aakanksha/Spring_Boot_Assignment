package com.springBoot.CourseRegistration.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="course")
@NoArgsConstructor
public class Course {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Getter @Setter
    @Column(name="title")
    private String title;

    @Getter @Setter
    @Column(name="duration")
    private String duration;

    @Getter @Setter
    @Column(name="rating")
    private double rating;

    @ManyToMany()
    @JoinTable(name="course_student",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="username"))
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student user) {
        if(students==null)
            students=new ArrayList<>();
        students.add(user);
    }
}
