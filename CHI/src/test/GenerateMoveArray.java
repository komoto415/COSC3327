package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GenerateMoveArray {
    // DO NOT CONSIDER THESE ABSOLUTE. When an array is produced, reproduce the list as
    // though you were playing it to get PROPER expected results!! Not to bend your code
    // to these
    public static void main(String[] args) {
        Random r = new Random();
        List<List<Integer>> boardsList = new ArrayList<>();
        for (int j = 0; j < 6; j++) {
			/* 	Note: Neither of these account for the possibility for continued set 
		    	marks after the game is over. 
				Can then be used for an expected fail case! */
//			Set<Integer> set = new HashSet<>(); // Better for testing getTurn, isGameOver and getWinner
            List<Integer> set = new ArrayList<>(); // Better for testing setMark
            for (int i = 0; i < r.nextInt(9); i++) {
                set.add(r.nextInt(9));
            }
            List<Integer> list = set.stream().collect(Collectors.toList());
            Collections.shuffle(list);
            for (int i = list.size(); i < 9; i++) {
                list.add(-1);
            }
            boardsList.add(list);
        }
        for (List<Integer> curSet : boardsList) {
            System.out.println(curSet);
        }
    }
}
