package Conditions;
import StateMachinery.MonkeyKingdom;
import Population.Monkey;
import Utils.Gender;

public class MarriageCondition implements Condition {
    @Override
    public boolean isConditionMet(MonkeyKingdom kingdom, Monkey monkey) {
        return (monkey.getGender() == Gender.MALE
                && !kingdom.isAdultListEmpty(Gender.FEMALE))
                || (monkey.getGender() == Gender.FEMALE
                && !kingdom.isAdultListEmpty(Gender.MALE));
    }
}

