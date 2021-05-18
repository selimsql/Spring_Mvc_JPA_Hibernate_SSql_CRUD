package com.selimsql.lesson.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.selimsql.lesson.util.Util;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final String VIEW_exception = "exception";

	@ExceptionHandler(CustomRuntimeException.class)
	public ModelAndView handleCustomRuntimeException(CustomRuntimeException ex) {
		ModelAndView model = new ModelAndView(VIEW_exception);
		model.addObject("errorCode", ex.getErrorCode());
		model.addObject("errorMessage", ex.getErrorMessage());

		return model; //goto VIEW_exception page!
	}

	//Ex1: org.hibernate.ObjectNotFoundException:
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView model = new ModelAndView(VIEW_exception);
		model.addObject("errorCode", "GLB: " + ex.getClass().getSimpleName());
		model.addObject("errorMessage", Util.getErrorMessage(ex));

		return model; //goto VIEW_exception page!
	}
}
