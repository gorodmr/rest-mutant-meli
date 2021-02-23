package co.com.mercado.libre.quasar.mapper;

import java.util.ArrayList;
import java.util.List;

import co.com.mercado.libre.quasar.model.SatelliteSplit;
import co.com.mercado.libre.quasar.model.SatelliteRequest;
import co.com.mercado.libre.quasar.exception.UndeterminedLocationException;
import co.com.mercado.libre.quasar.exception.QuasarOperationErrors;
import co.com.mercado.libre.quasar.model.Satellite;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SatelliteMapper {

	public static double[][] getSatellitesCoordinates(List<Satellite> satellites) {
		double[][] result = new double[satellites.size()][2];
		for (int i = 0; i < satellites.size(); i++) {
			result[i][0] = satellites.get(i).getX();
			result[i][1] = satellites.get(i).getY();
		}
		return result;
	}

	public static double[] mapSatellitesRequestToDistances(List<SatelliteRequest> satellitesInformationRequest,
			List<Satellite> satellites) {
		double[] distance = new double[satellitesInformationRequest.size()];
		int indexToSave = 0;
		for (int i = 0; i < satellites.size(); i++) {
			boolean distanceFound = false;
			for (int j = 0; j < satellitesInformationRequest.size(); j++) {
				if (satellites.get(i).getName().equalsIgnoreCase(satellitesInformationRequest.get(j).getName())) {
					distance[indexToSave++] = satellitesInformationRequest.get(j).getDistance();
					distanceFound = true;
					break;
				}
			}
			if (!distanceFound)
				throw new UndeterminedLocationException(QuasarOperationErrors.DISTANCE_NOT_FOUND);
		}
		return distance;
	}

	public static List<SatelliteRequest> mapSatelliteSplitToSatelliteRequest(List<SatelliteSplit> satelliteSplits) {
		List<SatelliteRequest> result = new ArrayList<>();
		satelliteSplits.forEach(s -> {
			result.add(new SatelliteRequest(s.getName(), s.getDistance(), s.getMessage()));
		});
		return result;
	}
}
