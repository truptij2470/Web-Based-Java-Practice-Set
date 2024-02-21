package com.app.controller;

import org.springframework.web.bind.annotation.*;

import com.app.pojos.Course;
import com.app.pojos.Student;
import com.app.service.CourseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.app.pojos.Course;
import com.app.pojos.Student;
import com.app.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public String launchNewCourse(@RequestBody Course course) {
        return courseService.launchNewCourse(course);
    }

    @PostMapping("/students")
    public String addStudent(@RequestBody Student student) {
        return courseService.addStudent(student);
    }

    @GetMapping("/students/course_title/{courseTitle}")
    public List<Student> viewAllStudents(@PathVariable String courseTitle) {
        return courseService.viewAllStudents(courseTitle);
    }

    @PutMapping("/{courseId}/fees/{fees}")
    public String updateCourseFees(@PathVariable Long courseId, @PathVariable double fees) {
        return courseService.updateCourseFees(courseId, fees);
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public String cancelStudentAdmission(@PathVariable Long courseId, @PathVariable Long studentId) {
        return courseService.cancelStudentAdmission(courseId, studentId);
    }
}
