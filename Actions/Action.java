package Actions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;

public interface Action {
    void executeAction(MonkeyKingdom kingdom, Monkey monkey);
}
