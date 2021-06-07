package com.meli.mutant.repository.impl;

import com.meli.mutant.domain.AnalysisDna;
import com.meli.mutant.domain.StatisticsDna;
import com.meli.mutant.repository.StatisticsDNARepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;


import static java.util.Objects.isNull;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

/**
 * Implement queries to get the Statistics
 */
@AllArgsConstructor
@Slf4j
@Repository
public class StatisticsDNARepositoryImpl implements StatisticsDNARepository {

    private transient MongoTemplate mongoTemplate;

    @Override
    public StatisticsDna getStatisticsDna() {

        Aggregation statisticsAggregation = Aggregation.newAggregation(
                group()
                        .sum(ConditionalOperators
                                .when(Criteria.where("isMutant").is(true)).then(1).otherwise(0)).as("mutant")
                        .sum(ConditionalOperators
                                .when(Criteria.where("isMutant").is(false)).then(1).otherwise(0)).as("human")
        );

        AggregationResults<StatisticsDna> result = mongoTemplate.aggregate(statisticsAggregation, AnalysisDna.class, StatisticsDna.class);

        StatisticsDna statisticsDna = result.getUniqueMappedResult();

        return calculateRatio(statisticsDna);
    }

    private StatisticsDna calculateRatio(StatisticsDna statisticsDna) {
        if(isNull(statisticsDna))
            return new StatisticsDna(0L, 0L, 0.0);

        log.debug("[calculateRatio] {}",  statisticsDna.toString());

        Long mutant = statisticsDna.getMutant();
        Long human = statisticsDna.getHuman();
        Double ratio = (human.compareTo(0L) == 0 ? 0.0 : Double.valueOf(mutant.doubleValue() / human.doubleValue()));
        statisticsDna.setRatio(ratio);

        return statisticsDna;

    }

}
