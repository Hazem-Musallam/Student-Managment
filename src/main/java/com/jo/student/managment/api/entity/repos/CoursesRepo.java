package com.jo.student.managment.api.entity.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jo.student.managment.api.entity.Course;

@Repository
public interface CoursesRepo extends JpaRepository<Course, Integer> {

}
