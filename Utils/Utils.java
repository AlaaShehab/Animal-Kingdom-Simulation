package Utils;

import java.util.Random;

public class Utils {
    private static final int BOUND = 2;
    public static Gender getRandomGender() {
        Random random = new Random();
        return random.nextInt(BOUND) == Gender.MALE.getValue()
                ? Gender.MALE : Gender.FEMALE;
    }
}
