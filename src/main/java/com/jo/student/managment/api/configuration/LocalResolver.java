package com.jo.student.managment.api.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Component
public class LocalResolver extends AcceptHeaderLocaleResolver {

	private static final List<Locale> LOCALES = Arrays.asList(new Locale("en"), new Locale("ar"));

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String language = request.getParameter("language");
		if (language == null || language.isEmpty()) {
			language = request.getHeader("Accept-Language");
			if (language == null || language.isEmpty()) {
				language = request.getParameter("Language");
				if (language == null || language.isEmpty()) {
					return Locale.getDefault();
				}
			}
		}
		List<Locale.LanguageRange> list = Locale.LanguageRange.parse(language);
		Locale locale = Locale.lookup(list, LOCALES);
		LocaleContextHolder.setDefaultLocale(locale);
		return locale;
	}

}