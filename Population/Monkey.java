package Population;

import StateMachinery.State;
import Utils.Gender;

public class Monkey {
    private State state;
    private Gender gender;

    // It represents the current age of the monkey.
    // Required for Bron to Adult transition.
    private int age;

    public Monkey (Gender gender) {
        state = new State();
        state.setStateValue(State.MonkeyState.BORN);
        this.gender = gender;
        age = 0;
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
