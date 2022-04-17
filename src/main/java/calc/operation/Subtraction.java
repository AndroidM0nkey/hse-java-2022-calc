package calc.operation;


public class Subtraction extends OperationEvaluation {

    public Subtraction() {
        setName('-');
        getSettings().setPriority(2);
    }

    @Override
    public Double evaluate(Double a, Double b) {
        return b - a;
    }
}
