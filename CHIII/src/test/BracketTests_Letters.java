package test;

import static org.junit.Assert.*;

import static tournament.Letters.a;
import static tournament.Letters.b;
import static tournament.Letters.c;
import static tournament.Letters.d;
import static tournament.Letters.e;
import static tournament.Letters.f;
import static tournament.Letters.g;
import static tournament.Letters.h;
import static tournament.Letters.i;
import static tournament.Letters.j;
import static tournament.Letters.k;
import static tournament.Letters.l;
import static tournament.Letters.m;
import static tournament.Letters.n;
import static tournament.Letters.o;
import static tournament.Letters.p;
import static tournament.Letters.q;
import static tournament.Letters.r;
import static tournament.Letters.s;
import static tournament.Letters.t;
import static tournament.Letters.u;
import static tournament.Letters.v;
import static tournament.Letters.w;
import static tournament.Letters.x;
import static tournament.Letters.y;
import static tournament.Letters.z;

import static tournament.Letters.A;
import static tournament.Letters.B;
import static tournament.Letters.C;
import static tournament.Letters.D;
import static tournament.Letters.E;
import static tournament.Letters.F;
import static tournament.Letters.G;
import static tournament.Letters.H;
import static tournament.Letters.I;
import static tournament.Letters.J;
import static tournament.Letters.K;
import static tournament.Letters.L;
import static tournament.Letters.M;
import static tournament.Letters.N;
import static tournament.Letters.O;
import static tournament.Letters.P;
import static tournament.Letters.Q;
import static tournament.Letters.R;
import static tournament.Letters.S;
import static tournament.Letters.T;
import static tournament.Letters.U;
import static tournament.Letters.V;
import static tournament.Letters.W;
import static tournament.Letters.X;
import static tournament.Letters.Y;
import static tournament.Letters.Z;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import tournament.Bracket;
import tournament.BracketImpl_Ng;
import tournament.FIFASoccerTeam;
import tournament.Letters;

@SuppressWarnings("unused")
public class BracketTests_Letters {

    /* Fast S.O.P for instance toString if available */
    private void print(Bracket<Letters> bracket) {
        System.out.println(bracket.toString());
    }

    private static final char TEST_DELIMETER_CHAR = '_';
    private static final String TEST_DELIMETER_STR = "_";

    // Up to 32 participant brackets instantiation and potential access to the participant
    private String getBracketPartP1() {
        return "A_";
    }

    private Bracket<Letters> getBracketP1() {
        String[] letters = getBracketPartP1().split(TEST_DELIMETER_STR);
        List<Letters> participants = Arrays.stream(letters).map(x -> Letters.translate(x)).collect(Collectors.toList());
        Bracket<Letters> bracket = new BracketImpl_Ng<>(participants);
        return bracket;
    }

    private String getBracketPartP2() {
        return "A_a_";
    }

    private Bracket<Letters> getBracketP2() {
        String[] letters = getBracketPartP2().split(TEST_DELIMETER_STR);
        List<Letters> participants = Arrays.stream(letters).map(x -> Letters.translate(x)).collect(Collectors.toList());
        Bracket<Letters> bracket = new BracketImpl_Ng<>(participants);
        return bracket;
    }

    private String getBracketPartP4() {
        return "A_a_B_b_";
    }

    private Bracket<Letters> getBracketP4() {
        String[] letters = getBracketPartP4().split(TEST_DELIMETER_STR);
        List<Letters> participants = Arrays.stream(letters).map(x -> Letters.translate(x)).collect(Collectors.toList());
        Bracket<Letters> bracket = new BracketImpl_Ng<>(participants);
        return bracket;
    }

    private String getBracketPartP8() {
        return "A_a_B_b_C_c_D_d_";
    }

    private Bracket<Letters> getBracketP8() {
        String[] letters = getBracketPartP8().split(TEST_DELIMETER_STR);
        List<Letters> participants = Arrays.stream(letters).map(x -> Letters.translate(x)).collect(Collectors.toList());
        Bracket<Letters> bracket = new BracketImpl_Ng<>(participants);
        return bracket;
    }

