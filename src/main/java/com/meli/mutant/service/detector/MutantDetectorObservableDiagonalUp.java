package com.meli.mutant.service.detector;

import java.beans.PropertyChangeListener;

/**
 * Searches for sequences in the matrix diagonally up
 */
public class MutantDetectorObservableDiagonalUp extends MutantDetectorObservable {

    public MutantDetectorObservableDiagonalUp(PropertyChangeListener pcl) {
        super(pcl);
    }

    @Override
    public void detectMutantSequence(char[][] dnaMatrix) {
        StringBuilder sequence = new StringBuilder();
        for (int diag = 1-dnaMatrix.length; diag < dnaMatrix.length; diag++) {
            for (int vert = (diag < 1 ? dnaMatrix.length-1 : dnaMatrix.length-1-diag), horztl = -Math.min(0, diag);
                 vert < dnaMatrix.length && vert >= 0 && horztl < dnaMatrix.length; vert--, horztl++) {
                validateContinuosSequence(String.valueOf(dnaMatrix[vert][horztl]), sequence);
            }

        }
    }
}
