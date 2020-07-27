package main.Statistics;

import main.Population.Monkey;
import main.StateMachinery.State;
import main.Utils.Gender;
import main.Utils.Stats;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Statistics {
    private static final int ASPECTS = Stats.values().length;
    List<String[]> stats;

    public Statistics () {
        stats = new ArrayList<>();
    }

    public void calculateStatistics (List<Monkey> population, int year) {
        stats.add(year, new String[ASPECTS]);
        addMaleStats(population, year)
                .addFemaleStats(population, year)
                .addTotalPopulationStats(population, year)
                .addNewbornStats(population, year)
                .addMarriedStats(population, year)
                .addDeadStats(population, year);
    }

    private Statistics addMaleStats(List<Monkey> population, int year) {
        stats.get(year)[Stats.MALE.getValue()] =
                String.valueOf(population.stream()
                        .filter(m -> m.getGender() == Gender.MALE
                                || m.getGender() == Gender.MARRIED)
                        .filter(m -> m.getMonkeyState() != State.MonkeyState.DEAD)
                        .count());
        return this;
    }

    private Statistics addFemaleStats(List<Monkey> population, int year) {
        stats.get(year)[Stats.FEMALE.getValue()] =
                String.valueOf(population.stream()
                        .filter(m -> m.getGender() == Gender.FEMALE
                                || m.getGender() == Gender.MARRIED)
                        .filter(m -> m.getMonkeyState() != State.MonkeyState.DEAD)
                        .count());
        return this;
    }

    private Statistics addTotalPopulationStats(List<Monkey> population, int year) {
        long countSingle = population.stream()
                .filter(m -> m.getGender() == Gender.FEMALE || m.getGender() == Gender.MALE)
                .filter(m -> m.getMonkeyState() != State.MonkeyState.DEAD)
                .count();
        long countMarried = population.stream()
                .filter(m -> m.getGender() == Gender.MARRIED)
                .filter(m -> m.getMonkeyState() != State.MonkeyState.DEAD)
                .count() * 2;
        stats.get(year)[Stats.TOTAL.getValue()] = String.valueOf(countMarried + countSingle);
        return this;
    }

    private Statistics addNewbornStats(List<Monkey> population, int year) {
        stats.get(year)[Stats.NEWBORN.getValue()] =
                String.valueOf(population.stream()
                        .filter(m -> m.getMonkeyState() == State.MonkeyState.BORN)
                        .filter(m -> m.getMonkeyState() != State.MonkeyState.DEAD)
                        .count());
        return this;
    }

    private Statistics addMarriedStats(List<Monkey> population, int year) {
        stats.get(year)[Stats.MARRIED.getValue()] =
                String.valueOf(population.stream()
                        .filter(m -> m.getMonkeyState() == State.MonkeyState.MARRIED)
                        .filter(m -> m.getMonkeyState() != State.MonkeyState.DEAD)
                        .count());
        return this;
    }


    private Statistics addDeadStats(List<Monkey> population, int year) {
        long newlyMarried = year == 0 ? 0 :
                Math.max(0, population.stream()
                .filter(m -> m.getMonkeyState() == State.MonkeyState.MARRIED)
                .count()
                - Long.valueOf(stats.get(year - 1)[Stats.MARRIED.getValue()]));

        stats.get(year)[Stats.DEAD.getValue()] =
                String.valueOf(population.stream()
                        .filter(m -> m.getMonkeyState() == State.MonkeyState.DEAD)
                        .count() - newlyMarried);
        return this;
    }

    public void writeStatsToFile () {
        File csvOutputFile = new File("statistics.csv");

        try (PrintWriter writer = new PrintWriter(csvOutputFile)) {
            // Print header to CSV file.
            writer.println(Arrays.stream(Stats.values())
                    .map(Enum::toString)
                    .collect(Collectors.joining(",")));
            // Print data to CSV file.
            stats.stream()
                    .map(this::convertToCSV)
                    .forEach(writer::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String convertToCSV(String[] data) {
        return String.join(",", data);
    }
}
