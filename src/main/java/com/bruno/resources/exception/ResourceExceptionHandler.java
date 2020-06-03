package com.bruno.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.bruno.service.exception.AuthorizationException;
import com.bruno.service.exception.DataIntegrityException;
import com.bruno.service.exception.FileException;
import com.bruno.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request) {

		StandardError erro = new StandardError(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException ex, HttpServletRequest request) {

		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException ex, HttpServletRequest request) {

		ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Error de  validação",
				System.currentTimeMillis());

		for (FieldError erro : ex.getBindingResult().getFieldErrors()) {

			validationError.addError(erro.getField(), erro.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException ex, HttpServletRequest request) {

		StandardError erro = new StandardError(HttpStatus.FORBIDDEN.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException ex, HttpServletRequest request) {

		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException ex, HttpServletRequest request) {
		
		HttpStatus code = HttpStatus.valueOf(ex.getErrorCode());
		StandardError erro = new StandardError(code.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(code).body(erro);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException ex, HttpServletRequest request) {
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonClientException ex, HttpServletRequest request) {
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
