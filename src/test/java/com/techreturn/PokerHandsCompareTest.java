package com.techreturn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandsCompareTest {

    @Test
    public void compareSimpleTest() {
        PokerHands pokerhands = new PokerHands();
        //string expected = null;
        // compare method return the person name in lowercase with higher rank, or null if they are same
        assertEquals( "white", pokerhands.compare("Black: 2H 3D 5S 9C KD", "White: 2C 3H 4S 8C AH" ));
    }
}
