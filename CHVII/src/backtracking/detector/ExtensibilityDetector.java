package backtracking.detector;

import sequence.Sequence;

public interface ExtensibilityDetector {
    public Detector getCanExtendPrecondition();

    public Detector getCanExtendPostcondition();

    public boolean canExtend(Sequence<Integer> sequence, int k);
}
