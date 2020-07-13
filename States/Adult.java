package States;

import Species.Species;
import Utlis.Living_State;

import java.util.Random;

public class Adult implements State {
    @Override
    public void handlingDeath(Species species) {
        species.setState(new Dead());
    }

    @Override
    public void handlingAging(Species species) {
        //TODO handle different probabilities for death and giving birth
        Random random = new Random();
        int livingState = random.nextInt(BOUND);
        if (livingState == Living_State.DEAD.getValue()) {
            handlingDeath(species);
        }
    }

    @Override
    public void handleBeingAdult(Species species) {
    }

    @Override
    public void handlingHavingNewBorn(Species species) {
    }
}
