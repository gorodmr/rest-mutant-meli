package com.meli.mutant.repository;

import com.meli.mutant.domain.StatisticsDna;

/**
 * Get ratio between mutants and humans
 */
public interface StatisticsDNARepository {

    /**
     * @return Total number of humans and mutants registered and the ratio between them
     */
    StatisticsDna getStatisticsDna();
}
