package Conditions;
import StateMachinery.MonkeyKingdom;
import Population.Monkey;
import Utils.Gender;

public class MarriageCondition implements Condition {
    @Override
    public boolean isConditionMet(MonkeyKingdom kingdom, Monkey monkey) {
        return (monkey.getGender() == Gender.MALE
                && kingdom.hasAdultMonkey(Gender.FEMALE))
                || (monkey.getGender() == Gender.FEMALE
                && kingdom.hasAdultMonkey(Gender.MALE));
    }
}

