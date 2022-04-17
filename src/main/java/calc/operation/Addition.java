package calc.operation;

public class Addition extends OperationEvaluation {

    public Addition() {
        setName('+');
        getSettings().setPriority(2);
    }

    @Override
    public Double evaluate(Double a, Double b) {
        return b + a;
    }
}
