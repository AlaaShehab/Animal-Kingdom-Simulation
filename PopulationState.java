import java.util.Random;

public abstract class PopulationState implements State {
    protected static final int BOUND = 1;

    @Override
    public void handleDeath(Species species) {
       species.setState(new Dead());
    }

    @Override
    public void handlingAging(Species species) {
        //TODO handle different probabilities for death and giving birth
        Random random = new Random();
        int livingState = random.nextInt(BOUND);
        if (livingState == Living_State.DEAD.getValue()) {
            handleDeath(species);
        }
    }

    @Override
    public void handleBeingAdult(Species species) {
    }

    @Override
    public void handleHavingNewBorn (Species species) {
    }
}
