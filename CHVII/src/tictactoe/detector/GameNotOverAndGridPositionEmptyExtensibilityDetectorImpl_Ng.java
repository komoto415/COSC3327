package tictactoe.detector;

import backtracking.detector.Detector;
import backtracking.detector.ExtensibilityDetector;
import sequence.Sequence;

public enum GameNotOverAndGridPositionEmptyExtensibilityDetectorImpl_Ng implements ExtensibilityDetector {
    SINGLETON;

    public Detector getCanExtendPrecondition() {
        throw new RuntimeException("NOT IMPLEMENTED!");
    }

    public Detector getCanExtendPostcondition() {
        throw new RuntimeException("NOT IMPLEMENTED!");
    }

    public boolean canExtend(Sequence<Integer> sequence, int i) {
        throw new RuntimeException("NOT IMPLEMENTED!");

        //		boolean canExtend = student needs to fill in the details;
        //
        //		assert !canExtend || (getCanExtendPostcondition().isDetected(SequenceUtils.shallowCopyOfExtended(sequence, i)));
        //
        //		return canExtend;
    }
}
