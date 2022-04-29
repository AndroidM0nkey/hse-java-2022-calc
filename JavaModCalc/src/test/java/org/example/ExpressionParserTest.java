package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {
    @Test
    void testParsing() {
        //test data
        BigInteger mod = BigInteger.valueOf(10);
        BigInteger[] valuesList = new BigInteger[3];
        valuesList[0] = BigInteger.valueOf(11);
        valuesList[1] = BigInteger.valueOf(12);
        valuesList[2] = BigInteger.valueOf(13);
        ArrayList<Object> operatorsList = new ArrayList<Object>();
        operatorsList.add(new Addition());
        operatorsList.add(new Substraction());
        boolean unparsed = false;
        String testString = "10 | 11 + 12 - 13";

        ExpressionParser exp = new ExpressionParser();
        Expression ideal = new Expression(mod, operatorsList, valuesList, unparsed);
        Expression real = exp.parseInput(testString);
        assert (ideal.getMod().equals(real.getMod()));
        assert (Arrays.equals(ideal.getValuesList(), real.getValuesList()));
        assert (ideal.isValid() == real.isValid());

        String testString2 = "10 | 11 12 - 13";
        real = exp.parseInput(testString2);
        assert (!real.isValid());

        String testString3 = "10 | 1ds13qq1 + 12 - 13";
        real = exp.parseInput(testString3);
        assert (!real.isValid());
    }
}