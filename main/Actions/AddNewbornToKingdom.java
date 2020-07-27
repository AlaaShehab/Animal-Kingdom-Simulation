package main.Actions;

import main.StateMachinery.MonkeyKingdom;
import main.Population.Monkey;
import main.StateMachinery.State;
import main.Utils.Gender;
import main.Utils.Utils;

// Handles adding a newborn to the system
// which resulted from a married monkey
// giving birth.
public class AddNewbornToKingdom implements Action {
    @Override
    public void execute(MonkeyKingdom kingdom, Monkey monkey) {
        if (monkey.getMonkeyState() != State.MonkeyState.MARRIED
            || !monkey.canHaveChildren()) return;
        monkey.increaseChildrenCount();
        Monkey newborn = new Monkey(Utils.getRandomGender());
        kingdom.addNewMonkey(newborn);
    }
}
