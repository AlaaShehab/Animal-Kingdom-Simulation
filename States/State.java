package States;

import Species.Species;

public interface State {
    int BOUND = 1;
    void handlingAging(Species species);
    void handlingDeath(Species species);
    void handleAdultAging(Species species);
    void handlingHavingNewBorn(Species species);
}
