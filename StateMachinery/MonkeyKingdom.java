package StateMachinery;

import Conditions.Condition;
import Population.Monkey;
import Population.PopulationParameters;
import Utils.Gender;

import java.util.*;

public class MonkeyKingdom {
    private List<Monkey> monkeys;
    private Map<Gender, List<Monkey>> adultMonkeys;
    private PopulationParameters parameters;

    private SystemTransitions systemTransitions;

    public MonkeyKingdom (PopulationParameters parameters) {
        this.parameters = parameters;
        monkeys = new ArrayList<>();
        adultMonkeys = new HashMap<>();
        adultMonkeys.put(Gender.FEMALE, new ArrayList<>());
        adultMonkeys.put(Gender.MALE, new ArrayList<>());

        // Setup the static system transitions.
        systemTransitions = SystemTransitions.getInstance();
        systemTransitions.setupTransitions(parameters);

        generatePopulation();
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
            List<Monkey> immutableMonkeys = new ArrayList<>(monkeys);
            for (Monkey monkey : immutableMonkeys) {
                List<Transition> transitions = SystemTransitions
                        .getAllPossibleStates(monkey.getMonkeyState());
                Optional<Transition> transition = pickTransition(transitions, monkey);
                transition.ifPresent(trans -> {
                    monkey.updateMonkeyState(trans.getEndState());
                    trans.getAction().ifPresent(
                            action -> action.execute(this, monkey)
                    );
                });
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

    private Optional<Transition> pickTransition(List<Transition> transitions, Monkey monkey) {
        // Remove transitions with unsatisfied conditions.
        for (int i = 0; i < transitions.size(); i++) {
            Optional<Condition> condition = transitions.get(i).getCondition();
            if (condition.isPresent()
                    && !condition.get().isConditionMet(this, monkey)){
                transitions.remove(i--);
            }
        }
        if (transitions.isEmpty()) {
            return Optional.empty();
        }
        double probability = Math.random();
        double cumulativeProbability = 0.0;
        for (Transition transition : transitions) {
            cumulativeProbability += transition.getProbability();
            if (probability <= cumulativeProbability) {
               return Optional.of(transition);
            }
        }
        return Optional.of(transitions.get(0));
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

    public Optional<Monkey> getAdultMonkey (Gender gender) {
        return adultMonkeys.get(gender).isEmpty()
                ? Optional.empty()
                : Optional.of(adultMonkeys.get(gender).get(0));
    }

    public boolean hasAdultMonkey(Gender gender) {
        return !adultMonkeys.get(gender).isEmpty();
    }

    public PopulationParameters getParameters () {
        return parameters;
    }

    public void updatePopulationParameters (PopulationParameters parameters) {
        this.parameters = parameters;
        systemTransitions.setupTransitions(parameters);
    }
}
