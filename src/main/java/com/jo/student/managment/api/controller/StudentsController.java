package com.jo.student.managment.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import com.jo.student.managment.api.entity.Course;
import com.jo.student.managment.api.entity.Student;
import com.jo.student.managment.api.entity.StudentCoursesRealtion;
import com.jo.student.managment.api.entity.services.CourseService;
import com.jo.student.managment.api.entity.services.StudentCourseRelationsService;
import com.jo.student.managment.api.entity.services.StudentService;
import com.jo.student.managment.api.exception.ErrorAndSuccessMessagges;
import com.jo.student.managment.api.exception.StudentException;
import com.jo.student.managment.api.model.response.BaseResponse;
import com.jo.student.managment.api.model.response.CourseResponse;
import com.jo.student.managment.api.model.response.StudentResponse;

@Component
public class StudentsController implements StudentsRootController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService coursesService;
	@Autowired
	private StudentCourseRelationsService relationService;

	@Value("${courseManagment.base}")
	private String basePath;

	@Override
	public BaseResponse getCourses(String token, Map<String, String> headers, HttpServletRequest request) {
		RestTemplate rest = new RestTemplate();
		BaseResponse forObject = rest.getForObject(basePath + "/v1/api/courses/", BaseResponse.class);
		return forObject;
	}

	@Override
	public BaseResponse assignStudentCourse(String token, Map<String, String> headers, HttpServletRequest request,
			int studentId, int courseId) {
		Course course = coursesService.findById(courseId);
		Student student = studentService.findById(studentId);

		if (course == null || student == null) {
			throw new StudentException(ErrorAndSuccessMessagges.COURSE_OR_STUDENT_NOT_FOUND);
		}
		failIdAddedThisCourseBefore(student, course);

		StudentCoursesRealtion stcr = StudentCoursesRealtion.builder()//
				.course(course)//
				.student(student)//
				.build();
		course.getStudents().add(stcr);
		student.getCourses().add(stcr);
		relationService.save(stcr);
		coursesService.save(course);
		studentService.save(student);
		return new BaseResponse("Course Added");
	}

	private void failIdAddedThisCourseBefore(Student student, Course course) {
		student.getCourses().stream().filter(courseRe -> {
			return courseRe.getCourse().getId().intValue() == course.getId();
		}).findAny().ifPresent(item -> {
			throw new StudentException(ErrorAndSuccessMessagges.COURSE_ADDED_BEFORE);
		});
	}

	@Override
	public BaseResponse get(String token, Map<String, String> headers, HttpServletRequest request) {

		List<Student> findAll = studentService.findAll();

		List<StudentResponse> collect = findAll.stream().map(student -> {
			return getStudentConverted(student);

		}).collect(Collectors.toList());

		return new BaseResponse(collect);
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
	public BaseResponse updateCourses(String token, @RequestHeader Map<String, String> headers,
			HttpServletRequest request, ArrayList<Integer> coursesIds, int studentid) {

		List<Course> courses = coursesIds.stream().map(id -> coursesService.findById(id)).collect(Collectors.toList());
		Student student = studentService.findById(studentid);
		List<StudentCoursesRealtion> collect = courses.stream().map(course -> {

			return StudentCoursesRealtion.builder()//
					.course(course)//
					.student(student)//
					.build();

		}).collect(Collectors.toList());
		student.getCourses().clear();

		relationService.removeByStudent(student);
		student.setCourses(collect);
		studentService.save(student);

		return new BaseResponse(getStudentConverted(student));
	}

	@Override
	public BaseResponse delete(String token, Map<String, String> headers, HttpServletRequest request, int studentId,
			int courseId) {
		Course course = coursesService.findById(courseId);
		Student student = studentService.findById(studentId);
		if (course == null || student == null) {
			throw new StudentException(ErrorAndSuccessMessagges.COURSE_OR_STUDENT_NOT_FOUND);
		}
		StudentCoursesRealtion orElseThrow = student.getCourses().stream().filter(courseRelation -> {
			return courseRelation.getCourse().getId().intValue() == course.getId();
		}).findAny().orElseThrow(() -> {
			throw new StudentException(ErrorAndSuccessMessagges.COURSE_OR_STUDENT_NOT_FOUND);
		});

		student.getCourses().removeIf(courseRelation -> {
			return courseRelation.getId() == orElseThrow.getId();
		});
		studentService.save(student);
		relationService.remove(orElseThrow);
		return get(token, headers, request);
	}

}