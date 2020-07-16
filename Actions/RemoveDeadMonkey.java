package Actions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;

public class RemoveDeadMonkey implements Action {
    @Override
    public void executeAction(MonkeyKingdom kingdom, Monkey monkey) {
        kingdom.removeMonkey(monkey);
    }
}

