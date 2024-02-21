package com.app.service;

import org.springframework.stereotype.Service;

import com.app.dao.CourseRepository;
import com.app.dao.StudentRepository;
import com.app.pojos.Course;
import com.app.pojos.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public String launchNewCourse(Course course) {
        courseRepository.save(course);
        return "Course launched successfully!";
    }

    @Override
    public String addStudent(Student student) {
        // Check if the student's score is greater than or equal to the course's minimum score
        if (student.getScore() >= student.getCourse().getMinScore()) {
            studentRepository.save(student);
            return "Student added successfully!";
        } else {
            return "Admission not granted. Score is below minimum.";
        }
    }

    @Override
    public List<Student> viewAllStudents(String courseTitle) {
        Course course = courseRepository.findByTitle(courseTitle);
        if (course != null) {
            return studentRepository.findByCourse_Title(courseTitle);
        } else {
            // Handle case when the course with the given title is not found
            return null;
        }
    }

    @Override
    public String updateCourseFees(Long courseId, double fees) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            course.setFees(fees);
            courseRepository.save(course);
            return "Course fees updated successfully!";
        } else {
            // Handle case when the course with the given ID is not found
            return "Course not found!";
        }
    }

    @Override
    public String cancelStudentAdmission(Long courseId, Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null && student.getCourse().getId().equals(courseId)) {
            studentRepository.deleteById(studentId);
            return "Student admission canceled successfully!";
        } else {
            // Handle case when the student with the given ID or course ID is not found
            return "Student or course not found!";
        }
    }
}

