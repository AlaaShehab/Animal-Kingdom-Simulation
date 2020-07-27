import main.Conditions.Condition;
import main.Conditions.MarriageCondition;
import main.Population.Monkey;
import main.StateMachinery.MonkeyKingdom;
import main.StateMachinery.State;
import main.Utils.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MonkeyTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void UpdateMonkeyState_ValidTransition_StateUpdated () {
        Monkey monkey = new Monkey(Gender.MALE);
        monkey.setMonkeyState(State.MonkeyState.BORN);

        monkey.updateMonkeyState(State.MonkeyState.ADULT);

        assertEquals(monkey.getMonkeyState(), State.MonkeyState.ADULT);
    }

    @Test
    public void UpdateMonkeyState_InValidTransition_StateUpdated () {
        Monkey monkey = new Monkey(Gender.MALE);
        monkey.setMonkeyState(State.MonkeyState.BORN);

        monkey.updateMonkeyState(State.MonkeyState.MARRIED);

        assertEquals(monkey.getMonkeyState(), State.MonkeyState.MARRIED);
    }

    @Test
    public void UpdateMonkeyState_ValidTransition_AgeChanges() {
        Monkey monkey = new Monkey(Gender.MALE);
        monkey.setMonkeyState(State.MonkeyState.BORN);

        int ageBeforeStateUpdate = monkey.getAge();
        monkey.updateMonkeyState(State.MonkeyState.DEAD);

        assertNotEquals(ageBeforeStateUpdate, monkey.getAge());
    }

    @Test
    public void UpdateMonkeyState_ValidTransition_AgeIncreases() {
        Monkey monkey = new Monkey(Gender.MALE);
        monkey.setMonkeyState(State.MonkeyState.BORN);

        int ageBeforeStateUpdate = monkey.getAge();
        monkey.updateMonkeyState(State.MonkeyState.DEAD);

        assertEquals(ageBeforeStateUpdate + 1, monkey.getAge());
    }

}
