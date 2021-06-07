package com.meli.mutant.service.impl;

import com.meli.mutant.domain.StatisticsDna;
import com.meli.mutant.repository.StatisticsDNARepository;
import com.meli.mutant.service.StatisticsDNAService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service statistics manager
 */
@Service
@AllArgsConstructor
public class StatisticsDNAServiceImpl implements StatisticsDNAService {

    private StatisticsDNARepository statisticsDNARepository;

    public StatisticsDna getStatisticsDna() {
        return statisticsDNARepository.getStatisticsDna();
    }
}
