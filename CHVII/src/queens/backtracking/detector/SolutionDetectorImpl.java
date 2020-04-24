package queens.backtracking.detector;

import backtracking.detector.Detector;
import sequence.Sequence;

public class SolutionDetectorImpl implements Detector {
    private int boardSize;
    private Detector viabilityDetector;

    public SolutionDetectorImpl(int boardSize) {
        this.boardSize = boardSize;
        this.viabilityDetector = ViabilityDetectorFactory.getViabilityDetector(boardSize);
    }

    public Detector getIsDetectedPrecondition() {
        return viabilityDetector;
    }

    public boolean isDetected(Sequence<Integer> queenPlacementSequence) {
        assert viabilityDetector.isDetected(queenPlacementSequence) :
                String.format("queenPlacementSequence is not viable!: queenPlacementSequence = %s, boardSize = %s",
                        queenPlacementSequence, boardSize);
        final boolean isSolution = (queenPlacementSequence.size() == boardSize);
        return isSolution;
    }
}
