package co.com.mercado.libre.quasar.controller;
import javax.validation.Valid;

import co.com.mercado.libre.quasar.exception.UndeterminableMessageException;
import co.com.mercado.libre.quasar.exception.UndeterminedLocationException;
import co.com.mercado.libre.quasar.service.impl.QuasarOperationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercado.libre.quasar.model.QuasarOperationRequest;
import co.com.mercado.libre.quasar.model.QuasarOperationResponse;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping(value = "/topsecret")
@AllArgsConstructor
public class QuasarOperationController {
	
	private final QuasarOperationServiceImpl quasarOperationService;

	/**
	 * Retrieve the message sent from the ship
	 *
	 * @param quasarOperationRequest satellites information
	 * @return Location and message of the ship
	 */
	@Operation(summary = "Recupera la fuente y el contenidodel mensaje enviado por la nave ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Mensaje y localización de la nave",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = QuasarOperationResponse.class)) }),
		    @ApiResponse(responseCode = "404", description = "No fue posible calcular la posición, faltan distancias",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = UndeterminedLocationException.class)) }),
			@ApiResponse(responseCode = "404", description = "No se puede determinar una de las palabras del mensaje",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = UndeterminableMessageException.class)) })})
	@PostMapping
	public ResponseEntity<QuasarOperationResponse> getTopsecret(@RequestBody @Valid QuasarOperationRequest quasarOperationRequest){
		return ResponseEntity.ok(quasarOperationService.getQuasarOperation(quasarOperationRequest));
	}
}
