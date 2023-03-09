package com.root.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.root.dto.DetailsError;

//Handle all catch exceptions
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// Handle all exceptions that had been detail
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<DetailsError> controlResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest wenRequest) {
		DetailsError detailsError = new DetailsError(new Date(), exception.getMessage(),
				wenRequest.getDescription(false));
		return new ResponseEntity<>(detailsError, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<DetailsError> controlGlobalException(Exception exception, WebRequest wenRequest) {
		DetailsError detailsError = new DetailsError(new Date(), exception.getMessage(),
				wenRequest.getDescription(false));
		return new ResponseEntity<>(detailsError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String campName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();

			errors.put(campName, message);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
