package com.meli.mutant.model;

import java.util.List;

import com.meli.mutant.util.JsonUtil;
import lombok.*;

/**
 * DNA Sequence request
 *
 * String letters can only be: (A, T, C, G)
 * The number of strings entered must be equal to the size of each string
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DNASequenceRequest {

	@NonNull
	private List<String> dna;

	/**
	 * Serializes current instance
	 * @return Serialized class
	 **/
	public String toString() {
		return JsonUtil.toJsonString(this);
	}

}
