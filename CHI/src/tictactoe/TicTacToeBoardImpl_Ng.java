package tictactoe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicTacToeBoardImpl_Ng implements TicTacToeBoard {
    private static final int NO_MOVE = -1;
    private static final int NO_MATCH = -1;
	private static final Mark NO_ONES_TURN = null;
	private static final Mark NO_WINNER = null;

    private int[] movesArray;

    /*
    	Class Invariant
	*/
    private static final int ARRAY_OVERFLOW_OFFSET = 1;
    private static final Set<Integer> validIntegers = new HashSet<>(Arrays.asList(-1,0,1,2,3,4,5,6,7,8));
    private boolean movesArrayIsWellFormed() {
        boolean isSubSet = validIntegers.containsAll(marksPlayedSet());
        boolean noDupsExceptNeg1 = noDupsExceptNeg1();
        boolean isProperLen = movesArray.length == ROW_COUNT*COLUMN_COUNT;
        boolean isRightSideFlush = isRightSideFlush();
		boolean noTurnsPlayedAfterWin = noTurnsPlayedAfterWin();

        return  isSubSet && noDupsExceptNeg1 &&
        		isProperLen && isRightSideFlush &&
        		noTurnsPlayedAfterWin;
    }
	private boolean noDupsExceptNeg1() {
		Map<Integer, Boolean> occurrences = new HashMap<>();
		int index = 0;
		boolean wellFormed = true;
		while (index < movesArray.length && wellFormed) {
			if (occurrences.containsKey(movesArray[index]) && movesArray[index] != NO_MOVE) wellFormed = false;
			occurrences.put(movesArray[index], wellFormed);
			index++;
		}

		return wellFormed;
	}
    private boolean noTurnsPlayedAfterWin() {
        boolean wellFormed = true;
        int marksPlayed = marksPlayedSet().size();
        if ((isAWinner(Mark.X) || isAWinner(Mark.O)) &&
        		marksPlayed < movesArray.length - ARRAY_OVERFLOW_OFFSET) {
			wellFormed = movesArray[marksPlayed + 1] == NO_MOVE;
        }

        return wellFormed;
    }
    private boolean isRightSideFlush() {
        int index = 0;
        boolean wellFormed = true;
        while (index < movesArray.length - ARRAY_OVERFLOW_OFFSET && wellFormed) {
            if (movesArray[index] == NO_MOVE && movesArray[index + 1] != NO_MOVE) wellFormed = false;
            index++;
        }

        return wellFormed;
    }

    public TicTacToeBoardImpl_Ng() {
        final int CELL_COUNT = ROW_COUNT*COLUMN_COUNT;
        movesArray = new int[CELL_COUNT];
        for (int i  = 0; i < CELL_COUNT; i++) {
            movesArray[i] = NO_MOVE;
        }
    }

    /*	Precondition(s):
                            0 <= row && row < ROW_COUNT
                            0 <= column && column < COLUMN_COUNT
	*/

	/*	Postcondition(s):
                            null if spot on board is empty
    */

	/*	Example Calls:
		movesArray [0, 3, 1, 4, 6, 2, 7, 8 5]
		movesArray.getMark(0, 0) = X
		movesArray.getMark(1, 0) = O
		movesArray.getMark(0, 1) = X
		movesArray.getMark(1, 1) = O
		movesArray.getMark(2, 0) = X
		movesArray.getMark(0, 2) = O
		movesArray.getMark(2, 1) = X
		movesArray.getMark(2, 2) = O
		movesArray.getMark(1, 2) = X

		movesArray = -1, -1, -1, -1, -1, -1, -1, -1, -1]
		movesArray.getMark(n, n) = null

		movesArray = [1, 2, 3, 4, -1, -1, -1, -1, -1]
		movesArray.getMark(0, 0) = null
		movesArray.getMark(1, 0) = X
		movesArray.getMark(0, 2) = O

	*/

	/*	Algorithm:
			Time:	O(n)
			Space:	O(n)

			Our primary currency exchange will always be some direction of row, col => direct index. Knowing this,
			all we need to do is find what that direct index, and look for it in movesArray
	*/
    public Mark getMark(int row, int column) {
		assert movesArrayIsWellFormed();

        assert 0 <= row && row < ROW_COUNT : "row is out of bounds! <" + row + ">";
        assert 0 <= column && column < COLUMN_COUNT : "column is out of bounds! <" + column + ">";

        Mark retMark = null;
        int fixedCoor = RowColumnToIndex(row, column);
        int index = 0;
        boolean found = false;
        while (index < movesArray.length && !found) {
        	found = movesArray[index] == fixedCoor;
            if (found) {
            	retMark = index % 2 == 0 ? Mark.X : Mark.O;
            } else {
            	index++;
            }
        }

        assert movesArrayIsWellFormed();
        return retMark;
    }

    /*	Precondition(s):
                            0 <= row && row < ROW_COUNT
                            0 <= column && column < COLUMN_COUNT
							getMark(row, column) == null
							!isGameOver
	*/

	/*	Postcondition(s):
                            places mark in specified location
    */

	/*	Example Calls:
		movesArray = [-1, -1, -1, -1, -1, -1, -1, -1, -1]
		movesArray.setMark(0, 0)
		movesArray = [0, -1, -1, -1, -1, -1, -1, -1, -1]

		movesArray = [0, -1, -1, -1, -1, -1, -1, -1, -1]
		movesArray.setMark(0, 1)
		movesArray = [0, 1, -1, -1, -1, -1, -1, -1, -1]

		movesArray = [0, 1, -1, -1, -1, -1, -1, -1, -1]
		movesArray.setMark(0, 0)
		No gu

	*/

	/*	Algorithm:
			Time:	O(n)
			Space:

			Because our internal representation doesn't use row,col directly as a way of storing where marks are placed, we will just need to translate it into the direct index. But we also need to determine where in movesArray to place the element. We do this by getting the size of the set composed of the elements in movesArray, excluding -1. This is because this size is indiciative of the next playable insert for movesArray.
	*/
    public void setMark(int row, int column) {
        assert movesArrayIsWellFormed();

        assert 0 <= row && row < ROW_COUNT : "row is out of bounds! <" + row + ">";
        assert 0 <= column && column < COLUMN_COUNT : "column is out of bounds! <" + column + ">";
        assert getMark(row, column) == null : "There's already a mark there! <(" + row + ", " + column + ")>";
        assert !isGameOver() : "The game is over. Go home";

        int fixedCoor = RowColumnToIndex(row, column);
        int marksPlayed = marksPlayedSet().size();
    	movesArray[marksPlayed] = fixedCoor;

        assert movesArrayIsWellFormed();
    }

    private int RowColumnToIndex(int row, int column) { return row * COLUMN_COUNT + column; }
    private Set<Integer> marksPlayedSet() {
    	return Arrays.stream(movesArray).boxed().filter(i -> i != NO_MOVE).collect(Collectors.toSet());
    }

	/*	Precondition(s):
                            None
	*/

	/*	Postcondition(s):
                        	null if isGameOver()
							X if even num marks on board
							O if odd num marks on board
    */

	/*	Example Calls:
		movesArray = [0, 3, 1, 4, 6, 2, 7, 8, 5]
		movesArray.getTurn() = null
		Game is over

		movesArray = [0, 1, 2, 3, 4, -1, -1, -1, -1]
		movesArray.getTurn() = O
		X just went

		movesArray = [-1, -1, -1, -1, -1, -1, -1, -1, -1]
		movesArray.getTurn() = X
		First turn is always X

		movesArray = [0, 1, 3, 4, 6, -1, -1, -1, -1]
		movesArray.getTurn() = null
		Game is over
	*/

	/*	Algorithm:
			Time:	O(n)
			Space:	O(n)

			Consider null just the base case, and we will try to 'prove me otherwise', potentially also refered to as immediate escape conditions, that it is in fact not null, there by avoiding silly one line else statements that just assigns the return value. The 'prove me otherwise' case for this method is that we start with the assumption that the game is in fact already over and we will have to prove that is isn't first.

			We want to determine how many turns have gone by because whether that is even or odd will determine for us whose turn it is. We can find the number of turns played simply by making a set of all the elements in movesArray, excluding -1, and getting the set's size. If the result is even, is must be X's turn as each player has played the same number of turns AND X always starts, so we come full circle. And then the only other possible option at this point is for it to be O
	*/
    public Mark getTurn() {
		assert movesArrayIsWellFormed();

        Mark turn = NO_ONES_TURN;
        if (!isGameOver()) {
			Set<Integer> marksPlayed = marksPlayedSet();
        	turn = marksPlayed.size() % 2 == 0 ? Mark.X : Mark.O;
        }

        assert movesArrayIsWellFormed();
        return turn;
    }

    /*	Precondition(s):
                            None
	*/

	/*	Postcondition(s):
                            Returns true if one of these are true else false
								there are 9 marks on the board
								there is a winnner
    */

	/*	Example Calls:
		movesArray = [0, 3, 1, 4, 6, 2, 7, 8, 5]
		movesArray.isGameOver() = True
		9 turns have been played

		movesArray = [0, 1, 2, 3, 4]
		movesArray.isGameOver() = False
		No win condition was met

		movesArray = [-1, -1, -1, -1, -1, -1, -1, -1, -1]
		movesArray.isGameOver() = False
		Not enough turns have happened to declare a winner

		movesArray = [0, 1, 3, 4, 6, -1, -1, -1, -1]
		movesArray.isGameOver() = True
		Some win condition was met

		movesArray = [0, 1, 3, 4, 5, 7, -1, -1, -1]
		movesArray.isGameOver() = True
		Some win condition was met

		movesArray = [0, 3, 1, 4, 2, -1, -1, -1, -1]
		movesArray.isGameOver() = True
		Some win condition was met

		movesArray = [0, 1, 3, 4, 6, 8, -1, -1, -1]
		movesArray.isGameOver() = No guu
		Violates class invariant

		movesArray = [0, 1, 3, 4, 5, 7, 8, -1, -1]
		movesArray.isGameOver() = No guu
		Violates class invariant
	*/

	/*	Algorithm:
			Time:
			Space:

			We already have 2 conditions in which we can easily check that will tell give us our return value. Both of them having to do with how many turns have been played. The order of it being done in in this implementation is purly for compactness. The first easy check made is whether or not ROW_COUNT*COLUMN_COUNT numbers of turns, 9 for a 3x3 board, have been played as it is the maximum ammount possible to be played. If the check turns out true, we can immediately set true and not check any of the other case.

			The second one is asking whether there as been (ROW_COUNT+COLUMN_COUNT - 1) number of turns played; 5 for a 3x3 board. This is because there are a certain amount of turns that MUST be played before a winner is even able to BE determined. Therefore, if this condition is ever met, we set false and stop checking our other conditions.

			From this point, the only checks we now need to make are whether one of the players have won. The main reason I have is structured as a second check on the next player and now a simple OR checking BOTH players is for the sake of debugging and making sure it was declaring the correct person as the having been the reason for the game being over or not. More on how the winnder is determined when we get to those methods.
	*/
	private static int MINIMUM_MARKS_PLAYED_TO_HAVE_A_WINNER = ROW_COUNT+COLUMN_COUNT - 1;
    public boolean isGameOver() {
		assert movesArrayIsWellFormed();

        int marksPlayed = marksPlayedSet().size();
        boolean gameEnded = marksPlayed == ROW_COUNT*COLUMN_COUNT;
        if (!gameEnded) {
	        if (marksPlayed < MINIMUM_MARKS_PLAYED_TO_HAVE_A_WINNER) gameEnded = false;
	        else {
	        	gameEnded = isAWinner(Mark.X);
	        	if (!gameEnded) {
	        		gameEnded = isAWinner(Mark.O);
	        	}
	        }
        }

        assert movesArrayIsWellFormed();
        return gameEnded;
    }

    /*	Precondition(s):
                            isGameOver()
	*/

	/*	Postcondition(s):
                            null if no winner
    */

	/*	Example Calls:
		movesArray = [0, 3, 1, 4, 6, 2, 7, 8, 5]
		movesArray.isGameOver() = null
		No win condition met

		movesArray = [0, 1, 2, 3, 4, -1, -1, -1, -1]
		movesArray.getWinner() = null
		No win condition met

		movesArray = [0, 1, 3, 4, 6, -1, -1, -1, -1]
		movesArray.getWinner() = X
		Column win condition met

		movesArray = [0, 1, 3, 4, 5, 7, -1, -1, -1]
		movesArray.getWinner() = O
		Column win condition met

		movesArray = [0, 3, 1, 4, 2, -1, -1, -1, -1]
		movesArray.getWinner() = X
		Row win condition met

		movesArray = [0, 3, 1, 4, 8, 5, -1, -1, -1]
		movesArray.getWinner() = 0
		Row win condition met

		movesArray = [0, 3, 4, 2, 8, -1, -1, -1, -1]
		movesArray.getWinner() = X
		Diagonal win condition met

		movesArray = [1, 6, 5, 4, 3, 2, -1, -1, -1]
		movesArray.getWinner() = O
		Atni-diagonal win condition met

		movesArray = [0, 1, 3, 4, 6, 8, -1, -1, -1]
		movesArray.getWinner() = No guu
		Game is already over

		movesArray = [0, 1, 3, 4, 5, 7, 8, -1, -1]
		movesArray.getWinner() = No guu
		Game is already over
	*/

	/*	Algorithm:
			Time:
			Space:

			Firstly, we will start with the assumption that there is NO WINNER and try to 'prove me otherwise'. In this case, we are only needing to check wether X or O are the winners, but determining the winners is where all the fun stuff happens. Put simply here, we want to check for the winning cases for the player one at a time, because of course, finding a winner is our 'escape condition' if you will. Go to isWinner() to see how exactly we do that
	*/
    public Mark getWinner() {
		assert movesArrayIsWellFormed();

        assert isGameOver() : "There can't be a winner if that game isn't over!";

        Mark winner = NO_WINNER;
        if (isAWinner(Mark.X)) winner = Mark.X;
        else if (isAWinner(Mark.O)) winner = Mark.O;

        assert movesArrayIsWellFormed();
        return winner;
    }

	/*
		We are just converting our movesArray just a list a particular player's turns, while also filtering out the NO-MOVE because they can mess up our verifier. In the case of player X, we only want the even indices of movesArray while player O will want only the odd indices.
	*/
    private static List<Integer> player_Turns;
    private List<Integer> xTurns() {
        return IntStream
            	.range(0, movesArray.length)
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> movesArray[i])
                .filter(i -> i > NO_MOVE)
                .collect(Collectors.toList());
    }
    private List<Integer> oTurns() {
        return IntStream
                .range(0, movesArray.length)
                .filter(i -> i % 2 != 0)
                .mapToObj(i -> movesArray[i])
                .filter(i -> i > NO_MOVE)
                .collect(Collectors.toList());
    }
	/*
	Step through all four win conditions sets
	Wins by rows, columns or the anti-diagonal all have the 'same' win conditions such that their turns have
	been normalised in the respective ways.
	*/
    private Boolean isAWinner(Mark mark) {
        player_Turns = mark.equals(Mark.X) ? xTurns() : oTurns();
		boolean winConditionMet = rowsWinCondition();
		if (!winConditionMet) {
			winConditionMet = colsWinCondition();
			if (!winConditionMet) {
				winConditionMet = diagWinCondition();
				if (!winConditionMet) {
					winConditionMet = antiWinCondition();
				}
			}
		}

		return winConditionMet;
    }
	/*
	Row Normalisation
	i 	=>	i / ROW_COUNT
	For every element of the list, we will divide it by ROW_COUNT
	Standard 3x3		Normalised 3x3
	0|1|2		==>		0|0|0
	3|4|5		==>		1|1|1
	6|7|8		==>		2|2|2

	Original 4x4			Normalised 4x4
	 0| 1| 2| 3		==>		0|0|0|0
	 4| 5| 6| 7		==>		1|1|1|1
	 8| 9|10|11		==>		2|2|2|2
	12|13|14|15 	==>		3|3|3|3

	Generic NxN
		  0|   1|   2|  ..| n-1		==>		   0|   0|   0|  ..|   0
		 1n|  ..|  ..|  ..|  ..		==>		   1|   1|   1|  ..|   1
		 2n|  ..|  ..|  ..|  ..		==>		   2|   2|   2|  ..|   2
		 ..|  ..|  ..|  ..|  ..		==>		  ..|  ..|  ..|  ..|  ..
	(n-1)*n|  ..|  ..|  ..|n*n-1	==>		 n-1| n-1| n-1|  ..| n-1
	*/
    private List<Integer> normaliseForRows() { return player_Turns.stream().map(i -> i / ROW_COUNT).collect(Collectors.toList()); }
    private boolean rowsWinCondition() {
    	List<Integer> rowsTransform = normaliseForRows();
    	boolean isWinner = threeOfAKind(rowsTransform);

        return isWinner;
    }

	/*
	Column Normalisation
	i 	=>	i % ROW_COUNT
	For every element of the list, we will mod it by ROW_COUNT
	Standard 3x3		Normalised 3x3
	0|1|2		==>		0|1|2
	3|4|5		==>		0|1|2
	6|7|8		==>		0|1|2

	Original 4x4			Normalised 4x4
	 0| 1| 2| 3		==>		0|1|2|3
	 4| 5| 6| 7		==>		0|1|2|3
	 8| 9|10|11		==>		0|1|2|3
	12|13|14|15 	==>		0|1|2|3

	Generic NxN
		  0|   1|   2|  ..| n-1		==>		  0|   1|   2|  ..| n-1
		 1n|  ..|  ..|  ..|  ..		==>		  0|   1|   2|  ..| n-1
		 2n|  ..|  ..|  ..|  ..		==>		  0|   1|   2|  ..| n-1
		 3n|  ..|  ..|  ..|  ..		==>		  0|   1|   2|  ..| n-1
		 ..|  ..|  ..|  ..|  ..		==>		 ..|  ..|  ..|  ..|  ..
	(n-1)*n|  ..|  ..|  ..|n*n-1	==>		  0|   1|   2|  ..| n-1
	*/
	private List<Integer> normaliseForCols() { return player_Turns.stream().map(i -> i % COLUMN_COUNT).collect(Collectors.toList()); }
    private boolean colsWinCondition() {
    	List<Integer> colsTransform = normaliseForCols();
    	boolean isWinner = threeOfAKind(colsTransform);

        return isWinner;
    }

	/*
	Anti-Diagonal Normalisation
	i 	=>	(i / ROW_COUNT) + (i % ROW_COUNT)
	For every element of the list, we take the sum of our row and column normalisation technique
	Standard 3x3		Normalised 3x3
	0|1|2		==>		0|1|2
	3|4|5		==>		1|2|3
	6|7|8		==>		2|3|4

	Original 4x4			Normalised 4x4
	 0| 1| 2| 3		==>		0|1|2|3
	 4| 5| 6| 7		==>		1|2|3|4
	 8| 9|10|11		==>		2|3|4|5
	12|13|14|15 	==>		3|4|5|6

	Generic NxN
	      0|   1|   2|  ..| n-1		==>		  0|   1|   2|  ..| n-1
	     1n|  ..|  ..|  ..|  ..		==>		  1|  ..|  ..| n-1|  ..
	     2n|  ..|  ..|  ..|  ..		==>		  2|  ..| n-1|  ..|  ..
	     ..|  ..|  ..|  ..|  ..		==>		 ..| n-1|  ..|  ..|  ..
	(n-1)*n|  ..|  ..|  ..|n*n-1	==>		n-1|  ..|  ..|  ..|  ..
	*/
	private List<Integer> normaliseForAnti() { return player_Turns.stream().map(i -> (i % ROW_COUNT) + (i / COLUMN_COUNT)).collect(Collectors.toList()); }
	private boolean antiWinCondition() {
		List<Integer> antiTransform = normaliseForAnti();
		boolean isWinner = threeOfAKind(antiTransform);

		return isWinner;
	}

	/*
	With our normalisation, now only need to check if there exists an N of a kind of, such
	that N is the length of any square board of any particular element
	*/
	private boolean threeOfAKind(List<Integer> normalisedList) {
    	Map<Object, Long> occurrences = normalisedList.stream()
    			.collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    	boolean hasROW_COUNT = occurrences.values().contains((long)ROW_COUNT);

		return hasROW_COUNT;
    }

	/*
	As it turns out, there's a forumalic way we can calculate what integer will be the diagonal given their row. Because of that, all we have to do is to check that we have N number of those calculated integers. Of course what that could have meant is we make a static table with lists based on the size of the board, but with this will cover ANY NxN board whereas making a static table will be bounded by how many rows we WANT to make.
	*/
    private boolean diagWinCondition() {
		int row = 0;
		int count = 0;
		while (row < ROW_COUNT) {
			if (player_Turns.contains(getDiagonalIndexOfRow(row))) count++;
			row++;
		}
		boolean isWinner = count == ROW_COUNT;

		return isWinner;
    }
	/* The Krabby Patty Secret Formula */
	private int getDiagonalIndexOfRow(int row) { return row * (ROW_COUNT + 1); }

    private int[] indexToRowColumn(int index) { return new int[]{index / ROW_COUNT, index % ROW_COUNT}; }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Integer> sortedMovesList = Arrays.stream(movesArray).boxed().filter(i -> i != NO_MOVE).collect(Collectors.toList());
        Collections.sort(sortedMovesList);
        for (int i = 0; i < ROW_COUNT*COLUMN_COUNT; i++) {
            if (sortedMovesList.contains(i)) {
                int[] getRowColumn = indexToRowColumn(i);
                sb.append(getMark(getRowColumn[0], getRowColumn[1]));
            }
            else sb.append(' ');
            if (i % COLUMN_COUNT == 0 || i % COLUMN_COUNT == 1) sb.append('|');
            if (i % COLUMN_COUNT == COLUMN_COUNT - 1 && i != ROW_COUNT*COLUMN_COUNT - 1) sb.append('\n');
        }

        assert movesArrayIsWellFormed();
        return sb.toString();
    }
}
