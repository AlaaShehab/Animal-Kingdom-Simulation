package Conditions;

import StateMachinery.*;
import Population.Monkey;

public class AdultCondition implements Condition {
    @Override
    public boolean conditionIsMet(MonkeyKingdom kingdom, Monkey monkey) {
        // TODO handle variable adult threshold
        return monkey.getMonkeyState() == State.MonkeyState.BORN && monkey.getAge() >= 1;
    }
}
