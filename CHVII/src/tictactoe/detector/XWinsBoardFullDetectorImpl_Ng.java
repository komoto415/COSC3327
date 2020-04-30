package tictactoe.detector;

import backtracking.detector.AlwaysReturnsTrueDetectorImpl;
import backtracking.detector.Detector;
import sequence.Sequence;
import tictactoe.Player;
import tictactoe.TicTacToeGame;
import tictactoe.TicTacToeGameImpl_Ng;

public enum XWinsBoardFullDetectorImpl_Ng implements Detector {
    SINGLETON;

    public Detector getIsDetectedPrecondition() {
        return AlwaysReturnsTrueDetectorImpl.SINGLETON;
    }

    public boolean isDetected(Sequence<Integer> sequence) {
        assert getIsDetectedPrecondition().isDetected(sequence) :
                String.format("tictactoeSequence is not viable!: tictactoeSequence = %s", sequence);

        TicTacToeGame game = new TicTacToeGameImpl_Ng(sequence);
        boolean isBoardFull = sequence.size() == 9;
        boolean xIsWinner = false;
        try {
            Player winner = game.getWinner();
            xIsWinner = winner == Player.X;
        } catch (AssertionError ignored) {
        }

        return isBoardFull && xIsWinner;
    }
}