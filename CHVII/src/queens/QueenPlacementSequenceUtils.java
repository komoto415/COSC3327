package queens;

import java.util.HashSet;
import java.util.Set;

import sequence.Sequence;
import sequence.SequenceUtils;

public class QueenPlacementSequenceUtils {
    private QueenPlacementSequenceUtils() {
        //DO NOT INSTANTIATE!
    }

    //Note that row indices and column indices are 0-based:
    //EX: boardSize = 8
    //(0,0)(0,1)(0,2)(0,3)(0,4)(0,5)(0,6)(0,7)   ( 0)( 1)( 2)( 3)( 4)( 5)( 6)( 7)
    //(1,0)(1,1)(1,2)(1,3)(1,4)(1,5)(1,6)(1,7)   ( 1)( 2)( 3)( 4)( 5)( 6)( 7)( 8)
    //(2,0)(2,1)(2,2)(2,3)(2,4)(2,5)(2,6)(2,7)   ( 2)( 3)( 4)( 5)( 6)( 7)( 8)( 9)
    //(3,0)(3,1)(3,2)(3,3)(3,4)(3,5)(3,6)(3,7)-->( 3)( 4)( 5)( 6)( 7)( 8)( 9)(10)
    //(4,0)(4,1)(4,2)(4,3)(4,4)(4,5)(4,6)(4,7)-->( 4)( 5)( 6)( 7)( 8)( 9)(10)(11)
    //(5,0)(5,1)(5,2)(5,3)(5,4)(5,5)(5,6)(5,7)   ( 5)( 6)( 7)( 8)( 9)(10)(11)(12)
    //(6,0)(6,1)(6,2)(6,3)(6,4)(6,5)(6,6)(6,7)   ( 6)( 7)( 8)( 9)(10)(11)(12)(13)
    //(7,0)(7,1)(7,2)(7,3)(7,4)(7,5)(7,6)(7,7)   ( 7)( 8)( 9)(10)(11)(12)(13)(14)
    //
    //(0,0)(0,1)(0,2)(0,3)(0,4)(0,5)(0,6)(0,7)   (22)(21)(20)(19)(18)(17)(16)(15)
    //(1,0)(1,1)(1,2)(1,3)(1,4)(1,5)(1,6)(1,7)   (23)(22)(21)(20)(19)(18)(17)(16)
    //(2,0)(2,1)(2,2)(2,3)(2,4)(2,5)(2,6)(2,7)   (24)(23)(22)(21)(20)(19)(18)(17)
    //(3,0)(3,1)(3,2)(3,3)(3,4)(3,5)(3,6)(3,7)-->(25)(24)(23)(22)(21)(20)(19)(18)
    //(4,0)(4,1)(4,2)(4,3)(4,4)(4,5)(4,6)(4,7)-->(26)(25)(24)(23)(22)(21)(20)(19)
    //(5,0)(5,1)(5,2)(5,3)(5,4)(5,5)(5,6)(5,7)   (27)(26)(25)(24)(23)(22)(21)(20)
    //(6,0)(6,1)(6,2)(6,3)(6,4)(6,5)(6,6)(6,7)   (28)(27)(26)(25)(24)(23)(22)(21)
    //(7,0)(7,1)(7,2)(7,3)(7,4)(7,5)(7,6)(7,7)   (29)(28)(27)(26)(25)(24)(23)(22)

