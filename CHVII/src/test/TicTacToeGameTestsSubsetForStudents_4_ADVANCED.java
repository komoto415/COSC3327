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
import tictactoe.Player;
import tictactoe.TicTacToeGame;
import tictactoe.detector.WinnerDetectorImpl_Skeleton;
import tictactoe.detector.XWinsBoardFullDetectorImpl_Skeleton;

public class TicTacToeGameTestsSubsetForStudents_4_ADVANCED extends TicTacToeGameTestsSubsetForStudents_3_INTERMEDIATE {
    protected static Detector getWinnerDetector(Player desiredWinner) {
        return new WinnerDetectorImpl_Skeleton(desiredWinner);
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void winnerDetector_emptyGame() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();

        Detector xWinnerDetector = getWinnerDetector(Player.X);
        Detector oWinnerDetector = getWinnerDetector(Player.O);
        Detector tieDetector = getWinnerDetector(TicTacToeGame.TIE);

        assertFalse(xWinnerDetector.isDetected(sequence));
        assertFalse(oWinnerDetector.isDetected(sequence));
        assertFalse(tieDetector.isDetected(sequence));
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void winnerDetector_almostFullGame() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(0);
        sequence.extend(1);
        sequence.extend(2);
        sequence.extend(5);
        sequence.extend(8);
        sequence.extend(7);
        sequence.extend(6);
        sequence.extend(3);

        Detector xWinnerDetector = getWinnerDetector(Player.X);
        Detector oWinnerDetector = getWinnerDetector(Player.O);
        Detector tieDetector = getWinnerDetector(TicTacToeGame.TIE);

        assertFalse(xWinnerDetector.isDetected(sequence));
        assertFalse(oWinnerDetector.isDetected(sequence));
        assertFalse(tieDetector.isDetected(sequence));
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void winnerDetector_finishedGame_XWinner() {
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

        Detector xWinnerDetector = getWinnerDetector(Player.X);
        Detector oWinnerDetector = getWinnerDetector(Player.O);
        Detector tieDetector = getWinnerDetector(TicTacToeGame.TIE);

        assertTrue(xWinnerDetector.isDetected(sequence));
        assertFalse(oWinnerDetector.isDetected(sequence));
        assertFalse(tieDetector.isDetected(sequence));
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void winnerDetector_finishedGame_OWinner() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(8);
        sequence.extend(0);
        sequence.extend(4);
        sequence.extend(1);
        sequence.extend(3);
        sequence.extend(2);

        Detector xWinnerDetector = getWinnerDetector(Player.X);
        Detector oWinnerDetector = getWinnerDetector(Player.O);
        Detector tieDetector = getWinnerDetector(TicTacToeGame.TIE);

        assertFalse(xWinnerDetector.isDetected(sequence));
        assertTrue(oWinnerDetector.isDetected(sequence));
        assertFalse(tieDetector.isDetected(sequence));
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void winnerDetector_finishedGame_Tie() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(4);
        sequence.extend(0);
        sequence.extend(8);
        sequence.extend(2);
        sequence.extend(1);
        sequence.extend(7);
        sequence.extend(3);
        sequence.extend(5);
        sequence.extend(6);

        Detector xWinnerDetector = getWinnerDetector(Player.X);
        Detector oWinnerDetector = getWinnerDetector(Player.O);
        Detector tieDetector = getWinnerDetector(TicTacToeGame.TIE);

        assertFalse(xWinnerDetector.isDetected(sequence));
        assertFalse(oWinnerDetector.isDetected(sequence));
        assertTrue(tieDetector.isDetected(sequence));
    }

    @Points(value = 5)
    @Test(timeout = 10000)
    public void allTicTacToeGamesWithXWinner() {
        ExtensibilityDetector extensibility = getGameNotOverAndGridPositionEmptyDetector();
        Detector solution = getWinnerDetector(Player.X);

        assert extensibility.getCanExtendPrecondition() == extensibility.getCanExtendPostcondition() :
                "Extensibility Pre != Post!";
        assert extensibility.getCanExtendPrecondition() == solution.getIsDetectedPrecondition() :
                "Extensibility Pre != Solution Post!";

        Detector viability = solution.getIsDetectedPrecondition();

        final int CELL_COUNT = TicTacToeGame.ROW_COUNT * TicTacToeGame.COLUMN_COUNT;

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(CELL_COUNT, extensibility, viability,
                solution);
        int expectedSize = 131184;

        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Points(value = 5)
    @Test(timeout = 10000)
    public void allTicTacToeGamesWithOWinner() {
        ExtensibilityDetector extensibility = getGameNotOverAndGridPositionEmptyDetector();
        Detector solution = getWinnerDetector(Player.O);

        assert extensibility.getCanExtendPrecondition() == extensibility.getCanExtendPostcondition() :
                "Extensibility Pre != Post!";
        assert extensibility.getCanExtendPrecondition() == solution.getIsDetectedPrecondition() :
                "Extensibility Pre != Solution Post!";

        Detector viability = solution.getIsDetectedPrecondition();

        final int CELL_COUNT = TicTacToeGame.ROW_COUNT * TicTacToeGame.COLUMN_COUNT;

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(CELL_COUNT, extensibility, viability,
                solution);
        int expectedSize = 77904;

        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Points(value = 5)
    @Test(timeout = 10000)
    public void allTicTacToeGamesWithoutWinner() {
        ExtensibilityDetector extensibility = getGameNotOverAndGridPositionEmptyDetector();
        final Player TIE = null;
        Detector solution = getWinnerDetector(TIE);

        assert extensibility.getCanExtendPrecondition() == extensibility.getCanExtendPostcondition() :
                "Extensibility Pre != Post!";
        assert extensibility.getCanExtendPrecondition() == solution.getIsDetectedPrecondition() :
                "Extensibility Pre != Solution Post!";

        Detector viability = solution.getIsDetectedPrecondition();

        final int CELL_COUNT = TicTacToeGame.ROW_COUNT * TicTacToeGame.COLUMN_COUNT;

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(CELL_COUNT, extensibility, viability,
                solution);
        int expectedSize = 46080;

        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void zeroWaysToFinishInATie() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(0);
        sequence.extend(2);
        sequence.extend(8);
        sequence.extend(6);

        //Student: Notice that extensibility could be strengthened to make the solver more efficient
        //by avoiding center square selection
        ExtensibilityDetector extensibility = getGameNotOverAndGridPositionEmptyDetector();
        final Player TIE = null;
        Detector solution = getWinnerDetector(TIE);

        assert extensibility.getCanExtendPrecondition() == extensibility.getCanExtendPostcondition() :
                "Extensibility Pre != Post!";
        assert extensibility.getCanExtendPrecondition() == solution.getIsDetectedPrecondition() :
                "Extensibility Pre != Solution Post!";

        Detector viability = solution.getIsDetectedPrecondition();

        final int CELL_COUNT = TicTacToeGame.ROW_COUNT * TicTacToeGame.COLUMN_COUNT;

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve_aux(sequence, CELL_COUNT,
                extensibility, viability, solution);
        int expectedSize = 0;
        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }

    @Points(value = 5)
    @Test(timeout = 10000)
    public void allTicTacToeGamesXWinsBoardFull() {
        ExtensibilityDetector extensibility = getGameNotOverAndGridPositionEmptyDetector();
        Detector solution = XWinsBoardFullDetectorImpl_Skeleton.SINGLETON;

        assert extensibility.getCanExtendPrecondition() == extensibility.getCanExtendPostcondition() :
                "Extensibility Pre != Post!";
        assert extensibility.getCanExtendPrecondition() == solution.getIsDetectedPrecondition() :
                "Extensibility Pre != Solution Post!";

        Detector viability = solution.getIsDetectedPrecondition();

        final int CELL_COUNT = TicTacToeGame.ROW_COUNT * TicTacToeGame.COLUMN_COUNT;

        Set<Sequence<Integer>> solutionSet = BacktrackingWithPruningSolver.solve(CELL_COUNT, extensibility, viability,
                solution);
        int expectedSize = 81792;

        String failureMessage = String.format("solutionSet.size() = %s <> %s = expectedSize", solutionSet.size(),
                expectedSize);
        assertEquals(failureMessage, expectedSize, solutionSet.size());
    }
}
