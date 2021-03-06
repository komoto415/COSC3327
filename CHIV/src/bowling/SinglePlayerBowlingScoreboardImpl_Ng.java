// Authored by Jeffrey Ng
package bowling;

import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;


public class SinglePlayerBowlingScoreboardImpl_Ng implements SinglePlayerBowlingScoreboard, AssignmentMetaData {
    private static final int MAXIMUM_ROLLS = 21;    //Maximum rolls in a one player game
    private String playerName;
    private int[] pinsKnockedDownArray = new int[MAXIMUM_ROLLS];
    private int rollCount = 0;
    // private static final boolean DEBUGGING_FLAG = false;
    private static final String huh = "Something went wrong";

    private static final Set<Integer> validIntegers = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

    private boolean classInvariantCheck() {
        boolean validPlayerName = playerName != null;
        boolean isSubSet =
                validIntegers.containsAll(Arrays.stream(pinsKnockedDownArray).boxed().collect(Collectors.toSet()));
        boolean rollCountInRange = 0 <= rollCount && rollCount <= MAXIMUM_ROLLS;
        return validPlayerName && isSubSet && rollCountInRange;
    }

    public SinglePlayerBowlingScoreboardImpl_Ng(String playerName) {
        assert playerName != null : "playerName is null!";
        this.playerName = playerName;
    }

    @Override
    public String getPlayerName() {
        assert classInvariantCheck();
        return playerName;
    }

    @Override
    public boolean isGameOver() {
        assert classInvariantCheck();

        int boxesCovered = getBoxesCovered();
        boolean gameOver = false;
        // A game can never be over period if there are less then 20 boxes filled
        if (boxesCovered >= MAXIMUM_ROLLS - 1) {
            // PKDA: pinsKnockedDownArray
            int secondLastRollIndexPKDA = rollCount - 2;
            int lastRollIndexPKDA = secondLastRollIndexPKDA - 1;
            switch (boxesCovered) {
                // When the number of boxes filled are 20, the only way this declares the game over is if both box 19
                // AND box 20 is not a strike AND if box two doesn't land a spare
                case MAXIMUM_ROLLS - 1:
                    gameOver = !isStrikeByIndex(secondLastRollIndexPKDA) && !isSpareByIndex(secondLastRollIndexPKDA) &&
                               !isStrikeByIndex(lastRollIndexPKDA);
                    break;

                // The only possible value this should in theory is 21, which in any case doesn't matter because the
                // game is then over automatically.
                default:
                    gameOver = true;
                    break;
            }
        }

        assert classInvariantCheck();
        return gameOver;
    }

	/*	Precondition(s):
			1 <= frameNumber <= 10
			getCurrentFrame > frameNumber
			TODO need to make a check if when the frame contains a strike or spare, that it is scorable
	*/

	/*	Postcondition(s):
			Not sure yet
	*/

    /*	Alogrithm:
            One key thing here is that until we get to the tenth frame, every score comes in pairs. That meaning,
            every frame's score is a composite of its two box numbers. This means we can actually just itereate two
            at a time through our pinsKnockedDownArray list, with some offset that will need to be made if there is a
             strike.

            Another key thing to consider is that all that spare and strikes do, in terms of scoring at least, are
            check forward one or two rolls respectively. This means two things, 1) these are preconditions that will
            need to be executable, and 2) the initial scoring remains the same, with just an additional step in the
            spare or strike cases. No need to reinvent the wheel entirely.
    */
    @Override
    public int getScore(int frameNumber) {
        assert classInvariantCheck();

        assert 1 <= frameNumber && frameNumber <= 10;
        assert getCurrentFrame() > frameNumber;
        assert frameNumber < 10 && (getMark(frameNumber, 2) != Mark.STRIKE || getMark(frameNumber, 2) != Mark.SPARE);

        // We want to iterate all the way until the second box of the frame. Thsi way with our 2 index step, we won't
        // hit and overflow issue and still capture everything we need. Now this also changes when we haev to account
        // for tenth frame.
        int endItr = frameNumToBoxTwoOfFrame(frameNumber);
        int index = 0;
        int score = 0;
        System.out.print("EndItr: " + endItr);
        while (index < endItr && index < BOX_BEFORE_TENTH_FRAME) {
            score += currFrameBoxesSum(index);
            if (isSpareByIndex(index)) {
                score += scoreSpare(index);
            } else if (isStrikeByIndex(index)) {
                score += scoreStrike(index);
                index--;
                endItr--;
            }
            index += 2;
        }
        while (index < endItr) {
            score += pinsKnockedDownArray[index];
        }
        System.out.println("Score: " + score);

        assert classInvariantCheck();
        return score;
    }


