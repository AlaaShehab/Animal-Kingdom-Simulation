package Conditions;

import Population.Monkey;
import StateMachinery.MonkeyKingdom;
import StateMachinery.State;

public class ChildHoodCondition implements Condition {
    @Override
    public boolean isConditionMet(MonkeyKingdom kingdom, Monkey monkey) {
        return monkey.getMonkeyState() == State.MonkeyState.BORN
                && monkey.getAge() < kingdom
                .getParameters().getAdulthoodTimeInYears();
    }
}
