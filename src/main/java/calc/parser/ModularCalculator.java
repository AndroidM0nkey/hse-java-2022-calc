package calc.parser;

import calc.operation.Division;
import calc.operation.Exponentiation;
import calc.operation.Multiplication;
import calc.operation.OperationEvaluation;
import calc.operation.Addition;
import calc.operation.Subtraction;
import calc.parser.utils.ParserException;

import java.util.HashMap;

public class ModularCalculator {
    static HashMap<String, OperationEvaluation> executors;
    private final ExpressionParser parser;
    static String delimiters = "|\\+|\\-|\\*|\\/|\\^|\\(|\\)";

    public ModularCalculator() {
        executors = setUpDefaultOperation();
        parser = new ExpressionParser();
    }

    public Double process(String input) throws ParserException {
        return parser.parse(input);
    }

    private HashMap<String, OperationEvaluation> setUpDefaultOperation() {
        var executors = new HashMap<String, OperationEvaluation>();
        executors.put("+", new Addition());
        executors.put("-", new Subtraction());
        executors.put("*", new Multiplication());
        executors.put("/", new Division());
        executors.put("^", new Exponentiation());
        return executors;
    }

}
