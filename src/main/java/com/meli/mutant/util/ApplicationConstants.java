package com.meli.mutant.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationConstants {

	/**
	 * Length of a valid DNA code
	 */
	public static final int LENGTH_DNA_CODE = 4;

	/**
	 * default count sequences mutant
	 */
	public static final int MIN_NUMBER_SEQUENCE_TO_BE_MUTANT = 2;
}
