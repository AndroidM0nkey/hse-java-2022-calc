package calc.parser;

import calc.parser.stack.Stack;
import calc.parser.utils.ParserException;

public class ExpressionParser {

    private final Stack<Double> values;
    private final Stack<String> operators;

    public ExpressionParser() {
        values = new Stack<>();
        operators = new Stack<>();
    }

    public Double parse(String input) throws ParserException {
        String[] tokens = splitTokens(input);
        for (String curToken : tokens) {
            curToken = curToken.trim();
            if (curToken.isBlank()) {
                continue;
            }
            if (curToken.charAt(0) >= '0' && curToken.charAt(0) <= '9') {
                double value = Double.parseDouble(curToken);
                values.push(value);
            } else if (curToken.equals("(")) {
                operators.push(curToken);
            } else if (curToken.equals(")")) {
                while (!operators.empty() && isOperator(operators.peek())) {
                    String toProcess = operators.peek();
                    operators.pop();
                    processOperator(toProcess);
                }
                if (!operators.empty() && operators.peek().equals("(")) {
                    operators.pop();
                }
            } else {
                if (!isOperator(curToken)) throw new ParserException("Can not recognize operator");
                while (!operators.empty() && getPriority(curToken) <= getPriority(operators.peek())) {
                    String toProcess = operators.peek();
                    operators.pop();
                    processOperator(toProcess);
                }
                operators.push(curToken);
            }
        }

        while (!operators.empty() && isOperator(operators.peek())) {
            String toProcess = operators.peek();
            operators.pop();
            processOperator(toProcess);
        }

        double result = values.peek();
        values.pop();
        if (!operators.empty() || !operators.empty()) throw new ParserException("Something went wrong...");

        return result;
    }

    private boolean isOperator(String operator) {
        return ModularCalculator.executors.containsKey(operator);
    }

    private int getPriority(String operator) {
        if (ModularCalculator.executors.containsKey(operator)) {
            return ModularCalculator.executors.get(operator).getSettings().getPriority();
        }
        return 0;
    }

    private void processOperator(String operator) throws ParserException {
        values.push(ModularCalculator.executors.get(operator).evaluate(getValue(operator), getValue(operator)));
    }

    private Double getValue(String operator) throws ParserException {
        if (values.empty()) throw new ParserException("Can not find values for the operator " + operator);
        Double x = values.peek();
        values.pop();
        return x;
    }

    private String[] splitTokens(String input) {
        return input.split("((?=:" + ModularCalculator.delimiters + ")|(?<=:" + ModularCalculator.delimiters + "))");
    }
}