	/*	Precondition(s):
			1 <= frameNumber <= 10
			1 <= boxIndex <= 3
			when 1 <= frameNumber < 10, 1 <= boxIndex <= 2
	*/

	/*	Postcondition(s):
			lol
	*/

    /*	Alogrithm:
            The naive approach is to recreate pinsKnockedDown array to just reflect an actual scoreboard in a Mark[]
            where strikes are haeded by Mark.EMPTY and box two of spares are Mark.SPARE
            This way we can just used array direct index accessing to get marks out of out array.
    */
    @Override
    public Mark getMark(int frameNumber, int boxIndex) {
        assert classInvariantCheck();

        assert 1 <= frameNumber && frameNumber <= 10;
        assert 1 <= boxIndex && boxIndex <= 3;

        assert isGameOver() || frameNumber <= getCurrentFrame();
        assert boxIndex == 3 ? frameNumber == 10 : true;
        assert boxWasCovered(frameNumber, boxIndex);

        Mark mark = naive(frameNumber, boxIndex);
//		Mark mark = ver2(frameNumber, boxIndex);

        assert classInvariantCheck();
        return mark;
    }

    @Override
    public int getCurrentFrame() {
        assert classInvariantCheck();

        assert !isGameOver();

        int boxesCovered = getBoxesCovered();

        int rv = boxesCovered < 2 ? 1 : boxesCovered / 2 + 1;

        assert classInvariantCheck();
        return rv;
    }

    @Override
    public int getCurrentBall() {
        assert classInvariantCheck();

        assert !isGameOver();

        int boxesCovered = getBoxesCovered();
        int currBall = 0;
        if (boxesCovered < 18) {
            currBall = boxesCovered % 2 == 0 ? BOX_ONE : BOX_TWO;
        } else {
            switch (boxesCovered) {
                case 18:
                    currBall = BOX_ONE;
                    break;
                case 19:
                    currBall = BOX_TWO;
                    break;
                case 20:
                    currBall = BOX_THR;
                    break;
            }
        }

        assert classInvariantCheck();
        return currBall;
    }

	/*	Precondition(s):
			!isGameOver()
			0 <= pinsKnockedDown <= 10
			10 - prevRollPinsKnockedDown >= pinsKnockedDown
			rollCount <= 21
	*/

    /*	Postcondition(s):
            Lol
    */
    @Override
    public void recordRoll(int pinsKnockedDown) {
        assert classInvariantCheck();
        System.out.println("Pins: " + pinsKnockedDown);

        System.out.println("Before: ");
        for (int curVal : pinsKnockedDownArray) {
            System.out.print(curVal + "=");
        }
        System.out.println();
        assert !isGameOver() : "Can't roll if game is over";
        assert rollCount <= MAXIMUM_ROLLS : "rollCount is out of bounds";
        assert 0 <= pinsKnockedDown && pinsKnockedDown <= INITIAL_PIN_COUNT : "pinsKnockedDown is out of bounrds";
        assert noOverflow(pinsKnockedDown) : "Cannot knock down more than a total of 10 pins";

        System.out.println("After: ");
        pinsKnockedDownArray[rollCount] = pinsKnockedDown;
        rollCount++;
        for (int curVal : pinsKnockedDownArray) {
            System.out.print(curVal + "=");
        }
        System.out.println();
        System.out.println();

        assert classInvariantCheck();
    }

    private static final int BOX_ONE = 1, BOX_TWO = 2, BOX_THR = 3;
    private static final int INITIAL_PIN_COUNT = 10;
    private static final int BOX_BEFORE_TENTH_FRAME = 18;

    private boolean boxWasCovered(int frameNumber, int boxIndex) {
        int boxesCovered = getBoxesCovered();
        int desiredBox = frameNumber * 2;
        if (boxIndex == BOX_ONE) {
            desiredBox--;
        } else if (boxIndex == BOX_THR) {
            desiredBox++;
        }
        return boxesCovered >= desiredBox;
    }

