package StateMachinery;

import Population.Monkey;
import Population.PopulationParameters;
import Utils.Gender;

import java.util.*;

public class MonkeyKingdom {
    private List<Monkey> monkeys;
    private Map<Gender, List<Monkey>> adultMonkeys;
    private PopulationParameters parameters;

    public MonkeyKingdom (PopulationParameters parameters) {
        this.parameters = parameters;
        monkeys = new ArrayList<>();
        adultMonkeys = new HashMap<>();
        adultMonkeys.put(Gender.FEMALE, new ArrayList<>());
        adultMonkeys.put(Gender.MALE, new ArrayList<>());
        
        SystemTransitions.getInstance();
        updateSystemTransitions();
        generatePopulation();
    }

    private void updateSystemTransitions() {
        // Update the death probability.
        EnumSet.allOf(State.MonkeyState.class)
                .forEach(startState -> SystemTransitions.updateTransitionProbability(
                        startState, State.MonkeyState.DEAD,
                        parameters.getDeathProbability()));

        // Update reproduction probability.
        SystemTransitions.updateTransitionProbability(
                State.MonkeyState.MARRIED, State.MonkeyState.MARRIED,
                parameters.getReproductionProbability());

    }

    private void generatePopulation() {
        int totalPopulation = parameters.getMalesNumber()
                + parameters.getFemalesNumber();
        for (int i = 0; i < totalPopulation; i++) {
            Monkey monkey = new Monkey(i < parameters.getMalesNumber()
                    ? Gender.MALE : Gender.FEMALE);
            monkeys.add(monkey);
        }
    }

    public int runSimulation (int yearsToRunSimulation) {
        for (int year = 0; year < yearsToRunSimulation; year++) {
            //TODO handle removing from list while looping (ConcurrentModification)
            for (Monkey monkey : monkeys) {
                List<Transition> transitions = SystemTransitions
                        .getAllPossibleStates(monkey.getMonkeyState());
                Transition transition = pickTransition(transitions, monkey);
                monkey.updateMonkeyState(transition.getEndState());
                transition.getAction().execute(this, monkey);
            }
        }
        return calculatePopulation();
    }

    private int calculatePopulation() {
        int populationCount = 0;
        for (Monkey monkey : monkeys) {
            populationCount += monkey.getMonkeyState() == State.MonkeyState.MARRIED ? 2 : 1;
        }
        return populationCount;
    }

    private Transition pickTransition(List<Transition> transitions, Monkey monkey) {
        // Remove transitions with unstatisfied conditions
        for (int i = 0; i < transitions.size(); i++) {
            if (!transitions.get(i).getCondition().isConditionMet(this, monkey)) {
                transitions.remove(i--);
            }
        }
        if (transitions.isEmpty()) {
            //Throw an exception
            return null;
        }
        double probability = Math.random();
        double cumulativeProbability = 0.0;
        for (Transition transition : transitions) {
            cumulativeProbability += transition.getProbability();
            if (probability <= cumulativeProbability) {
               return transition;
            }
        }
        return transitions.get(0);
    }

    public void removeMonkey (Monkey monkey) {
        monkeys.remove(monkey);
        removeAdultMonkey(monkey, Gender.FEMALE);
        removeAdultMonkey(monkey, Gender.MALE);
    }

    public void addNewMonkey (Monkey monkey) {
        monkeys.add(monkey);
    }

    public void addAdultMonkey (Monkey monkey, Gender gender) {
        adultMonkeys.get(gender).add(monkey);
    }

    public void removeAdultMonkey (Monkey monkey, Gender gender) {
        adultMonkeys.get(gender).remove(monkey);
    }

    public Monkey getAdultMonkey (Gender gender) {
        return adultMonkeys.get(gender).isEmpty()
                ? null : adultMonkeys.get(gender).get(0);
    }

    public boolean isAdultListEmpty (Gender gender) {
        return adultMonkeys.get(gender).isEmpty();
    }

    public PopulationParameters getParameters () {
        return parameters;
    }
}
