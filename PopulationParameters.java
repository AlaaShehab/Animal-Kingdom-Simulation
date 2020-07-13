public class PopulationParameters {
    private int femalesNumber;
    private int malesNumber;
    private double deathProbability = 0.5;
    private double reproductionProbability = 0.5;
    private int yearFrequencyInDays = 10;
    private int dayFrequencyInSeconds = 1;

    PopulationParameters (ParametersBuilder builder) {
        this.femalesNumber = builder.femalesNumber;
        this.malesNumber = builder.malesNumber;
        this.dayFrequencyInSeconds = builder.dayFrequencyInSeconds;
        this.yearFrequencyInDays = builder.yearFrequencyInDays;
        this.deathProbability = builder.deathProbability;
        this.reproductionProbability = builder.reproductionProbability;
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

    public static class ParametersBuilder {
        private int femalesNumber;
        private int malesNumber;
        private double deathProbability;
        private double reproductionProbability;
        private int yearFrequencyInDays;
        private int dayFrequencyInSeconds;

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

        public PopulationParameters build() {
            PopulationParameters parameters =  new PopulationParameters(this);
            return parameters;
        }
    }
}
