package test;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import backtracking.BacktrackingWithPruningSolver;
import backtracking.detector.AlwaysReturnsTrueDetectorImpl;
import backtracking.detector.AlwaysReturnsTrueExtensibilityDetectorImpl;
import backtracking.detector.Detector;
import backtracking.detector.ExtensibilityDetector;
import backtracking.detector.NoDuplicatesDetectorImpl;
import backtracking.detector.NoDuplicatesExtensibilityDetectorImpl;
import backtracking.detector.TargetedSizeDetectorImpl;
import queens.backtracking.detector.ExtensibilityDetectorImpl;
import queens.backtracking.detector.SolutionDetectorImpl;
import queens.backtracking.detector.ViabilityDetectorImpl;
import sequence.Sequence;
import sequence.SequenceImpl;

public class BacktrackingWithPruningSolverTest {
    private static int getExpectedSize_AnythingGoes(int n) {
        int expectedSize = 1;
        for (int i = 0; i < n; i++) {
            expectedSize = n * expectedSize;
        }
        return expectedSize;
    }

    @Test
    public void anythingGoes_1() {
        int n = 1;

        Detector viability = AlwaysReturnsTrueDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = AlwaysReturnsTrueExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_AnythingGoes(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void anythingGoes_2() {
        int n = 2;

        Detector viability = AlwaysReturnsTrueDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = AlwaysReturnsTrueExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_AnythingGoes(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void anythingGoes_3() {
        int n = 3;

        Detector viability = AlwaysReturnsTrueDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = AlwaysReturnsTrueExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_AnythingGoes(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void anythingGoes_4() {
        int n = 4;

        Detector viability = AlwaysReturnsTrueDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = AlwaysReturnsTrueExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_AnythingGoes(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void anythingGoes_5() {
        int n = 5;

        Detector viability = AlwaysReturnsTrueDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = AlwaysReturnsTrueExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_AnythingGoes(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void anythingGoes_6() {
        int n = 6;

        Detector viability = AlwaysReturnsTrueDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = AlwaysReturnsTrueExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_AnythingGoes(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void anythingGoes_7() {
        int n = 7;

        Detector viability = AlwaysReturnsTrueDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = AlwaysReturnsTrueExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_AnythingGoes(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void anythingGoes_8() {
        int n = 5;

        Detector viability = AlwaysReturnsTrueDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = AlwaysReturnsTrueExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_AnythingGoes(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    private static int getExpectedSize_NoDuplicates(int n) {
        int expectedSize = 1;
        for (int i = n; i > 1; i--) {
            expectedSize = expectedSize * i;
        }
        return expectedSize;
    }

    @Test
    public void noDuplicates_1() {
        int n = 1;

        Detector viability = NoDuplicatesDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = NoDuplicatesExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_NoDuplicates(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void noDuplicates_2() {
        int n = 2;

        Detector viability = NoDuplicatesDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = NoDuplicatesExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_NoDuplicates(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void noDuplicates_3() {
        int n = 3;

        Detector viability = NoDuplicatesDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = NoDuplicatesExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_NoDuplicates(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void noDuplicates_4() {
        int n = 4;

        Detector viability = NoDuplicatesDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = NoDuplicatesExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_NoDuplicates(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void noDuplicates_5() {
        int n = 5;

        Detector viability = NoDuplicatesDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = NoDuplicatesExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_NoDuplicates(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void noDuplicates_6() {
        int n = 6;

        Detector viability = NoDuplicatesDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = NoDuplicatesExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_NoDuplicates(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void noDuplicates_7() {
        int n = 7;

        Detector viability = NoDuplicatesDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = NoDuplicatesExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_NoDuplicates(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void noDuplicates_8() {
        int n = 8;

        Detector viability = NoDuplicatesDetectorImpl.SINGLETON;
        ExtensibilityDetector addDetector = NoDuplicatesExtensibilityDetectorImpl.SINGLETON;
        Detector solution = new TargetedSizeDetectorImpl(n);

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(n, addDetector, viability, solution);
        int expectedSize = getExpectedSize_NoDuplicates(n);
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }
}
