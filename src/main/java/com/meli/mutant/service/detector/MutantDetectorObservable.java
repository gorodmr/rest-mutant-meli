package com.meli.mutant.service.detector;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static com.meli.mutant.util.ApplicationConstants.LENGTH_DNA_CODE;
import static org.springframework.util.StringUtils.isEmpty;

@AllArgsConstructor
@Slf4j
public abstract class MutantDetectorObservable {

    private PropertyChangeSupport support;
    private int numSequences;

    public MutantDetectorObservable(PropertyChangeListener pcl) {
        numSequences = 0;
        support = new PropertyChangeSupport(this);
        support.addPropertyChangeListener(pcl);
    }

    public void setFindDNASequence() {
        support.firePropertyChange("numSequences", this.numSequences, this.numSequences+1);
    }

    protected void validateContinuosSequence(String charDNA, StringBuilder sequence) {
        if (sequence.lastIndexOf(charDNA) == -1 && !isEmpty(sequence.toString())) {
            sequence.delete(0, sequence.length());
        }
        sequence.append(charDNA);
        if(sequence.length() == LENGTH_DNA_CODE) {
            log.info("Notifico al observador que se encontro una secuencia {}",  sequence.toString());
            setFindDNASequence();
            sequence.delete(0, sequence.length());
        }
    }

    public abstract void detectMutantSequence(char[][] dnaMatrix);
}
