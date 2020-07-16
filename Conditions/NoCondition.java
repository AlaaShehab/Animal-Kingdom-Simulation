package Conditions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;

public class NoCondition implements Condition {
    @Override
    public boolean conditionIsMet(MonkeyKingdom kingdom, Monkey monkey) {
        return true;
    }
}
