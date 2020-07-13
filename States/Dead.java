package States;

import Species.Species;

public class Dead extends PopulationState {
    @Override
    public void handlingAging(Species species) {
    }

    @Override
    public void handleDeath(Species species) {
        //TODO handled by kingdom
    }
}
