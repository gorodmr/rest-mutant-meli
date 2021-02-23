package co.com.mercado.libre.quasar.service;

import co.com.mercado.libre.quasar.model.SatelliteRequest;
import co.com.mercado.libre.quasar.model.QuasarOperationRequest;

public interface QuasarOperationSplitService {

	void addSatelliteSplitInformation(SatelliteRequest satelliteRequest);
	
	QuasarOperationRequest getQuasarOperationRequest();
	
	void disableSatellitesSplitStatus();
}
