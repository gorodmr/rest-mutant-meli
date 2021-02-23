package co.com.mercado.libre.quasar.controller;

import co.com.mercado.libre.quasar.exception.SatelliteException;
import co.com.mercado.libre.quasar.model.QuasarOperationResponse;
import co.com.mercado.libre.quasar.service.impl.QuasarOperationSplitServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import co.com.mercado.libre.quasar.util.Sample;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuasarOperationSplitIntegrationTest {
	@InjectMocks
	private QuasarOperationSplitController quasarOperationSplitController;

	@Autowired
	QuasarOperationSplitServiceImpl quasarOperationSplitService;

	@Before
	public void setup() {
		quasarOperationSplitController = new QuasarOperationSplitController(quasarOperationSplitService);
	}

	@Test
	public void requestIsOk_ThenReturnSucces() {
		ResponseEntity<QuasarOperationResponse> response =
				quasarOperationSplitController.postTopSecretSplit("Sato", Sample.buildSatelliteRequest());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test(expected = SatelliteException.class)
	public void requestIsBad_ThenReturnException() {
		ResponseEntity<QuasarOperationResponse> response =
				quasarOperationSplitController.postTopSecretSplit("Nave desconocida", Sample.buildSatelliteRequest());
	}

}
