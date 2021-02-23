package co.com.mercado.libre.quasar.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SatelliteSplit {

	private String name;

	private double distance;

	private String[] message;

	private boolean active;

}