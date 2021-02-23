package co.com.mercado.libre.quasar.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import co.com.mercado.libre.quasar.model.GenericResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class QuasarExceptionHandler {

	@ExceptionHandler(QuasarException.class)
	public ResponseEntity<GenericResponseDto> handleUndeterminableLocationException(QuasarException ex) {
		GenericResponseDto response = new GenericResponseDto();
		response.setMessage(ex.getQuasarOperationErrors().getMessage());
		log.error(ex.getQuasarOperationErrors().getMessage());
		return ResponseEntity.status(ex.getQuasarOperationErrors().getStatusCode()).body(response);
	}
}
