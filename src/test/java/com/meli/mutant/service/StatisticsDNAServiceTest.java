package com.meli.mutant.service;

import com.meli.mutant.domain.StatisticsDna;
import com.meli.mutant.repository.StatisticsDNARepository;
import com.meli.mutant.service.impl.StatisticsDNAServiceImpl;
import com.meli.mutant.util.Sample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsDNAServiceTest {

    @InjectMocks
    private StatisticsDNAServiceImpl statisticsDNAService;

    @MockBean
    private StatisticsDNARepository statisticsDNARepository;

    @Before
    public void setup(){
        this.statisticsDNAService = new StatisticsDNAServiceImpl(statisticsDNARepository);
    }

    @Test
    public void whenRequestIsOk_ThenReturnStatistics() {

        StatisticsDna statisticsDna = Sample.buildStatisticsDnaResponse();

        Mockito.when(statisticsDNARepository.getStatisticsDna()).thenReturn(statisticsDna);
        Assert.assertTrue(statisticsDNAService.getStatisticsDna().equals(statisticsDna));
    }

}