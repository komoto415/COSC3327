package test;

import org.junit.Test;

public class TicTacToeGameTestsSubsetForStudents_1_ENVIRONMENT {
    protected String TEST_GOAL_MESSAGE;

    @Points(value = 0)
    @Test(expected = AssertionError.class)
    public void assertionsEnabledTest() {
        TEST_GOAL_MESSAGE = "Check whether assertions are enabled";
        assert false;
        throw new RuntimeException("ENABLE ASSERTIONS IN RUN CONFIGURATION!");
    }
}
