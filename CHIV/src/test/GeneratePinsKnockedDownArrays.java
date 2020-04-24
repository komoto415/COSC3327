package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GeneratePinsKnockedDownArrays {
    public static void main(String[] args) {
        List<List<Integer>> pinsList = new ArrayList<>();
        Random r = new Random();
        for (int j = 0; j < 10; j++) {
            List<Integer> list = new ArrayList<>(Arrays.asList(r.nextInt(11)));
            for (int i = 1; i < r.nextInt(21); i++) {
                int potent = r.nextInt(9);
                if (list.get(i - 1) != 10) {
                    boolean valid = false;
                    while (!valid) {
                        potent = r.nextInt(10 - list.get(i - 1));
                        valid = list.get(i - 1) + potent <= 10;
                        if (valid) {
                            list.add(potent);
                        }
                    }
                } else {
                    list.add(potent);
                }
            }
            pinsList.add(list);
        }
        pinsList.forEach(System.out::println);
        ;
    }
}
