package States;

import Species.Species;

public class Born extends PopulationState {
    @Override
    public void handleBeingAdult(Species species) {
        State newState = new Adult();
        species.setState(newState);
    }
}
