package Actions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;

public interface Action {
    void execute(MonkeyKingdom kingdom, Monkey monkey);
}
