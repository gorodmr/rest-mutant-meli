package com.meli.mutant.exception;

public class InvalidCharactersException extends MutantException {

	private static final long serialVersionUID = 1L;
	
	public InvalidCharactersException(MutantErrors mutantErrors) {
		super(mutantErrors);
	}
	
}
