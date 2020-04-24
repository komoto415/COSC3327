package backtracking.detector;

import sequence.Sequence;

public class TargetedSizeDetectorImpl implements Detector {
    private int targetSize;

    public TargetedSizeDetectorImpl(int targetSize) {
        this.targetSize = targetSize;
    }

    public Detector getIsDetectedPrecondition() {
        return AlwaysReturnsTrueDetectorImpl.SINGLETON;
    }

    public boolean isDetected(Sequence<Integer> sequence) {
        boolean hasTargetSize = (sequence.size() == targetSize);
        return hasTargetSize;
    }
}
