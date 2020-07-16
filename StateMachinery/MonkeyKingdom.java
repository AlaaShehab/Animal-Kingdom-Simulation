package StateMachinery;

import Population.Monkey;
import Population.PopulationParameters;
import Utils.Gender;

import java.util.ArrayList;
import java.util.List;

public class MonkeyKingdom {
    private List<Monkey> monkeys;

    private List<Monkey> adultMales;
    private List<Monkey> adultFemales;
    private PopulationParameters parameters;

    public MonkeyKingdom (PopulationParameters parameters) {
        this.parameters = parameters;
        monkeys = new ArrayList<>();
        adultMales = new ArrayList<>();
        adultFemales = new ArrayList<>();
        
        SystemTransitions.getInstance();
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
            //TODO handle removing from list while looping (
            for (int curMonkey = 0; curMonkey < monkeys.size(); curMonkey++) {
                Monkey monkey = monkeys.get(curMonkey);
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

    public List<Monkey> getAdultMales() {
        return adultMales;
    }

    public List<Monkey> getAdultFemales() {
        return adultFemales;
    }

    public void removeMonkey (Monkey monkey) {
        monkeys.remove(monkey);
        adultFemales.remove(monkey);
        adultMales.remove(monkey);
    }

    public void addNewMonkey (Monkey monkey) {
        monkeys.add(monkey);
    }
}
