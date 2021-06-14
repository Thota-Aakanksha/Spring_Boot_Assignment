package com.springBoot.CourseRegistration;


import com.springBoot.CourseRegistration.dao.CourseRepository;
import com.springBoot.CourseRegistration.entities.Course;
import com.springBoot.CourseRegistration.exceptionHandling.CourseNotFoundException;
import com.springBoot.CourseRegistration.services.CourseService;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTests {

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @Test
    public void findAllTest(){
        Course testCourse1=new Course(1,"Linux Essentials","Learn linux commands","12 hours");
        Course testCourse2=new Course(2,"Networking Basics","Learn networking","8 hours");
        List<Course> courses=new ArrayList<>();
        courses.add(testCourse1);
        courses.add(testCourse2);

        when(courseRepository.findAll()).thenReturn(courses);
        assertEquals(courses,courseService.findAll());

    }

    @Test
    public void findByIdTest()  {
        Course testCourse=new Course(1,"Linux Essentials","Learn linux commands","12 hours");
        when(courseRepository.findById(testCourse.getId()))
                .thenReturn(java.util.Optional.of(testCourse));

        assertEquals(testCourse,courseService.findById(testCourse.getId()));
    }

    @Test
    public void findByIdExceptionTest(){
        int tempId=1;
        when(courseRepository.findById(tempId)).thenReturn(java.util.Optional.empty());
        Throwable exception = assertThrows( CourseNotFoundException.class, ()->courseService.findById(tempId));
        assertEquals("Course with id : "+tempId+" not found",exception.getMessage());
    }

    @Test
    public void saveTest(){
        Course testCourse=new Course(1,"Linux Essentials","Learn linux commands","12 hours");
        courseService.save(testCourse);
        verify(courseRepository,times(1)).save(testCourse);
    }

    @Test
    public void deleteByIdTest(){
        Course testCourse=new Course(1,"Linux Essentials","Learn linux commands","12 hours");
        when(courseRepository.findById(testCourse.getId())).thenReturn(java.util.Optional.of(testCourse));
        courseService.deleteById(testCourse.getId());
        verify(courseRepository,times(1)).deleteById(testCourse.getId());
    }

    @Test
    public void deleteByIdExceptionTest(){
        int tempId=1;
        when(courseRepository.findById(tempId)).thenReturn(java.util.Optional.empty());
        Throwable exception = assertThrows(CourseNotFoundException.class, ()->courseService.findById(tempId));
        assertEquals("Course with id : "+tempId+" not found",exception.getMessage());
    }

}
