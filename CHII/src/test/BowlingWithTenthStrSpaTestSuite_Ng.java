package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bowling.Mark;
import bowling.SinglePlayerBowlingScoreboard;
import bowling.SinglePlayerBowlingScoreboardImpl_Ng;
import bowling.AssignmentMetaData;

public class BowlingWithTenthStrSpaTestSuite_Ng {
    /* Instance Getters */
    private SinglePlayerBowlingScoreboard getScoreboard() {
        return new SinglePlayerBowlingScoreboardImpl_Ng(BOWLER_NAME);
    }

    private SinglePlayerBowlingScoreboard getScoreboard(String testName) {
        if (testName == null) {
            return new SinglePlayerBowlingScoreboardImpl_Ng(testName);
        }
        SinglePlayerBowlingScoreboard board = new SinglePlayerBowlingScoreboardImpl_Ng(BOWLER_NAME);
        if (!Character.isDigit(testName.charAt(testName.length() - 1))) {
            return board;
        }
        String sub = testName.substring(testName.indexOf(TEST_DELIMETER_CHAR) + 1);
        sub = sub.substring(sub.indexOf(TEST_DELIMETER_CHAR) + 1);
        String[] auxilary = sub.split(TEST_DELIMETER_STR);
        int hold = auxilary.length;
        int[] pinsKnockedDownArray = new int[hold];
        for (int i = 0; i < hold; i++) {
            pinsKnockedDownArray[i] = Integer.parseInt(auxilary[i]);
        }
        for (int curVal : pinsKnockedDownArray) {
            board.recordRoll(curVal);
        }
        return board;
    }

    /* Fast S.O.P for instance toString if available */
    private void print(SinglePlayerBowlingScoreboard board) {
        System.out.println(board.toString());
    }

    /* Testing dividers */
    private String testName;

    private void bookend(String testName) {
        System.out.println(String.format("******NOW TESTING %s******", testName.toUpperCase()));
    }

    private void bookendd(String testName) {
        System.out.println(String.format("******DONE TESTING %s******\n", testName.toUpperCase()));
    }

    /* Constants */
    private String BOWLER_NAME = "Jeffrey Ng";
    private static final char TEST_DELIMETER_CHAR = '_';
    private static final String TEST_DELIMETER_STR = "_";


    @Test(expected = AssertionError.class)
    public void getCurrentBall_pinsKnockedDownArray_0_1_0_2_0_3_0_4_0_5_0_6_0_7_0_8_0_9_0_1_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        // Should fail as the game is now over
        board.getCurrentBall();

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void getCurrentBall_pinsKnockedDownArray_0_1_0_2_0_3_0_4_0_5_0_6_0_7_0_8_0_9_0_1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        // Should fail as the game is now over
        board.getCurrentBall();

