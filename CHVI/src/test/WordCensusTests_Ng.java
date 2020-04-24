package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import census.WordCensus;
import census.WordCensusImpl_Ng;

public class WordCensusTests_Ng {
    private static List<String> getWordList(Scanner wordScanner) {
        List<String> wordList = new ArrayList<String>();
        while (wordScanner.hasNext()) {
            wordList.add(wordScanner.next().toLowerCase());
        }
        return wordList;
    }

    private static WordCensus getWordCensus(Scanner wordScanner) {
        List<String> wordList = getWordList(wordScanner);
        return new WordCensusImpl_Ng(wordList);
    }

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Test
    public void oneWordTest() {
        final String WORD = "aardvark";
        Scanner scanner = new Scanner(WORD);
        WordCensus wordCensus = getWordCensus(scanner);
        assertEquals(1, wordCensus.getCount(WORD));
    }

    @Test
    public void threeWordTest() {
        final String WORDS = "aardvark aardvark turtle";
        Scanner scanner = new Scanner(WORDS);
        WordCensus wordCensus = getWordCensus(scanner);
        assertEquals(2, wordCensus.getCount("aardvark"));
        assertEquals(0, wordCensus.getCount("giraffe"));
        assertEquals(1, wordCensus.getCount("turtle"));
    }

    @Test
    public void noWordsTest() {
        final String WORDS = "";
        Scanner scanner = new Scanner(WORDS);
        WordCensus wordCensus = getWordCensus(scanner);
        assertEquals(0, wordCensus.getCount(""));
        assertEquals(0, wordCensus.getDistinctWordCount());
        try {
            wordCensus.getWordWithRank(0);
            System.out.println("Should have failed!");
            assert false;
        } catch (AssertionError ae) {
        }
    }

    @Test
    public void sameSpellingWierdCasingTest() {
        final String WORDS = "POTENTIAL pOteNtIaL POteNTiaL potenTIAL";
        Scanner scanner = new Scanner(WORDS);
        WordCensus wordCensus = getWordCensus(scanner);
        assertEquals(0, wordCensus.getCount("POTENTIAL"));
        assertEquals(0, wordCensus.getCount("pOteNtIaL"));
        assertEquals(0, wordCensus.getCount("POteNTiaL"));
        assertEquals(0, wordCensus.getCount("potenTIAL"));
        assertEquals(4, wordCensus.getCount("potential"));

        assertEquals(1, wordCensus.getDistinctWordCount());

        assertEquals("potential", wordCensus.getWordWithRank(1));
    }

    @Test
    public void specialChracters() {
        final String WORDS = ".. .. .. .. .... .. .... ..";
        Scanner scanner = new Scanner(WORDS);
        WordCensus wordCensus = getWordCensus(scanner);
        assertEquals(6, wordCensus.getCount(".."));
        assertEquals(2, wordCensus.getCount("...."));

        assertEquals(2, wordCensus.getDistinctWordCount());

        assertEquals("..", wordCensus.getWordWithRank(1));
        assertEquals("....", wordCensus.getWordWithRank(2));
    }

    @Test
    public void warAndPeaceLowerCaseTest() {
        Scanner warAndPeaceScanner = null;
        try {
            warAndPeaceScanner = getWarAndPeaceScanner();
        } catch (IOException e) {
            fail("War and Peace not read!");
        }
        WordCensus warAndPeaceCensus = getWordCensus(warAndPeaceScanner);
        assertEquals(288, warAndPeaceCensus.getCount("war"));
        assertEquals(109, warAndPeaceCensus.getCount("peace"));
        assertEquals(22070, warAndPeaceCensus.getCount("and"));
        assertEquals(10456, warAndPeaceCensus.getCount("a"));
        assertEquals(34375, warAndPeaceCensus.getCount("the"));
        assertEquals(0, warAndPeaceCensus.getCount("tomato"));
        assertEquals(1614, warAndPeaceCensus.getCount("an"));

        assertEquals(21902, warAndPeaceCensus.getDistinctWordCount());

        final int DESIRED_LENGTH = 2000;
        for (int i = 1; i <= DESIRED_LENGTH; i++) {
            String word_i = warAndPeaceCensus.getWordWithRank(i);
            int count_i = warAndPeaceCensus.getCount(word_i);
            System.out.println(String.format("%s: <%s>\t%s", i, word_i, count_i));
        }

        assertEquals("the", warAndPeaceCensus.getWordWithRank(1));
        assertEquals("and", warAndPeaceCensus.getWordWithRank(2));
        assertEquals("to", warAndPeaceCensus.getWordWithRank(3));
        assertEquals("of", warAndPeaceCensus.getWordWithRank(4));
        assertEquals("a", warAndPeaceCensus.getWordWithRank(5));

        assertEquals(100, warAndPeaceCensus.getCount("legs"));
        assertEquals(100, warAndPeaceCensus.getCount("ought"));
        assertEquals(100, warAndPeaceCensus.getCount("run"));

        assertEquals("legs", warAndPeaceCensus.getWordWithRank(618));
        assertEquals("ought", warAndPeaceCensus.getWordWithRank(619));
        assertEquals("run", warAndPeaceCensus.getWordWithRank(620));
    }

    private static Scanner getWarAndPeaceScanner() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("resources/WarAndPeace.txt");
        assert input != null :
                "input is null! : Check that the resources folder is on the classpath, the file name is correct, and the file is in the resources folder";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        Scanner warAndPeaceScanner = new Scanner(bufferedReader);

        //Scanner defaults don't handle single quotes:
        warAndPeaceScanner.useDelimiter("(" + warAndPeaceScanner.delimiter().pattern() + "|[.!,?\"()])+");
        return warAndPeaceScanner;
    }
}
