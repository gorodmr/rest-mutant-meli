package co.com.mercado.libre.quasar.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuasarException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final QuasarOperationErrors quasarOperationErrors;
}