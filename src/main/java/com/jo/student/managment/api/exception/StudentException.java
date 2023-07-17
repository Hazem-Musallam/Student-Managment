package com.jo.student.managment.api.exception;

public class StudentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorAndSuccessMessagges error;

	public StudentException(ErrorAndSuccessMessagges error) {
		this.error = (error);
	}

	public ErrorAndSuccessMessagges getError() {
		return error;
	}

}
