import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemTransitions {
    // alternative use MultiValueMap
    private static SystemTransitions instance = null;
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

    //TODO handle change of probability
    private void setupBornStateTransitions() {
        List<Transition> stateTransitions = new ArrayList<>();
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.BORN)
                .setEndState(State.MonkeyState.BORN)
                .setAction(new NoActionTransitions())
                .setCondition(new AdultCondition())
                .setProbability(0.5));
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.BORN)
                .setEndState(State.MonkeyState.DEAD)
                .setAction(new RemoveDeadMonkey())
                .setCondition(new NoCondition())
                .setProbability(0.5));
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.BORN)
                .setEndState(State.MonkeyState.ADULT)
                .setAction(new HandleNewAdultsAddedToSystem())
                .setProbability(0.5)
                .setCondition(new AdultCondition()));
        transitions.put(State.MonkeyState.BORN, stateTransitions);
    }

    private void setupAdultStateTransitions() {
        List<Transition> stateTransitions = new ArrayList<>();
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.ADULT)
                .setEndState(State.MonkeyState.ADULT)
                .setAction(new NoActionTransitions())
                .setCondition(new NoCondition())
                .setProbability(0.25));
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.ADULT)
                .setEndState(State.MonkeyState.DEAD)
                .setAction(new RemoveDeadMonkey())
                .setCondition(new NoCondition())
                .setProbability(0.5));
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.ADULT)
                .setEndState(State.MonkeyState.MARRIED)
                .setAction(new HandleMonkeyMarriage())
                .setCondition(new MarriageCondition())
                .setProbability(0.25));
        transitions.put(State.MonkeyState.ADULT, stateTransitions);
    }

    private void setupMarriedStateTransitions() {
        List<Transition> stateTransitions = new ArrayList<>();
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.MARRIED)
                .setEndState(State.MonkeyState.MARRIED)
                .setAction(new NoActionTransitions())
                .setCondition(new NoCondition())
                .setProbability(0.25));
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.MARRIED)
                .setEndState(State.MonkeyState.DEAD)
                .setAction(new RemoveDeadMonkey())
                .setCondition(new NoCondition())
                .setProbability(0.5));
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.MARRIED)
                .setEndState(State.MonkeyState.MARRIED)
                .setAction(new AddNewbornToKingdom())
                .setCondition(new NoCondition())
                .setProbability(0.25));
        transitions.put(State.MonkeyState.MARRIED, stateTransitions);
    }

    private void setupDeadStateTransitions() {
        List<Transition> stateTransitions = new ArrayList<>();
        stateTransitions.add(new Transition()
                .setStartState(State.MonkeyState.DEAD)
                .setEndState(State.MonkeyState.DEAD)
                .setAction(new NoActionTransitions())
                .setCondition(new NoCondition())
                .setProbability(1.0));
        transitions.put(State.MonkeyState.DEAD, stateTransitions);
    }

    public static List<Transition> getAllPossibleStates (State.MonkeyState state) {
        List<Transition> stateTransitions = new ArrayList<>();
        if (!transitions.containsKey(state)) return stateTransitions;
        stateTransitions.addAll(transitions.get(state));
        return stateTransitions;
    }

    public static void addTransition (State.MonkeyState state, Transition transition) {
        List<Transition> stateTransitions =
                transitions.containsKey(state)
                        ? transitions.get(state)
                        : new ArrayList<>();

        stateTransitions.add(transition);
        //TODO ensure it gets updated
        transitions.put(state, stateTransitions);
    }

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
