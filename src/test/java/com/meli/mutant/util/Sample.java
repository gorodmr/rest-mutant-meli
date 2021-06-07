package com.meli.mutant.util;

import java.util.Arrays;

import com.meli.mutant.domain.StatisticsDna;
import com.meli.mutant.model.DNASequenceRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Sample {

	public static DNASequenceRequest buildSuccessMutantDNASequenceRequest() {
		return new DNASequenceRequest(
				Arrays.asList("ATGCTGCA","AAGATGCA", "ATGCTGGT", "CTGTGCCA",
						"ACGCTGCG", "ATCCAGCA", "ATGCTACA", "ATGCTGCA"));
	}

	public static DNASequenceRequest buildSuccessHumanDNASequenceRequest() {
		return new DNASequenceRequest(
				Arrays.asList("ATGCTG", "AAGATG", "ATACTG", "CTGAGC", "ACGCTG", "ATGCTG"));
	}

	public static DNASequenceRequest buildSuccessHuman2DNASequenceRequest() {
		return new DNASequenceRequest(
				Arrays.asList("ATGCTG", "CAGATG", "ATACTG", "CTGAGC", "ACGCTG", "ATGCTG"));
	}

	public static DNASequenceRequest buildSuccessHuman3DNASequenceRequest() {
		return new DNASequenceRequest(
				Arrays.asList("ATGCTG", "GAGATG", "ATACTG", "CTGAGC", "ACGCTG", "ATGCTG"));
	}

	public static DNASequenceRequest buildBadMatrixSizeDNASequenceRequest() {
		return new DNASequenceRequest(
				Arrays.asList("ATGCTGCACTGACCCC", "ATGCTGCACTGACCCC", "ATGCTGCACTGACCCC", "ATGCTGCACTGACCCC"));
	}

	public static DNASequenceRequest buildBadCharsDNASequenceRequest() {
		return new DNASequenceRequest(
				Arrays.asList("ATGCTGCA", "ATGXTGCA", "ATGCTGCA",
					"ATGCTGCA","ATGCTGCA","ATGCTGCA","ATGCTGCA","ATGCTGCA"));
	}

	public static DNASequenceRequest buildBadSequenceSizeDNASequenceRequest() {
		return new DNASequenceRequest(
				Arrays.asList("ATGCTGCA", "ATGTGCA", "ATGCTGCA", "ATGCTGCA",
						"ATGCTGCA", "ATGCTGCA", "ATGCTGCA", "ATGCTGCA"));
	}

	public static DNASequenceRequest buildBadSequenceLeesToFourDNASequenceRequest() {
		return new DNASequenceRequest(
				Arrays.asList("ATG", "CCC", "ATG"));
	}

    public static StatisticsDna buildStatisticsDnaResponse() {

		return new StatisticsDna(10L, 20L, 0.50);
    }
}
