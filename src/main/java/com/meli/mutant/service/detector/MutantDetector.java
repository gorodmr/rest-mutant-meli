package com.meli.mutant.service.detector;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static com.meli.mutant.util.ApplicationConstants.MIN_NUMBER_SEQUENCE_TO_BE_MUTANT;

@Service
@NoArgsConstructor
public class MutantDetector {

    public boolean analysis(char[][] dnaMatrix) {

        int numSequences = 0;

        MutantDetectorObserver mutantDetectorObserver = new MutantDetectorObserver();

        MutantDetectorObservable mutantDetectorObservableHorizontal = new MutantDetectorObservableHorizontal(mutantDetectorObserver);
        MutantDetectorObservable mutantDetectorObservableVertical = new MutantDetectorObservableVertical(mutantDetectorObserver);
        MutantDetectorObservable mutantDetectorObservableDiagonalUp = new MutantDetectorObservableDiagonalUp(mutantDetectorObserver);
        MutantDetectorObservable mutantDetectorObservableDiagonalDown = new MutantDetectorObservableDiagonalDown(mutantDetectorObserver);

        ExecutorService execHorizontal = createExecutor(mutantDetectorObservableHorizontal, dnaMatrix);
        ExecutorService execVertical = createExecutor(mutantDetectorObservableVertical, dnaMatrix);
        ExecutorService execDiagonalDown = createExecutor(mutantDetectorObservableDiagonalDown, dnaMatrix);
        ExecutorService execDiagonalUp = createExecutor(mutantDetectorObservableDiagonalUp, dnaMatrix);

        Stream<ExecutorService> executors = Stream.of(execDiagonalDown, execDiagonalUp, execVertical, execHorizontal);

        do{
            numSequences = mutantDetectorObserver.getNumSequences();
            if(numSequences >= MIN_NUMBER_SEQUENCE_TO_BE_MUTANT)
                executors.filter(s -> !s.isShutdown()).forEach(ExecutorService::shutdownNow);
        }while (!threadsFinished(execDiagonalDown, execDiagonalUp, execVertical, execHorizontal));

        return numSequences >= MIN_NUMBER_SEQUENCE_TO_BE_MUTANT;
    }

    private boolean threadsFinished(ExecutorService execDiagonalDown, ExecutorService execDiagonalUp,
                                           ExecutorService execVertical, ExecutorService execHorizontal) {
        return (execDiagonalDown.isShutdown() && execDiagonalUp.isShutdown()
                && execVertical.isShutdown() && execHorizontal.isShutdown());
    }


    private ExecutorService createExecutor(MutantDetectorObservable mutantDetectorObservable,
                                                  char[][] dnaMatrix) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.submit(() -> {
            mutantDetectorObservable.detectMutantSequence(dnaMatrix);
            exec.shutdown();
        });
        return exec;
    }
}
