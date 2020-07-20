package Actions;

import main.StateMachinery.MonkeyKingdom;
import main.Population.Monkey;

public interface Action {
    void execute(MonkeyKingdom kingdom, Monkey monkey);
}
