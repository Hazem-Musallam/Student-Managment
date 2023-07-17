package com.jo.student.managment.api.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jo.student.managment.api.entity.Student;
import com.jo.student.managment.api.entity.StudentCoursesRealtion;
import com.jo.student.managment.api.entity.services.StudentService;
import com.jo.student.managment.api.exception.ErrorAndSuccessMessagges;
import com.jo.student.managment.api.exception.StudentException;
import com.jo.student.managment.api.model.request.StudentRequest;
import com.jo.student.managment.api.model.response.BaseResponse;
import com.jo.student.managment.api.model.response.CourseResponse;
import com.jo.student.managment.api.model.response.StudentResponse;
import com.jo.student.managment.security.JwtTokenProvider;

@Component
public class PublicStudentsController implements PublicStudentRootController {
	@Autowired
	private StudentService studentService;

	@Value("${courseManagment.base}")
	private String basePath;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public BaseResponse add(Map<String, String> headers, HttpServletRequest request, StudentRequest studentRequest) {
		if (!isvalidRequest(studentRequest)) {
			throw new StudentException(ErrorAndSuccessMessagges.WRONG_DATA);
		}
		String email = studentRequest.getEmail();
		Student s = studentService.findByEmail(email);
		if (s != null) {
			throw new StudentException(ErrorAndSuccessMessagges.USER_EXIST);
		}
		Student student = Student.builder()//
				.nameArabic(studentRequest.getNameAr())//
				.nameEnglish(studentRequest.getName())//
				.address(studentRequest.getAddress())//
				.courses(new ArrayList<StudentCoursesRealtion>())//
				.phoneNumber(studentRequest.getTelephone())//
				.email(email).build();

		Student save = studentService.save(student);

		String generateToken = jwtTokenProvider.generateToken(student.getEmail());

		return new BaseResponse(getStudentConverted(student, generateToken));
	}

	private boolean isvalidRequest(StudentRequest studentRequest) {
		// TODO Auto-generated method stub
		return studentRequest != null && studentRequest.getEmail() != null && studentRequest.getEmail().isEmpty();
	}

	private Object getStudentConverted(Student student, String generateToken) {
		StudentResponse studentConverted = getStudentConverted(student);
		studentConverted.setToken(generateToken);
		return studentConverted;
	}

	private StudentResponse getStudentConverted(Student student) {
		return StudentResponse.builder()//
				.name(student.getNameEnglish())//
				.nameAr(student.getNameArabic())//
				.username(student.getEmail())//
				.id(student.getId())//
				.courses(student.getCourses().stream().map(courseRelation -> {
					return getCourseConverted(courseRelation);

				}).collect(Collectors.toList())).build();
	}

	private CourseResponse getCourseConverted(StudentCoursesRealtion courseRelation) {
		return CourseResponse.builder()//
				.courseName(courseRelation.getCourse().getCourseName())//
				.courseNumber(courseRelation.getCourse().getNumber())//
				.id(courseRelation.getCourse().getId())//
				.build();
	}

	@Override
	public BaseResponse generateToken(Map<String, String> headers, HttpServletRequest request, int studentId) {
		Student findById = studentService.findById(studentId);
		if (findById == null) {
			throw new StudentException(ErrorAndSuccessMessagges.COURSE_OR_STUDENT_NOT_FOUND);
		}

		String generateToken = jwtTokenProvider.generateToken(findById.getEmail());

		return new BaseResponse(getStudentConverted(findById, generateToken));
	}
}