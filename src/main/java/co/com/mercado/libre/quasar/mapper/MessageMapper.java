package co.com.mercado.libre.quasar.mapper;

import java.util.List;

import co.com.mercado.libre.quasar.model.SatelliteRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageMapper {

	public static String[][] mapSatellitesMessagesToArrays(List<SatelliteRequest> satellites) {
		String[][] messagesMatrix = new String[satellites.size()][];
		for (int i = 0; i < satellites.size(); i++) {
			messagesMatrix[i] = satellites.get(i).getMessage();
		}
		return messagesMatrix;
	}

}
