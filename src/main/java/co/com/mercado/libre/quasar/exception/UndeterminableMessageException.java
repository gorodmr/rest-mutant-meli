package co.com.mercado.libre.quasar.exception;

public class UndeterminableMessageException extends QuasarException {

	private static final long serialVersionUID = 1L;
	
	public UndeterminableMessageException(QuasarOperationErrors quasarOperationErrors) {
		super(quasarOperationErrors);
	}
	
}