    //EX: boardSize = n
    //(0,0)(0,1)(0,2)(0,3).............(0,n-1)   ( 0)( 1)( 2)( 3)............( n-1)
    //(1,0)(1,1)(1,2)..................(2,n-1)   ( 1)( 2)( 3)................( n+0)
    //(2,0)(2,1).......................(3,n-1)   ( 2)( 3)....................( n+1)
    //(3,0)............................(4,n-1)-->( 3)........................( n+2)
    //........................................-->..................................
    //........................................   ..................................
    //........................................   ..................................
    //(n-1,0)........................(n-1,n-1)   (n-1)......................(2*n-2)
    //
    //(0,0)(0,1)(0,2)(0,3).............(0,n-1)   (3*n-2).......(2*n+1)(2*n+0)(2*n-1)
    //(1,0)(1,1)(1,2)..................(2,n-1)   (3*n-1).......(2*n+2)(2*n+1)(2*n+0)
    //(2,0)(2,1).......................(3,n-1)   (3*n+0)..............(2*n+2)(2*n+1)
    //(3,0)............................(4,n-1)-->(3*n+1).....................(2*n+2)
    //........................................-->..................................
    //........................................   ..................................
    //........................................   ..................................
    //(n-1,0)........................(n-1,n-1)   (4*n-3).....................(3*n-2)
    public static Set<Integer> diagonalsOccupied(Sequence<Integer> queenPlacementSequence, int boardSize) {
        assert queenPlacementSequence != null : "queenPlacementSequence is null!";
        Set<Integer> setOfDiagonalsOccupied = new HashSet<Integer>();
        for (int i = 0; i < queenPlacementSequence.size(); i++) {
            //see table above for how diagonals have been assigned indices
            int rowIndex = i;
            int columnIndex = queenPlacementSequence.getElement(i);
            int diagonalNumber = getForwardSlashDiagonalNumber(rowIndex, columnIndex, boardSize);
            assert 0 <= diagonalNumber : String.format("diagonalNumber = %s < 0!", diagonalNumber);
            assert diagonalNumber <= (2 * boardSize - 2) :
                    String.format("diagonalNumber = %s > %s!", diagonalNumber, (2 * boardSize - 2));
            int diagonalNumber2 = getBackwardSlashDiagonalNumber(rowIndex, columnIndex, boardSize);
            assert (2 * boardSize - 1) <= diagonalNumber2 :
                    String.format("diagonalNumber2 = %s < %s!", diagonalNumber2, (2 * boardSize - 1));
            assert diagonalNumber2 <= (4 * boardSize - 3) :
                    String.format("diagonalNumber2 = %s > %s!", diagonalNumber2, (4 * boardSize - 3));

            setOfDiagonalsOccupied.add(diagonalNumber);
            setOfDiagonalsOccupied.add(diagonalNumber2);
        }
        return setOfDiagonalsOccupied;
    }

    public static int getForwardSlashDiagonalNumber(int rowIndex, int columnIndex, int boardSize) {
        assert rowIndex >= 0 : "rowIndex = " + rowIndex + " < 0!";
        assert rowIndex < boardSize : String.format("rowIndex = " + rowIndex + " >= %s = boardSize!", boardSize);
        assert columnIndex >= 0 : "columnIndex = " + columnIndex + " < 0!";
        assert columnIndex < boardSize :
                String.format("columnIndex = " + columnIndex + " >= %s = boardSize!", boardSize);

        int forwardSlashDiagonalNumber = (rowIndex + columnIndex);

        assert 0 <= forwardSlashDiagonalNumber :
                String.format("forwardSlashDiagonalNumber = %s < 0!", forwardSlashDiagonalNumber);
        assert forwardSlashDiagonalNumber <= (2 * boardSize - 2) :
                String.format("forwardSlashDiagonalNumber = %s > %s!", forwardSlashDiagonalNumber, (2 * boardSize - 2));

        return forwardSlashDiagonalNumber;
    }

    public static int getBackwardSlashDiagonalNumber(int rowIndex, int columnIndex, int boardSize) {
        assert rowIndex >= 0 : "rowIndex = " + rowIndex + " < 0!";
        assert rowIndex < boardSize : String.format("rowIndex = " + rowIndex + " >= %s = boardSize!", boardSize);
        assert columnIndex >= 0 : "columnIndex = " + columnIndex + " < 0!";
        assert columnIndex < boardSize :
                String.format("columnIndex = " + columnIndex + " >= %s = boardSize!", boardSize);

        int backwardSlashDiagonalNumber = (rowIndex - columnIndex + (3 * boardSize - 2));

        assert (2 * boardSize - 1) <= backwardSlashDiagonalNumber :
                String.format("backwardSlashDiagonalNumber = %s < %s!", backwardSlashDiagonalNumber,
                        (2 * boardSize - 1));
        assert backwardSlashDiagonalNumber <= (4 * boardSize - 3) :
                String.format("backwardSlashDiagonalNumber = %s > %s!", backwardSlashDiagonalNumber,
                        (4 * boardSize - 3));

        return backwardSlashDiagonalNumber;
    }

    public static Set<Integer> columnsOccupied(Sequence<Integer> queenPlacementSequence) {
        assert queenPlacementSequence != null : "queenPlacementSequence is null!";
        return SequenceUtils.getElementsAsSet(queenPlacementSequence);
    }
}