// Authored by Jeffrey Ng
package test;

import static org.junit.Assert.*;

import bowling.Mark;
import bowling.SinglePlayerBowlingScoreboard;
import bowling.SinglePlayerBowlingScoreboardImpl_Ng;
import bowling.AssignmentMetaData;

import java.util.stream.Collectors;

import org.junit.Test;

public class BowlingNoTenthOrCharsTestSuite_Ng {
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
        System.out.println(board.toString() + '\n');
    }

    /* Testing dividers */
    private String testName;

    private void bookend(String testName) {
        System.out.println(String.format("******NOW TESTING %s******", testName));
    }

    private void bookendd(String testName) {
        System.out.println(String.format("******DONE TESTING %s******\n", testName));
    }

    /* Constants */
    private String BOWLER_NAME = "Jeffrey Ng";
    private static final char TEST_DELIMETER_CHAR = '_';
    private static final String TEST_DELIMETER_STR = "_";


    @Test(expected = AssertionError.class)
    public void recordRollSingle_pinsKnockedArray_11() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void recordRollSingle_pinsKnockedArray_Neg1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard();
        board.recordRoll(-1);

        bookendd(testName);
    }

    @Test
    public void recordRollSingle_pinsKnockedArray_3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        print(board);

        bookendd(testName);
    }

    @Test
    public void recordRollSingle_pinsKnockedArray_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        print(board);

        bookendd(testName);
    }

    @Test
    public void recordRollSingle_pinsKnockedArray_9() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        print(board);

        bookendd(testName);
    }


    @Test(expected = AssertionError.class)
    public void recordRollMulti_pinsKnockedArray_1_3_0_0_5_8_3_4_2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void recordRollMulti_pinsKnockedArray_7_8_4() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void recordRollMulti_pinsKnockedArray_5_8_5_1_2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void recordRollMulti_pinsKnockedArray_1_4_8_8_4_5_6_8() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void recordRollMulti_pinsKnockedArray_2_3_7_8_1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        bookendd(testName);
    }


    @Test
    public void recordRollMulti_pinsKnockedArray_3_3_4_1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        print(board);

        bookendd(testName);
    }

    @Test
    public void recordRollMulti_pinsKnockedArray_6_2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        print(board);

        bookendd(testName);
    }

    public void recordRollMulti_pinsKnockedArray_8_0_0_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        print(board);

        bookendd(testName);
    }

    @Test
    public void recordRollMulti_pinsKnockedArray_6_2_5_2_0_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        print(board);

        bookendd(testName);
    }

    @Test
    public void recordRollMulti_pinsKnockedArray_6_2_4_0_1_2_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        print(board);

        bookendd(testName);
    }


    @Test
    public void getCurrentFrame_pinsKnockedArray_() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 1;
        int actual = board.getCurrentFrame();
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getCurrentFrame_pinsKnockedArray_1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 1;
        int actual = board.getCurrentFrame();
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getCurrentFrame_pinsKnockedArray_1_3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 2;
        int actual = board.getCurrentFrame();
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getCurrentFrame_pinsKnockedArray_1_3_7() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 2;
        int actual = board.getCurrentFrame();
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }
    /*
     * bigger getCurrentFrame that pass
     * bigger getCurrentFrame that pass
     * bigger getCurrentFrame that pass
     * bigger getCurrentFrame that pass
     * bigger getCurrentFrame that pass
     */

    @Test
    public void getCurrentBall_pinsKnockedArray_() {
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
    public void getCurrentBall_pinsKnockedArray_1_6_2_0_2_2_0() {
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
    public void getCurrentBall_pinsKnockedArray_5_1_7_0() {
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
    public void getCurrentBall_pinsKnockedArray_8_1_1_2_5_4_1_7_0() {
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
    public void getCurrentBall_pinsKnockedArray_7_0_4_3() {
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
    public void getCurrentBall_pinsKnockedArray_8_0_0_1_6_1_1_4_2_1_6() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int expected = 2;
        int actual = board.getCurrentBall();
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void getScore0_pinsKnockedArray_9_0_3_3_2_3_3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 15;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void getScore4_pinsKnockedArray_9_0_3_3_2_3_3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 15;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void getScore1_pinsKnockedArray_9_0_3_3_2_3_3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 9;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getScore2_pinsKnockedArray_9_0_3_3_2_3_3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 15;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getScore3_pinsKnockedArray_9_0_3_3_2_3_3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 20;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }


    @Test(expected = AssertionError.class)
    public void getScore5_pinsKnockedArray_8_1_1_2_5_4_1_7_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 9;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void getScore1_pinsKnockedArray_8_1_1_2_5_4_1_7_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 9;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getScore2_pinsKnockedArray_8_1_1_2_5_4_1_7_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 12;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getScore3_pinsKnockedArray_8_1_1_2_5_4_1_7_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 21;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getScore4_pinsKnockedArray_8_1_1_2_5_4_1_7_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 29;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }


    @Test(expected = AssertionError.class)
    public void getScore6_pinsKnockedArray_8_0_0_1_6_1_1_4_2_1_6() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 6;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void getScore5_pinsKnockedArray_8_0_0_1_6_1_1_4_2_1_6() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 24;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getScore2_pinsKnockedArray_8_0_0_1_6_1_1_4_2_1_6() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 9;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getScore1_pinsKnockedArray_8_0_0_1_6_1_1_4_2_1_6() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 8;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getScore4_pinsKnockedArray_8_0_0_1_6_1_1_4_2_1_6() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 21;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }


    @Test(expected = AssertionError.class)
    public void getScore9_pinsKnockedArray_0_1_6_2_7_1_5() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 17;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void getScore4_pinsKnockedArray_0_1_6_2_7_1_5() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 17;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void getScore1_pinsKnockedArray_0_1_6_2_7_1_5() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 1;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getScore2_pinsKnockedArray_0_1_6_2_7_1_5() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 9;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void getScore3_pinsKnockedArray_0_1_6_2_7_1_5() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 17;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }


    @Test(expected = AssertionError.class)
    public void getScore3_pinsKnockedArray_2_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 15;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void getScore2_pinsKnockedArray_2_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 15;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void getScore9_pinsKnockedArray_2_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 15;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void getScore1_pinsKnockedArray_2_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 2;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    //	@Test(expected = AssertionError.class) // TODO New tests
    public void getScoreN_pinsKnockedArray_() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);

        int frameNumber = testName.charAt(testName.indexOf(TEST_DELIMETER_CHAR) - 1) - '0';
        int expected = 15;
        int actual = board.getScore(frameNumber);
        assertEquals(expected, actual);

        bookendd(testName);
    }


    @Test
    public void testGetMark11_pinsKnockedDownArray_6_0_9_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.SIX;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void testGetMark12_pinsKnockedDownArray_6_0_9_0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.ZERO;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void testGetMark11_pinsKnockedDownArray_1_2_1_2_1_2_1_2_1_2_1_2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.ONE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void testGetMark12_pinsKnockedDownArray_1_2_1_2_1_2_1_2_1_2_1_2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.TWO;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void testGetMark21_pinsKnockedDownArray_1_2_1_2_1_2_1_2_1_2_1_2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.ONE;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void testGetMark22_pinsKnockedDownArray_1_2_1_2_1_2_1_2_1_2_1_2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.TWO;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }

    @Test
    public void testGetMark32_pinsKnockedDownArray_1_2_1_2_1_2_1_2_1_2_1_2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        SinglePlayerBowlingScoreboard board = getScoreboard(testName);
        int index = testName.indexOf(TEST_DELIMETER_CHAR) - 1;
        int boxIndex = testName.charAt(index) - '0';
        int frameNumber = Character.isDigit(testName.charAt(index - 2)) ? 10 : testName.charAt(index - 1) - '0';
        Mark expected = Mark.TWO;
        Mark actual = board.getMark(frameNumber, boxIndex);
        assertEquals(expected, actual);

        print(board);

        bookendd(testName);
    }



	/*
		[6, 0, 9, 0]
		[1, 4, 1, 5]
		[2, 7, 2, 2, 6, 0]
	 */
}
