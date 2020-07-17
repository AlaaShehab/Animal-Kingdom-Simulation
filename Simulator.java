import Population.PopulationParameters;
import StateMachinery.MonkeyKingdom;

public class Simulator {
    public static void main (String[] args) {
        PopulationParameters parameters =
                new PopulationParameters
                        .ParametersBuilder()
                        .setFemalesNumber(15)
                        .setMalesNumber(10)
                        .setDeathProbability(0.3)
                        .setReproductionProbability(0.65)
                        .build();

        MonkeyKingdom kingdom = new MonkeyKingdom(parameters);
        System.out.println(kingdom.runSimulation(10));
    }
}
