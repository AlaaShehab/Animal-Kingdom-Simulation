import Utils.Gender;

public class MarriageCondition implements Condition{
    @Override
    public boolean conditionIsMet(MonkeyKingdom kingdom, Monkey monkey) {
        return (monkey.getGender() == Gender.MALE
                && kingdom.femaleAdultsExist())
                || (monkey.getGender() == Gender.FEMALE
                && kingdom.maleAdultsExist());
    }
}

class AdultCondition implements Condition {
    @Override
    public boolean conditionIsMet(MonkeyKingdom kingdom, Monkey monkey) {
        // TODO handle variable adult threshold
        return monkey.getMonkeyState() == State.MonkeyState.ADULT && monkey.getAge() >= 2;
    }
}

class NoCondition implements Condition {
    @Override
    public boolean conditionIsMet(MonkeyKingdom kingdom, Monkey monkey) {
        return true;
    }
}
