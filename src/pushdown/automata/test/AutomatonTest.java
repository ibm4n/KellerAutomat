package pushdown.automata.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pushdown.automata.Automaton;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutomatonTest {

    private Automaton automaton;
    private final static String TEST_A = "34+62+89+43+***";
    private final static String TEST_B = "31+78+987+1214++7++++++";
    private final static String TEST_C1 = "34+*";
    private final static String TEST_C2 = "8+9+7*2*";

    @BeforeEach
    void setup() {
        this.automaton = new Automaton();
    }


    @Test
    public void testA() {
        String result = automaton.runAutomate(TEST_A);
        assertEquals("6664", result);
    }

    @Test
    public void testB() {
        String result = automaton.runAutomate(TEST_B);
        assertEquals("58", result);
    }

    @Test
    public void testC1() {
        String result = automaton.runAutomate(TEST_C1);
        assertEquals("Böööp", result);
    }

    @Test
    public void testC2() {
        String result = automaton.runAutomate(TEST_C2);
        assertEquals("Böööp", result);
    }


}
