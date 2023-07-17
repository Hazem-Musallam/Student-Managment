package com.jo.student.managment.api.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
	private int id;
	private String username;
	private String name;
	private String nameAr;
	private List<CourseResponse> courses;
	private String token;
}
