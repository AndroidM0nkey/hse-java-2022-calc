package calc.operation;


public class Division extends OperationEvaluation {

    public Division() {
        setName('/');
        getSettings().setPriority(1);
    }

    @Override
    public Double evaluate(Double a, Double b) {
        return b / a;
    }
}
