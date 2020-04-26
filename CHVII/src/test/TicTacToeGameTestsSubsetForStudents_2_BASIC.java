package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import sequence.Sequence;
import sequence.SequenceImpl;
import tictactoe.Mark;
import tictactoe.Player;
import tictactoe.TicTacToeGame;
import tictactoe.TicTacToeGameImpl_Ng;

public class TicTacToeGameTestsSubsetForStudents_2_BASIC extends TicTacToeGameTestsSubsetForStudents_1_ENVIRONMENT {
    public static final String TICTACTOE_PACKAGE_NAME = "tictactoe";
    public static final String TICTACTOEGAME_PREFIX = "TicTacToeGame";
    public static final String SUFFIX = "";

    protected TicTacToeGame ticTacToeGame_STUDENT;

    /**********************************************************************************************************************/
    private void initializeBoard(Sequence<Integer> sequence) {
        ticTacToeGame_STUDENT = new TicTacToeGameImpl_Ng(sequence);
    }

    /**********************************************************************************************************************/

    @Points(value = 5)
    @Test(timeout = 3000)
    public void emptyBoardTest() {
        TEST_GOAL_MESSAGE = "Get all calls to getMark() correct for the empty game";

        Sequence<Integer> sequence = new SequenceImpl<Integer>();

        initializeBoard(sequence);

        for (int i = 0; i < TicTacToeGame.ROW_COUNT; i++) {
            for (int j = 0; j < TicTacToeGame.COLUMN_COUNT; j++) {
                Mark mark_ij = ticTacToeGame_STUDENT.getMark(i, j);
                assertEquals(TicTacToeGame.EMPTY, mark_ij);
            }
        }
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void oneSymbolTest() {
        TEST_GOAL_MESSAGE = "Get all calls to getMark() correct for a particular game with one mark";

        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(2);

        initializeBoard(sequence);

        int x1_i = 0;
        int x1_j = 2;

        for (int i = 0; i < TicTacToeGame.ROW_COUNT; i++) {
            for (int j = 0; j < TicTacToeGame.COLUMN_COUNT; j++) {
                Mark mark_ij = ticTacToeGame_STUDENT.getMark(i, j);
                if (i == x1_i && j == x1_j) {
                    assertEquals(Mark.X1, mark_ij);
                } else {
                    assertEquals(TicTacToeGame.EMPTY, mark_ij);
                }
            }
        }
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void twoSymbolTest() {
        TEST_GOAL_MESSAGE = "Get all calls to getMark() correct for a particular game with two marks";

        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(4);
        sequence.extend(8);

        initializeBoard(sequence);

        int x1_i = 1;
        int x1_j = 1;

        int o1_i = 2;
        int o1_j = 2;

        for (int i = 0; i < TicTacToeGame.ROW_COUNT; i++) {
            for (int j = 0; j < TicTacToeGame.COLUMN_COUNT; j++) {
                Mark mark_ij = ticTacToeGame_STUDENT.getMark(i, j);
                if (i == x1_i && j == x1_j) {
                    assertEquals(Mark.X1, mark_ij);
                } else if (i == o1_i && j == o1_j) {
                    assertEquals(Mark.O1, mark_ij);
                } else {
                    assertEquals(TicTacToeGame.EMPTY, mark_ij);
                }
            }
        }
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void threeSymbolTest() {
        TEST_GOAL_MESSAGE = "Get all calls to getMark() correct for a particular game with three marks";

        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(1);
        sequence.extend(2);
        sequence.extend(3);

        initializeBoard(sequence);

        int x1_i = 0;
        int x1_j = 1;

        int o1_i = 0;
        int o1_j = 2;

        int x2_i = 1;
        int x2_j = 0;

        for (int i = 0; i < TicTacToeGame.ROW_COUNT; i++) {
            for (int j = 0; j < TicTacToeGame.COLUMN_COUNT; j++) {
                Mark mark_ij = ticTacToeGame_STUDENT.getMark(i, j);
                if (i == x1_i && j == x1_j) {
                    assertEquals(Mark.X1, mark_ij);
                } else if (i == o1_i && j == o1_j) {
                    assertEquals(Mark.O1, mark_ij);
                } else if (i == x2_i && j == x2_j) {
                    assertEquals(Mark.X2, mark_ij);
                } else {
                    String failureMessage = String.format("Expected EMPTY, actual %s, i = %s, j = %s", mark_ij, i, j);
                    assertEquals(failureMessage, TicTacToeGame.EMPTY, mark_ij);
                }
            }
        }
    }

    @Points(value = 5)
    @Test(timeout = 3000)
    public void earlyWinnerTest() {
        TEST_GOAL_MESSAGE = "Get getWinner() and isGameOver() correct for a particular game with an early winner";

        Sequence<Integer> sequence = new SequenceImpl<Integer>();
        sequence.extend(3);
        sequence.extend(8);
        sequence.extend(4);
        sequence.extend(7);
        sequence.extend(5);

        initializeBoard(sequence);

        assertEquals(Player.X, ticTacToeGame_STUDENT.getWinner());
        assertTrue(ticTacToeGame_STUDENT.isGameOver());
    }

    @Points(value = 5)
    @Test(expected = AssertionError.class, timeout = 3000)
    public void setMarkOutOfBounds() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();

        initializeBoard(sequence);

        final int ROW = 4;
        final int COLUMN = 3;

        TEST_GOAL_MESSAGE = "Throw an AssertionError on the call " + "setMark(" + ROW + ", " + COLUMN + ")";
        ticTacToeGame_STUDENT.setMark(ROW, COLUMN);
    }

    @Points(value = 5)
    @Test(expected = AssertionError.class, timeout = 3000)
    public void setMarkOutOfBounds2() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();

        initializeBoard(sequence);

        final int ROW = 0;
        final int COLUMN = 13;

        TEST_GOAL_MESSAGE = "Throw an AssertionError on the call " + "setMark(" + ROW + ", " + COLUMN + ")";
        ticTacToeGame_STUDENT.setMark(ROW, COLUMN);
    }

    @Points(value = 5)
    @Test(expected = AssertionError.class, timeout = 3000)
    public void setMarkTwice() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();

        initializeBoard(sequence);

        final int ROW = 1;
        final int COLUMN = 2;

        TEST_GOAL_MESSAGE = "Correctly throw an AssertionError on the call " + "setMark(" + ROW + ", " + COLUMN + ")";

        ticTacToeGame_STUDENT.setMark(ROW, COLUMN);
        ticTacToeGame_STUDENT.setMark(ROW, COLUMN);
    }

