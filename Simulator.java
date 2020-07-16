import Population.PopulationParameters;
import StateMachinery.MonkeyKingdom;

public class Simulator {
    public static void main (String[] args) {
        PopulationParameters parameters =
                new PopulationParameters
                        .ParametersBuilder()
                        .setFemalesNumber(5)
                        .setMalesNumber(5)
                        .build();

        MonkeyKingdom kingdom = new MonkeyKingdom(parameters);
        System.out.println(kingdom.runSimulation(10));
    }
}
