package com.meli.mutant.service.impl;

import com.meli.mutant.domain.AnalysisDna;
import com.meli.mutant.model.DNASequenceRequest;
import com.meli.mutant.repository.DNARepository;
import com.meli.mutant.service.detector.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.meli.mutant.service.MutantDNAService;
import lombok.AllArgsConstructor;

import java.util.List;

@Component("mutantDNAServiceImpl")
@AllArgsConstructor
@Slf4j
public class MutantDNAServiceImpl implements MutantDNAService {

	private DNARepository dnaRepository;

	private MutantDetector mutantDetector;

	public boolean isMutant(DNASequenceRequest dNASequenceRequest) {

		log.info("[isMutant] :: Identification starts with  {}", dNASequenceRequest);

		char[][] dnaMatrix = convertToMatrix(dNASequenceRequest.getDna());

		boolean isMutant = mutantDetector.analysis(dnaMatrix);

		saveAnalysisDna(dNASequenceRequest, isMutant);

		return isMutant;
	}

	/**
	 * Add new analysis of DNA sequences
	 *
	 * @param dNASequenceRequest Matrix DNA
	 * @param isMutant           true for mutant and false for human
	 */
	private void saveAnalysisDna(DNASequenceRequest dNASequenceRequest, boolean isMutant) {

		AnalysisDna analysisDna = new AnalysisDna();
		analysisDna.setDnaMatrix(dNASequenceRequest);
		analysisDna.setMutant(isMutant);

		dnaRepository.save(analysisDna);
	}

	private char[][] convertToMatrix(List<String> sequenceDNAList) {
		char[][] dnaMatrix = new char[sequenceDNAList.size()][sequenceDNAList.size()];
		for (int i = 0; i < sequenceDNAList.size(); i++) {
			dnaMatrix[i] = sequenceDNAList.get(i).toCharArray();
		}
		return dnaMatrix;
	}
}