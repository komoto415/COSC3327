package queens.backtracking.detector;

import backtracking.detector.Detector;
import backtracking.detector.ExtensibilityDetector;
import queens.QueenPlacementSequenceUtils;
import sequence.Sequence;
import sequence.SequenceUtils;

public class ExtensibilityDetectorImpl implements ExtensibilityDetector {
    private int boardSize;
    private Detector viabilityDetector;

    public ExtensibilityDetectorImpl(int boardSize) {
        this.boardSize = boardSize;
        viabilityDetector = ViabilityDetectorFactory.getViabilityDetector(boardSize);
    }

    public Detector getCanExtendPrecondition() {
        return viabilityDetector;
    }

    public Detector getCanExtendPostcondition() {
        return viabilityDetector;
    }

    public boolean canExtend(Sequence<Integer> sequence, int i) {
        assert getCanExtendPrecondition().isDetected(sequence) :
                String.format("sequence = %s is not viable!, sequence");
        assert i >= 0 : "i = " + i + " < 0!";
        assert i < boardSize : String.format("i = " + i + " >= %s = boardSize!", boardSize);

        int rowIndex = sequence.size();
        int columnIndex = i;

        //Each queen occupies a unique row because of the interpretation of queenPlacementSequence
        boolean rowAlreadyOccupied = false;

        boolean columnAlreadyOccupied = SequenceUtils.getElementsAsSet(sequence).contains(i);

        int forwardSlashDiagonalNumber = QueenPlacementSequenceUtils.getForwardSlashDiagonalNumber(rowIndex,
                columnIndex, boardSize);
        int backwardSlashDiagonalNumber = QueenPlacementSequenceUtils.getBackwardSlashDiagonalNumber(rowIndex,
                columnIndex, boardSize);

        boolean diagonalAlreadyOccupied = QueenPlacementSequenceUtils.diagonalsOccupied(sequence, boardSize).contains(
                forwardSlashDiagonalNumber) ||
                                          QueenPlacementSequenceUtils.diagonalsOccupied(sequence, boardSize).contains(
                                                  backwardSlashDiagonalNumber);

        return !rowAlreadyOccupied && !columnAlreadyOccupied && !diagonalAlreadyOccupied;
    }
}
