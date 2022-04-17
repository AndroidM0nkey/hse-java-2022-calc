package calc.operation;


public abstract class OperationEvaluation {
    private Character name;
    private OperationSettings settings = new OperationSettings();

    public abstract Double evaluate(Double a, Double b);

    public Character getName() {
        return name;
    }

    protected void setName(Character name) {
        this.name = name;
    }

    public OperationSettings getSettings() {
        return settings;
    }

    protected void setSettings(OperationSettings settings) {
        this.settings = settings;
    }
}
