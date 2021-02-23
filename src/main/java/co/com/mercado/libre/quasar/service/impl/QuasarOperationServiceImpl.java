package co.com.mercado.libre.quasar.service.impl;

import java.util.List;

import co.com.mercado.libre.quasar.model.Satellite;
import co.com.mercado.libre.quasar.repository.SatelliteRepository;
import org.springframework.stereotype.Component;

import co.com.mercado.libre.quasar.service.QuasarOperationService;
import co.com.mercado.libre.quasar.model.QuasarOperationRequest;
import co.com.mercado.libre.quasar.model.Position;
import co.com.mercado.libre.quasar.model.QuasarOperationResponse;
import co.com.mercado.libre.quasar.mapper.MessageMapper;
import co.com.mercado.libre.quasar.mapper.SatelliteMapper;
import co.com.mercado.libre.quasar.util.MessageProcessorUtil;
import co.com.mercado.libre.quasar.util.MultiPointTrilaterationUtil;
import lombok.AllArgsConstructor;

@Component("quasarOperationServiceImpl")
@AllArgsConstructor
public class QuasarOperationServiceImpl implements QuasarOperationService {

	private final SatelliteRepository satelliteRepository;

	@Override
	public QuasarOperationResponse getQuasarOperation(QuasarOperationRequest quasarOperationRequest) {
		List<Satellite> satellites = satelliteRepository.findAll();

		MultiPointTrilaterationUtil multiPointTrilaterationUtil = new MultiPointTrilaterationUtil(SatelliteMapper.getSatellitesCoordinates(satellites));

		Position location = multiPointTrilaterationUtil
				.getLocation(SatelliteMapper.mapSatellitesRequestToDistances(quasarOperationRequest.getSatellitesInformation(), satellites));
		String message = MessageProcessorUtil
				.getMessage(MessageMapper.mapSatellitesMessagesToArrays(quasarOperationRequest.getSatellitesInformation()));

		return new QuasarOperationResponse(location, message);
	}

}
