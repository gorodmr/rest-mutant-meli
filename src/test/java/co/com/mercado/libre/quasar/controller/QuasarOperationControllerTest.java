package co.com.mercado.libre.quasar.controller;

import co.com.mercado.libre.quasar.exception.QuasarOperationErrors;
import co.com.mercado.libre.quasar.exception.UndeterminableMessageException;
import co.com.mercado.libre.quasar.model.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.mercado.libre.quasar.util.Sample;
import co.com.mercado.libre.quasar.service.impl.QuasarOperationServiceImpl;
import co.com.mercado.libre.quasar.model.QuasarOperationResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuasarOperationControllerTest {
	
	@InjectMocks
	private QuasarOperationController quasarOperationController;

	@Mock
	@Qualifier(value = "quasarOperationServiceImpl")
	QuasarOperationServiceImpl quasarOperationService;
	
	@Before
	public void setup() {
		quasarOperationController = new QuasarOperationController(quasarOperationService);
	}
	
	@Test
	public void requestIsOk_ThenReturnSucces() {
		Mockito.when(quasarOperationService.getQuasarOperation(Mockito.any())).
				thenReturn(new QuasarOperationResponse(new Position(1.0, 1.0), "este es un mensaje"));
		ResponseEntity<QuasarOperationResponse> response =
				quasarOperationController.getTopsecret(Sample.buildQuasarOperationRequest());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test(expected = UndeterminableMessageException.class)
	public void requestIsBad_ThenReturnException() {
		Mockito.when(quasarOperationService.getQuasarOperation(Mockito.any())).
				thenThrow(new UndeterminableMessageException(QuasarOperationErrors.DIFFERENT_ARRAY_LENGTH));
		ResponseEntity<QuasarOperationResponse> response =
				quasarOperationController.getTopsecret(Sample.buildBadQuasarOperationRequest());
	}
}
