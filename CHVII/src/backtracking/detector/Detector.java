package backtracking.detector;

import sequence.Sequence;

public interface Detector {
    public Detector getIsDetectedPrecondition();

    public boolean isDetected(Sequence<Integer> sequence);
}
