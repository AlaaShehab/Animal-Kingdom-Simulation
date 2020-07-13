import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monkey implements Species, Runnable{
    private static final int BOUND = 1;

    private Gender gender;
    private State state;
    private boolean adult;

    private static double deathProbability;
    private static double reproductionProbability;

    private List<Species> children;

    private Timer timer;

    public void setDeathProbability(float deathProbability) {
        Monkey.deathProbability = deathProbability;
    }

    public void setReproductionProbability(float reproductionProbability) {
        Monkey.reproductionProbability = reproductionProbability;
    }

    public void Monkey () {
        Random random = new Random();
        setGender(random.nextInt(BOUND)
                == Gender.FEMALE.getValue()
                ? Gender.FEMALE : Gender.MALE);

        setState(new Born());
        children = new ArrayList<>();
    }

    @Override
    public void run() {
        int currentDay = timer.getDay();
        int currentYear = timer.getYear();

        while (!(state instanceof Dead)) {
            if (currentDay - timer.getDay() > 0) {
                oneDayPasses();
                currentDay++;
            }
            if (currentYear - timer.getYear() > 0) {
                childHoodPasses();
                currentYear++;
            }
        }
    }

    @Override
    public void oneDayPasses() {
        state.handlingAging(this);
    }

    @Override
    public void childHoodPasses() {
        state.handleBeingAdult(this);
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

    }

    public Species getChild () {
        return children.isEmpty() ? null : children.get(0);
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
