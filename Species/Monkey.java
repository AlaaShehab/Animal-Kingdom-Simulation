package Species;

import PopulationManagement.PopulationParameters;
import States.Born;
import States.Dead;
import States.State;
import Utlis.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monkey implements Species {
    private static final int BOUND = 1;

    private Gender gender;
    private State state;
    private int stepCount;

    private static PopulationParameters parameters;

    private List<Species> children;

    public Monkey () {
        Random random = new Random();
        setGender(random.nextInt(BOUND)
                == Gender.FEMALE.getValue()
                ? Gender.FEMALE : Gender.MALE);

        setState(new Born());
        children = new ArrayList<>();
        stepCount = 0;
    }

    @Override
    public void setParameters(PopulationParameters parameters) {
        Monkey.parameters = parameters;
    }

    @Override
    public void oneDayPasses() {
        stepCount++;
        state.handlingAging(this);
        if (!(state instanceof Dead) &&
                stepCount == parameters.getAdulthoodTimeInYears()) {
            childHoodPasses();
        }
    }

    @Override
    public void childHoodPasses() {
        state.handleAdultAging(this);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addChild (Species monkey) {
        children.add(monkey);
    }

    public Species getChild () {
        return children.isEmpty() ? null : children.remove(0);
    }
}