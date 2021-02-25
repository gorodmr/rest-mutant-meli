package co.com.mercado.libre.quasar.service.impl;

import java.util.List;

import co.com.mercado.libre.quasar.exception.SatelliteException;
import co.com.mercado.libre.quasar.exception.QuasarOperationErrors;
import co.com.mercado.libre.quasar.model.Satellite;
import co.com.mercado.libre.quasar.model.SatelliteSplit;
import co.com.mercado.libre.quasar.repository.SatelliteRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import co.com.mercado.libre.quasar.service.QuasarOperationSplitService;
import co.com.mercado.libre.quasar.model.SatelliteRequest;
import co.com.mercado.libre.quasar.model.QuasarOperationRequest;
import co.com.mercado.libre.quasar.mapper.SatelliteMapper;

import static java.util.Objects.isNull;

@Primary
@Component("quasarOperationSplitServiceImpl")
public class QuasarOperationSplitServiceImpl extends QuasarOperationServiceImpl implements QuasarOperationSplitService {

	private SatelliteRepository satelliteRepository;

	private static List<SatelliteSplit> satellitesSplit;

	public QuasarOperationSplitServiceImpl(SatelliteRepository satelliteRepository) {
		super(satelliteRepository);
		this.satelliteRepository = satelliteRepository;
		satellitesSplit = this.satelliteRepository.getSatellitesSplitList();
	}

	@Override
	public void addSatelliteSplitInformation(SatelliteRequest satelliteRequest) {
		List<Satellite> satellites = satelliteRepository.findAll();

		Satellite satelliteResult = satellites.stream().filter(s -> s.getName().equalsIgnoreCase(satelliteRequest.getName())).findAny().orElse(null);
		if (isNull(satelliteResult))
			throw new SatelliteException(QuasarOperationErrors.SATELLITE_NOT_FOUND);

		SatelliteSplit satelliteSplit = satelliteRepository.getSatelliteSplit(satellitesSplit, satelliteRequest.getName());

		if (satelliteSplit.isActive())
			throw new SatelliteException(QuasarOperationErrors.DUPLICATE_SATELLITE_INFORMATION);
		satelliteSplit.setMessage(satelliteRequest.getMessage());
		satelliteSplit.setDistance(satelliteRequest.getDistance());
		satelliteSplit.setActive(Boolean.TRUE);

	}

	@Override
	public QuasarOperationRequest getQuasarOperationRequest() {
		return new QuasarOperationRequest(SatelliteMapper.mapSatelliteSplitToSatelliteRequest(satellitesSplit));
	}

	@Override
	public void disableSatellitesSplitStatus() {
		satellitesSplit.forEach(s -> {
			s.setActive(Boolean.FALSE);
		});
	}

	public static List<SatelliteSplit> getSatellitesSplit() {
		return satellitesSplit;
	}

}
