package Actions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;
import Utils.Gender;

public class HandleMonkeyMarriage implements Action {
    @Override
    public void execute(MonkeyKingdom kingdom, Monkey monkey) {
        Monkey toBeMarried;
        if (monkey.getGender() == Gender.FEMALE) {
            toBeMarried = kingdom.getAdultMonkey(Gender.MALE);
        } else {
            toBeMarried = kingdom.getAdultMonkey(Gender.FEMALE);
        }
        if (toBeMarried == null) {
            //Throw exception
            return;
        }
        kingdom.removeMonkey(toBeMarried);
        monkey.setGender(Gender.MARRIED);
    }
}