    private String getBracketPartP16() {
        return "A_a_B_b_C_c_D_d_E_e_F_f_G_g_H_h_";
    }

    private Bracket<Letters> getBracketP16() {
        String[] letters = getBracketPartP16().split(TEST_DELIMETER_STR);
        List<Letters> participants = Arrays.stream(letters).map(x -> Letters.translate(x)).collect(Collectors.toList());
        Bracket<Letters> bracket = new BracketImpl_Ng<>(participants);
        return bracket;
    }

    private String getBracketPartP32() {
        return "A_a_B_b_C_c_D_d_E_e_F_f_G_g_H_h_I_i_J_j_K_k_L_l_M_m_N_n_O_o_P_p_";
    }

    private Bracket<Letters> getBracketP32() {
        String[] letters = getBracketPartP32().split(TEST_DELIMETER_STR);
        List<Letters> participants = Arrays.stream(letters).map(x -> Letters.translate(x)).collect(Collectors.toList());
        Bracket<Letters> bracket = new BracketImpl_Ng<>(participants);
        return bracket;
    }

    /* Testing dividers */
    private String testName;

    private void bookend(String testName) {
        System.out.println(String.format("******NOW TESTING %s******", testName.toUpperCase()));
    }

    private void bookendd(String testName) {
        System.out.println(String.format("******DONE TESTING %s******\n", testName.toUpperCase()));
    }

    // Just testing some toString
    @Test
    public void test_toString__P1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP1();

        print(bracket);

