package Species;

import PopulationManagement.PopulationParameters;
import States.State;
import Utlis.Gender;

public interface Species {
    void oneDayPasses();
    void childHoodPasses();

    void setGender(Gender gender);
    Gender getGender();
    void setState(State state);
    State getState();

    void addChild (Species monkey);
    Species getChild ();
    void setParameters(PopulationParameters parameters);
}
