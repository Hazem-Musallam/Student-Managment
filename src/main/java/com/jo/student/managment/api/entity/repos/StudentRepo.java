package com.jo.student.managment.api.entity.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jo.student.managment.api.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	Student findByEmail(String email);

}
