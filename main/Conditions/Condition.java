package main.Conditions;

import main.StateMachinery.MonkeyKingdom;
import main.Population.Monkey;

public interface Condition {
    boolean isConditionMet(MonkeyKingdom kingdom, Monkey monkey);
}
