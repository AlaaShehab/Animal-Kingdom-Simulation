package PopulationManagement;

public interface Kingdom {
    void reconfigurePopulationParameters(PopulationParameters parameters);
    int getPopulationCount(int yearsPassed);
}
