package Conditions;

import StateMachinery.*;
import Population.Monkey;

public class AdultCondition implements Condition {
    @Override
    public boolean isConditionMet(MonkeyKingdom kingdom, Monkey monkey) {
        return monkey.getMonkeyState() == State.MonkeyState.BORN
                && monkey.getAge() >= kingdom
                .getParameters().getAdulthoodTimeInYears();
    }
}
