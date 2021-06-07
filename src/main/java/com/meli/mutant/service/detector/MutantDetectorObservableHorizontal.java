package com.meli.mutant.service.detector;

import java.beans.PropertyChangeListener;

/**
 * Searches for sequences in the matrix horizontally
 */
public class MutantDetectorObservableHorizontal extends MutantDetectorObservable {

    public MutantDetectorObservableHorizontal(PropertyChangeListener pcl) {
        super(pcl);
    }

    @Override
    public void detectMutantSequence(char[][] dnaMatrix) {
        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < dnaMatrix.length; i++) {
            for (int j = 0; j < dnaMatrix.length; j++) {
                validateContinuosSequence(String.valueOf(dnaMatrix[i][j]), sequence);
            }
            sequence.delete(0, sequence.length());
        }
    }
}
