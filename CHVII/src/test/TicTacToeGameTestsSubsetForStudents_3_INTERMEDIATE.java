package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import backtracking.BacktrackingWithPruningSolver;
import backtracking.detector.Detector;
import backtracking.detector.ExtensibilityDetector;
import sequence.Sequence;
import sequence.SequenceImpl;
import tictactoe.TicTacToeGame;
import tictactoe.detector.GameNotOverAndGridPositionEmptyExtensibilityDetectorImpl_Skeleton;
import tictactoe.detector.GameOverDetectorImpl_Skeleton;

public class TicTacToeGameTestsSubsetForStudents_3_INTERMEDIATE extends TicTacToeGameTestsSubsetForStudents_2_BASIC {
    protected static ExtensibilityDetector getGameNotOverAndGridPositionEmptyDetector() {
        return GameNotOverAndGridPositionEmptyExtensibilityDetectorImpl_Skeleton.SINGLETON;
    }

    protected static Detector getGameOverDetector() {
        return GameOverDetectorImpl_Skeleton.SINGLETON;
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void gameOverDetector_almostFullGame() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(0);
        sequence.extend(1);
        sequence.extend(2);
        sequence.extend(5);
        sequence.extend(8);
        sequence.extend(7);
        sequence.extend(6);
        sequence.extend(3);

        Detector gameOverDetector = getGameOverDetector();

        assertFalse(gameOverDetector.isDetected(sequence));
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void gameOverDetector_finishedGame() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(0);
        sequence.extend(1);
        sequence.extend(2);
        sequence.extend(5);
        sequence.extend(8);
        sequence.extend(7);
        sequence.extend(6);
        sequence.extend(3);
        sequence.extend(4);

        Detector gameOverDetector = getGameOverDetector();

        assertTrue(gameOverDetector.isDetected(sequence));
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void extensibilityDetector() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(0);
        sequence.extend(1);
        sequence.extend(2);
        sequence.extend(5);
        sequence.extend(8);
        sequence.extend(7);
        sequence.extend(6);
        sequence.extend(3);

        ExtensibilityDetector extensibilityDetector = getGameNotOverAndGridPositionEmptyDetector();

        assertTrue(extensibilityDetector.canExtend(sequence, 4));
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void singleWayToFinish() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(0);
        sequence.extend(1);
        sequence.extend(2);
        sequence.extend(5);
        sequence.extend(8);
        sequence.extend(7);
        sequence.extend(6);
        sequence.extend(3);

        ExtensibilityDetector extensibility = getGameNotOverAndGridPositionEmptyDetector();
        Detector solution = getGameOverDetector();

        assert extensibility.getCanExtendPrecondition() == extensibility.getCanExtendPostcondition() :
                "Extensibility Pre != Post!";
        assert extensibility.getCanExtendPrecondition() == solution.getIsDetectedPrecondition() :
                "Extensibility Pre != Solution Post!";

        Detector viability = solution.getIsDetectedPrecondition();

        final int CELL_COUNT = TicTacToeGame.ROW_COUNT * TicTacToeGame.COLUMN_COUNT;

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve_aux(sequence, CELL_COUNT,
                extensibility, viability, solution);
        int expectedSize = 1;
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void twoWaysToFinish() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(0);
        sequence.extend(1);
        sequence.extend(2);
        sequence.extend(5);
        sequence.extend(8);
        sequence.extend(7);
        sequence.extend(6);
        ExtensibilityDetector extensibility = getGameNotOverAndGridPositionEmptyDetector();
        Detector solution = getGameOverDetector();

        assert extensibility.getCanExtendPrecondition() == extensibility.getCanExtendPostcondition() :
                "Extensibility Pre != Post!";
        assert extensibility.getCanExtendPrecondition() == solution.getIsDetectedPrecondition() :
                "Extensibility Pre != Solution Post!";

        Detector viability = solution.getIsDetectedPrecondition();

        final int CELL_COUNT = TicTacToeGame.ROW_COUNT * TicTacToeGame.COLUMN_COUNT;

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve_aux(sequence, CELL_COUNT,
                extensibility, viability, solution);
        int expectedSize = 2;
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Points(value = 5)
    @Test(timeout = 30000)
    public void allFinishedTicTacToeGames() {
        ExtensibilityDetector extensibility = getGameNotOverAndGridPositionEmptyDetector();
        Detector solution = getGameOverDetector();

        assert extensibility.getCanExtendPrecondition() == extensibility.getCanExtendPostcondition() :
                "Extensibility Pre != Post!";
        assert extensibility.getCanExtendPrecondition() == solution.getIsDetectedPrecondition() :
                "Extensibility Pre != Solution Post!";

        Detector viability = solution.getIsDetectedPrecondition();

        final int CELL_COUNT = TicTacToeGame.ROW_COUNT * TicTacToeGame.COLUMN_COUNT;

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(CELL_COUNT, extensibility, viability,
                solution);
        int expectedSize = 255168;

        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }
}
