package co.com.mercado.libre.quasar.controller;

import javax.validation.Valid;

import co.com.mercado.libre.quasar.model.SatelliteRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercado.libre.quasar.service.impl.QuasarOperationSplitServiceImpl;
import co.com.mercado.libre.quasar.model.QuasarOperationResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/topsecret_split")
@AllArgsConstructor
public class QuasarOperationSplitController {

	private final QuasarOperationSplitServiceImpl quasarOperationSplitService;

	@Operation(summary = "Envia la fuente y parte del mensaje recibido por un satelite")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "El satelite no existe"),
			@ApiResponse(responseCode = "404", description = "Ya habian sido ingresados los datos de este satelite")})
	@PostMapping(value = "/{satelliteName}")
	public ResponseEntity<QuasarOperationResponse> postTopSecretSplit(
			@PathVariable(name = "satelliteName") String satelliteName, @RequestBody @Valid SatelliteRequest satelliteRequest) {
		satelliteRequest.setName(satelliteName);
		quasarOperationSplitService.addSatelliteSplitInformation(satelliteRequest);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Operation(summary = "Obtiene el mensaje y la posición de la nave, posteriormente debió ser " +
			"recibida por cada satélite la distancia y el mensaje enviado por la nave")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "No fue posible calcular la posición, faltan distancias"),
			@ApiResponse(responseCode = "404", description = "Las estructuras de los mensajes son diferentes"),
			@ApiResponse(responseCode = "404", description = "Los mensajes poseen diferente tamaño")})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuasarOperationResponse> getTopSecretSplit() {
		QuasarOperationResponse response = quasarOperationSplitService
				.getQuasarOperation(quasarOperationSplitService.getQuasarOperationRequest());
		quasarOperationSplitService.disableSatellitesSplitStatus();
		return ResponseEntity.ok(response);
	}
}
