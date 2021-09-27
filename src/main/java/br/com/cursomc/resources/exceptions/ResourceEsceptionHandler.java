package br.com.cursomc.resources.exceptions;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cursomc.services.exceptions.DataIntegrityException;
import br.com.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceEsceptionHandler {

	@ExceptionHandler(value = ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(
			ObjectNotFoundException e, HttpServletRequest request) {

		var error = new StandardError(HttpStatus.NOT_FOUND.value(),
				e.getMessage(), LocalDate.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(value = DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e,
			HttpServletRequest request) {

		var error = new StandardError(HttpStatus.BAD_REQUEST.value(),
				e.getMessage(), LocalDate.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> dataIntegrity(
			MethodArgumentNotValidException e, HttpServletRequest request) {

		var error = new ValidationError(HttpStatus.BAD_REQUEST.value(),
				"Erro de validação", LocalDate.now());

		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			error.addError(fieldError.getField(),
					fieldError.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
