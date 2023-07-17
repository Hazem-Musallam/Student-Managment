package com.jo.student.managment.api.controller;

import java.time.Instant;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.jo.student.managment.api.model.response.BaseResponse;

@Aspect
@Component
public class ContollerAdviser {

	/**
	 * Run after the method returned a result, intercept the returned result as
	 * well.
	 * 
	 * @param joinPoint
	 * @param result
	 */
	@SuppressWarnings("unused")
	@AfterReturning(pointcut = "within(com.jo.managment.api.controller.*)", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		HttpServletRequest request = null;
		HttpServletResponse response = null;

		for (Object arg : joinPoint.getArgs()) {
			if (arg instanceof HttpServletRequest) {
				request = (HttpServletRequest) arg;
				Map<String, String[]> parameterMap = request.getParameterMap();
				for (Entry<String, String[]> entry : parameterMap.entrySet()) {
					System.out.println("key-> " + entry.getKey());
					String[] value = entry.getValue();
					for (String string : value) {
						System.out.println("Key -> " + entry + " value -> " + string);
					}

				}

			}

			if (arg instanceof HttpServletResponse) {
				response = (HttpServletResponse) arg;
			}
		}

		if (request != null) {
			BaseResponse baseResponse = (BaseResponse) result;
			baseResponse.setPath(request.getServletPath());
			baseResponse.setStatus(200);
			baseResponse.setTimestamp(Instant.now());
		}
	}
}