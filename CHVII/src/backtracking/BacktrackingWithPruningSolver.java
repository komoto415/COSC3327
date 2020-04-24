package backtracking;

import java.util.HashSet;
import java.util.Set;

import backtracking.detector.Detector;
import backtracking.detector.ExtensibilityDetector;
import queens.QueensChessBoardState;
import queens.QueensChessBoardStateImpl;
import queens.backtracking.detector.ExtensibilityDetectorImpl;
import queens.backtracking.detector.SolutionDetectorImpl;
import sequence.Sequence;
import sequence.SequenceImpl;
import sequence.SequenceUtils;

public interface BacktrackingWithPruningSolver {
    public static Set<Sequence<Integer>> solve(
            int n, ExtensibilityDetector extensibility, Detector viability, Detector solution) {
        Sequence<Integer> emptySequence = new SequenceImpl<Integer>();
        return solve_aux(emptySequence, n, extensibility, viability, solution);
    }

    //part of post: all sequences in rv have sequence as a prefix
    public static Set<Sequence<Integer>> solve_aux(
            Sequence<Integer> prefix, int n, ExtensibilityDetector extensibility, Detector viability,
            Detector solution) {
        assert prefix != null : "prefix is null!";
        assert viability.isDetected(prefix) : String.format("prefix is not viable! : prefix = %s, n = %s", prefix, n);

        Set<Sequence<Integer>> solutionWithPrefix_Set = new HashSet<Sequence<Integer>>();

        for (int i = 0; i < n; i++) {
            if (extensibility.canExtend(prefix, i)) {
                prefix.extend(i);
                if (solution.isDetected(prefix)) {
                    solutionWithPrefix_Set.add(SequenceUtils.shallowCopy(prefix));
                } else {
                    solutionWithPrefix_Set.addAll(solve_aux(prefix, n, extensibility, viability, solution));
                }
                prefix.retract();
            }
        }
        return solutionWithPrefix_Set;
    }

    public static void main(String[] args) {
        final int CLASSIC_CHESS_BOARD_SIZE = 8;

        ExtensibilityDetector extensibility = new ExtensibilityDetectorImpl(CLASSIC_CHESS_BOARD_SIZE);
        Detector solution = new SolutionDetectorImpl(CLASSIC_CHESS_BOARD_SIZE);

        assert extensibility.getCanExtendPostcondition() == solution.getIsDetectedPrecondition() :
                "Extensibility postcondtion != solution precondition!";

        Detector viability = solution.getIsDetectedPrecondition();

        Set<Sequence<Integer>> solutionSet = solve(CLASSIC_CHESS_BOARD_SIZE, extensibility, viability, solution);
        for (Sequence<Integer> queensPlacementSequence : solutionSet) {
            QueensChessBoardState board = new QueensChessBoardStateImpl(queensPlacementSequence,
                    CLASSIC_CHESS_BOARD_SIZE);
            System.out.println(board + "\n");
            //			try {
            //				System.in.read();
            //			} catch (IOException e) {
            //				e.printStackTrace();
            //			}
        }
        System.out.println("count = " + solutionSet.size());
    }
}