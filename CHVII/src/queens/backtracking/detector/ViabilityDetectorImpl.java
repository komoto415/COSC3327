package queens.backtracking.detector;

import backtracking.detector.AlwaysReturnsTrueDetectorImpl;
import backtracking.detector.Detector;
import queens.QueensChessBoardState;
import queens.QueensChessBoardStateImpl;
import sequence.Sequence;

public class ViabilityDetectorImpl implements Detector {
    private int boardSize;

    public ViabilityDetectorImpl(int boardSize) {
        this.boardSize = boardSize;
    }

    public Detector getIsDetectedPrecondition() {
        return AlwaysReturnsTrueDetectorImpl.SINGLETON;
    }

    public boolean isDetected(Sequence<Integer> sequence) {
        boolean isSequenceNull = (sequence == null);
        boolean isSequenceTooLong = (sequence.size() > boardSize);
        QueensChessBoardState queensChessBoard = new QueensChessBoardStateImpl(sequence, boardSize);
        boolean doesSequenceHaveSharedRow = queensChessBoard.existsSharedRow();
        boolean doesSequenceHaveSharedColumn = queensChessBoard.existsSharedColumn();
        boolean doesSequenceHaveSharedDiagonal = queensChessBoard.existsSharedDiagonal();

        boolean isViable = !((isSequenceNull) || (isSequenceTooLong) || (doesSequenceHaveSharedRow) ||
                             (doesSequenceHaveSharedColumn) || (doesSequenceHaveSharedDiagonal));
        return isViable;
    }
}
