package tictactoe.detector;

import backtracking.detector.AlwaysReturnsTrueDetectorImpl;
import backtracking.detector.Detector;
import backtracking.detector.ExtensibilityDetector;
import sequence.Sequence;
import sequence.SequenceUtils;
import tictactoe.TicTacToeGame;
import tictactoe.TicTacToeGameImpl_Ng;

import javax.sound.midi.SysexMessage;

public enum GameNotOverAndGridPositionEmptyExtensibilityDetectorImpl_Ng implements ExtensibilityDetector {
    SINGLETON;

    public Detector getCanExtendPrecondition() {
        return AlwaysReturnsTrueDetectorImpl.SINGLETON;
    }

    public Detector getCanExtendPostcondition() {
        return AlwaysReturnsTrueDetectorImpl.SINGLETON;
    }

    public boolean canExtend(Sequence<Integer> sequence, int i) {
        TicTacToeGame game = new TicTacToeGameImpl_Ng(sequence);
        boolean gameNotOver = !game.isGameOver();
        boolean gridPositionIsEmpty = !SequenceUtils.asList(sequence).contains(i);
        boolean canExtend = gameNotOver && gridPositionIsEmpty;

        assert !canExtend || (getCanExtendPostcondition().isDetected(SequenceUtils.shallowCopyOfExtended(sequence, i)));

        return canExtend;
    }
}
