package com.meli.mutant.controller;

import com.meli.mutant.domain.StatisticsDna;
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

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = StatisticsDNAController.class)
public class StatisticsDNAControllerTest {

    @Autowired
    private StatisticsDNAController statisticsDNAController;

    @MockBean
    StatisticsDNAServiceImpl statisticsDNAServiceImpl;

    public static final String CHARSET_UTF_8 = ";charset=UTF-8";

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenRequestIsOk_ThenReturnStatistics() throws Exception {

        StatisticsDna statisticsDna = Sample.buildStatisticsDnaResponse();

        doReturn(statisticsDna)
                .when(statisticsDNAServiceImpl)
                .getStatisticsDna();

        mvc.perform(get("/stats").accept(MediaType.APPLICATION_JSON+ CHARSET_UTF_8))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"human\": 10, \"mutant\": 20, \"ratio\": 0.50}"));

    }

    @Test
    public void whenRequestIsOkButNoRecordDB_ThenReturnStatistics() throws Exception {

        StatisticsDna statisticsDna = new StatisticsDna(0L, 0L, 0.0);

        doReturn(statisticsDna)
                .when(statisticsDNAServiceImpl)
                .getStatisticsDna();

        mvc.perform(get("/stats").accept(MediaType.APPLICATION_JSON+ CHARSET_UTF_8))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"human\": 0, \"mutant\": 0, \"ratio\": 0.0}"));

    }
}