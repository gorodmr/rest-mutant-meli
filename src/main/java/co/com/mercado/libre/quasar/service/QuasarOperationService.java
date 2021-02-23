package co.com.mercado.libre.quasar.service;

import co.com.mercado.libre.quasar.model.QuasarOperationRequest;
import co.com.mercado.libre.quasar.model.QuasarOperationResponse;

public interface QuasarOperationService {

	QuasarOperationResponse getQuasarOperation(QuasarOperationRequest quasarOperationRequest);
}
