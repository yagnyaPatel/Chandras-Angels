package edu.ucsb.munchease;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PartyHomeActivityUnitTests {
    @Test
    public void testFunction_isCorrect_4_4() {
        assertEquals(4, TestFunctions.testFunction(4));
    }

    @Test
    public void testFunction_isCorrect_5_5() {
        assertEquals(5, TestFunctions.testFunction(5));
    }
}