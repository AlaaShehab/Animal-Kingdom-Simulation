package Actions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;
import Utils.Utils;

public class AddNewbornToKingdom implements Action {
    @Override
    public void executeAction(MonkeyKingdom kingdom, Monkey monkey) {
        Monkey newborn = new Monkey(Utils.randomlyPickGender());
        kingdom.addNewMonkey(newborn);
    }
}
