package main.Utils;

public enum Stats {
    TOTAL(0),
    MALE(1),
    FEMALE(2),
    NEWBORN(3),
    MARRIED(4),
    DEAD(5);


    private int value;

    Stats(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}