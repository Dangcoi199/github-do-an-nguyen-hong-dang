package com.nguyenhongdang.error;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorAdvice {
	/*
	 * @ExceptionHandler(NotFoundException.class)
	 * 
	 * @ResponseStatus(code = HttpStatus.NOT_FOUND) public ApiError
	 * handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
	 * ApiError error = new ApiError(400, ex.getMessage(),
	 * request.getServletPath()); return error; }
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiError handleMethodArgNotValid(MethodArgumentNotValidException exception , HttpServletRequest request) {
		ApiError error = new ApiError(400, exception.getMessage(), request.getServletPath());
		BindingResult bindingResult = exception.getBindingResult();
		Map<String, String> validationErrors = new HashMap<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
		}
		error.setValidationErrors(validationErrors);
		return error;
	}
	
}