    private Mark ver2(int frameNumber, int boxIndex) {
        Mark mark = Mark.EMPTY;
		/* Notes
		frameNumber will tell us how far we need to look inside pinsKnockedDownArray
		similar to getBoxesCovered, we can keep track of how many strikes there are. Technically that's not what that
		method is doing inherently, but it kind of is.
		pinsKnockedDownArray = new int[] {10, 10, 1, 5, 0, 3}
										   0   1  2  3  4  5
		getMark(1,1)	==>		Mark.Empty
		getMark(1,2)	==>		Mark.Strike
		means the last potentially accessed index is 1

		getMark(3,1)	==>		Mark.ONE
		getMark(3,2)	==>		Mark.FIVE
		means the last potentially accessed index is 5
		WHY, say no strike spares
		pinsKnockedDownArray = new int[] {1, 2, 3, 4, 1, 5, 0, 3}
										  0  1  2  3  4  5, 6, 7

		if we hit a strike, end--
		itr = 0
		0 < 6	=> true
		isStrikeByIndex(0)	=>	true
		6--

		itr = 1
		1 < 5	=>	true
		isStrikeByIndex(1)	=> true
		5--

		itr = 2
		2 < 4


		*/
        int itr = 0;
        int end = frameNumber * 2;
        while (itr < end && end - itr > 1) {
            if (isStrikeByIndex(itr)) {
                end--;
            }
            itr++;
        }
        int boxOnePins = pinsKnockedDownArray[end--];
        int boxTwoPins = pinsKnockedDownArray[end -= 2];


        return mark;
    }

    private Mark naive(int frameNumber, int boxIndex) {
        System.out.println(frameNumber + " " + boxIndex);
        int coverage = frameNumber * 2;
        if (boxIndex == BOX_THR) {
            coverage++;
        }
        Mark[] boxVisual = new Mark[coverage];
        int strikeCount = 0;
        int pinsArrayIndex = 0;
        for (int i = 0; i < boxVisual.length; i++) {
            pinsArrayIndex = i - strikeCount;
            System.out.println(pinsArrayIndex);
            if (i < BOX_BEFORE_TENTH_FRAME) {
                if (isStrikeByIndex(pinsArrayIndex)) {
                    // System.out.println("Stike!");
                    boxVisual[i] = Mark.EMPTY;
                    boxVisual[i + 1] = Mark.STRIKE;
                    strikeCount++;
                    i++;
                } else if (isSpareByIndex(pinsArrayIndex) && pinsArrayIndex % 2 == 0) {
                    boxVisual[i] = Mark.translate(pinsKnockedDownArray[pinsArrayIndex]);
                    boxVisual[i + 1] = Mark.SPARE;
                    i++;
                } else {
                    boxVisual[i] = Mark.translate(pinsKnockedDownArray[pinsArrayIndex]);
                }
            } else {
                if (isStrikeByIndex(pinsArrayIndex)) {
                    boxVisual[i] = Mark.STRIKE;
                } else if (isSpareByIndex(pinsArrayIndex)) {
                    boxVisual[i] = Mark.translate(pinsKnockedDownArray[pinsArrayIndex]);
                    boxVisual[i + 1] = Mark.SPARE;
                    i++;
                } else {
                    boxVisual[i] = Mark.translate(pinsKnockedDownArray[pinsArrayIndex]);
                }
            }
            System.out.println(boxVisual[i]);
            System.out.println();
        }

        for (Mark curVal : boxVisual) {
            System.out.print("<" + curVal + ">");
        }
        System.out.println();

        Mark mark = Mark.EMPTY;
        int firstBoxOfFrameIndex = frameNumToBoxOneOfFrame(frameNumber);
        Mark boxOnePins = boxVisual[firstBoxOfFrameIndex];
        Mark boxTwoPins = boxVisual[firstBoxOfFrameIndex + 1];
        Mark boxThrPins = null;
        if (boxIndex == BOX_THR) {
            boxThrPins = boxVisual[firstBoxOfFrameIndex + 2];
        }
        mark = boxIndex != BOX_THR ? (boxIndex == BOX_ONE ? boxOnePins : boxTwoPins) : boxThrPins;
        return mark;
    }

