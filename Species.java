import javafx.util.Pair;

public interface Species {
    void oneDayPasses();
    void childHoodPasses();

    void setGender(Gender gender);
    Gender getGender();
    void setState(State state);
    State getState();
    void setAdult(boolean adult);
    boolean isAdult();

    void addChild (Species monkey);
    Species getChild ();
    void setDeathProbability(float deathProbability);
    void setReproductionProbability(float reproductionProbability);
    void setTimer(Timer timer);
}
