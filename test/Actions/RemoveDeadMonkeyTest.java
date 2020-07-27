import main.Actions.Action;
import main.Actions.RemoveDeadMonkey;
import main.Population.Monkey;
import main.Population.PopulationParameters;
import main.StateMachinery.MonkeyKingdom;
import main.StateMachinery.State;
import main.Utils.Gender;
import main.Utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveDeadMonkeyTest {
    MonkeyKingdom kingdom;
    Monkey monkey;

    @BeforeEach
    void setUp() {
        kingdom = createEmptyKingdom();
        monkey = new Monkey(Utils.getRandomGender());
        kingdom.addNewMonkey(monkey);
    }

    @Test
    public void testExecute_FemaleDeadMonkey_MonkeyRemoved () {
        monkey.updateMonkeyState(State.MonkeyState.DEAD);
        monkey.setGender(Gender.FEMALE);
        Action action = new RemoveDeadMonkey();

        action.execute(kingdom, monkey);

        assertEquals(kingdom.getMonkeysCount(), 0);
    }

    @Test
    public void testExecute_MaleDeadMonkey_MonkeyRemoved () {
        monkey.updateMonkeyState(State.MonkeyState.DEAD);
        monkey.setGender(Gender.MALE);
        Action action = new RemoveDeadMonkey();

        action.execute(kingdom, monkey);

        assertEquals(kingdom.getMonkeysCount(), 0);
    }

    @Test
    public void testExecute_BornMonkey_MonkeyRemoved () {
        monkey.updateMonkeyState(State.MonkeyState.BORN);
        Action action = new RemoveDeadMonkey();

        action.execute(kingdom, monkey);

        assertEquals(kingdom.getMonkeysCount(), 0);
    }

    @Test
    public void testExecute_AdultMonkey_MonkeyRemoved () {
        monkey.updateMonkeyState(State.MonkeyState.ADULT);
        Action action = new RemoveDeadMonkey();

        action.execute(kingdom, monkey);

        assertEquals(kingdom.getMonkeysCount(), 0);
    }

    @Test
    public void testExecute_MarriedMonkey_MonkeyRemoved () {
        monkey.updateMonkeyState(State.MonkeyState.MARRIED);
        Action action = new RemoveDeadMonkey();

        action.execute(kingdom, monkey);

        assertEquals(kingdom.getMonkeysCount(), 0);
    }

    private MonkeyKingdom createEmptyKingdom() {
        PopulationParameters parameters = new PopulationParameters
                .ParametersBuilder()
                .setFemalesNumber(0)
                .setMalesNumber(0)
                .build();
        return new MonkeyKingdom(parameters);
    }
}
