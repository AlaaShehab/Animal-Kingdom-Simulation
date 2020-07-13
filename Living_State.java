public enum Living_State {
    DEAD(0), ALIVE(1);
    private int value;

    Living_State(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
