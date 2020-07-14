import PopulationManagement.MonkeyKingdom;
import PopulationManagement.PopulationParameters;

import java.util.Random;

public class Simulation {
    public static void main (String[] args) {
        PopulationParameters parameters =
                new PopulationParameters
                        .ParametersBuilder()
                        .setFemalesNumber(100)
                        .setMalesNumber(100)
                        .build();

        MonkeyKingdom kingdom = new MonkeyKingdom(parameters);
        System.out.println(kingdom.getPopulationCount(4));
    }
}