    @Points(value = 5)
    @Test(expected = AssertionError.class, timeout = 3000)
    public void setMarkAfterGameOver() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();

        initializeBoard(sequence);

        TEST_GOAL_MESSAGE = "Correctly throw an AssertionError on the call " + "setMark(" + 1 + ", " + 2 + ")";

        ticTacToeGame_STUDENT.setMark(0, 0);
        ticTacToeGame_STUDENT.setMark(1, 0);
        ticTacToeGame_STUDENT.setMark(0, 1);
        ticTacToeGame_STUDENT.setMark(1, 1);
        ticTacToeGame_STUDENT.setMark(0, 2);
        ticTacToeGame_STUDENT.setMark(1, 2);
    }

    @Points(value = 5)
    @Test(expected = AssertionError.class, timeout = 3000)
    public void setMarkAfterGameOver2() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();

        initializeBoard(sequence);

        TEST_GOAL_MESSAGE = "Correctly throw an AssertionError on the call " + "setMark(" + 2 + ", " + 0 + ")";

        ticTacToeGame_STUDENT.setMark(1, 1);
        ticTacToeGame_STUDENT.setMark(0, 0);
        ticTacToeGame_STUDENT.setMark(2, 2);
        ticTacToeGame_STUDENT.setMark(0, 2);
        ticTacToeGame_STUDENT.setMark(1, 0);
        ticTacToeGame_STUDENT.setMark(0, 1);
        ticTacToeGame_STUDENT.setMark(2, 0);
    }

    @Points(value = 5)
    @Test(expected = AssertionError.class, timeout = 3000)
    public void getMarkOutOfBounds() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();

        initializeBoard(sequence);

        final int ROW = 2;
        final int COLUMN = 4;

        TEST_GOAL_MESSAGE = "Correctly throw an AssertionError on the call " + "getMark(" + ROW + ", " + COLUMN + ")";

        ticTacToeGame_STUDENT.setMark(ROW, COLUMN);
    }

    @Points(value = 5)
    @Test(expected = AssertionError.class, timeout = 3000)
    public void emptyBoardGetWinner() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();

        initializeBoard(sequence);

        TEST_GOAL_MESSAGE = "Correctly throw an AssertionError on the call " + ".getWinner()";

        ticTacToeGame_STUDENT.getWinner();
    }

    @Points(value = 5)
    @Test(expected = AssertionError.class, timeout = 3000)
    public void twoRoundsGetWinner() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();

        initializeBoard(sequence);

        TEST_GOAL_MESSAGE = "Correctly throw an AssertionError on the call " + "getWinner()";

        ticTacToeGame_STUDENT.setMark(0, 0);
        ticTacToeGame_STUDENT.setMark(1, 1);
        ticTacToeGame_STUDENT.getWinner();
    }

    @Points(value = 5)
    @Test(expected = AssertionError.class, timeout = 3000)
    public void threeRoundsGetWinner() {
        Sequence<Integer> sequence = new SequenceImpl<Integer>();

        initializeBoard(sequence);

        TEST_GOAL_MESSAGE = "Correctly throw an AssertionError on the call " + "getWinner()";

        ticTacToeGame_STUDENT.setMark(0, 0);
        ticTacToeGame_STUDENT.setMark(1, 1);
        ticTacToeGame_STUDENT.setMark(2, 0);
        ticTacToeGame_STUDENT.getWinner();
    }
}
