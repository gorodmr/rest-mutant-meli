package co.com.mercado.libre.quasar.exception;

public class SatelliteException extends QuasarException {

	private static final long serialVersionUID = 1L;
	
	public SatelliteException(QuasarOperationErrors quasarOperationErrors) {
		super(quasarOperationErrors);
	}
	
}
