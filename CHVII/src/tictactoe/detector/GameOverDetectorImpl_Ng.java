package tictactoe.detector;

import backtracking.detector.AlwaysReturnsTrueDetectorImpl;
import backtracking.detector.Detector;
import sequence.Sequence;
import tictactoe.TicTacToeGame;
import tictactoe.TicTacToeGameImpl_Ng;

public enum GameOverDetectorImpl_Ng implements Detector {
    SINGLETON;

    public Detector getIsDetectedPrecondition() {
        return AlwaysReturnsTrueDetectorImpl.SINGLETON;
    }

    public boolean isDetected(Sequence<Integer> sequence) {
        assert getIsDetectedPrecondition().isDetected(sequence) :
                String.format("sequence fails precondition!: sequence = %s", sequence);

        TicTacToeGame game = new TicTacToeGameImpl_Ng(sequence);
        return game.isGameOver();
    }
}
