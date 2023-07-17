package com.jo.student.managment.api.entity.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jo.student.managment.api.entity.Student;

@Service
public interface StudentService {
	Student findById(Integer id);

	Student save(Student user);

	List<Student> findAll();

	Page<Student> findAll(Pageable pageable);

	void deleteById(Integer id);

	void delete(Student course);

	Student findByEmail(String email);

}
