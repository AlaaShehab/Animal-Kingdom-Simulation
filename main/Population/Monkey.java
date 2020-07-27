package main.Population;

import main.StateMachinery.State;
import main.Utils.Gender;

public class Monkey {
    private static int MAX_CHILDREN = 1;
    private State state;
    private Gender gender;
    private int childrenCount;
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
        setAge(age + 1);
    }

    public void setAge (int age) {
        this.age = age;
    }

    public void setMonkeyState (State.MonkeyState stateValue) {
        state.setStateValue(stateValue);
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

    public void increaseChildrenCount() {
        childrenCount++;
    }

    public boolean canHaveChildren() {
       return childrenCount < MAX_CHILDREN;
    }

    public void setMaxChildren(int maxChildren) {
        MAX_CHILDREN = maxChildren;
    }
}
