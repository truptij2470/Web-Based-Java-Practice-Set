package com.app.service;

import java.util.List;

import com.app.pojos.Course;
import com.app.pojos.Student;

public interface CourseService {
    String launchNewCourse(Course course);

    String addStudent(Student student);

    List<Student> viewAllStudents(String courseTitle);

    String updateCourseFees(Long courseId, double fees);

    String cancelStudentAdmission(Long courseId, Long studentId);
}
