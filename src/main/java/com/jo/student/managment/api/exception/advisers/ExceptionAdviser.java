package com.jo.student.managment.api.exception.advisers;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jo.student.managment.api.exception.ErrorAndSuccessMessagges;
import com.jo.student.managment.api.exception.StudentException;
import com.jo.student.managment.api.model.response.BaseResponse;

@ControllerAdvice
public class ExceptionAdviser extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { StudentException.class, })
	protected ResponseEntity<Object> handleInternal5GException(RuntimeException ex, HttpServletRequest request) {
		StudentException exCasted = (StudentException) ex;
		ErrorAndSuccessMessagges error = exCasted.getError();
		String language = request.getParameter("language");

		Errors errorResponse = new Errors();
		errorResponse.setErrorCode(error.getId());
		errorResponse.setMessage(
				language != null && language.equalsIgnoreCase("ar") ? error.getMessageAr() : error.getMessageEn());
		errorResponse.setStatus(error.name());

		BaseResponse response = new BaseResponse();
		response.setError(errorResponse);
		response.setMessage("failed");
		response.setStatus(400);
		response.setTimestamp(Instant.now());
		response.setPath(request.getContextPath());
		response.setResponseCode(400);

		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

}