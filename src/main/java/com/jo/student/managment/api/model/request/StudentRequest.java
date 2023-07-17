package com.jo.student.managment.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
	private String name;
	private String nameAr;
	private String email;
	private String telephone;
	private String address;

}
