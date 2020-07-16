package Actions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;
import Utils.Gender;

public class HandleNewAdultsAddedToSystem implements Action {
    @Override
    public void executeAction(MonkeyKingdom kingdom, Monkey monkey) {
        if (monkey.getGender() == Gender.FEMALE) {
            kingdom.getAdultFemales().add(monkey);
        } else {
            kingdom.getAdultMales().add(monkey);
        }
    }
}
