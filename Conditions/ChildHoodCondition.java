package Conditions;

import Population.Monkey;
import StateMachinery.MonkeyKingdom;
import StateMachinery.State;

public class ChildHoodCondition implements Condition {
    @Override
    public boolean conditionIsMet(MonkeyKingdom kingdom, Monkey monkey) {
        // TODO handle variable adult threshold
        return monkey.getMonkeyState() == State.MonkeyState.BORN && monkey.getAge() < 1;
    }
}
