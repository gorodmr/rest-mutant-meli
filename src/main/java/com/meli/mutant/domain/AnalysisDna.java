package com.meli.mutant.domain;

import com.meli.mutant.model.DNASequenceRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "analysisDna")
public class AnalysisDna {

    @Id
    private DNASequenceRequest dnaMatrix;

    @Indexed(name = "is_mutant")
    private boolean isMutant;
}
