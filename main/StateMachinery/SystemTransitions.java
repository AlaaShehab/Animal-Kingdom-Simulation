package main.StateMachinery;

import main.Actions.*;
import main.Conditions.*;
import main.Population.PopulationParameters;
import main.StateMachinery.State.*;

import java.util.*;

public class SystemTransitions {
    // Transitions are static throughout the entire population.
    private static SystemTransitions instance = null;
    // An alternative to using a map is using MultiValueMap.
    private static Map<MonkeyState, List<Transition>> transitions;

    public static SystemTransitions getInstance() {
        if (instance == null)
            instance = new SystemTransitions();
        return instance;
    }
    private SystemTransitions () {
        transitions = new HashMap<>();
    }

    public void setupTransitions(PopulationParameters parameters) {
        setupBornStateTransitions(parameters);
        setupAdultStateTransitions(parameters);
        setupMarriedStateTransitions(parameters);
        setupDeadStateTransitions();
    }

    private void setupBornStateTransitions(PopulationParameters parameters) {
        List<Transition> stateTransitions = new ArrayList<>();
        stateTransitions.add(new Transition()
                .setStartState(MonkeyState.BORN)
                .setEndState(MonkeyState.BORN)
                .setAction(Optional.empty())
                .setCondition(Optional.of(new ChildHoodCondition()))
                .setProbability(1-parameters.getDeathProbability()));
        stateTransitions.add(new Transition()
                .setStartState(MonkeyState.BORN)
                .setEndState(MonkeyState.ADULT)
                .setAction(Optional.of(new HandleNewAdultsAddedToSystem()))
                .setCondition(Optional.of((new AdultCondition())))
                .setProbability(1-parameters.getDeathProbability()));
        stateTransitions.add(new Transition()
                .setStartState(MonkeyState.BORN)
                .setEndState(MonkeyState.DEAD)
                .setAction(Optional.of(new RemoveDeadMonkey()))
                .setCondition(Optional.empty())
                .setProbability(parameters.getDeathProbability()));
        transitions.put(MonkeyState.BORN, stateTransitions);
    }

    private void setupAdultStateTransitions(PopulationParameters parameters) {
        List<Transition> stateTransitions = new ArrayList<>();
        stateTransitions.add(new Transition()
                .setStartState(MonkeyState.ADULT)
                .setEndState(MonkeyState.ADULT)
                .setAction(Optional.empty())
                .setCondition(Optional.of(new AdultCondition()))
                .setProbability((1 - parameters.getDeathProbability())/2));
        stateTransitions.add(new Transition()
                .setStartState(MonkeyState.ADULT)
                .setEndState(MonkeyState.MARRIED)
                .setAction(Optional.of(new HandleMonkeyMarriage()))
                .setCondition(Optional.of(new MarriageCondition()))
                .setProbability((1 - parameters.getDeathProbability())/2));
        stateTransitions.add(new Transition()
                .setStartState(MonkeyState.ADULT)
                .setEndState(MonkeyState.DEAD)
                .setAction(Optional.of(new RemoveDeadMonkey()))
                .setCondition(Optional.empty())
                .setProbability(parameters.getDeathProbability()));
        transitions.put(MonkeyState.ADULT, stateTransitions);
    }

    private void setupMarriedStateTransitions(PopulationParameters parameters) {
        List<Transition> stateTransitions = new ArrayList<>();
        stateTransitions.add(new Transition()
                .setStartState(MonkeyState.MARRIED)
                .setEndState(MonkeyState.MARRIED)
                .setAction(Optional.empty())
                .setCondition(Optional.empty())
                .setProbability(1
                        - parameters.getDeathProbability()
                        - parameters.getReproductionProbability()));
        stateTransitions.add(new Transition()
                .setStartState(MonkeyState.MARRIED)
                .setEndState(MonkeyState.MARRIED)
                .setAction(Optional.of(new AddNewbornToKingdom()))
                .setCondition(Optional.empty())
                .setProbability(parameters.getReproductionProbability()));
        stateTransitions.add(new Transition()
                .setStartState(MonkeyState.MARRIED)
                .setEndState(MonkeyState.DEAD)
                .setAction(Optional.of(new RemoveDeadMonkey()))
                .setCondition(Optional.empty())
                .setProbability(parameters.getDeathProbability()));
        transitions.put(MonkeyState.MARRIED, stateTransitions);
    }

    private void setupDeadStateTransitions() {
        List<Transition> stateTransitions = new ArrayList<>();
        stateTransitions.add(new Transition()
                .setStartState(MonkeyState.DEAD)
                .setEndState(MonkeyState.DEAD)
                .setAction(Optional.of(new RemoveDeadMonkey()))
                .setCondition(Optional.empty())
                .setProbability(1.0));
        transitions.put(MonkeyState.DEAD, stateTransitions);
    }

    // Get all possible transitions given a current state.
    public static List<Transition> getAllPossibleStates (MonkeyState state) {
        List<Transition> stateTransitions = new ArrayList<>();
        if (!transitions.containsKey(state)) return stateTransitions;
        stateTransitions.addAll(transitions.get(state));
        return stateTransitions;
    }

    // Add a new transition to the system.
    public static void addTransition (MonkeyState state, Transition transition) {
        List<Transition> stateTransitions =
                transitions.containsKey(state)
                        ? transitions.get(state)
                        : new ArrayList<>();

        stateTransitions.add(transition);
        transitions.put(state, stateTransitions);
    }

    // Update the probability of a transition.
    public static void updateTransitionProbability
            (MonkeyState startState,
             MonkeyState endState, double probability) {
        List<Transition> stateTransitions =
                transitions.containsKey(startState)
                        ? transitions.get(startState)
                        : new ArrayList<>();
        if (stateTransitions.isEmpty()) return;

        for (Transition trans : stateTransitions) {
            if (trans.getEndState() == endState) {
                trans.setProbability(probability);
                return;
            }
        }
    }

    // Remove a system transition.
    public static void removeTransition (MonkeyState state, Transition transition) {
        List<Transition> stateTransitions =
                transitions.containsKey(state)
                        ? transitions.get(state)
                        : new ArrayList<>();

        if (stateTransitions.isEmpty()) {
            return;
        }
        stateTransitions.remove(transition);
        if (!stateTransitions.isEmpty()) {
            transitions.put(state, stateTransitions);
            return;
        }
        transitions.remove(state);
    }

    public static Transition getTransition (MonkeyState startState, MonkeyState endState) {
        List<Transition> stateTransitions = transitions.get(startState);
        for (Transition transition : stateTransitions) {
            if (transition.getEndState() == endState) return transition;
        }
        return null;
    }
}
