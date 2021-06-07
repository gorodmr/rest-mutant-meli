package com.meli.mutant.service;

import com.meli.mutant.repository.DNARepository;
import com.meli.mutant.service.detector.MutantDetector;
import com.meli.mutant.service.impl.MutantDNAServiceImpl;
import com.meli.mutant.util.Sample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantDNAServiceTest {
	@InjectMocks
	private MutantDNAServiceImpl mutantDNAService;

	@MockBean
	private DNARepository dnaRepository;

	@Autowired
	private MutantDetector mutantDetector;

	@Before
	public void setup(){
		this.mutantDNAService = new MutantDNAServiceImpl(dnaRepository, mutantDetector);
	}
	@Test
	public void whenRequestIsOkAndMatrixHasMutantSequence_ThenReturnTrue() {

		Mockito.when(dnaRepository.save(Mockito.any())).thenReturn(null);
		Assert.assertTrue(mutantDNAService.isMutant(Sample.buildSuccessMutantDNASequenceRequest()));
	}

	@Test
	public void whenRequestIsOkAndMatrixHasNotMutantSequence_ThenReturnFalse() {
		Mockito.when(dnaRepository.save(Mockito.any())).thenReturn(null);
		Assert.assertFalse(mutantDNAService.isMutant(Sample.buildSuccessHumanDNASequenceRequest()));
	}

}
