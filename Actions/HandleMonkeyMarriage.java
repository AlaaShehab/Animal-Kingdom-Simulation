package Actions;

import main.StateMachinery.MonkeyKingdom;
import main.Population.Monkey;
import main.StateMachinery.State;
import main.Utils.Gender;

import java.util.Optional;

// Handles the transition of 2 monkeys into Married
// state.
public class HandleMonkeyMarriage implements Action {
    @Override
    public void execute(MonkeyKingdom kingdom, Monkey monkey) {
        Optional<Monkey> toBeMarried;
        if (monkey.getGender() == Gender.FEMALE) {
            toBeMarried = kingdom.getAdultMonkey(Gender.MALE);
        } else {
            toBeMarried = kingdom.getAdultMonkey(Gender.FEMALE);
        }
        toBeMarried.ifPresent(
                monkeyToMarry -> {
                    monkeyToMarry
                        .updateMonkeyState(State.MonkeyState.DEAD);
                    kingdom.removeAdultMonkey(monkeyToMarry, monkeyToMarry.getGender());
                    kingdom.removeAdultMonkey(monkey, monkey.getGender());
                    monkey.setGender(Gender.MARRIED);
                });
    }
}
