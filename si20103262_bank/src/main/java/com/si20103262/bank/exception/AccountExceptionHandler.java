package com.si20103262.bank.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AccountExceptionHandler  extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = AccountNotFoundException.class)
	public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException exception, WebRequest request) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(value = InvalidAccountNumberException.class)
	public ResponseEntity<Object> handleInvalidAccountNumberException(InvalidAccountNumberException exception, WebRequest request) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		
		ErrorResponse error = new ErrorResponse("Validation Failed", details);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
