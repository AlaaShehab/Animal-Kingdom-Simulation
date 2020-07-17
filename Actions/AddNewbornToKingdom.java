package Actions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;
import Utils.Utils;

// Handles adding a newborn to the system
// which resulted from a married monkey
// giving birth.
public class AddNewbornToKingdom implements Action {
    @Override
    public void execute(MonkeyKingdom kingdom, Monkey monkey) {
        Monkey newborn = new Monkey(Utils.getRandomGender());
        kingdom.addNewMonkey(newborn);
    }
}
