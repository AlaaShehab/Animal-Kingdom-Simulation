package Species;

import States.State;
import Utlis.Gender;
import Utlis.Timer;

public interface Species {
    void oneDayPasses();
    void childHoodPasses();
    void runMonkeyThread();;

    void setGender(Gender gender);
    Gender getGender();
    void setState(State state);
    State getState();
    void setAdult(boolean adult);
    boolean isAdult();

    void addChild (Species monkey);
    Species getChild ();
    void setDeathProbability(double deathProbability);
    void setReproductionProbability(double reproductionProbability);
    void setTimer(Timer timer);
}
