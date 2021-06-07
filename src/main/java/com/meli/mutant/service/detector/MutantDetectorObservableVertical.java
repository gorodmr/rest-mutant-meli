package com.meli.mutant.service.detector;

import java.beans.PropertyChangeListener;

/**
 * Searches for sequences in the matrix vertically
 */
public class MutantDetectorObservableVertical extends MutantDetectorObservable {

    public MutantDetectorObservableVertical(PropertyChangeListener pcl) {
        super(pcl);
    }

    @Override
    public void detectMutantSequence(char[][] dnaMatrix) {
        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < dnaMatrix.length; i++) {
            for (int j = 0; j < dnaMatrix.length; j++) {
                validateContinuosSequence(String.valueOf(dnaMatrix[j][i]), sequence);
            }
            sequence.delete(0, sequence.length());
        }
    }
}