    private int frameNumToBoxOneOfFrame(int frameNumber) {
        return (frameNumber - 1) * 2;
    }

    private int frameNumToBoxTwoOfFrame(int frameNumber) {
        return frameNumber * 2 - 1;
    }

    private boolean noOverflow(int pinsKnockedDown) {
        int boxesCovered = getBoxesCovered();
        int prevRollPinsKnockedDown = 0;
        int lastRollIndexPKDA = rollCount - 1;
        int secondLastRollIndexPKDA = lastRollIndexPKDA - 1;
        System.out.println("Boxes: " + boxesCovered);
        System.out.println("Roll Count: " + rollCount);
        if (boxesCovered <= BOX_BEFORE_TENTH_FRAME) {
            if (boxesCovered > 0 && boxesCovered % 2 == 1) {
                prevRollPinsKnockedDown = pinsKnockedDownArray[lastRollIndexPKDA];
            }
        } else {
            System.out.println(pinsKnockedDownArray[lastRollIndexPKDA]);
            if (!isStrikeByIndex(lastRollIndexPKDA) && !isSpareByIndex(secondLastRollIndexPKDA)) {
                prevRollPinsKnockedDown = pinsKnockedDownArray[lastRollIndexPKDA];
            }
        }
        System.out.println(prevRollPinsKnockedDown);
        return INITIAL_PIN_COUNT - prevRollPinsKnockedDown >= pinsKnockedDown;
    }

    private int getBoxesCovered() {
        int index = 0;
        int boxesCovered = 0;
        while (index < rollCount && boxesCovered < BOX_BEFORE_TENTH_FRAME) {
            if (isStrikeByIndex(index)) {
                boxesCovered++;
            }
            boxesCovered++;
            index++;
        }

        while (index < rollCount) {
            boxesCovered++;
            index++;
        }
        return boxesCovered;
    }

    private int currFrameBoxesSum(int index) {
        return pinsKnockedDownArray[index] + pinsKnockedDownArray[index + 1];
    }

    private int scoreSpare(int index) {
        return pinsKnockedDownArray[index + 2];
    }

    private int scoreStrike(int index) {
        return currFrameBoxesSum(index + 2);
    }

    private boolean isSpareByIndex(int index) {
        return pinsKnockedDownArray[index] + pinsKnockedDownArray[index + 1] == 10;
    }

    private boolean isStrikeByIndex(int index) {
        boolean rv = pinsKnockedDownArray[index] == 10;
        if (index > 0) {
            rv = pinsKnockedDownArray[index] == 10 && pinsKnockedDownArray[index - 1] != 0;
        }
        return rv;
    }


    private static final String VERTICAL_SEPARATOR = "#";
    private static final String HORIZONTAL_SEPARATOR = "#";
    private static final String LEFT_EDGE_OF_SMALL_SQUARE = "[";
    private static final String RIGHT_EDGE_OF_SMALL_SQUARE = "]";

