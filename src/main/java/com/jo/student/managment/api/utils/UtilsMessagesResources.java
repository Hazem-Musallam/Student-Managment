package com.jo.student.managment.api.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

@Component
public class UtilsMessagesResources {
	@Autowired
	@Qualifier("textsResourceBundleMessageSource")
	private ResourceBundleMessageSource resoucre;

	@Autowired
	private LocaleResolver localeResolver;

	public String translate(String errorMessage, HttpServletRequest request) {
		return resoucre.getMessage(errorMessage, null, localeResolver.resolveLocale(request));
	}

	public static HttpServletRequest getCurrentHttpRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			return request;
		}
		return null;
	}

}
