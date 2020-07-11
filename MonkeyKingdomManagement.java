public class MonkeyKingdomManagement implements PopulationManagement {
    private int counter;
    private int years;

    private PopulationParameters parameters;

    MonkeyKingdomManagement (PopulationParameters parameters) {
        counter = 0;
        years = 0;
        this.parameters = parameters;

    }

    @Override
    public void reconfigurePopulationParameters(PopulationParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public int getPopulationCount(int yearsPassed) {
        return 0;
    }
}
