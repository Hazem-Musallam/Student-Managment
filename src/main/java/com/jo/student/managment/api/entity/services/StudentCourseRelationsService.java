package com.jo.student.managment.api.entity.services;

import org.springframework.stereotype.Service;

import com.jo.student.managment.api.entity.Student;
import com.jo.student.managment.api.entity.StudentCoursesRealtion;

@Service
public interface StudentCourseRelationsService {

	StudentCoursesRealtion save(StudentCoursesRealtion user);

	void removeByStudent(Student student);

	void remove(StudentCoursesRealtion courseRelation);

}
