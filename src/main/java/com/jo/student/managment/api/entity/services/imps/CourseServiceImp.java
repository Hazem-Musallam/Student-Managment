package com.jo.student.managment.api.entity.services.imps;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jo.student.managment.api.entity.Course;
import com.jo.student.managment.api.entity.repos.CoursesRepo;
import com.jo.student.managment.api.entity.services.CourseService;

@Service
public class CourseServiceImp implements CourseService {
	@Autowired
	private CoursesRepo repo;

	@Override
	public Course findById(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Course save(Course user) {
		return repo.save(user);
	}

	@Override
	public List<Course> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Course> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public void delete(Course course) {
		if (course.getStudents().size() > 0) {
			course.setDeleted(true);
		} else {
			repo.delete(course);
		}

	}

}
