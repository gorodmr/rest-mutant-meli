package com.meli.mutant.controller;

import com.meli.mutant.model.DNASequenceRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.meli.mutant.util.Sample;
import com.meli.mutant.service.impl.MutantDNAServiceImpl;
import org.springframework.test.web.servlet.MockMvc;

import static com.meli.mutant.exception.MutantErrors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MutantDNAController.class)
public class MutantDNAControllerTest {

	@Autowired
	private MutantDNAController mutantDNAController;

	@MockBean
	MutantDNAServiceImpl mutantDNAService;

	@Autowired
	private MockMvc mvc;

	public static final String CHARSET_UTF_8 = ";charset=UTF-8";

	@Test
	public void whenRequestHasMutant_ThenReturnSuccess() {
		DNASequenceRequest matrixDNA = Sample.buildSuccessMutantDNASequenceRequest();
		Mockito.when(mutantDNAService.isMutant(matrixDNA)).
				thenReturn(true);
		ResponseEntity<String> response =
				mutantDNAController.validateADN(matrixDNA);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void whenRequestHasNotMutant_ThenReturnForbidden() {
		DNASequenceRequest matrixDNA = Sample.buildSuccessHumanDNASequenceRequest();
		Mockito.when(mutantDNAService.isMutant(matrixDNA)).
				thenReturn(false);
		ResponseEntity<String> response =
				mutantDNAController.validateADN(matrixDNA);
		Assert.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	public void whenRequestHasNotValidCharacters_ThenReturnValidationMessage() throws Exception {
		DNASequenceRequest matrixDNA = Sample.buildBadCharsDNASequenceRequest();

		mvc.perform(post("/mutant").header("Content-Type", "application/json")
				.contentType(MediaType.APPLICATION_JSON+ CHARSET_UTF_8)
				.content(String.valueOf(matrixDNA)))
		.andExpect(status().isBadRequest())
		.andExpect(content().json("{\"message\":\"" + NOT_VALID_CHARACTERS.getMessage() + "\"}"));

	}

	@Test
	public void whenRequestHasArrayLess_ThenReturnValidationMessage() throws Exception {
		DNASequenceRequest matrixDNA = Sample.buildBadSequenceLeesToFourDNASequenceRequest();

		mvc.perform(post("/mutant").header("Content-Type", "application/json")
				.contentType(MediaType.APPLICATION_JSON+ CHARSET_UTF_8)
				.content(String.valueOf(matrixDNA)))
				.andExpect(status().isBadRequest())
				.andExpect(content().json("{\"message\":\"" + ARRAY_SIZE_IS_LESS_TO_MINIMUM.getMessage()+ "\"}"));

	}

	@Test
	public void whenRequestHasBadMatrixSize_ThenReturnValidationMessage() throws Exception {
		DNASequenceRequest matrixDNA = Sample.buildBadMatrixSizeDNASequenceRequest();

		mvc.perform(post("/mutant").header("Content-Type", "application/json")
				.contentType(MediaType.APPLICATION_JSON+ CHARSET_UTF_8)
				.content(String.valueOf(matrixDNA)))
				.andExpect(status().isBadRequest())
				.andExpect(content().json("{\"message\":\"" + ARRAY_SIZE_IS_BAD.getMessage()+ "\"}"));

	}

	@Test
	public void whenRequestHasBadSequenceSize_ThenReturnValidationMessage() throws Exception {
		DNASequenceRequest matrixDNA = Sample.buildBadSequenceSizeDNASequenceRequest();

		mvc.perform(post("/mutant").header("Content-Type", "application/json")
				.contentType(MediaType.APPLICATION_JSON+ CHARSET_UTF_8)
				.content(String.valueOf(matrixDNA)))
				.andExpect(status().isBadRequest())
				.andExpect(content().json("{\"message\":\"" + ARRAY_SIZE_IS_BAD.getMessage()+ "\"}"));

	}

}
