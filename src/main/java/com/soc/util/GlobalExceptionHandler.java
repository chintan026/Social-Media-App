package com.soc.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorMessage> customException(CustomException ex)
	{
		ErrorMessage err = new ErrorMessage();
		err.setError(ex.getMessage());
		return ResponseEntity.badRequest().body(err);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> generalException(Exception ex)
	{
		ErrorMessage err = new ErrorMessage();
		err.setError(ex.getMessage());
		return ResponseEntity.internalServerError().body(err);
	}
}
