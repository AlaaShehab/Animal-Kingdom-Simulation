package Conditions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;

public interface Condition {
    boolean isConditionMet(MonkeyKingdom kingdom, Monkey monkey);
}
