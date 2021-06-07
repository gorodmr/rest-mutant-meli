package com.meli.mutant.service.detector;

import java.beans.PropertyChangeListener;

/**
 * Searches for sequences in the matrix diagonally down
 */
public class MutantDetectorObservableDiagonalDown extends MutantDetectorObservable {

    public MutantDetectorObservableDiagonalDown(PropertyChangeListener pcl) {
        super(pcl);
    }

    @Override
    public void detectMutantSequence(char[][] dnaMatrix) {

        StringBuilder sequence = new StringBuilder();
        for (int diag = 1-dnaMatrix.length; diag <= dnaMatrix.length; diag++) {
            for (int vert = Math.max(0, diag), horztl = -Math.min(0, diag);
                 vert < dnaMatrix.length && horztl < dnaMatrix.length; vert++, horztl++) {
                validateContinuosSequence(String.valueOf(dnaMatrix[vert][horztl]), sequence);
            }
            sequence = new StringBuilder();
        }

    }

}
