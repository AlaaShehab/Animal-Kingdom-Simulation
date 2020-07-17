package Actions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;

// Remove monkeys, that transition into Dead state,
// from the system.
public class RemoveDeadMonkey implements Action {
    @Override
    public void execute(MonkeyKingdom kingdom, Monkey monkey) {
        kingdom.removeMonkey(monkey);
    }
}

