package com.techreturn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandsCompareTest {

    //@Test
    @ParameterizedTest
    @CsvSource({"'white','Black: 2H 3D 5S 9C KD','White: 2C 3H 4S 8C AH'",
                "'black','Black: 2H AD 5S 9C KD','White: 2C 3H KS 8C QH'",
                "'Tie','Black: 2H AD 5S 9C KD','White: 2C AH 9S 5C KH'",
                "'black','Black: 2H QD 5S 9C KD','White: 2C 3H KS 8C JH'",
                        "'white','Black: 9C 3D 5S 2H KD','White: 2C 4H 5S 9C KH'"
    })
    public void compareSimpleTest(String expected, String black, String white) {
        PokerHands pokerhands = new PokerHands();
        //string expected = null;
        // compare method return the person name in lowercase with higher rank, or null if they are same
        assertEquals( expected, pokerhands.compare(black, white ));
    }

    //@Test
    @ParameterizedTest
    @CsvSource({"'black','Black: 2H 3D 5S 9C 9D','White: 2C 3H 4S 8C AH'",
            "'white','Black: 2H 3D 5S 9C 9D','White: 2C 3H 4S AC AH'"
        })
    public void compareDifferentRanks(String expected, String black, String white) {
        PokerHands pokerhands = new PokerHands();

        assertEquals( expected, pokerhands.compare(black, white ));
    }
}
