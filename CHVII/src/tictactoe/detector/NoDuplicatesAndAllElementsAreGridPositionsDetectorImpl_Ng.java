package tictactoe.detector;

import backtracking.detector.AlwaysReturnsTrueDetectorImpl;
import backtracking.detector.Detector;
import sequence.Sequence;
import sequence.SequenceUtils;
import tictactoe.TicTacToeGame;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum NoDuplicatesAndAllElementsAreGridPositionsDetectorImpl_Ng implements Detector {
    SINGLETON;

    public Detector getIsDetectedPrecondition() {
        return AlwaysReturnsTrueDetectorImpl.SINGLETON;
    }

    private static final Set<Integer> validIntegers = new HashSet<>(Arrays.asList(-1, 0, 1, 2, 3, 4, 5, 6, 7, 8));
    public boolean isDetected(Sequence<Integer> sequence) {
        assert getIsDetectedPrecondition().isDetected(sequence) :
                String.format("sequence fails precondition!: sequence = %s", sequence);

        boolean hasDuplicates = sequence.size() == SequenceUtils.getElementsAsSet(sequence).size();
        boolean validGridPositions = validIntegers.containsAll(SequenceUtils.getElementsAsSet(sequence));

        return hasDuplicates && validGridPositions;
    }
}
