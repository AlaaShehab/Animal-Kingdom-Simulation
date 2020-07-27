package main;

import main.Population.PopulationParameters;
import main.StateMachinery.MonkeyKingdom;

public class Simulator {
    public static void main (String[] args) {
        PopulationParameters parameters =
                new PopulationParameters
                        .ParametersBuilder()
                        .setFemalesNumber(20)
                        .setMalesNumber(20)
                        .setDeathProbability(0.1)
                        .setReproductionProbability(0.65)
                        .setMaxChildrenPerMonkey(10000)
                        .build();

        MonkeyKingdom kingdom = new MonkeyKingdom(parameters);
        System.out.println(kingdom.runSimulation(500));
    }
}
