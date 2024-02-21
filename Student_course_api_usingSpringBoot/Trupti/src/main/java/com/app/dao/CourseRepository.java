package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByTitle(String title);
}
