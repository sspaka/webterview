package com.ssafy.webterview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public void handleException(Exception ex, HttpServletResponse response) throws IOException {
		logger.error("Exception 발생 : {}", ex.getMessage());
		response.sendRedirect("https://i7c205.p.ssafy.io/");
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void handle404(NoHandlerFoundException ex, HttpServletResponse response) throws IOException {
		logger.error("404 발생 : {}", "404 page not found");
		response.sendRedirect("https://i7c205.p.ssafy.io/");
	}
	
}

