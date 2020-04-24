package test;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import backtracking.BacktrackingWithPruningSolver;
import backtracking.detector.Detector;
import backtracking.detector.ExtensibilityDetector;
import queens.backtracking.detector.ExtensibilityDetectorImpl;
import queens.backtracking.detector.SolutionDetectorImpl;
import sequence.Sequence;

//Test result sizes from:
//http://www.durangobill.com/N_Queens.html
public class NQueensSolverTest {
    @Test
    public void eightQueensCount() {
        final int CLASSIC_CHESS_BOARD_SIZE = 8;
        int boardSize = CLASSIC_CHESS_BOARD_SIZE;

        ExtensibilityDetector extensibilityDetector = new ExtensibilityDetectorImpl(boardSize);
        Detector solution = new SolutionDetectorImpl(boardSize);

        assert extensibilityDetector.getCanExtendPostcondition() == solution.getIsDetectedPrecondition() :
                "Extensibility postcondition != solution precondition!";

        Detector viability = solution.getIsDetectedPrecondition();

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(boardSize, extensibilityDetector,
                viability, solution);
        int expectedSize = 92;
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void nineQueensCount() {
        int boardSize = 9;

        ExtensibilityDetector extensibilityDetector = new ExtensibilityDetectorImpl(boardSize);
        Detector solution = new SolutionDetectorImpl(boardSize);

        assert extensibilityDetector.getCanExtendPostcondition() == solution.getIsDetectedPrecondition() :
                "Extensibility postcondition != solution precondition!";

        Detector viability = solution.getIsDetectedPrecondition();

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(boardSize, extensibilityDetector,
                viability, solution);
        int expectedSize = 352;
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void tenQueensCount() {
        int boardSize = 10;

        ExtensibilityDetector extensibilityDetector = new ExtensibilityDetectorImpl(boardSize);
        Detector solution = new SolutionDetectorImpl(boardSize);

        assert extensibilityDetector.getCanExtendPostcondition() == solution.getIsDetectedPrecondition() :
                "Extensibility postcondition != solution precondition!";

        Detector viability = solution.getIsDetectedPrecondition();

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(boardSize, extensibilityDetector,
                viability, solution);
        int expectedSize = 724;
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void elevenQueensCount() {
        int boardSize = 11;

        ExtensibilityDetector extensibilityDetector = new ExtensibilityDetectorImpl(boardSize);
        Detector solution = new SolutionDetectorImpl(boardSize);

        assert extensibilityDetector.getCanExtendPostcondition() == solution.getIsDetectedPrecondition() :
                "Extensibility postcondition != solution precondition!";

        Detector viability = solution.getIsDetectedPrecondition();

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(boardSize, extensibilityDetector,
                viability, solution);
        int expectedSize = 2680;
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Test
    public void twelveQueensCount() {
        int boardSize = 12;

        ExtensibilityDetector extensibilityDetector = new ExtensibilityDetectorImpl(boardSize);
        Detector solution = new SolutionDetectorImpl(boardSize);

        assert extensibilityDetector.getCanExtendPostcondition() == solution.getIsDetectedPrecondition() :
                "Extensibility postcondition != solution precondition!";

        Detector viability = solution.getIsDetectedPrecondition();

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(boardSize, extensibilityDetector,
                viability, solution);
        int expectedSize = 14200;
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }
}
