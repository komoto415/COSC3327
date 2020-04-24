package backtracking.detector;

import java.util.Set;

import sequence.Sequence;
import sequence.SequenceUtils;

public enum NoDuplicatesExtensibilityDetectorImpl implements ExtensibilityDetector {
    SINGLETON;

    public Detector getCanExtendPrecondition() {
        return NoDuplicatesDetectorImpl.SINGLETON;
    }

    public Detector getCanExtendPostcondition() {
        return NoDuplicatesDetectorImpl.SINGLETON;
    }

    public boolean canExtend(Sequence<Integer> sequence, int k) {
        Set<Integer> elements = SequenceUtils.getElementsAsSet(sequence);
        boolean canAdd = (!elements.contains(k));
        return canAdd;
    }
}
