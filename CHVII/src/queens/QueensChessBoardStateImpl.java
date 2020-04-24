package queens;

import java.util.HashSet;
import java.util.Set;

import sequence.Sequence;
import sequence.SequenceUtils;

public class QueensChessBoardStateImpl implements QueensChessBoardState {
    private Sequence<Integer> queenPlacementSequence;
    private int boardSize;

    //Let queenPlacementSequence = <3, 1, 7>.
    //This corresponds to the board:
    //(0,0)(0,1)(0,2)  Q  (0,4)(0,5)(0,6)(0,7)
    //(1,0)  Q  (1,2)(1,3)(1,4)(1,5)(1,6)(1,7)
    //(2,0)(2,1)(2,2)(2,3)(2,4)(2,5)(2,6)  Q
    //(3,0)(3,1)(3,2)(3,3)(3,4)(3,5)(3,6)(3,7)
    //(4,0)(4,1)(4,2)(4,3)(4,4)(4,5)(4,6)(4,7)
    //(5,0)(5,1)(5,2)(5,3)(5,4)(5,5)(5,6)(5,7)
    //(6,0)(6,1)(6,2)(6,3)(6,4)(6,5)(6,6)(6,7)
    //(7,0)(7,1)(7,2)(7,3)(7,4)(7,5)(7,6)(7,7)
    //In general, if queenPlacementSequence is <e_0, e_1, e_2, ..., e_7>
    //then the board has queens located at squares (0, e_0), (1, e_1), (2, e_2),
    //..., (7, e_7) and empty squares everywhere else.
    public QueensChessBoardStateImpl(Sequence<Integer> queenPlacementSequence, int boardSize) {
        assert queenPlacementSequence.size() <= boardSize :
                String.format("queenPlacementSequence.size() = %s >= %s = boardSize!", queenPlacementSequence.size(),
                        boardSize);
        assert (queenPlacementSequence.size() == 0) || (getMinimum(queenPlacementSequence) >= 0) :
                String.format("Out of range! getMinimum(queenPlacementSequence) = %s < 0!",
                        getMinimum(queenPlacementSequence));
        assert (queenPlacementSequence.size() == 0) || (getMaximum(queenPlacementSequence) < boardSize) :
                String.format("Out of range! getMaximum(queenPlacementSequence) = %s >= %s!",
                        getMaximum(queenPlacementSequence), boardSize);
        this.queenPlacementSequence = SequenceUtils.shallowCopy(queenPlacementSequence);
        this.boardSize = boardSize;
    }

    public int boardSize() {
        return boardSize;
    }

    public boolean squareOccupied(int row, int column) {
        assert 0 <= row : String.format("row = %s < 0!", row);
        assert row < boardSize : String.format("row = %s >= %s = boardSize!", row, boardSize);
        assert 0 <= column : String.format("row = %s < 0!", column);
        assert column < boardSize : String.format("column = %s >= %s = boardSize!", column, boardSize);

        boolean queenHasBeenPlacedInRow = (queenPlacementSequence.size() - 1 >= row);

        //return (queenHasBeenPlacedInRow) && ("That row has the queen in the right column!")
        return (queenHasBeenPlacedInRow) && (queenPlacementSequence.getElement(row).equals(column));
    }

    public boolean existsSharedRow() {
        return false;
    }

    public boolean existsSharedColumn() {
        Set<Integer> columnsOccupied = QueenPlacementSequenceUtils.columnsOccupied(queenPlacementSequence);
        boolean existsSharedColumn = (columnsOccupied.size() < queenPlacementSequence.size());
        return existsSharedColumn;
    }

    public boolean existsSharedDiagonal() {
        Set<Integer> setOfDiagonalsOccupied = new HashSet<Integer>();
        for (int i = 0; i < queenPlacementSequence.size(); i++) {
            int rowIndex = i;
            int columnIndex = queenPlacementSequence.getElement(i);
            int forwardSlashDiagonalNumber = QueenPlacementSequenceUtils.getForwardSlashDiagonalNumber(rowIndex,
                    columnIndex, boardSize);
            int backwardSlashDiagonalNumber = QueenPlacementSequenceUtils.getBackwardSlashDiagonalNumber(rowIndex,
                    columnIndex, boardSize);

            assert 0 <= forwardSlashDiagonalNumber :
                    String.format("forwardSlashDiagonalNumber = %s < 0!", forwardSlashDiagonalNumber);
            assert forwardSlashDiagonalNumber <= (2 * boardSize - 2) :
                    String.format("forwardSlashDiagonalNumber = %s > %s!", forwardSlashDiagonalNumber,
                            (2 * boardSize - 2));
            assert (2 * boardSize - 1) <= backwardSlashDiagonalNumber :
                    String.format("backwardSlashDiagonalNumber = %s < %s!", backwardSlashDiagonalNumber,
                            (2 * boardSize - 1));
            assert backwardSlashDiagonalNumber <= (4 * boardSize - 3) :
                    String.format("backwardSlashDiagonalNumber = %s > 30!", backwardSlashDiagonalNumber,
                            (4 * boardSize - 3));

            setOfDiagonalsOccupied.add(backwardSlashDiagonalNumber);
            setOfDiagonalsOccupied.add(forwardSlashDiagonalNumber);
        }
        boolean existsSharedDiagonal = (setOfDiagonalsOccupied.size() < 2 * queenPlacementSequence.size());
        return existsSharedDiagonal;
    }

