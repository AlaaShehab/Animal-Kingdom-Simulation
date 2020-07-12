public class PopulationParameters {
    private int femalesNumber;
    private int malesNumber;
    private float deathProbability;
    private float reproductionProbability;
    private int yearFrequencyInDays;
    private int dayFrequencyInSeconds;

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

    public float getDeathProbability() {
        return deathProbability;
    }

    public float getReproductionProbability() {
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
        private float deathProbability;
        private float reproductionProbability;
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

        public ParametersBuilder setDeathProbability(float deathProbability) {
            this.deathProbability = deathProbability;
            return this;
        }

        public ParametersBuilder setReproductionProbability(float reproductionProbability) {
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
