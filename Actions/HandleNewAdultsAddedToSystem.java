package Actions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;

public class HandleNewAdultsAddedToSystem implements Action {
    @Override
    public void execute(MonkeyKingdom kingdom, Monkey monkey) {
        kingdom.addAdultMonkey(monkey, monkey.getGender());
    }
}
