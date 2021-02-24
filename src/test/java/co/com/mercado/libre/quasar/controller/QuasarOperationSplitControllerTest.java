package co.com.mercado.libre.quasar.controller;

import co.com.mercado.libre.quasar.exception.SatelliteException;
import co.com.mercado.libre.quasar.model.Position;
import co.com.mercado.libre.quasar.model.QuasarOperationResponse;
import co.com.mercado.libre.quasar.model.SatelliteRequest;
import co.com.mercado.libre.quasar.service.impl.QuasarOperationSplitServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import co.com.mercado.libre.quasar.util.Sample;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuasarOperationSplitControllerTest {
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

	@Test
	public void requestSplitIsOk_ThenReturnSucces() {
		List<SatelliteRequest> requests = Sample.buildSatellitesWithSatellitesSplitList();
		quasarOperationSplitService.disableSatellitesSplitStatus();
		requests.forEach(sr ->{
				ResponseEntity<QuasarOperationResponse> response =
						quasarOperationSplitController.postTopSecretSplit(sr.getName(), sr);
				Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		});
		ResponseEntity<QuasarOperationResponse> response =
				quasarOperationSplitController.getTopSecretSplit();
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
