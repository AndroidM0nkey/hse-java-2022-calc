package calc.operation;

public class Exponentiation extends OperationEvaluation {
    @Override
    public Double evaluate(Double a, Double b) {
        return Math.pow(b, a);
    }
}
