package com.meli.mutant.exception;

import com.meli.mutant.model.GenericResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class MutantExceptionHandler {

	@ExceptionHandler(MutantException.class)
	public ResponseEntity<GenericResponseDto> handleUndeterminableLocationException(MutantException ex) {
		GenericResponseDto response = new GenericResponseDto();
		response.setMessage(ex.getMutantErrors().getMessage());
		log.error(ex.getMutantErrors().getMessage());
		return ResponseEntity.status(ex.getMutantErrors().getStatusCode()).body(response);
	}
}
