package Conditions;
import StateMachinery.MonkeyKingdom;
import Population.Monkey;
import Utils.Gender;

public class MarriageCondition implements Condition {
    @Override
    public boolean conditionIsMet(MonkeyKingdom kingdom, Monkey monkey) {
        return (monkey.getGender() == Gender.MALE
                && !kingdom.getAdultFemales().isEmpty())
                || (monkey.getGender() == Gender.FEMALE
                && !kingdom.getAdultMales().isEmpty());
    }
}

