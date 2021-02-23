package co.com.mercado.libre.quasar.util;

import java.util.Arrays;
import java.util.List;

import co.com.mercado.libre.quasar.model.SatelliteRequest;
import co.com.mercado.libre.quasar.model.QuasarOperationRequest;
import co.com.mercado.libre.quasar.model.Position;
import co.com.mercado.libre.quasar.model.QuasarOperationResponse;
import co.com.mercado.libre.quasar.model.SatelliteSplit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Sample {

	public static QuasarOperationRequest buildQuasarOperationRequest() {
		return new QuasarOperationRequest(
				Arrays.asList(new SatelliteRequest("kenobi", 149.88, new String[] { "este", "", "", "mensaje" }),
						new SatelliteRequest("sato", 103.08, new String[] { "", "es", "", "" }),
						new SatelliteRequest("skywalker", 142.0, new String[] { "", "", "un", "" })));
	}

	public static QuasarOperationRequest buildBadQuasarOperationRequest() {
		return new QuasarOperationRequest(
				Arrays.asList(new SatelliteRequest("kenobi", 149.88, new String[] { "este", "", "", "mensaje" }),
						new SatelliteRequest("sato", 103.08, new String[] { "Y", "es", "", "" }),
						new SatelliteRequest("skywalker", 142.0, new String[] { "", "", "un" })));
	}

	public static SatelliteRequest buildSatelliteRequest() {
		return new SatelliteRequest(null, 149.88, new String[] { "este", "", "", "mensaje" });
	}

	public static List<SatelliteRequest> buildSatellitesWithSatellitesSplitList() {
		return Arrays.asList(new SatelliteRequest("kenobi", 75.0, new String[] { "este", "", "", "mensaje" }),
				new SatelliteRequest("skywalker", 75.0, new String[] { "", "es", "", "" }),
				new SatelliteRequest("Sato", 75.0, new String[] { "", "", "un", "" }));
	}

}
