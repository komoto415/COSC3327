package census;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import java.util.List;

public class WordCensusImpl_Ng implements WordCensus {
    private Map<String, Long> wordCountLinkedHashMap;
    private List<String> wordsSortedByCountList;

    public WordCensusImpl_Ng(List<String> wordList) {
        assert wordList != null : "Invalid send! : <null> " + "You cannot send a negative rank!";

        Map<String, Long> wordCountHash =
                wordList.stream().collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        wordCountLinkedHashMap = hashMapToSortedLinkedHashMap(wordCountHash);

        wordsSortedByCountList = new ArrayList<>();
        wordsSortedByCountList.addAll(wordCountLinkedHashMap.keySet());

//		System.out.println(wordsSortedByCountList);
//		System.out.println(wordCount);
    }

    @Override
    public int getCount(String word) {
        long count = wordCountLinkedHashMap.containsKey(word) ? wordCountLinkedHashMap.get(word) : 0;

        return (int) count;
    }

    public String getWordWithRank(int i) {
        assert 0 < i : "Invalid send! : <" + i + "> " + "You cannot send a negative rank!";
        assert i <= getDistinctWordCount() :
                "Invalid send! : <" + i + "> " + "You cannot send a lower rank than possible!";

        return wordsSortedByCountList.get(i - 1);
    }

    public int getDistinctWordCount() {
        return wordCountLinkedHashMap.size();
    }

    private static Map<String, Long> hashMapToSortedLinkedHashMap(Map<String, Long> map) {
        List<Map.Entry<String, Long>> sortingList = new ArrayList<>(map.entrySet());

        // Sort list by integer values then by string keys
        Collections.sort(sortingList, (a, b) -> {
            int cmp1 = b.getValue().compareTo(a.getValue());
            if (cmp1 != 0) {
                return cmp1;
            } else {
                return a.getKey().compareTo(b.getKey());
            }
        });

        Map<String, Long> sortedHash = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : sortingList)
            sortedHash.put(entry.getKey(), entry.getValue());

        return sortedHash;
    }
}
