package Actions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;
import Utils.Gender;

public class HandleMonkeyMarriage implements Action {
    @Override
    public void execute(MonkeyKingdom kingdom, Monkey monkey) {
        Monkey toBeMarried = null;
        if (monkey.getGender() == Gender.FEMALE) {
            toBeMarried = kingdom.getAdultMales().isEmpty()
                    ? null : kingdom.getAdultMales().get(0);
        } else {
            toBeMarried = kingdom.getAdultFemales().isEmpty()
                    ? null : kingdom.getAdultFemales().get(0);
        }
        if (toBeMarried == null) {
            //Throw exception
            return;
        }
        kingdom.removeMonkey(toBeMarried);
        monkey.setGender(Gender.MARRIED);
    }
}
