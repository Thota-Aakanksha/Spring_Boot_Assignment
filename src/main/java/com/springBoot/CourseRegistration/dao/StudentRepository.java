package com.springBoot.CourseRegistration.dao;

import com.springBoot.CourseRegistration.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
}
