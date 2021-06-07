package com.meli.mutant.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MutantException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final MutantErrors mutantErrors;
}