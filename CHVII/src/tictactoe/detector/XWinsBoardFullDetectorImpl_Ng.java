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
        boolean xIsWinner = game.getWinner() == Player.X;

        return isBoardFull && xIsWinner;
    }
}