package com.jo.student.managment.api.entity.repos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jo.student.managment.api.entity.Student;
import com.jo.student.managment.api.entity.StudentCoursesRealtion;

@Repository
public interface StudentCourseRelationRepo extends JpaRepository<StudentCoursesRealtion, Integer> {
	@Transactional
	void deleteByStudent(Student student);

	@Transactional
	void removeByStudentAndCourse(Student student, Student student2);

}
