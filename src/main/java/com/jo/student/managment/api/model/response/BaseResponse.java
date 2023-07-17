package com.jo.student.managment.api.model.response;

import java.time.Instant;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jo.student.managment.api.exception.advisers.Errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class BaseResponse {
	public BaseResponse(Object data) {
		this.data = data;
	}

	private Object data;

	private Instant timestamp;

	private int status;

	private int responseCode;

	private String message;

	private String path;
	private boolean success;
	private Errors error;

}