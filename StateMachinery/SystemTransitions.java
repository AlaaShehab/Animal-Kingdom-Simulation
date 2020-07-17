package StateMachinery;

import Actions.*;
import Conditions.AdultCondition;
import Conditions.ChildHoodCondition;
import Conditions.MarriageCondition;

import java.util.*;

public class SystemTransitions {
    // Transitions are static throughout the entire population.
    private static SystemTransitions instance = null;

    // An alternative to using a map is using MultiValueMap.
    private static Map<State.MonkeyState, List<Transition>> transitions;

    public static void getInstance() {
        if (instance == null)
            instance = new SystemTransitions();
    }
    private SystemTransitions () {
        transitions = new HashMap<>();
        setupTransitions();
    }

    private void setupTransitions() {
        setupBornStateTransitions();
        setupAdultStateTransitions();
        setupMarriedStateTransitions();
        setupDeadStateTransitions();
    }

    private void setupBornStateTransitions() {
        List<Transition> stateTransitions = new ArrayList<>();
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.BORN)
                .setEndState(State.MonkeyState.BORN)
                .setAction(Optional.empty())
                .setCondition(Optional.of(new ChildHoodCondition()))
                .setProbability(0.5));
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.BORN)
                .setEndState(State.MonkeyState.DEAD)
                .setAction(Optional.of(new RemoveDeadMonkey()))
                .setCondition(Optional.empty())
                .setProbability(0.5));
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.BORN)
                .setEndState(State.MonkeyState.ADULT)
                .setAction(Optional.of(new HandleNewAdultsAddedToSystem()))
                .setCondition(Optional.of((new AdultCondition())))
                .setProbability(0.5));
        transitions.put(State.MonkeyState.BORN, stateTransitions);
    }

    private void setupAdultStateTransitions() {
        List<Transition> stateTransitions = new ArrayList<>();
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.ADULT)
                .setEndState(State.MonkeyState.ADULT)
                .setAction(Optional.empty())
                .setCondition(Optional.empty())
                .setProbability(0.25));
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.ADULT)
                .setEndState(State.MonkeyState.DEAD)
                .setAction(Optional.of(new RemoveDeadMonkey()))
                .setCondition(Optional.empty())
                .setProbability(0.5));
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.ADULT)
                .setEndState(State.MonkeyState.MARRIED)
                .setAction(Optional.of(new HandleMonkeyMarriage()))
                .setCondition(Optional.of(new MarriageCondition()))
                .setProbability(0.25));
        transitions.put(State.MonkeyState.ADULT, stateTransitions);
    }

    private void setupMarriedStateTransitions() {
        List<Transition> stateTransitions = new ArrayList<>();
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.MARRIED)
                .setEndState(State.MonkeyState.MARRIED)
                .setAction(Optional.empty())
                .setCondition(Optional.empty())
                .setProbability(0.25));
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.MARRIED)
                .setEndState(State.MonkeyState.DEAD)
                .setAction(Optional.of(new RemoveDeadMonkey()))
                .setCondition(Optional.empty())
                .setProbability(0.5));
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.MARRIED)
                .setEndState(State.MonkeyState.MARRIED)
                .setAction(Optional.of(new AddNewbornToKingdom()))
                .setCondition(Optional.empty())
                .setProbability(0.25));
        transitions.put(State.MonkeyState.MARRIED, stateTransitions);
    }

    private void setupDeadStateTransitions() {
        List<Transition> stateTransitions = new ArrayList<>();
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.DEAD)
                .setEndState(State.MonkeyState.DEAD)
                .setAction(Optional.empty())
                .setCondition(Optional.empty())
                .setProbability(1.0));
        transitions.put(State.MonkeyState.DEAD, stateTransitions);
    }

    // Get all possible transitions given a current state.
    public static List<Transition> getAllPossibleStates (State.MonkeyState state) {
        List<Transition> stateTransitions = new ArrayList<>();
        if (!transitions.containsKey(state)) return stateTransitions;
        stateTransitions.addAll(transitions.get(state));
        return stateTransitions;
    }

    // Add a new transition to the system.
    public static void addTransition (State.MonkeyState state, Transition transition) {
        List<Transition> stateTransitions =
                transitions.containsKey(state)
                        ? transitions.get(state)
                        : new ArrayList<>();

        stateTransitions.add(transition);
        transitions.put(state, stateTransitions);
    }

    // Update a transition by changing its action, condition,
    // its probability or a combination of these parameters.
    public static void updateTransition
            (State.MonkeyState startState,
             State.MonkeyState endState, Transition transition) {
        List<Transition> stateTransitions =
                transitions.containsKey(startState)
                        ? transitions.get(startState)
                        : new ArrayList<>();
        if (stateTransitions.isEmpty()) return;

        for (Transition trans : stateTransitions) {
            if (trans.getEndState() == endState) {
                trans.setAction(transition.getAction())
                        .setCondition(transition.getCondition())
                        .setProbability(transition.getProbability());
                return;
            }
        }
    }

    // Update the probability of a transition.
    public static void updateTransitionProbability
            (State.MonkeyState startState,
             State.MonkeyState endState, double probability) {
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
    public static void removeTransition (State.MonkeyState state, Transition transition) {
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

}
