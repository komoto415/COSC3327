package backtracking.detector;

import sequence.Sequence;
import sequence.SequenceUtils;

public enum NoDuplicatesDetectorImpl implements Detector {
    SINGLETON;

    public Detector getIsDetectedPrecondition() {
        return AlwaysReturnsTrueDetectorImpl.SINGLETON;
    }

    public boolean isDetected(Sequence<Integer> sequence) {
        boolean containsDuplicates = (sequence.size() == SequenceUtils.getElementsAsSet(sequence).size());
        return containsDuplicates;
    }
}
