package com.jo.student.managment.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jo.student.managment.api.model.request.StudentRequest;
import com.jo.student.managment.api.model.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("public/v1/api/students")
public interface PublicStudentRootController {

	@Operation(summary = "Register Student", description = "This api to add a Student", tags = { "Student Action" })
	@PostMapping(value = "/", produces = { "application/json" })
	public BaseResponse add(@RequestHeader Map<String, String> headers, HttpServletRequest request,
			@RequestBody StudentRequest studentRequest);

	@Operation(summary = "Generate Token For Testing Purposes", description = "This api to Generate Token for testing a Student", tags = {
			"Student Action" })
	@GetMapping(value = "/generate", produces = { "application/json" })
	public BaseResponse generateToken(@RequestHeader Map<String, String> headers, HttpServletRequest request,
			@RequestParam int studentId);

}
