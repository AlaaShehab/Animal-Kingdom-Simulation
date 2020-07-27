
import main.Conditions.Condition;
import main.Conditions.MarriageCondition;
import main.Population.Monkey;
import main.Population.PopulationParameters;
import main.StateMachinery.MonkeyKingdom;
import main.Utils.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MarriageConditionTest {
    MonkeyKingdom kingdom;

    @BeforeEach
    void setUp() {
        kingdom = createEmptyKingdom();
    }

    @Test
    public void testExecute_OppositeMaleGenderExists_True () {
        Monkey monkey = new Monkey(Gender.MALE);
        kingdom.addAdultMonkey(monkey, monkey.getGender());
        Monkey tobeMarried = new Monkey(Gender.FEMALE);
        kingdom.addAdultMonkey(tobeMarried, tobeMarried.getGender());
        Condition condition = new MarriageCondition();

        boolean conditionIsMet = condition.isConditionMet(kingdom, tobeMarried);

        assertTrue(conditionIsMet);
    }

    @Test
    public void testExecute_OppositeFemaleGenderExists_True () {
        Monkey monkey = new Monkey(Gender.FEMALE);
        kingdom.addAdultMonkey(monkey, monkey.getGender());
        Monkey tobeMarried = new Monkey(Gender.MALE);
        kingdom.addAdultMonkey(tobeMarried, tobeMarried.getGender());
        Condition condition = new MarriageCondition();

        boolean conditionIsMet = condition.isConditionMet(kingdom, tobeMarried);

        assertTrue(conditionIsMet);
    }
    @Test
    public void testExecute_OppositeGenderDoesnotExists_True () {
        Monkey tobeMarried = new Monkey(Gender.MALE);
        kingdom.addAdultMonkey(tobeMarried, tobeMarried.getGender());
        Condition condition = new MarriageCondition();

        boolean conditionIsMet = condition.isConditionMet(kingdom, tobeMarried);

        assertFalse(conditionIsMet);
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