package com.cgs.task1.bank.register.customexception;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler { // Custom Exception

	@ExceptionHandler(NoSuchDataException.class) // Custom Exception method
	public ResponseEntity<String> NoSuchDataTypeExceptionHandler(NoSuchDataException ex) {
		return new ResponseEntity<String>("pls enter Data...", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class) // Invalid details handler method
	ResponseEntity<Set<String>> handleConstraintViolation(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

		Set<String> messages = new HashSet<>(constraintViolations.size());
		messages.addAll(constraintViolations.stream()
				.map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
						constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
				.collect(Collectors.toList()));

		return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);

	}

}
