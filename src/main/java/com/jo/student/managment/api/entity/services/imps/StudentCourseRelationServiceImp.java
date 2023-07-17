package com.jo.student.managment.api.entity.services.imps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jo.student.managment.api.entity.Student;
import com.jo.student.managment.api.entity.StudentCoursesRealtion;
import com.jo.student.managment.api.entity.repos.StudentCourseRelationRepo;
import com.jo.student.managment.api.entity.services.StudentCourseRelationsService;

@Service
public class StudentCourseRelationServiceImp implements StudentCourseRelationsService {
	@Autowired
	private StudentCourseRelationRepo repo;

	@Override
	public StudentCoursesRealtion save(StudentCoursesRealtion entity) {
		return repo.save(entity);
	}

	@Override
	public void removeByStudent(Student student) {
		repo.deleteByStudent(student);
	}

	@Override
	public void remove(StudentCoursesRealtion courseRelation) {
		repo.deleteById(courseRelation.getId());
	}

}
