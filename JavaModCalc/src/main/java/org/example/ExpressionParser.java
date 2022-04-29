package org.example;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpressionParser {
    //todo template for codegen
    private HashMap<String, Operator> createOperationsMap(){
        HashMap<String, Operator> operationsMap = new HashMap<String, Operator>();
        operationsMap.put("+", new Addition());
        operationsMap.put("-", new Substraction());
        operationsMap.put("*", new Multiplication());
        operationsMap.put("/", new Division());
        operationsMap.put("^", new Pow());
        return operationsMap;
    }

    public Expression parseInput(String inputString) {
        String[] args = inputString.split("\\s+");
        boolean unparsed = false;

        String modString = args[0];
        BigInteger mod = BigInteger.valueOf(0);
        try {
            mod = new BigInteger(modString);
        } catch (Exception e) {
            unparsed = true;
        }

        if (unparsed) {
            return new Expression(unparsed); //todo make custom exception
        }

        if (args.length % 2 != 1 || args.length <= 2 || !args[1].equals("|")) {
            unparsed = true;
        }

        BigInteger[] valuesList = new BigInteger[(args.length - 1)/2];
        try {
            for (int i = 2; i < args.length; i+=2) {
                    valuesList[(i - 2)/2] = new BigInteger(args[i]);
            }
        } catch (Exception e) {
            unparsed = true;
        }

        if (unparsed) {
            return new Expression(unparsed); //todo make custom exception
        }

        HashMap<String, Operator> operationsMap = createOperationsMap();
        ArrayList<Object> operators = new ArrayList<Object>();

        try {
            for (int i = 3; i < args.length; i += 2) {
                if (operationsMap.get(args[i]) != null) {
                    operators.add(operationsMap.get(args[i]));
                } else {
                    unparsed = true;
                    break;
                }
            }
        } catch (Exception e) {
            unparsed = true;
        }

        if (unparsed) {
            return new Expression(unparsed); //todo make custom exception
        }

        return new Expression(mod, operators, valuesList, unparsed);
    }
}


