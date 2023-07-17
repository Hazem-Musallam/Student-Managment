package com.jo.student.managment.api.exception;

public enum ErrorAndSuccessMessagges {
//TODO here you can either Get them via resource Bundle or any other Way
	COURSE_OR_STUDENT_NOT_FOUND("Your Have Wrong Inputs ", "ادخال حاطئ", "4001"), //
	COURSE_ADDED_BEFORE("Course Added Before", "النهج مضاف مسبقا", "4002"),
	UN_AUTHORISED("Not Authorised", "غير مخول", "401"), USER_EXIST("User Exist", "المستخدم موجود", "401"),
	WRONG_DATA("Your Have Wrong Inputs ", "ادخال حاطئ", "4005"); //

	private String id;
	private String messageEn;
	private String messageAr;

	ErrorAndSuccessMessagges(String errorMessage, String messageAr, String id) {
		this.id = id;
		this.messageEn = errorMessage;
		this.messageAr = messageAr;
	}

	public String getId() {
		return id;
	}

	public String getMessageEn() {
		return messageEn;
	}

	public String getMessageAr() {
		return messageAr;
	}

}
