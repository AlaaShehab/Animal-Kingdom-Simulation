package Actions;

import main.StateMachinery.MonkeyKingdom;
import main.Population.Monkey;

public class HandleNewAdultsAddedToSystem implements Action {
    @Override
    public void execute(MonkeyKingdom kingdom, Monkey monkey) {
        kingdom.addAdultMonkey(monkey, monkey.getGender());
    }
}
