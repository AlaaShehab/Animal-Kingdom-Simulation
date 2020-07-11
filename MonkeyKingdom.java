public class MonkeyKingdom implements Kingdom {
    private boolean gender;
    private State state;

    private static int totalPopulation;
    private static int adultMales;
    private static int adultFemales;

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

    //TODO add locks
    public static void increasePopulationCount() {
        totalPopulation++;
    }

    public static void decreasePopulationCount() {
        totalPopulation--;
    }

    public static void increaseAdultMalesCount() {
        adultMales++;
    }

    public static void decreaseAdultMalesCount() {
        adultMales--;
    }

    public static void increaseAdultFemalesCount() {
        adultFemales++;
    }

    public static void decreaseAdultFemalesCount() {
        adultFemales--;
    }

    @Override
    public void oneDayPasses() {
        state.updateAge(this);
    }

    @Override
    public void oppositeGenderExists() {
        state.handleGettingMarried(this);
    }

    @Override
    public void childHoodPasses() {
        state.handleBeingAdult(this);
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
