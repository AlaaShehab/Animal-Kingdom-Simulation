public class State {
    public enum MonkeyState {
        BORN,
        ADULT,
        MARRIED,
        DEAD
    }
    public State () {
        value = MonkeyState.BORN;
    }
    private MonkeyState value;

    public void setStateValue (MonkeyState value) {
        this.value = value;
    }
    public MonkeyState getStateValue () {
        return value;
    }
}
