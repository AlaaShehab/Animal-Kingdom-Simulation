public class Transition {
    private double probability;
    private Action action;
    private State.MonkeyState startState;
    private State.MonkeyState endState;
    private Condition condition;

    public double getProbability() {
        return probability;
    }

    public Transition setProbability(double probability) {
        this.probability = probability;
        return this;
    }

    public Action getAction() {
        return action;
    }

    public Transition setAction(Action action) {
        this.action = action;
        return this;
    }

    public State.MonkeyState getStartState() {
        return startState;
    }

    public Transition setStartState(State.MonkeyState startState) {
        this.startState = startState;
        return this;
    }

    public State.MonkeyState getEndState() {
        return endState;
    }

    public Transition setEndState(State.MonkeyState endState) {
        this.endState = endState;
        return this;
    }

    public Condition getCondition() {
        return condition;
    }

    public Transition setCondition(Condition condition) {
        this.condition = condition;
        return this;
    }
}
