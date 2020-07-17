package Actions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;
import StateMachinery.State;
import Utils.Gender;

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
                monkeyToMarry -> monkeyToMarry
                        .updateMonkeyState(State.MonkeyState.DEAD));
    }
}
