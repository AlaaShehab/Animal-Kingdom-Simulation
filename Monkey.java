import Utils.Gender;

public class Monkey {
    private State state;
    private Gender gender;
    private int age;
    public Monkey (Gender gender) {
        state = new State();
        state.setStateValue(State.MonkeyState.BORN);
        this.gender = gender;
    }
    public void updateMonkeyState (State.MonkeyState stateValue) {
        state.setStateValue(stateValue);
        age++;
    }

    public State.MonkeyState getMonkeyState() {
        return state.getStateValue();
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public int getAge() {
        return age;
    }
}
