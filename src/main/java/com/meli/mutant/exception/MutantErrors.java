package com.meli.mutant.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

import static com.meli.mutant.util.ApplicationConstants.LENGTH_DNA_CODE;

@AllArgsConstructor
@Getter
public enum MutantErrors {
	ARRAY_SIZE_IS_EMPTY("El arreglo ingresado es vacío", HttpStatus.BAD_REQUEST),
	ARRAY_SIZE_IS_BAD("El tamaño de cada secuencia dentro del arreglo debe ser igual al tamaño del arreglo", HttpStatus.BAD_REQUEST),
	ARRAY_SIZE_IS_LESS_TO_MINIMUM("El tamaño del arreglo y de cada secuencia dentro de el debe ser mayor que "
			+ LENGTH_DNA_CODE, HttpStatus.BAD_REQUEST),
	NOT_VALID_CHARACTERS("Las cadenas ingresadas solo pueden contener las letas (A,T,C,G)", HttpStatus.BAD_REQUEST);

	private String message;
	private HttpStatus statusCode;

}
