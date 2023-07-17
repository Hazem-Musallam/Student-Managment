package com.jo.student.managment.api.entity.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jo.student.managment.api.entity.Course;

@Service
public interface CourseService {
	Course findById(Integer id);

	Course save(Course user);

	List<Course> findAll();

	Page<Course> findAll(Pageable pageable);

	void deleteById(Integer id);

	void delete(Course course);

}
