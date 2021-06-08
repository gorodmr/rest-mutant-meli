package com.meli.mutant.repository;

import com.meli.mutant.controller.MutantDNAController;
import com.meli.mutant.controller.StatisticsDNAController;
import com.meli.mutant.domain.StatisticsDna;
import com.meli.mutant.model.DNASequenceRequest;
import com.meli.mutant.service.impl.StatisticsDNAServiceImpl;
import com.meli.mutant.util.Sample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {StatisticsDNAController.class, MutantDNAController.class})
public class DnaRepositoryTest {

    @Autowired
    private StatisticsDNAController statisticsDNAController;

    @Autowired
    private MutantDNAController mutantDNAController;

    public static final String CHARSET_UTF_8 = ";charset=UTF-8";

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenHaveThreeHumansAndOneMutant_ThenReturnOneThirdInRatio() throws Exception {

        mvc.perform(post("/mutant").header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON+ CHARSET_UTF_8)
                .content(String.valueOf(Sample.buildSuccessHumanDNASequenceRequest())));

        mvc.perform(post("/mutant").header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON+ CHARSET_UTF_8)
                .content(String.valueOf(Sample.buildSuccessHuman2DNASequenceRequest())));

        mvc.perform(post("/mutant").header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON+ CHARSET_UTF_8)
                .content(String.valueOf(Sample.buildSuccessHuman3DNASequenceRequest())));

        mvc.perform(post("/mutant").header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON + CHARSET_UTF_8)
                .content(String.valueOf(Sample.buildSuccessMutantDNASequenceRequest())));


        mvc.perform(get("/stats").accept(MediaType.APPLICATION_JSON+ CHARSET_UTF_8))
                .andExpect(status().isOk());

    }
}