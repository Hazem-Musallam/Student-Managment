package com.jo.student.managment.api.entity.services.imps;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jo.student.managment.api.entity.Student;
import com.jo.student.managment.api.entity.repos.StudentRepo;
import com.jo.student.managment.api.entity.services.StudentService;

@Service
public class StudentServiceImp implements StudentService {
	@Autowired
	private StudentRepo repo;

	@Override
	public Student findById(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Student save(Student user) {
		return repo.save(user);
	}

	@Override
	public List<Student> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Student> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public void delete(Student course) {
		repo.delete(course);
	}

	@Override
	public Student findByEmail(String email) {
		// TODO Auto-generated method stub
		return repo.findByEmail(email);
	}

}
