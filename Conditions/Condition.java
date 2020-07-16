package Conditions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;

public interface Condition {
    boolean conditionIsMet(MonkeyKingdom kingdom, Monkey monkey);
}
