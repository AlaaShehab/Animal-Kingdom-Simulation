package Conditions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;

// To maintain the consistency of the transition class
// a NoCondition is set for probabilistic transition
// that don't require a condition to be satisfied before
// taking them.
public class NoCondition implements Condition {
    @Override
    public boolean isConditionMet(MonkeyKingdom kingdom, Monkey monkey) {
        return true;
    }
}
