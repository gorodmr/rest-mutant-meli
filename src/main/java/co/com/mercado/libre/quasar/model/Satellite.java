package co.com.mercado.libre.quasar.model;

import lombok.*;

/**
 * Class that represent a satellite and its location
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Satellite {

	private String name;

	private double x;

	private double y;

}
