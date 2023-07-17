package com.jo.student.managment.api.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jo.student.managment.api.entity.Student;
import com.jo.student.managment.api.model.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/v1/api/")
public interface StudentsRootController {

	@Operation(summary = "Get Courses", description = "This api to Get a Courses", tags = { "Student Action" })
	@GetMapping(value = "/courses", produces = { "application/json" })
	public BaseResponse getCourses(@RequestHeader("token") String token, @RequestHeader Map<String, String> headers,
			HttpServletRequest request);

	@Operation(summary = "get All Student with courses ", description = "This api to get all Student Courses", tags = {
			"Student Action" })
	@GetMapping(value = "/students/all", produces = { "application/json" })
	public BaseResponse get(@RequestHeader("token") String token, @RequestHeader Map<String, String> headers,
			HttpServletRequest request);

	@Operation(summary = "Add Student Courses ", description = "This api to add a Student Courses", tags = {
			"Student Action" })
	@PostMapping(value = "students/assign-course", produces = { "application/json" })
	public BaseResponse assignStudentCourse(@RequestHeader("token") String token,
			@RequestHeader Map<String, String> headers, HttpServletRequest request,
			@RequestParam(name = "student-id") int studentId, @RequestParam(name = "course-id") int courseId);

	@Operation(summary = "update Student  courses ", description = "This api to update all Student Courses", tags = {
			"Student Action" })
	@PutMapping(value = "students/{id}/courses", produces = { "application/json" })
	public BaseResponse updateCourses(@RequestHeader("token") String token, @RequestHeader Map<String, String> headers,
			HttpServletRequest request, @RequestBody ArrayList<Integer> courses,
			@PathVariable(name = "id") int student);

	@Operation(summary = "Delete Student Courses ", description = "This api to Delete Student Course", tags = {
			"Student Action" })
	@DeleteMapping(value = "students/delete-course", produces = { "application/json" })
	public BaseResponse delete(@RequestHeader("token") String token, @RequestHeader Map<String, String> headers,
			HttpServletRequest request, @RequestParam(name = "student-id") int student,
			@RequestParam(name = "course-id") int course);

}
