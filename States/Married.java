package States;

import Species.Species;
import Species.Monkey;
import Utlis.Living_State;

import java.util.Random;

public class Married implements State {
    @Override
    public void handlingAging(Species species) {
        Random random = new Random();
        int livingState = random.nextInt(BOUND);
        if (livingState == Living_State.DEAD.getValue()) {
            handlingDeath(species);
            return;
        }

        int givingBirth = random.nextInt(BOUND);
        if (givingBirth == 1) {
            handlingHavingNewBorn(species);
        }
    }

    @Override
    public void handleAdultAging(Species species) {
    }

    @Override
    public void handlingHavingNewBorn(Species species) {
        Species monkey = new Monkey();
        species.addChild(monkey);
    }

    @Override
    public void handlingDeath(Species species) {
        species.setState(new Dead());
    }
}
