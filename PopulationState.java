public abstract class PopulationState implements State {
    protected static final int DEAD = 0;
    protected static final int ALIVE = 1;

    @Override
    public void handleDeath(Species population) {
        //TODO
        /*
        set state to dead and call handleDeath() on new state
         */
    }

    @Override
    public void handlingAging(Species population) {
        //TODO
        /*
        Randomly choose a (0/1) to represent staying alive or
        dying.
        if ALIVE then return
        else call handleDeath()
         */
    }

    @Override
    public void handleBeingAdult(Species population) {
    }

    @Override
    public void handleGettingMarried(Species population) {
    }

    @Override
    public void handleHavingNewBorn (Species population) {
    }
}
