package calc.operation;

public class Multiplication extends OperationEvaluation {

    public Multiplication() {
        setName('*');
        getSettings().setPriority(1);
    }

    @Override
    public Double evaluate(Double a, Double b) {
        return b * a;
    }
}
