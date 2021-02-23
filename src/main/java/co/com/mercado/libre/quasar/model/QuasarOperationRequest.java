package co.com.mercado.libre.quasar.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuasarOperationRequest {

	private List<SatelliteRequest> satellitesInformation;
}
