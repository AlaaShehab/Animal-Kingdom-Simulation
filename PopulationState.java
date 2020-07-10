public abstract class PopulationState implements State {
    protected static final int DEAD = 0;
    protected static final int ALIVE = 1;

    @Override
    public void handleDeath(Kingdom population) {
        //TODO
        /*
        set state to dead and call handleDeath() on new state
         */
    }

    @Override
    public void updateAge(Kingdom population) {
        //TODO
        /*
        Randomly choose a (0/1) to represent staying alive or
        dying.
        if ALIVE then return
        else call handleDeath()
         */
    }

    @Override
    public void handleBeingAdult(Kingdom population) {
    }

    @Override
    public void handleGettingMarried(Kingdom population) {
    }

    @Override
    public void handleHavingNewBorn (Kingdom population) {
    }
}
