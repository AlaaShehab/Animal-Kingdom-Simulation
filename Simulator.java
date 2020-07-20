import main.Population.PopulationParameters;
import main.StateMachinery.MonkeyKingdom;

public class Simulator {
    public static void main (String[] args) {
        PopulationParameters parameters =
                new PopulationParameters
                        .ParametersBuilder()
                        .setFemalesNumber(50)
                        .setMalesNumber(50)
                        .setDeathProbability(0.1)
                        .setReproductionProbability(0.8)
                        .build();

        MonkeyKingdom kingdom = new MonkeyKingdom(parameters);
        System.out.println(kingdom.runSimulation(50));
    }
}
