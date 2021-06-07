package com.meli.mutant.service.detector;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


@Getter
@Setter
@Slf4j
public class MutantDetectorObserver implements PropertyChangeListener {

    private int numSequences;

    public MutantDetectorObserver() {
        this.numSequences = 0;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        int sequence = (int) evt.getNewValue();
        log.info("Recibio evento {} total numsequences {} ", sequence, numSequences);
        if (sequence > 0) {
            numSequences += sequence;
        }

    }
}
