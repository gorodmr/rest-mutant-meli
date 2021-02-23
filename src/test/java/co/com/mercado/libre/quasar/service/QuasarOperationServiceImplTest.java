package co.com.mercado.libre.quasar.service;

import co.com.mercado.libre.quasar.exception.UndeterminableMessageException;
import co.com.mercado.libre.quasar.model.QuasarOperationRequest;
import co.com.mercado.libre.quasar.model.QuasarOperationResponse;
import co.com.mercado.libre.quasar.model.SatelliteRequest;
import co.com.mercado.libre.quasar.service.impl.QuasarOperationServiceImpl;
import co.com.mercado.libre.quasar.repository.SatelliteRepository;
import co.com.mercado.libre.quasar.service.impl.QuasarOperationSplitServiceImpl;
import co.com.mercado.libre.quasar.util.Sample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuasarOperationServiceImplTest {
	@InjectMocks
	private QuasarOperationServiceImpl quasarOperationService;

	@Autowired
	private QuasarOperationSplitServiceImpl quasarOperationSplitService;

	@Autowired
	private SatelliteRepository satelliteRepository;
	
	@Before
	public void setup() {

		quasarOperationService = new QuasarOperationServiceImpl(satelliteRepository);
		quasarOperationSplitService = new QuasarOperationSplitServiceImpl(satelliteRepository);
	}
	
	@Test
	public void whenRequestIsOk_ThenReturnQuasarOperationResponse() {
		QuasarOperationResponse response = quasarOperationService.getQuasarOperation(Sample.buildQuasarOperationRequest());
		Assert.assertEquals("este es un mensaje", response.getMessage());
	}

	@Test(expected = UndeterminableMessageException.class)
	public void whenRequestMessageIsBad_ThenThorwException() {
		QuasarOperationResponse response = quasarOperationService.getQuasarOperation(Sample.buildBadQuasarOperationRequest());
	}

	@Test
	public void whenThreeRequestAreOk_And_GetQuasarOperation_ThenReturnQuasarOperationResponse() {
		List<SatelliteRequest> satellitesRequestList = Sample.buildSatellitesWithSatellitesSplitList();
		satellitesRequestList.forEach(s ->
				quasarOperationSplitService.addSatelliteSplitInformation(s)
		);
		QuasarOperationRequest request = quasarOperationSplitService.getQuasarOperationRequest();

		QuasarOperationResponse response = quasarOperationSplitService.getQuasarOperation(request);
		Assert.assertEquals("este es un mensaje", response.getMessage());
	}
}
