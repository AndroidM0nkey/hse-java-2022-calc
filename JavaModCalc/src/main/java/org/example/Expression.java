package org.example;
import java.math.*;
import java.util.ArrayList;
import java.util.List;

public class Expression {
    BigInteger mod;
    ArrayList<Object> operatorsList;
    BigInteger[] valuesList;
    boolean unparsed = false;
    public Expression (boolean unparsedC) {
        unparsed = unparsedC;
    }

    public boolean isValid () {
        return !unparsed;
    }

    public ArrayList<Object> getOperators() {
        return operatorsList;
    }

    public BigInteger[] getValuesList() {
        return valuesList;
    }

    public BigInteger getMod() {
        return mod;
    }

    public Expression (BigInteger modC, ArrayList<Object> operatorsListC, BigInteger[] valuesListC, boolean unparsedC) {
        mod = modC;
        operatorsList = operatorsListC;
        valuesList = valuesListC;
        unparsed = unparsedC;
    }
}
