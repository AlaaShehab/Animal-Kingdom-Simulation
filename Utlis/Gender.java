package Utlis;

public enum Gender {
    MALE(0),
    FEMALE(1),
    UNSPECIFIED(2);

    private int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}