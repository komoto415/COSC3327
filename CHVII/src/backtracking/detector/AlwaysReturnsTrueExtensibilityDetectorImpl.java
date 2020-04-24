package backtracking.detector;

import sequence.Sequence;

public enum AlwaysReturnsTrueExtensibilityDetectorImpl implements ExtensibilityDetector {
    SINGLETON;

    public Detector getCanExtendPrecondition() {
        return AlwaysReturnsTrueDetectorImpl.SINGLETON;
    }

    public Detector getCanExtendPostcondition() {
        return AlwaysReturnsTrueDetectorImpl.SINGLETON;
    }

    public boolean canExtend(Sequence<Integer> sequence, int k) {
        return true;
    }
}
