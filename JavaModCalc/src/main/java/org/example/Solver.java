package org.example;
import java.math.*;
import java.util.ArrayList;
import java.util.List;

public class Solver {
    public BigInteger solve (String inputString) {

        ExpressionParser expParser = new ExpressionParser();
        Expression expression = expParser.parseInput(inputString);

        if (expression.isValid()) {

            BigInteger[] values = expression.getValuesList();
            ArrayList<Object> operators = expression.getOperators();
            BigInteger mod = expression.getMod();

            BigInteger state = values[0];
            for (int i = 0; i < operators.size(); i++) {
                Operator op = (Operator) operators.get(i);
                state = op.apply(state, values[i+1]);
                state = state.mod(mod);
            }

            return state;

        } else {
            return null;
        }
    }
}