    private static String getRepeatedCharacterString(char charToRepeat, int desiredLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < desiredLength; i++) {
            sb.append(charToRepeat);
        }
        return sb.toString();
    }

    public String toString() {
        final int SQUARE_WIDTH = 3;    //Ex: " Q ".length() = 3
        final String VERTICAL_DIVIDER = "|";
        final int VERTICAL_DIVIDER_WIDTH = VERTICAL_DIVIDER.length();
        final String HORIZONTAL_DIVIDER = getRepeatedCharacterString('-',
                boardSize * SQUARE_WIDTH + (boardSize + 1) * (VERTICAL_DIVIDER_WIDTH));

        StringBuilder sb = new StringBuilder();
        sb.append(HORIZONTAL_DIVIDER);
        sb.append("\n");
        for (int i = 0; i < boardSize; i++) {
            sb.append(VERTICAL_DIVIDER);
            for (int j = 0; j < boardSize; j++) {
                String squareContents = (squareOccupied(i, j) ? " Q " : "   ");
                assert squareContents.length() == SQUARE_WIDTH : String.format(
                        "squareContents.length() = %s <> %s = SQUARE_WIDTH!, squareContents.length, SQUARE_WIDTH");
                sb.append(squareContents);
                if (j < boardSize - 1) {
                    sb.append(VERTICAL_DIVIDER);
                }
            }
            //The next line changes the length of sb by VERTICAL_DIVIDER_WIDTH:
            sb.append(VERTICAL_DIVIDER);
            sb.append("\n");
            sb.append(HORIZONTAL_DIVIDER);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static int getMinimum(Sequence<Integer> sequence) {
        assert sequence.size() > 0 : String.format("sequence.size() = %s", sequence.size());
        int currentMin = sequence.getElement(0);
        for (int i = 1; i < sequence.size(); i++) {
            if (sequence.getElement(i) < currentMin) {
                currentMin = sequence.getElement(i);
            }
        }
        return currentMin;
    }

    private static int getMaximum(Sequence<Integer> sequence) {
        assert sequence.size() > 0 : String.format("sequence.size() = %s", sequence.size());
        int currentMax = sequence.getElement(0);
        for (int i = 1; i < sequence.size(); i++) {
            if (currentMax < sequence.getElement(i)) {
                currentMax = sequence.getElement(i);
            }
        }
        return currentMax;
    }

    public int hashCode() {
        //this is a really poor hashcode, but satisfies contract with equals()...
        return 0;
    }

    public boolean equals(Object obj) {
        boolean areEqual = false;
        if (QueensChessBoardState.class.isAssignableFrom(obj.getClass())) {
            QueensChessBoardState queensChessBoardState2 = (QueensChessBoardState) obj;
            boolean foundDisagreement = false;
            int i = 0;
            while (!foundDisagreement && i < boardSize) {
                int j = 0;
                while (!foundDisagreement && j < boardSize) {
                    foundDisagreement = (squareOccupied(i, j) != queensChessBoardState2.squareOccupied(i, j));
                    j++;
                }
                i++;
            }
            areEqual = (!foundDisagreement);
        }
        return areEqual;
    }

    public static void main(String[] args) {
        //Student question to ponder:
        //What should the printout be here?
        Sequence<Integer> queenPlacementSequence = new sequence.SequenceImpl<Integer>();
        queenPlacementSequence.extend(0);
        queenPlacementSequence.extend(1);
        queenPlacementSequence.extend(2);
        final int CLASSIC_CHESS_BOARD_SIZE = 8;
        QueensChessBoardState queensChessBoardState1 = new QueensChessBoardStateImpl(queenPlacementSequence,
                CLASSIC_CHESS_BOARD_SIZE);
        QueensChessBoardState queensChessBoardState2 = new QueensChessBoardStateImpl(queenPlacementSequence,
                CLASSIC_CHESS_BOARD_SIZE);

        Set<QueensChessBoardState> boardSet = new HashSet<QueensChessBoardState>();
        boardSet.add(queensChessBoardState1);
        boardSet.add(queensChessBoardState2);

        System.out.println("boardSet.size() = " + boardSet.size());
        System.out.println(boardSet);
    }
}
