package queens.backtracking.detector;

import java.util.HashMap;
import java.util.Map;

import backtracking.detector.Detector;

public class ViabilityDetectorFactory {
    private static Map<Integer, Detector> boardSizeToDetectorMap;

    static {
        boardSizeToDetectorMap = new HashMap<Integer, Detector>();
    }

    public static Detector getViabilityDetector(int boardSize) {
        Detector viabilityDetector = null;
        if (!boardSizeToDetectorMap.keySet().contains(boardSize)) {
            boardSizeToDetectorMap.put(boardSize, new ViabilityDetectorImpl(boardSize));
        }
        viabilityDetector = boardSizeToDetectorMap.get(boardSize);
        return viabilityDetector;
    }
}
