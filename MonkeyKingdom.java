import java.util.ArrayList;
import java.util.List;

public class MonkeyKingdom implements Kingdom {

    private PopulationParameters parameters;
    private List<Thread> males;
    private List<Thread> females;



    MonkeyKingdom(PopulationParameters parameters) {
        reconfigurePopulationParameters(parameters);
        males = new ArrayList<>();
        females = new ArrayList<>();
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
