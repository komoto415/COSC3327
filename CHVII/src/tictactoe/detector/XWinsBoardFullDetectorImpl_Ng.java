package tictactoe.detector;

import backtracking.detector.Detector;
import sequence.Sequence;

public enum XWinsBoardFullDetectorImpl_Ng implements Detector {
    SINGLETON;

    public Detector getIsDetectedPrecondition() {
        throw new RuntimeException("NOT IMPLEMENTED!");
    }

    public boolean isDetected(Sequence<Integer> sequence) {
        assert getIsDetectedPrecondition().isDetected(sequence) :
                String.format("tictactoeSequence is not viable!: tictactoeSequence = %s", sequence);

        throw new RuntimeException("NOT IMPLEMENTED!");
    }
}