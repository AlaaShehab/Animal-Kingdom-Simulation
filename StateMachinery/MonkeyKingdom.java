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

    public void runSimulation (int yearsToRunSimulation) {
        for (int year = 0; year < yearsToRunSimulation; year++) {
            for (Monkey monkey : monkeys) {
                List<Transition> transitions = SystemTransitions
                        .getAllPossibleStates(monkey.getMonkeyState());
                Transition transition = pickTransition(transitions, monkey);
                monkey.updateMonkeyState(transition.getEndState());
                transition.getAction().executeAction(this, monkey);
            }
        }
    }

    private Transition pickTransition(List<Transition> transitions, Monkey monkey) {
        // Remove transitions with unstatisfied conditions
        for (int i = 0; i < transitions.size(); i++) {
            if (!transitions.get(i).getCondition().conditionIsMet(this, monkey)) {
                transitions.remove(i--);
            }
        }
        if (transitions.isEmpty()) {
            //Throw an exception
            return null;
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
