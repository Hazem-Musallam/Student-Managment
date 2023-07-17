package com.jo.student.managment.api.configuration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class DataBinderClassConfig {
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		String[] denylist = new String[] { "class.*", "Class.*", "*.class.*", "*.Class.*" };
		dataBinder.setDisallowedFields(denylist);
	}

}
