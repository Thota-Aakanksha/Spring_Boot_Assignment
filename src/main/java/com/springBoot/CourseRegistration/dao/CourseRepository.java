package com.springBoot.CourseRegistration.dao;

import com.springBoot.CourseRegistration.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {

}
