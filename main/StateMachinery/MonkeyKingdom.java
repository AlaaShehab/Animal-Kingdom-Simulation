package main.StateMachinery;

import main.Conditions.Condition;
import main.Population.Monkey;
import main.Population.PopulationParameters;
import main.Statistics.Statistics;
import main.Utils.Gender;

import java.util.*;

public class MonkeyKingdom {
    private List<Monkey> monkeys;
    private Map<Gender, List<Monkey>> adultMonkeys;
    private PopulationParameters parameters;

    private SystemTransitions systemTransitions;
    private Statistics statistics;

    private int initialPopulation;

    public MonkeyKingdom (PopulationParameters parameters) {
        if (parameters == null) return;

        this.parameters = parameters;
        monkeys = new ArrayList<>();
        adultMonkeys = new HashMap<>();
        adultMonkeys.put(Gender.FEMALE, new ArrayList<>());
        adultMonkeys.put(Gender.MALE, new ArrayList<>());

        statistics = new Statistics();

        // Setup the static system transitions.
        systemTransitions = SystemTransitions.getInstance();
        systemTransitions.setupTransitions(parameters);

        generatePopulation();
        initialPopulation = parameters.getMalesNumber()
                + parameters.getFemalesNumber();
    }

    private void generatePopulation() {
        int totalPopulation = parameters.getMalesNumber()
                + parameters.getFemalesNumber();
        for (int i = 0; i < totalPopulation; i++) {
            Monkey monkey = new Monkey(i < parameters.getMalesNumber()
                    ? Gender.MALE : Gender.FEMALE);
            monkey.setMaxChildren(parameters.getMaxChildrenPerMonkey());
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
            statistics.calculateStatistics(immutableMonkeys, year);
            staticDeathRateUpdate();
            //updateMaxChildren();
        }
        statistics.writeStatsToFile();
        return getPopulationCount();
    }

    private void updateMaxChildren() {
        if (getPopulationCount() > initialPopulation * 30) {
            parameters.updateMaxChildrenPerMonkey(3);
            parameters.updateReproductionProbability(0.7);
            parameters.updateDeathProbability(0.08);
            systemTransitions.setupTransitions(parameters);
            monkeys.get(0).setMaxChildren(3);
        }
    }

    private void staticDeathRateUpdate() {
        if (getPopulationCount() > initialPopulation * 5) {
            parameters.updateDeathProbability(0.3);
            parameters.updateReproductionProbability(0.5);
            systemTransitions.setupTransitions(parameters);
        }

        if (getPopulationCount() < initialPopulation) {
            parameters.updateDeathProbability(0.1);
            parameters.updateReproductionProbability(0.8);
            systemTransitions.setupTransitions(parameters);
        }
    }

    private void dynamicDeathRateUpdate(List<Monkey> previousYearPopulation) {
        double changeInPopulation = getPopulationCount(monkeys)
                - getPopulationCount(previousYearPopulation);

        parameters.updateDeathProbability(
                parameters.getDeathProbability()
                        + (changeInPopulation/getPopulationCount(monkeys)));
        parameters.updateReproductionProbability(
                parameters.getReproductionProbability()
                        - (changeInPopulation/getPopulationCount(monkeys)));
        systemTransitions.setupTransitions(parameters);
    }

    public int getPopulationCount() {
        int populationCount = 0;
        for (Monkey monkey : monkeys) {
            populationCount += monkey.getMonkeyState() == State.MonkeyState.MARRIED ? 2 : 1;
        }
        return populationCount;
    }

    public int getPopulationCount(List<Monkey> population) {
        int populationCount = 0;
        for (Monkey monkey : population) {
            populationCount += monkey.getMonkeyState() == State.MonkeyState.MARRIED ? 2 : 1;
        }
        return populationCount;
    }

    public Optional<Transition> pickTransition(List<Transition> transitions, Monkey monkey) {
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
        if (gender == Gender.MARRIED) return;
        adultMonkeys.get(gender).add(monkey);
    }

    public void removeAdultMonkey (Monkey monkey, Gender gender) {
        if (gender == Gender.MARRIED) return;
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

    public int getMonkeysCount () {
        return monkeys.size();
    }
}
