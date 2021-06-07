package com.meli.mutant.controller;

import com.meli.mutant.service.MutantDNAService;
import com.meli.mutant.service.validator.MatrixDNAValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.mutant.model.DNASequenceRequest;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping(value = "/mutant")
@AllArgsConstructor
public class MutantDNAController {
	
	private final MutantDNAService mutantDNAService;

	private final MatrixDNAValidator matrixDNAValidator;

	/**
	 * Valida si las secuencias de ADN ingresadas corresponden a un mtante
	 *
	 * @param dNASequenceRequest Arreglo de strings con la secuencia de ADN
	 * @return void
	 */
	@Operation(summary = " Detecta si un humano es mutante basado en la secuencia de ADN ingresada. ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La cadena de ADN ingresada corresponde a un mutante"),
		    @ApiResponse(responseCode = "403", description = "La cadena de ADN ingresada no corresponde a un mutante"),
			@ApiResponse(responseCode = "404", description = "Los datos ingresados no son validos")})
	@PostMapping
	public ResponseEntity<String> validateADN(@RequestBody DNASequenceRequest dNASequenceRequest){
		matrixDNAValidator.isValid(dNASequenceRequest);
		boolean isMutant = mutantDNAService.isMutant(dNASequenceRequest);
		return isMutant ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();

	}
}
