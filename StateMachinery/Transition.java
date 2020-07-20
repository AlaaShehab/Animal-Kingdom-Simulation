package main.StateMachinery;

import Actions.Action;
import Conditions.Condition;

import java.util.Optional;

public class Transition {
    // It defines the probability of a transition.
    private double probability;

    //It defines the action occurring after a transition
    // is taken.
    private Optional<Action> action;
    private State.MonkeyState startState;
    private State.MonkeyState endState;

    // It defines the condition need to be satisfied
    // before taking a transition.
    private Optional<Condition> condition;

    public double getProbability() {
        return probability;
    }

    public Transition setProbability(double probability) {
        this.probability = probability;
        return this;
    }

    public Optional<Action> getAction() {
        return action;
    }

    public Transition setAction(Optional<Action> action) {
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

    public Optional<Condition> getCondition() {
        return condition;
    }

    public Transition setCondition(Optional<Condition> condition) {
        this.condition = condition;
        return this;
    }
}
