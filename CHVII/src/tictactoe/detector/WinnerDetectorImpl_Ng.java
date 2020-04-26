package tictactoe.detector;

import backtracking.detector.AlwaysReturnsTrueDetectorImpl;
import backtracking.detector.Detector;
import sequence.Sequence;
import tictactoe.Player;
import tictactoe.TicTacToeGame;
import tictactoe.TicTacToeGameImpl_Ng;

public class WinnerDetectorImpl_Ng implements Detector {
    private Player desiredWinner;
    private static final Player NO_WINNER = null;

    public WinnerDetectorImpl_Ng(Player desiredWinner) {
        this.desiredWinner = desiredWinner;
    }

    public Detector getIsDetectedPrecondition() {
        return AlwaysReturnsTrueDetectorImpl.SINGLETON;
    }

    public boolean isDetected(Sequence<Integer> sequence) {
        assert getIsDetectedPrecondition().isDetected(sequence) :
                String.format("tictactoeSequence is not viable!: tictactoeSequence = %s", sequence);

        TicTacToeGame game = new TicTacToeGameImpl_Ng(sequence);
        desiredWinner = game.getWinner();
        return desiredWinner != NO_WINNER;
    }
}
