package main.Population;

public class PopulationParameters {
    private int femalesNumber;
    private int malesNumber;
    private double deathProbability;
    private double reproductionProbability;
    private int yearFrequencyInDays;
    private int dayFrequencyInSeconds;
    private int adulthoodTimeInYears;

    private PopulationParameters(ParametersBuilder builder) {
        this.femalesNumber = builder.femalesNumber;
        this.malesNumber = builder.malesNumber;
        this.dayFrequencyInSeconds = builder.dayFrequencyInSeconds;
        this.yearFrequencyInDays = builder.yearFrequencyInDays;
        this.deathProbability = builder.deathProbability;
        this.reproductionProbability = builder.reproductionProbability;
        this.adulthoodTimeInYears = builder.adulthoodTimeInYears;
    }
    public int getFemalesNumber() {
        return femalesNumber;
    }

    public int getMalesNumber() {
        return malesNumber;
    }

    public double getDeathProbability() {
        return deathProbability;
    }

    public double getReproductionProbability() {
        return reproductionProbability;
    }

    public int getYearFrequencyInDays() {
        return yearFrequencyInDays;
    }

    public int getDayFrequencyInSeconds() {
        return dayFrequencyInSeconds;
    }

    public int getAdulthoodTimeInYears() {
        return adulthoodTimeInYears;
    }

    public static class ParametersBuilder {
        private int femalesNumber;
        private int malesNumber;
        private double deathProbability = 0.5;
        private double reproductionProbability = 0.5;
        // Frequency parameters are used when using
        // a real simulation timer
        private int yearFrequencyInDays = 10;
        private int dayFrequencyInSeconds = 1;

        // Alters the number of steps to transition
        // from Bron to Adult state.
        private int adulthoodTimeInYears = 1;

        public ParametersBuilder setFemalesNumber(int femalesNumber) {
            this.femalesNumber = femalesNumber;
            return this;
        }

        public ParametersBuilder setMalesNumber(int malesNumber) {
            this.malesNumber = malesNumber;
            return this;
        }

        public ParametersBuilder setDeathProbability(double deathProbability) {
            this.deathProbability = deathProbability;
            return this;
        }

        public ParametersBuilder setReproductionProbability(double reproductionProbability) {
            this.reproductionProbability = reproductionProbability;
            return this;
        }

        public ParametersBuilder setYearFrequencyInDays(int yearFrequencyInDays) {
            this.yearFrequencyInDays = yearFrequencyInDays;
            return this;
        }

        public ParametersBuilder setDayFrequencyInSeconds(int dayFrequencyInSeconds) {
            this.dayFrequencyInSeconds = dayFrequencyInSeconds;
            return this;
        }

        public ParametersBuilder setAdulthoodTimeInYears(int adulthoodTimeInYears) {
            this.adulthoodTimeInYears = adulthoodTimeInYears;
            return this;
        }

        public PopulationParameters build() {
            return new PopulationParameters(this);
        }
    }
}