        bookendd(testName);
    }

    @Test
    public void test_toString__P2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP2();

        print(bracket);

        bookendd(testName);
    }

    @Test
    public void test_toString__P4() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP4();

        print(bracket);

        bookendd(testName);
    }

    @Test
    public void test_toString__P8() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP8();

        print(bracket);

        bookendd(testName);
    }

    @Test
    public void test_toString__P16() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP16();

        print(bracket);

        bookendd(testName);
    }

    @Test
    public void test_toString__P32() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP32();

        print(bracket);

        bookendd(testName);
    }

    // Testing maxLevels
    @Test
    public void test0_maxLevel__P1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP1();

        int expected = Integer.parseInt(String.valueOf(testName.charAt(4)));
        assertEquals(expected, bracket.getMaxLevel());

        bookendd(testName);
    }

    @Test
    public void test1_maxLevel__P2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP2();

        int expected = Integer.parseInt(String.valueOf(testName.charAt(4)));
        assertEquals(expected, bracket.getMaxLevel());

        bookendd(testName);
    }

    @Test
    public void test2_maxLevel__P4() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP4();

        int expected = Integer.parseInt(String.valueOf(testName.charAt(4)));
        assertEquals(expected, bracket.getMaxLevel());

        bookendd(testName);
    }

    @Test
    public void test3_maxLevel__P8() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP8();

        int expected = Integer.parseInt(String.valueOf(testName.charAt(4)));
        assertEquals(expected, bracket.getMaxLevel());

        bookendd(testName);
    }

    @Test
    public void test4_maxLevel__P16() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP16();

        int expected = Integer.parseInt(String.valueOf(testName.charAt(4)));
        assertEquals(expected, bracket.getMaxLevel());

        bookendd(testName);
    }

    @Test
    public void test5_maxLevel__P32() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP32();

        int expected = Integer.parseInt(String.valueOf(testName.charAt(4)));
        assertEquals(expected, bracket.getMaxLevel());

        bookendd(testName);
    }

    // Testing getGroupings
    @Test(expected = AssertionError.class)
    public void testLFail1_getGroupings_P1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP1();

        int level = 1;
        Set<Set<Letters>> groupings = bracket.getGroupings(level);

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void testFail2_getGroupings_P1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP1();

        int level = -1;
        Set<Set<Letters>> groupings = bracket.getGroupings(level);

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P1_Level0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP1();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP1();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch = parts.charAt(i);
            assertTrue(
                    groupings.contains(new HashSet<Letters>(Arrays.asList(Letters.translate(Character.toString(ch))))));
        }

        bookendd(testName);
    }

    @Test(expected = AssertionError.class)
    public void testFail1_getGroupings_P2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP1();

        int level = 2;
        Set<Set<Letters>> groupings = bracket.getGroupings(level);

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P2_Level0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP2();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP2();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch = parts.charAt(i);
            assertTrue(
                    groupings.contains(new HashSet<Letters>(Arrays.asList(Letters.translate(Character.toString(ch))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P2_Level1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP2();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP2();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch_0 = parts.charAt(i);
            char ch_1 = parts.charAt(i + 2);
            assertTrue(groupings.contains(new HashSet<Letters>(
                    Arrays.asList(Letters.translate(Character.toString(ch_0)),
                            Letters.translate(Character.toString(ch_1))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P4_Level0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP4();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP4();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch = parts.charAt(i);
            assertTrue(
                    groupings.contains(new HashSet<Letters>(Arrays.asList(Letters.translate(Character.toString(ch))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P4_Level1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP4();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP4();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch_0 = parts.charAt(i);
            char ch_1 = parts.charAt(i + 2);
            assertTrue(groupings.contains(new HashSet<Letters>(
                    Arrays.asList(Letters.translate(Character.toString(ch_0)),
                            Letters.translate(Character.toString(ch_1))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P4_Level2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP4();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP4();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch_0 = parts.charAt(i);
            char ch_1 = parts.charAt(i + 2);
            char ch_2 = parts.charAt(i + 4);
            char ch_3 = parts.charAt(i + 6);
            assertTrue(groupings.contains(new HashSet<Letters>(
                    Arrays.asList(Letters.translate(Character.toString(ch_0)),
                            Letters.translate(Character.toString(ch_1)), Letters.translate(Character.toString(ch_2)),
                            Letters.translate(Character.toString(ch_3))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P8_Level0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP8();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP8();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch = parts.charAt(i);
            assertTrue(
                    groupings.contains(new HashSet<Letters>(Arrays.asList(Letters.translate(Character.toString(ch))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P8_Level1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP8();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP8();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch_0 = parts.charAt(i);
            char ch_1 = parts.charAt(i + 2);
            assertTrue(groupings.contains(new HashSet<Letters>(
                    Arrays.asList(Letters.translate(Character.toString(ch_0)),
                            Letters.translate(Character.toString(ch_1))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P8_Level2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP8();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP8();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch_0 = parts.charAt(i);
            char ch_1 = parts.charAt(i + 2);
            char ch_2 = parts.charAt(i + 4);
            char ch_3 = parts.charAt(i + 6);
            assertTrue(groupings.contains(new HashSet<Letters>(
                    Arrays.asList(Letters.translate(Character.toString(ch_0)),
                            Letters.translate(Character.toString(ch_1)), Letters.translate(Character.toString(ch_2)),
                            Letters.translate(Character.toString(ch_3))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P8_Level3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP8();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP8();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch_0 = parts.charAt(i);
            char ch_1 = parts.charAt(i + 2);
            char ch_2 = parts.charAt(i + 4);
            char ch_3 = parts.charAt(i + 6);
            char ch_4 = parts.charAt(i + 8);
            char ch_5 = parts.charAt(i + 10);
            char ch_6 = parts.charAt(i + 12);
            char ch_7 = parts.charAt(i + 14);
            assertTrue(groupings.contains(new HashSet<Letters>(
                    Arrays.asList(Letters.translate(Character.toString(ch_0)),
                            Letters.translate(Character.toString(ch_1)), Letters.translate(Character.toString(ch_2)),
                            Letters.translate(Character.toString(ch_3)), Letters.translate(Character.toString(ch_4)),
                            Letters.translate(Character.toString(ch_5)), Letters.translate(Character.toString(ch_6)),
                            Letters.translate(Character.toString(ch_7))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P16_Level0() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP16();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP16();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch = parts.charAt(i);
            assertTrue(
                    groupings.contains(new HashSet<Letters>(Arrays.asList(Letters.translate(Character.toString(ch))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P16_Level1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP16();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP16();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch_0 = parts.charAt(i);
            char ch_1 = parts.charAt(i + 2);
            assertTrue(groupings.contains(new HashSet<Letters>(
                    Arrays.asList(Letters.translate(Character.toString(ch_0)),
                            Letters.translate(Character.toString(ch_1))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P16_Level2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP16();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP16();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch_0 = parts.charAt(i);
            char ch_1 = parts.charAt(i + 2);
            char ch_2 = parts.charAt(i + 4);
            char ch_3 = parts.charAt(i + 6);
            assertTrue(groupings.contains(new HashSet<Letters>(
                    Arrays.asList(Letters.translate(Character.toString(ch_0)),
                            Letters.translate(Character.toString(ch_1)), Letters.translate(Character.toString(ch_2)),
                            Letters.translate(Character.toString(ch_3))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P16_Level3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP16();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP16();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch_0 = parts.charAt(i);
            char ch_1 = parts.charAt(i + 2);
            char ch_2 = parts.charAt(i + 4);
            char ch_3 = parts.charAt(i + 6);
            char ch_4 = parts.charAt(i + 8);
            char ch_5 = parts.charAt(i + 10);
            char ch_6 = parts.charAt(i + 12);
            char ch_7 = parts.charAt(i + 14);
            assertTrue(groupings.contains(new HashSet<Letters>(
                    Arrays.asList(Letters.translate(Character.toString(ch_0)),
                            Letters.translate(Character.toString(ch_1)), Letters.translate(Character.toString(ch_2)),
                            Letters.translate(Character.toString(ch_3)), Letters.translate(Character.toString(ch_4)),
                            Letters.translate(Character.toString(ch_5)), Letters.translate(Character.toString(ch_6)),
                            Letters.translate(Character.toString(ch_7))))));
        }

        bookendd(testName);
    }

    @Test
    public void test_getGroupings_P16_Level4() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);
        Bracket<Letters> bracket = getBracketP16();

        int level = Integer.parseInt(String.valueOf(testName.charAt(testName.length() - 1)));
        Set<Set<Letters>> groupings = bracket.getGroupings(level);
        int itr = (int) Math.pow(2, level + 1);

        printGroupings(groupings);

        String parts = getBracketPartP16();
        for (int i = 0; i < parts.length(); i += itr) {
            char ch_0 = parts.charAt(i);
            char ch_1 = parts.charAt(i + 2);
            char ch_2 = parts.charAt(i + 4);
            char ch_3 = parts.charAt(i + 6);
            char ch_4 = parts.charAt(i + 8);
            char ch_5 = parts.charAt(i + 10);
            char ch_6 = parts.charAt(i + 12);
            char ch_7 = parts.charAt(i + 14);
            char ch_8 = parts.charAt(i + 16);
            char ch_9 = parts.charAt(i + 18);
            char ch_10 = parts.charAt(i + 20);
            char ch_11 = parts.charAt(i + 22);
            char ch_12 = parts.charAt(i + 24);
            char ch_13 = parts.charAt(i + 26);
            char ch_14 = parts.charAt(i + 28);
            char ch_15 = parts.charAt(i + 30);
            assertTrue(groupings.contains(new HashSet<Letters>(
                    Arrays.asList(Letters.translate(Character.toString(ch_0)),
                            Letters.translate(Character.toString(ch_1)), Letters.translate(Character.toString(ch_2)),
                            Letters.translate(Character.toString(ch_3)), Letters.translate(Character.toString(ch_4)),
                            Letters.translate(Character.toString(ch_5)), Letters.translate(Character.toString(ch_6)),
                            Letters.translate(Character.toString(ch_7)), Letters.translate(Character.toString(ch_8)),
                            Letters.translate(Character.toString(ch_9)), Letters.translate(Character.toString(ch_10)),
                            Letters.translate(Character.toString(ch_11)), Letters.translate(Character.toString(ch_12)),
                            Letters.translate(Character.toString(ch_13)), Letters.translate(Character.toString(ch_14)),
                            Letters.translate(Character.toString(ch_15))))));
        }

        bookendd(testName);
    }

    private void printGroupings(Set<Set<Letters>> groupings) {
        for (Set<Letters> curVal : groupings) {
            System.out.print(curVal);
        }
        System.out.println();
    }

    public Bracket<Letters> getBracketP16State1() {
        Bracket<Letters> bracket = getBracketP16();

        bracket.setWinCount(A, 1);
        bracket.setWinCount(b, 1);
        bracket.setWinCount(H, 4);
        bracket.setWinCount(A, 2);
        bracket.setWinCount(G, 2);
        bracket.setWinCount(F, 1);
        bracket.setWinCount(F, 1);
        bracket.setWinCount(D, 4);
        print(bracket);

        return bracket;
    }


    protected Set<Letters> expected;
    protected Set<Letters> actual;

    // getViableParticipants
    @Test
    public void test_getViableParticipantP16State1Level1() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        Bracket<Letters> bracket = getBracketP16State1();
        print(bracket);

        expected = new HashSet<>(Arrays.asList(b));
        actual = bracket.getViableParticipants(new HashSet<>(Arrays.asList(B, b)));
        assertEquals(expected, actual);

        expected = new HashSet<>(Arrays.asList(C, c));
        actual = bracket.getViableParticipants(new HashSet<>(Arrays.asList(C, c)));
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void test_getViableParticipantP16State1Level2() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        Bracket<Letters> bracket = getBracketP16State1();
        print(bracket);

        expected = new HashSet<>(Arrays.asList(A));
        actual = bracket.getViableParticipants(new HashSet<>(Arrays.asList(A, a, B, b)));
        assertEquals(expected, actual);

        expected = new HashSet<>(Arrays.asList(E, e, F));
        actual = bracket.getViableParticipants(new HashSet<>(Arrays.asList(E, e, F, f)));
        assertEquals(expected, actual);

        bracket.setWinCount(e, 1);
        print(bracket);
        expected = new HashSet<>(Arrays.asList(e, F));
        actual = bracket.getViableParticipants(new HashSet<>(Arrays.asList(E, e, F, f)));
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void test_getViableParticipantP16State1Level3() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        Bracket<Letters> bracket = getBracketP16State1();
        print(bracket);

        expected = new HashSet<>(Arrays.asList(D));
        actual = bracket.getViableParticipants(new HashSet<>(Arrays.asList(A, a, B, b, C, c, D, d)));
        assertEquals(expected, actual);

        expected = new HashSet<>(Arrays.asList(E, e, F, G));
        actual = bracket.getViableParticipants(new HashSet<>(Arrays.asList(E, e, F, f, G, g, H, h)));
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void test_getViableParticipantP16State1Level4() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        Bracket<Letters> bracket = getBracketP16State1();
        print(bracket);

        expected = new HashSet<>(Arrays.asList(D));
        actual = bracket.getViableParticipants(
                new HashSet<>(Arrays.asList(A, a, B, b, C, c, D, d, E, e, F, f, G, g, H, h)));
        assertEquals(expected, actual);

        bookendd(testName);
    }

    @Test
    public void makeBracket() {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        bookend(testName);

        Bracket<Letters> bracket = getBracketP16();
        print(bracket);

        bracket.setWinCount(D, 4);
        print(bracket);

        bracket.setWinCount(D, 3);
        print(bracket);

        bracket.setWinCount(D, 2);
        print(bracket);

        bookendd(testName);
    }

}
