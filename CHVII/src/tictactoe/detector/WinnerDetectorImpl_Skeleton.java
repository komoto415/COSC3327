package tictactoe.detector;

import backtracking.detector.Detector;
import sequence.Sequence;
import tictactoe.Player;

public class WinnerDetectorImpl_Skeleton implements Detector {
    private Player desiredWinner;

    public WinnerDetectorImpl_Skeleton(Player desiredWinner) {
        this.desiredWinner = desiredWinner;
    }

    public Detector getIsDetectedPrecondition() {
        throw new RuntimeException("NOT IMPLEMENTED!");
    }

    public boolean isDetected(Sequence<Integer> sequence) {
        assert getIsDetectedPrecondition().isDetected(sequence) :
                String.format("tictactoeSequence is not viable!: tictactoeSequence = %s", sequence);

        throw new RuntimeException("NOT IMPLEMENTED!");
    }
}
