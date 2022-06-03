package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    @Test
    void testSolving() {
        String testString = "10 | 11 + 14 - 13";
        BigInteger preferedAnswer = BigInteger.valueOf(2);

        String testString2 = "10 | 11 * 11";
        BigInteger preferedAnswer2 = BigInteger.valueOf(1);

        String testString3 = "10 | 11 / 11";
        BigInteger preferedAnswer3 = BigInteger.valueOf(1);

        Solver slv = new Solver();
        BigInteger realAnswer = slv.solve(testString);
        BigInteger realAnswer2 = slv.solve(testString2);
        BigInteger realAnswer3 = slv.solve(testString3);

        assert (realAnswer.equals(preferedAnswer));
        assert (realAnswer2.equals(preferedAnswer2));
        assert (realAnswer3.equals(preferedAnswer3));
    }
}