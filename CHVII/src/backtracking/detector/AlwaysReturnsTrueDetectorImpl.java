package backtracking.detector;

import sequence.Sequence;

public enum AlwaysReturnsTrueDetectorImpl implements Detector {
    SINGLETON;

    public Detector getIsDetectedPrecondition() {
        return SINGLETON;
    }

    public boolean isDetected(Sequence<Integer> sequence) {
        return true;
    }
}
