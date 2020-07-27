package main.Conditions;

import main.Population.Monkey;
import main.StateMachinery.MonkeyKingdom;
import main.StateMachinery.State;
import main.Utils.Gender;

public class AdultCondition implements Condition {
    @Override
    public boolean isConditionMet(MonkeyKingdom kingdom, Monkey monkey) {
        // Transition to state Adult if you passed childhood years
        // and if you are an adult but you can't marry
        return isAdult(kingdom, monkey) ||
                         hasNoPartnerToMarry(kingdom, monkey);
    }
    private boolean isAdult (MonkeyKingdom kingdom, Monkey monkey) {
        return monkey.getMonkeyState() == State.MonkeyState.BORN
                && monkey.getAge() >= kingdom
                .getParameters().getAdulthoodTimeInYears();
    }
    private boolean hasNoPartnerToMarry (MonkeyKingdom kingdom, Monkey monkey) {
        return monkey.getMonkeyState() == State.MonkeyState.ADULT
                && !((monkey.getGender() == Gender.MALE
                        && kingdom.hasAdultMonkey(Gender.FEMALE))
                        || (monkey.getGender() == Gender.FEMALE
                        && kingdom.hasAdultMonkey(Gender.MALE)));
    }
}
