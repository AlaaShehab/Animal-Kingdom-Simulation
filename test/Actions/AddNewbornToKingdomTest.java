
import main.Actions.Action;
import main.Actions.AddNewbornToKingdom;
import main.Population.Monkey;
import main.Population.PopulationParameters;
import main.StateMachinery.MonkeyKingdom;
import main.StateMachinery.State;
import main.Utils.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AddNewbornToKingdomTest {
    MonkeyKingdom kingdom;
    Monkey monkey;

    @BeforeEach
    void setUp() {
        kingdom = createEmptyKingdom();
        monkey = new Monkey(Gender.MARRIED);
        kingdom.addNewMonkey(monkey);
    }

    @Test
    public void testExecute_MarriedStateMonkey_NewbornAdded () {
        monkey.updateMonkeyState(State.MonkeyState.MARRIED);
        Action action = new AddNewbornToKingdom();

        action.execute(kingdom, monkey);

        assertEquals(kingdom.getMonkeysCount(), 2);
    }

    @Test
    public void testExecute_BornStateMonkey_newbornNotAdded () {
        monkey.updateMonkeyState(State.MonkeyState.BORN);
        Action action = new AddNewbornToKingdom();

        action.execute(kingdom, monkey);

        assertEquals(kingdom.getMonkeysCount(), 1);
    }

    @Test
    public void testExecute_AdultStateMonkey_newbornNotAdded () {
        monkey.updateMonkeyState(State.MonkeyState.ADULT);
        Action action = new AddNewbornToKingdom();

        action.execute(kingdom, monkey);

        assertEquals(kingdom.getMonkeysCount(), 1);
    }

    @Test
    public void testExecute_DeadStateMonkey_newbornNotAdded () {
        monkey.updateMonkeyState(State.MonkeyState.DEAD);
        Action action = new AddNewbornToKingdom();

        action.execute(kingdom, monkey);

        assertEquals(kingdom.getMonkeysCount(), 1);
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