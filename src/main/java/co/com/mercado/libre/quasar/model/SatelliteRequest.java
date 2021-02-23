package co.com.mercado.libre.quasar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteRequest {

	private String name;
	private double distance;
	private String[] message;
}
