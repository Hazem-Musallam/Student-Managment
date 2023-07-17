package com.jo.student.managment.api.exception.advisers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Errors {

	private String errorCode;
	private String message;
	private String status;
}