    private String getScoreboardDisplay() {
        StringBuffer frameNumberLineBuffer = new StringBuffer();
        StringBuffer markLineBuffer = new StringBuffer();
        StringBuffer horizontalRuleBuffer = new StringBuffer();
        StringBuffer scoreLineBuffer = new StringBuffer();
        frameNumberLineBuffer.append(VERTICAL_SEPARATOR);

        markLineBuffer.append(VERTICAL_SEPARATOR);
        horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
        scoreLineBuffer.append(VERTICAL_SEPARATOR);

        for (int frameNumber = 1; frameNumber <= 9; frameNumber++) {
            frameNumberLineBuffer.append("  " + frameNumber + "  ");
            markLineBuffer.append(" ");
            markLineBuffer.append(getMark(frameNumber, 1));
            markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
            markLineBuffer.append(getMark(frameNumber, 2));
            markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);

            final int CHARACTER_WIDTH_SCORE_AREA = 5;
            for (int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) horizontalRuleBuffer.append(HORIZONTAL_SEPARATOR);
            if (isGameOver() || frameNumber < getCurrentFrame()) {
                int score = getScore(frameNumber);
                final int PADDING_NEEDED_BEHIND_SCORE = 1;
                final int PADDING_NEEDED_IN_FRONT_OF_SCORE =
                        CHARACTER_WIDTH_SCORE_AREA - ("" + score).length() - PADDING_NEEDED_BEHIND_SCORE;
                for (int i = 0; i < PADDING_NEEDED_IN_FRONT_OF_SCORE; i++) scoreLineBuffer.append(" ");
                scoreLineBuffer.append(score);
                for (int i = 0; i < PADDING_NEEDED_BEHIND_SCORE; i++) scoreLineBuffer.append(" ");
            } else {
                for (int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) scoreLineBuffer.append(" ");
            }

            frameNumberLineBuffer.append(VERTICAL_SEPARATOR);
            markLineBuffer.append(VERTICAL_SEPARATOR);
            horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
            scoreLineBuffer.append(VERTICAL_SEPARATOR);
        }
        //Frame 10:
        {
            final String THREE_SPACES = "   ";
            frameNumberLineBuffer.append(THREE_SPACES + 10 + THREE_SPACES);

            markLineBuffer.append(" ");
            markLineBuffer.append(getMark(10, 1));
            markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
            markLineBuffer.append(getMark(10, 2));
            markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);
            markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
            markLineBuffer.append(getMark(10, 3));
            markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);

            final int CHARACTER_WIDTH_SCORE_AREA = 8;
            for (int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) horizontalRuleBuffer.append(HORIZONTAL_SEPARATOR);
            if (isGameOver()) {
                int score = getScore(10);
                final int PADDING_NEEDED_BEHIND_SCORE = 1;
                final int PADDING_NEEDED_IN_FRONT_OF_SCORE =
                        CHARACTER_WIDTH_SCORE_AREA - ("" + score).length() - PADDING_NEEDED_BEHIND_SCORE;
                for (int i = 0; i < PADDING_NEEDED_IN_FRONT_OF_SCORE; i++) scoreLineBuffer.append(" ");
                scoreLineBuffer.append(score);
                for (int i = 0; i < PADDING_NEEDED_BEHIND_SCORE; i++) scoreLineBuffer.append(" ");
            } else {
                for (int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) scoreLineBuffer.append(" ");
            }

            frameNumberLineBuffer.append(VERTICAL_SEPARATOR);
            markLineBuffer.append(VERTICAL_SEPARATOR);
            horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
            scoreLineBuffer.append(VERTICAL_SEPARATOR);
        }

        return getPlayerName() + "\n" + horizontalRuleBuffer.toString() + "\n" + frameNumberLineBuffer.toString() +
               "\n" + horizontalRuleBuffer.toString() + "\n" + markLineBuffer.toString() + "\n" +
               scoreLineBuffer.toString() + "\n" + horizontalRuleBuffer.toString();
    }

    private String myToString() {
        StringBuilder horizontalBuffer = new StringBuilder();
        StringBuilder frameNumber = new StringBuilder();
        StringBuilder boxes = new StringBuilder();
        StringBuilder frameScore = new StringBuilder();

        int offset = 0;
        Mark[] boxVersionMarks = new Mark[MAXIMUM_ROLLS];
        int[] boxVersionInts = new int[MAXIMUM_ROLLS];
        for (int frame = 0; frame < MAXIMUM_ROLLS; frame++) {
            int pinIndex = frame - offset;
            Mark curMark = Mark.translate(pinsKnockedDownArray[pinIndex]);
            if (pinsKnockedDownArray[pinIndex] == 10) {
                boxVersionMarks[frame] = Mark.EMPTY;
                boxVersionMarks[frame + 1] = Mark.STRIKE;
                boxVersionInts[frame] = 0;
                boxVersionInts[frame + 1] = 10;
                offset++;
                frame++;
            } else {
                boxVersionMarks[frame] = curMark;
                boxVersionInts[frame] = pinsKnockedDownArray[pinIndex];
            }

        }

        int prevScore = 0;
        int score = 0;
        offset = 0;
        for (int frame = 0; frame < MAXIMUM_ROLLS - 3; frame += 2) {
            frameNumber.append(HORIZONTAL_SEPARATOR);
            frameNumber.append("   " + (frame / 2 + 1) + "   ");

            boxes.append(VERTICAL_SEPARATOR + LEFT_EDGE_OF_SMALL_SQUARE);
            boxes.append(boxVersionMarks[frame]);
            boxes.append(RIGHT_EDGE_OF_SMALL_SQUARE);

            boxes.append(VERTICAL_SEPARATOR + LEFT_EDGE_OF_SMALL_SQUARE);
            boxes.append(boxVersionMarks[frame + 1]);
            boxes.append(RIGHT_EDGE_OF_SMALL_SQUARE);

            frameScore.append(HORIZONTAL_SEPARATOR);
            score = prevScore + boxVersionInts[frame] + boxVersionInts[frame + 1];
            if (boxVersionInts[frame] + boxVersionInts[frame + 1] == 10 && boxVersionInts[frame + 1] != 10) {
                if (boxVersionMarks[frame + 2] == Mark.EMPTY) {
                    score += 10;
                } else {
                    score += boxVersionInts[frame + 2];
                }
            }
            if (boxVersionInts[frame] + boxVersionInts[frame + 1] == 10 && boxVersionInts[frame + 1] == 10) {
                if (boxVersionMarks[frame + 2] == Mark.EMPTY && boxVersionMarks[frame + 4] == Mark.EMPTY) {
                    score += 20;
                } else if (boxVersionMarks[frame + 2] == Mark.EMPTY && boxVersionMarks[frame + 4] != Mark.EMPTY) {
                    score += 10 + boxVersionInts[frame + 4];
                } else if (boxVersionMarks[frame + 2] != Mark.EMPTY) {
                    score += boxVersionInts[frame + 2] + boxVersionInts[frame + 3];
                }
            }
            prevScore = score;
            switch (Integer.toString(score).length()) {
                case 3:
                    frameScore.append("  " + score + "  ");
                    break;

                case 2:
                    frameScore.append("   " + score + "  ");
                    break;

                case 1:
                    frameScore.append("    " + score + "  ");
                    break;
            }

            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
        }

        // tenth frame
        {
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            horizontalBuffer.append(HORIZONTAL_SEPARATOR);
            frameNumber.append(VERTICAL_SEPARATOR + "     " + 10 + "    ");

            boxes.append(VERTICAL_SEPARATOR);
            boxes.append(LEFT_EDGE_OF_SMALL_SQUARE);
            boxes.append(boxVersionMarks[18]);
            boxes.append(RIGHT_EDGE_OF_SMALL_SQUARE);

            boxes.append(VERTICAL_SEPARATOR);
            boxes.append(LEFT_EDGE_OF_SMALL_SQUARE);
            boxes.append(boxVersionMarks[19]);
            boxes.append(RIGHT_EDGE_OF_SMALL_SQUARE);

            boxes.append(VERTICAL_SEPARATOR);
            boxes.append(LEFT_EDGE_OF_SMALL_SQUARE);
            boxes.append(boxVersionMarks[20]);
            boxes.append(RIGHT_EDGE_OF_SMALL_SQUARE);

            frameScore.append(VERTICAL_SEPARATOR);
            score = 0;
            switch (Integer.toString(score).length()) {
                case 3:
                    frameScore.append("      " + score + "  ");
                    break;

                case 2:
                    frameScore.append("       " + score + "  ");
                    break;

                case 1:
                    frameScore.append("        " + score + "  ");
                    break;
            }
        }

        frameNumber.append(VERTICAL_SEPARATOR);
        boxes.append(VERTICAL_SEPARATOR);
        horizontalBuffer.append(VERTICAL_SEPARATOR);
        frameScore.append(VERTICAL_SEPARATOR);

        return getPlayerName() + ":\n" + horizontalBuffer.toString() + '\n' + frameNumber.toString() + '\n' +
               horizontalBuffer.toString() + '\n' + boxes.toString() + '\n' + horizontalBuffer.toString() + '\n' +
               frameScore.toString() + '\n' + horizontalBuffer.toString();
    }

    public String toString() {
        // return pinsArray();
        return myToString();
        // return getScoreboardDisplay();
    }

    @Override
    public String getFirstNameOfSubmitter() {
        return "Jeffrey";
    }

    @Override
    public String getLastNameOfSubmitter() {
        return "Ng";
    }

    @Override
    public double getHoursSpentWorkingOnThisAssignment() {
        return 26;
    }

    @Override
    public int getScoreAgainstTestCasesSubset() {
        return 24 * 5;
    }
}
