package test;

import static org.junit.Assert.*;

import org.junit.Test;

import tictactoe.TicTacToeBoard;
import tictactoe.TicTacToeBoardImpl_Ng;
import tictactoe.Mark;

public class TicTacToeBoardImplTest {

    private TicTacToeBoard getTicTacToeBoard() {
        return new TicTacToeBoardImpl_Ng();
    }

    private static final int ROW_COUNT = TicTacToeBoard.ROW_COUNT;
    private static final int COLUMN_COUNT = TicTacToeBoard.COLUMN_COUNT;
    private static final Mark X = Mark.X, O = Mark.O;
    private String testName;
    private String ef = " expected failure";

    private void failed() {
        System.out.println("You should've failed");
    }

    private void print(TicTacToeBoard board) {
        System.out.println(board.toString());
    }

    private void bookend(String testName) {
        System.out.println(String.format("******NOW TESTING %s******", testName.toUpperCase()));
    }

    private void bookendd(String testName) {
        System.out.println(String.format("******DONE TESTING %s******\n", testName.toUpperCase()));
    }

    private int[] indexToRowColumn(int index) {
        return new int[]{index / 3, index % 3};
    }

    @Test(expected = AssertionError.class)
    public void setMark_bad_row_lower() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(-1, 1);
    }

    @Test(expected = AssertionError.class)
    public void setMark_bad_row_upper() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(5, 1);
    }

    @Test(expected = AssertionError.class)
    public void setMark_bad_col_lower() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(1, -11);
    }

    @Test(expected = AssertionError.class)
    public void setMark_bad_col_upper() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(1, Integer.MAX_VALUE);
    }

    @Test
    public void test() {

    }

    @Test(expected = AssertionError.class)
    public void occupancySequential() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName + ef);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 0);
    }

    @Test(expected = AssertionError.class)
    public void occupancynNonSequential() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName + ef);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(1, 0);
        board.setMark(0, 0);
    }

    @Test
    public void no_winner() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(1, 0);
        board.setMark(0, 1);
        board.setMark(1, 1);
        board.setMark(2, 0);
        board.setMark(0, 2);
        board.setMark(2, 1);
        board.setMark(2, 2);
        board.setMark(1, 2);
        print(board);

        assertEquals(null, board.getTurn());
        assertTrue(board.isGameOver());
        assertEquals(null, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void pregame_board1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 1);
        board.setMark(2, 2);
        board.setMark(2, 0);
        board.setMark(1, 1);
        board.setMark(0, 2);
        board.setMark(1, 0);
        print(board);

        assertEquals(X, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            failed();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test
    public void pregame_board4() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(1, 1);
        board.setMark(0, 1);
        board.setMark(0, 2);
        board.setMark(2, 2);
        board.setMark(2, 0);
        print(board);

        assertEquals(null, board.getTurn());
        assertTrue(board.isGameOver());
        assertEquals(O, board.getWinner());

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void pregame_board5() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName + ef);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(1, 0);
        board.setMark(0, 1);
        board.setMark(1, 1);
        board.setMark(0, 2);
        board.setMark(1, 2);
    }

    @Test
    public void pregame_board6() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 1);
        print(board);

        assertEquals(O, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            failed();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test
    public void getMark_board__7_1_3_8_6() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        assertEquals(null, board.getMark(0, 0));
        assertEquals(null, board.getMark(2, 1));

        board.setMark(2, 1);
        print(board);
        System.out.println();
        assertEquals(X, board.getMark(2, 1));

        board.setMark(0, 1);
        print(board);
        System.out.println();
        assertEquals(null, board.getMark(2, 0));

        board.setMark(1, 0);
        print(board);
        System.out.println();

        board.setMark(2, 2);
        print(board);
        System.out.println();
        assertEquals(O, board.getMark(0, 1));
        assertEquals(O, board.getMark(2, 2));

        board.setMark(2, 0);
        print(board);
        System.out.println();
        assertEquals(X, board.getMark(2, 1));

        bookendd(testName);
    }

    @Test
    public void getNark_board__0_2_6_8_4_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        print(board);
        System.out.println();
        assertEquals(null, board.getMark(2, 2));
        assertEquals(X, board.getMark(0, 0));
        assertEquals(null, board.getMark(0, 2));

        board.setMark(0, 2);
        print(board);
        System.out.println();
        assertEquals(O, board.getMark(0, 2));

        board.setMark(2, 0);
        print(board);
        System.out.println();

        board.setMark(2, 2);
        print(board);
        System.out.println();

        board.setMark(1, 1);
        print(board);
        System.out.println();
        assertEquals(X, board.getMark(2, 0));
        assertEquals(X, board.getMark(0, 0));
        assertEquals(O, board.getMark(2, 2));

        board.setMark(2, 1);
        print(board);
        System.out.println();
        assertEquals(O, board.getMark(2, 1));

        bookendd(testName);
    }

    @Test
    public void test_board__4_6_0_5_2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(1, 1);
        board.setMark(2, 0);
        board.setMark(0, 0);
        board.setMark(1, 2);
        board.setMark(0, 2);
        print(board);

        assertEquals(O, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            fail();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test
    public void test_board__0_2_6_8_4_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 2);
        board.setMark(2, 0);
        board.setMark(2, 2);
        board.setMark(1, 1);
        board.setMark(2, 1);
        print(board);

        assertEquals(X, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            failed();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test
    public void test_board__0_2_6_8_4_7_3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 2);
        board.setMark(2, 0);
        board.setMark(2, 2);
        board.setMark(1, 1);
        board.setMark(2, 1);
        board.setMark(1, 0);
        print(board);

        assertEquals(null, board.getTurn());
        assertTrue(board.isGameOver());
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void test_board__0_1_2_3_4_5_6() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 1);
        board.setMark(0, 2);
        board.setMark(1, 0);
        board.setMark(1, 1);
        board.setMark(1, 2);
        board.setMark(2, 0);
        print(board);

        assertEquals(null, board.getTurn());
        assertTrue(board.isGameOver());
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void test_board__0_1_2_3_4_5_6_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName + ef);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 1);
        board.setMark(0, 2);
        board.setMark(1, 0);
        board.setMark(1, 1);
        board.setMark(1, 2);
        board.setMark(2, 0);
        board.setMark(2, 1);
    }

    @Test
    public void test_board__3_5_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(1, 0);
        board.setMark(1, 2);
        board.setMark(2, 1);
        print(board);

        assertEquals(O, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            failed();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test
    public void test_board__7_1_3_8_6() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(2, 1);
        board.setMark(0, 1);
        board.setMark(1, 0);
        board.setMark(2, 2);
        board.setMark(2, 0);
        print(board);

        assertEquals(O, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            failed();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test
    public void test_board__3_0_7_8_5() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        int[] plays = new int[]{3, 0, 7, 8, 5};
        for (int i = 0; i < plays.length; i++) {
            int[] rc = indexToRowColumn(plays[i]);
            board.setMark(rc[0], rc[1]);
        }
        print(board);

        assertEquals(O, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            failed();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test
    public void test_board__0_1_6_8_5_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        int[] plays = new int[]{0, 1, 6, 8, 5, 7};
        for (int i = 0; i < plays.length; i++) {
            int[] rc = indexToRowColumn(plays[i]);
            board.setMark(rc[0], rc[1]);
        }
        print(board);

        assertEquals(X, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            failed();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test
    public void test_board__2_8_5_0_3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        int[] plays = new int[]{2, 8, 5, 0, 3};
        for (int i = 0; i < plays.length; i++) {
            int[] rc = indexToRowColumn(plays[i]);
            board.setMark(rc[0], rc[1]);
        }
        print(board);

        assertEquals(O, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            failed();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test
    public void test_board__6_2_3_8() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        int[] plays = new int[]{6, 2, 3, 8};
        for (int i = 0; i < plays.length; i++) {
            int[] rc = indexToRowColumn(plays[i]);
            board.setMark(rc[0], rc[1]);
        }
        print(board);

        assertEquals(X, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            failed();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void test_board__3_5_5() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName + ef);

        TicTacToeBoard board = getTicTacToeBoard();
        int[] plays = new int[]{3, 5, 5};
        for (int i = 0; i < plays.length; i++) {
            int[] rc = indexToRowColumn(plays[i]);
            board.setMark(rc[0], rc[1]);
        }
    }

    @Test(expected = AssertionError.class)
    public void test_board__7_7_5_1_0_8() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName + ef);

        TicTacToeBoard board = getTicTacToeBoard();
        int[] plays = new int[]{7, 7, 5, 1, 0, 8};
        for (int i = 0; i < plays.length; i++) {
            int[] rc = indexToRowColumn(plays[i]);
            board.setMark(rc[0], rc[1]);
        }
    }

    @Test
    public void test_board__1_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        int[] plays = new int[]{1, 0};
        for (int i = 0; i < plays.length; i++) {
            int[] rc = indexToRowColumn(plays[i]);
            board.setMark(rc[0], rc[1]);
        }
        print(board);

        assertEquals(X, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            failed();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test
    public void test_board__3_2_1_8() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        int[] plays = new int[]{3, 2, 1, 8};
        for (int i = 0; i < plays.length; i++) {
            int[] rc = indexToRowColumn(plays[i]);
            board.setMark(rc[0], rc[1]);
        }
        print(board);

        assertEquals(X, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            failed();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void test_board__3_1_1_3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        int[] plays = new int[]{3, 1, 1, 3};
        for (int i = 0; i < plays.length; i++) {
            int[] rc = indexToRowColumn(plays[i]);
            board.setMark(rc[0], rc[1]);
        }
    }

    @Test
    public void test_board__empty() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        print(board);
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                assertEquals(null, board.getMark(i, j));
            }
        }
        assertEquals(X, board.getTurn());
        assertFalse(board.isGameOver());
        try {
            board.getWinner();
            failed();
        } catch (AssertionError ae) {
        }

        bookendd(testName);
    }

    @Test
    public void rows_X_three_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(1, 0);
        board.setMark(0, 1);
        board.setMark(1, 1);
        board.setMark(0, 2);
        print(board);

        assertTrue(board.isGameOver());
        try {
            board.setMark(1, 2);
            failed();
        } catch (AssertionError ae) {
        }
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void rows_X_four_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(1, 0);
        board.setMark(2, 2);
        board.setMark(1, 1);
        board.setMark(0, 1);
        board.setMark(2, 0);
        board.setMark(0, 2);
        print(board);

        assertTrue(board.isGameOver());
        try {
            board.setMark(1, 2);
            failed();
        } catch (AssertionError ae) {
        }
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void rows_O_three_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(1, 0);
        board.setMark(2, 2);
        board.setMark(1, 1);
        board.setMark(0, 1);
        board.setMark(1, 2);
        print(board);

        assertTrue(board.isGameOver());
        try {
            board.setMark(0, 2);
            failed();
        } catch (AssertionError ae) {
        }
        assertEquals(O, board.getWinner());

        bookendd(testName);
    }

    public void rows_O_four_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(1, 0);
        board.setMark(2, 2);
        board.setMark(2, 1);
        board.setMark(2, 0);
        board.setMark(1, 1);
        board.setMark(0, 1);
        board.setMark(1, 2);
        print(board);

        assertTrue(board.isGameOver());
        try {
            board.setMark(0, 2);
            failed();
        } catch (AssertionError ae) {
        }
        assertEquals(O, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void rows_X_five_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(2, 2);
        board.setMark(0, 2);
        board.setMark(1, 0);
        board.setMark(1, 2);
        board.setMark(1, 1);
        board.setMark(2, 1);
        board.setMark(2, 0);
        board.setMark(0, 1);
        print(board);

        assertEquals(null, board.getTurn());
        assertTrue(board.isGameOver());
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void cols_X_three_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 1);
        board.setMark(1, 0);
        board.setMark(1, 1);
        board.setMark(2, 0);
        print(board);

        assertEquals(null, board.getTurn());
        assertTrue(board.isGameOver());
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void cols_X_four_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 1);
        board.setMark(0, 2);
        board.setMark(1, 2);
        board.setMark(1, 0);
        board.setMark(1, 1);
        board.setMark(2, 0);
        print(board);

        assertEquals(null, board.getTurn());
        assertTrue(board.isGameOver());
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void cols_O_three_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 1);
        board.setMark(1, 0);
        board.setMark(1, 1);
        board.setMark(2, 2);
        board.setMark(2, 1);
        print(board);

        assertEquals(null, board.getTurn());
        assertTrue(board.isGameOver());
        assertEquals(O, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void cols_O_four_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 1);
        board.setMark(0, 2);
        board.setMark(1, 2);
        board.setMark(1, 0);
        board.setMark(1, 1);
        board.setMark(2, 2);
        board.setMark(2, 1);
        print(board);

        assertEquals(null, board.getTurn());
        assertTrue(board.isGameOver());
        assertEquals(O, board.getWinner());

        bookend(testName);
    }

    @Test
    public void cols_X_five_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 1);
        board.setMark(0, 2);
        board.setMark(1, 0);
        board.setMark(1, 2);
        board.setMark(1, 1);
        board.setMark(2, 1);
        board.setMark(2, 0);
        board.setMark(2, 2);
        print(board);

        assertEquals(null, board.getTurn());
        assertTrue(board.isGameOver());
        assertEquals(X, board.getWinner());

        bookend(testName);
    }

    @Test
    public void diag_X_three_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 1);
        board.setMark(1, 1);
        board.setMark(0, 2);
        board.setMark(2, 2);
        print(board);

        assertTrue(board.isGameOver());
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void diag_X_four_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 1);
        board.setMark(1, 1);
        board.setMark(1, 2);
        board.setMark(1, 0);
        board.setMark(0, 2);
        board.setMark(2, 2);
        print(board);

        assertTrue(board.isGameOver());
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void diag_O_three_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 1);
        board.setMark(1, 1);
        board.setMark(0, 2);
        board.setMark(2, 2);
        board.setMark(2, 0);
        board.setMark(0, 0);
        print(board);

        assertTrue(board.isGameOver());
        assertEquals(O, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void diag_O_four_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 1);
        board.setMark(1, 1);
        board.setMark(0, 2);
        board.setMark(2, 2);
        board.setMark(1, 0);
        board.setMark(1, 2);
        board.setMark(2, 0);
        board.setMark(0, 0);
        print(board);

        assertTrue(board.isGameOver());
        assertEquals(O, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void diag_X_five_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 0);
        board.setMark(0, 1);
        board.setMark(0, 2);
        board.setMark(1, 0);
        board.setMark(1, 1);
        board.setMark(1, 2);
        board.setMark(2, 1);
        board.setMark(2, 0);
        board.setMark(2, 2);
        print(board);

        assertTrue(board.isGameOver());
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void anti_X_three_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 2);
        board.setMark(0, 0);
        board.setMark(1, 1);
        board.setMark(0, 1);
        board.setMark(2, 0);
        print(board);

        assertTrue(board.isGameOver());
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void anti_X_four_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(0, 2);
        board.setMark(0, 0);
        board.setMark(1, 1);
        board.setMark(1, 0);
        board.setMark(1, 2);
        board.setMark(0, 1);
        board.setMark(2, 0);
        print(board);

        assertTrue(board.isGameOver());
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void anti_O_three_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(2, 2);
        board.setMark(0, 2);
        board.setMark(0, 0);
        board.setMark(1, 1);
        board.setMark(0, 1);
        board.setMark(2, 0);
        print(board);

        assertTrue(board.isGameOver());
        assertEquals(O, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void anti_O_four_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(2, 1);
        board.setMark(0, 2);
        board.setMark(0, 0);
        board.setMark(1, 1);
        board.setMark(1, 0);
        board.setMark(1, 2);
        board.setMark(0, 1);
        board.setMark(2, 0);
        print(board);

        assertTrue(board.isGameOver());
        assertEquals(O, board.getWinner());

        bookendd(testName);
    }

    @Test
    public void anti_X_five_turn_win() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        TicTacToeBoard board = getTicTacToeBoard();
        board.setMark(2, 1);
        board.setMark(2, 2);
        board.setMark(0, 2);
        board.setMark(0, 0);
        board.setMark(1, 1);
        board.setMark(1, 0);
        board.setMark(1, 2);
        board.setMark(0, 1);
        board.setMark(2, 0);
        print(board);

        assertTrue(board.isGameOver());
        assertEquals(X, board.getWinner());

        bookendd(testName);
    }
}
