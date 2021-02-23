package co.com.mercado.libre.quasar.exception;

public class UndeterminedLocationException extends QuasarException {

	private static final long serialVersionUID = 1L;
	
	public UndeterminedLocationException(QuasarOperationErrors quasarOperationErrors) {
		super(quasarOperationErrors);
	}
}
