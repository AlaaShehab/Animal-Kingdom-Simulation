package Actions;

import StateMachinery.MonkeyKingdom;
import Population.Monkey;

// To maintain the consistency of the transition class
// a NoAction is set for transitions that don't have
// a consequent action after taking them.
public class NoActionTransition implements Action {
    @Override
    public void execute(MonkeyKingdom kingdom, Monkey monkey) {

    }
}