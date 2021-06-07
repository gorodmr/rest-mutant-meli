package com.meli.mutant.service.validator;

import com.meli.mutant.exception.InvalidCharactersException;
import com.meli.mutant.exception.MatrixSizeException;
import com.meli.mutant.model.DNASequenceRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.meli.mutant.exception.MutantErrors.*;
import static com.meli.mutant.util.ApplicationConstants.LENGTH_DNA_CODE;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@NoArgsConstructor
@Slf4j
public class MatrixDNAValidator {

    /**
     * Validate the array of DNA sequences
     * @param dNASequenceRequest DNA matrix sequence
     */
    public void isValid(DNASequenceRequest dNASequenceRequest) {

        List<String> sequenceDNAList = dNASequenceRequest != null ? dNASequenceRequest.getDna():null;
        if(isEmpty(sequenceDNAList)) {
            log.error("[isValid] ::  {}", ARRAY_SIZE_IS_EMPTY.getMessage());
            throw new MatrixSizeException(ARRAY_SIZE_IS_EMPTY);
        }

        int sizeCode = sequenceDNAList.size();
        // Que la matriz si tenga un tamaño NxN
        if(!sequenceDNAList.stream().noneMatch(s -> s.length() != sizeCode)) {
            log.error("[isValid] ::  {}", ARRAY_SIZE_IS_BAD.getMessage());
            throw new MatrixSizeException(ARRAY_SIZE_IS_BAD);
        }

        //Cada secuencia tenga una longitud mayor a 4
        if(sizeCode < LENGTH_DNA_CODE) {
            log.error("[isValid] ::  {}", ARRAY_SIZE_IS_LESS_TO_MINIMUM.getMessage());
            throw new MatrixSizeException(ARRAY_SIZE_IS_LESS_TO_MINIMUM);
        }

        if(sequenceDNAList.stream().anyMatch(s -> !s.matches("[ATCG]+"))) {
            log.error("[isValid] ::  {}", NOT_VALID_CHARACTERS.getMessage());
            throw new InvalidCharactersException(NOT_VALID_CHARACTERS);
        }
    }

}
