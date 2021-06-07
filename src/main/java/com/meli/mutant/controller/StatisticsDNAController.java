package com.meli.mutant.controller;

import com.meli.mutant.domain.StatisticsDna;
import com.meli.mutant.model.DNASequenceRequest;
import com.meli.mutant.service.StatisticsDNAService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(value = "/stats")
@AllArgsConstructor
public class StatisticsDNAController {
	
	private final StatisticsDNAService statisticsDNAService;

	/**
	 * Obtiene las estadisticas de los analisis de ADN realizados nteriormente
	 *
	 * @return StatisticsDna cantidad de mutantes, cantidad de humanos y ratio
	 */
	@Operation(summary = " Obtiene las estadisticas de los analisis de ADN realizados nteriormente. ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Json con las estadisticas de la base de datos"),
			@ApiResponse(responseCode = "503", description = "El servicio de base de datos presentaun error")})
	@GetMapping
	public ResponseEntity<StatisticsDna> getStatistics(){
		try {
			return ResponseEntity.ok(statisticsDNAService.getStatisticsDna());
		}
		catch (RuntimeException e){
			throw new ResponseStatusException(
					HttpStatus.SERVICE_UNAVAILABLE, "El servicio de base de datos presentaun error", e);
		}

	}
}
