package States;

import Species.Species;
import Species.Monkey;
import Utlis.Living_State;

import java.util.Random;

public class Married extends PopulationState {
    //TODO handle different probabilities for death and giving birth
    @Override
    public void handlingAging(Species species) {
        Random random = new Random();
        int livingState = random.nextInt(BOUND);
        if (livingState == Living_State.DEAD.getValue()) {
            handleDeath(species);
            return;
        }

        int givingBirth = random.nextInt(BOUND);
        if (givingBirth == 1) {
            handleHavingNewBorn(species);
        }
    }

    @Override
    public void handleHavingNewBorn(Species species) {
        Random random = new Random();
        Species monkey = new Monkey();
        species.addChild(monkey);
    }
}
