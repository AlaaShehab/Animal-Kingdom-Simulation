public class Monkey implements Species {

    private Gender gender;
    private State state;

    private static float deathProbability;
    private static float reproductionProbability;

    public void Kingdom () {
        //TODO
        /*
        Randomly choose a (0/1) to represent gender
        increase population number
        set state to Born
         */
    }

    @Override
    public void oneDayPasses() {
        state.handlingAging(this);
    }

    @Override
    public void oppositeGenderExists() {
        state.handleGettingMarried(this);
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
}
