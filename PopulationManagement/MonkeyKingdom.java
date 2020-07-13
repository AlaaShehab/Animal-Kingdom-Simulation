package PopulationManagement;

import Species.Species;
import Species.Monkey;
import States.Adult;
import States.Dead;
import States.Married;
import Utlis.Gender;

import java.util.ArrayList;
import java.util.List;

public class MonkeyKingdom implements Kingdom {
    private PopulationParameters parameters;

    // TODO manage the parameter which controls when a newborn
    // turns to an adult.
    private List<Species> monkeys;
    private List<Species> marriedMonkeys;

    public MonkeyKingdom(PopulationParameters parameters) {
        reconfigurePopulationParameters(parameters);
        monkeys = new ArrayList<>();
        marriedMonkeys = new ArrayList<Species>();
    }

    @Override
    public void reconfigurePopulationParameters
            (PopulationParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public int getPopulationCount(int yearsToRunSimulator) {
        generatePopulation();
        runSimulator(yearsToRunSimulator);
        return 0;
    }

    private void generatePopulation() {
        for (int i = 0; i < parameters.getMalesNumber(); i++) {
            Species monkey = new Monkey();
            monkey.setGender(Gender.MALE);
            monkeys.add(monkey);
        }
        for (int i = 0; i < parameters.getFemalesNumber(); i++) {
            Species monkey = new Monkey();
            monkey.setGender(Gender.FEMALE);
            monkeys.add(monkey);
        }
    }

    private void runSimulator(int yearsToRunSimulator) {
        for (int year = 0; year < yearsToRunSimulator; year++) {
            moveOneStepInTime();
            handleNewBorn();
            handleDeath();
            handleMarriage();
        }
    }

    private void handleDeath() {
        for (Species monkey : monkeys) {
            if (monkey.getState() instanceof Dead) monkeys.remove(monkey);
        }
    }

    private void handleMarriage() {
        List<Species> females = new ArrayList<Species>();
        List<Species> males = new ArrayList<Species>();

        for (Species monkey : monkeys) {
            if (monkey.getGender() == Gender.FEMALE
                    && monkey.getState() instanceof Adult) {
                females.add(monkey);
                monkeys.remove(monkey);
            } else if (monkey.getGender() == Gender.MALE
                    && monkey.getState() instanceof Adult) {
                males.add(monkey);
                monkeys.remove(monkey);
            }

            if (!females.isEmpty() && !males.isEmpty()) {
                females.remove(0);
                males.remove(0);

                Species marriedMonkey = new Monkey();
                marriedMonkey.setGender(Gender.MARRIED);
                marriedMonkey.setState(new Married());
                marriedMonkeys.add(marriedMonkey);
            }
        }

        for (Species monkey : females) {
            monkeys.add(monkey);
            females.remove(monkey);
        }
        for (Species monkey : males) {
            monkeys.add(monkey);
            males.remove(monkey);
        }
    }

    // we could handle giving birth directly from here
    private void handleNewBorn() {
        for (Species monkey : marriedMonkeys) {
            Species child = monkey.getChild();
            while (child != null) {
                monkeys.add(child);
                child = monkey.getChild();
            }
        }
    }

    private void moveOneStepInTime() {
        for (Species monkey : monkeys) {
            monkey.oneDayPasses();
        }
    }
}
