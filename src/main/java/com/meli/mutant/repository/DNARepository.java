package com.meli.mutant.repository;

import com.meli.mutant.domain.AnalysisDna;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DNARepository extends MongoRepository<AnalysisDna, AnalysisDna> {

}
