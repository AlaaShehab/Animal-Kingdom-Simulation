package main.Actions;

import main.StateMachinery.MonkeyKingdom;
import main.Population.Monkey;
import main.StateMachinery.State;

// Remove monkeys, that transition into Dead state,
// from the system.
public class RemoveDeadMonkey implements Action {
    @Override
    public void execute(MonkeyKingdom kingdom, Monkey monkey) {
        kingdom.removeMonkey(monkey);
    }
}