        bookendd(testName);
    }

    @Test
    public void getCurrentBall_pinsKnockedDownArray_1_2_1_2_1_2_1_2_1_2_1_2_1_2_1_2_1_2_1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 2;
        int actual = board.getCurrentBall();
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getCurrentBall_pinsKnockedDownArray_1_2_1_2_1_2_1_2_1_2_1_2_1_2_1_2_1_2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 1;
        int actual = board.getCurrentBall();
        assertEquals(expected, actual);
        print(board);

        bookendd(testName);
    }

    @Test
    public void getCurrentBall_pinsKnockedDownArray_1_2_1_2_1_2_1_2_1_2_1_2_1_2_1_2_1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 2;
        int actual = board.getCurrentBall();
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void getCurrentBall_pinsKnockedDownArray_1_2_1_2_1_2_1_2_1_2_1_2_1_2_1_2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 1;
        int actual = board.getCurrentBall();
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void getCurrentBall_pinsKnockedDownArray_10() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 1;
        int actual = board.getCurrentBall();
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void getCurrentBall_pinsKnockedDownArray_10_10() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 1;
        int actual = board.getCurrentBall();
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void getCurrentBall_pinsKnockedDownArray_10_10_1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 2;
        int actual = board.getCurrentBall();
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void getCurrentBall_pinsKnockedDownArray_10_10_1_2_1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 2;
        int actual = board.getCurrentBall();
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void getCurrentBall_pinsKnockedDownArray_10_10_1_2_10() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 1;
        int actual = board.getCurrentBall();
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void isGameOver_pinsKnockedDownArray_0_1_0_2_0_3_0_4_0_5_0_6_0_7_0_8_0_9_10_1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        assertFalse(board.isGameOver());
        print(board);

        bookendd(testName);
    }

    @Test
    public void isGameOver_pinsKnockedDownArray_0_1_0_2_0_3_0_4_0_5_0_6_0_7_0_8_0_9_10_10() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        assertFalse(board.isGameOver());

        bookendd(testName);
    }

    @Test
    public void isGameOver_pinsKnockedDownArray_0_1_0_2_0_3_0_4_0_5_0_6_0_7_0_8_0_9_10_10_10() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        assertTrue(board.isGameOver());

        bookendd(testName);
    }

    @Test
    public void isGameOver_pinsKnockedDownArray_0_1_0_2_0_3_0_4_0_5_0_6_0_7_0_8_0_9_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        assertFalse(board.isGameOver());

        bookendd(testName);
    }

    @Test
    public void isGameOver_pinsKnockedDownArray_0_1_0_2_0_3_0_4_0_5_0_6_0_7_0_8_0_9_0_1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        assertTrue(board.isGameOver());

        bookendd(testName);
    }

    @Test
    public void isGameOver_pinsKnockedDownArray_0_1_0_2_0_3_0_4_0_5_0_6_0_7_0_8_0_9_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        assertFalse(board.isGameOver());

        bookendd(testName);
    }

    @Test
    public void isGameOver_pinsKnockedDownArray_10_10_10_10_10_10_10_10_10_10_10_10() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        assertTrue(board.isGameOver());

        bookendd(testName);
    }


    @Test(expected = AssertionError.class)
    public void testGetMark21_pinsKnockedDownArray_10_9_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.ZERO;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark11_pinsKnockedDownArray_10_9_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.EMPTY;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark12_pinsKnockedDownArray_10_9_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.STRIKE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark31_pinsKnockedDownArray_0_1_0_2_0_3_0_4_0_5() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.ZERO;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark32_pinsKnockedDownArray_0_1_0_2_0_3_0_4_0_5() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.THREE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark51_pinsKnockedDownArray_1_2_1_2_1_2_1_2_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.THREE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark52_pinsKnockedDownArray_1_2_1_2_1_2_1_2_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.SPARE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark61_pinsKnockedDownArray_1_2_1_2_1_2_1_2_3_7_10() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.EMPTY;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark62_pinsKnockedDownArray_1_2_1_2_1_2_1_2_3_7_10() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.STRIKE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark11_pinsKnockedDownArray_3_7_3_7_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.THREE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark12_pinsKnockedDownArray_3_7_3_7_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.SPARE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark21_pinsKnockedDownArray_3_7_3_7_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.THREE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark22_pinsKnockedDownArray_3_7_3_7_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.SPARE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark31_pinsKnockedDownArray_3_7_3_7_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.THREE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark32_pinsKnockedDownArray_3_7_3_7_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.SPARE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }


    @Test
    public void testGetMark11_pinsKnockedDownArray_10_3_7_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.EMPTY;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark12_pinsKnockedDownArray_10_3_7_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.STRIKE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark11_pinsKnockedDownArray_0_10_3_7_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.ZERO;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void testGetMark12_pinsKnockedDownArray_0_10_3_7_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.SPARE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        bookendd(testName);
    }


    @Test
    public void getCurrentBall_pinsKnockedDownArray_0_1_0_2_0_3_0_4_0_5_0_6_0_7_0_8_0_9_10_1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int expected = 3;
        int actual = board.getCurrentBall();
        assertEquals(expected, actual);

        bookendd(testName);
    }
//	@Test
//	public void testGetMark_pinsKnockedDownArray_10_10_10_10_10_10_10_10_10_10_10_10() {
//		testName = Thread.currentThread().getStackTrace()[1].getMethodName();
//		bookend(testName);
//
//		SinglePlayerBowlingScoreboard board = getScoreboard(testName);
//
////		for (int frame = 0; frame < 10; frame++) {
////			Mark leftBox = board.getMark(frame, 1);
////			Mark rightBox = board.getMark(frame, 2);
////			assertEquals(Mark.EMPTY, leftBox);
////			assertEquals(Mark.STRIKE, rightBox);
////		}
//		
//		assertEquals(Mark.EMPTY, board.getMark(1, 1));
//		
//		bookendd(testName);
//	}

}
