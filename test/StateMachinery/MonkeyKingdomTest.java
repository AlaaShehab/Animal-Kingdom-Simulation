import main.Population.Monkey;
import main.Population.PopulationParameters;
import main.StateMachinery.MonkeyKingdom;
import main.StateMachinery.State.MonkeyState;
import main.StateMachinery.SystemTransitions;
import main.StateMachinery.Transition;
import main.Utils.Gender;
import main.Utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MonkeyKingdomTest {
    MonkeyKingdom kingdom;

    @BeforeEach
    void setUp() {
        kingdom = createEmptyKingdom(Math.random(),Math.random(), 1);
    }

    @Test
    public void getMonkeysCount_EmptyKingdom_ReturnZero () {
        int kingdomSize = kingdom.getMonkeysCount();

        assertEquals(0, kingdomSize);
    }

    @Test
    public void getMonkeysCount_NonEmptyKingdom_ReturnSize () {
        Monkey monkey = new Monkey(Utils.getRandomGender());
        kingdom.addNewMonkey(monkey);

        int kingdomSize = kingdom.getMonkeysCount();

        assertEquals(1, kingdomSize);
    }

    @Test
    public void hasAdultMonkey_NoAdults_False () {
        boolean hasAdult = kingdom.hasAdultMonkey(Utils.getRandomGender());

        assertFalse(hasAdult);
    }

    @Test
    public void hasAdultMonkey_FemaleAdultExists_True () {
        Monkey monkey = new Monkey(Gender.FEMALE);
        kingdom.addAdultMonkey(monkey, monkey.getGender());

        boolean hasAdult = kingdom.hasAdultMonkey(Gender.FEMALE);

        assertTrue(hasAdult);
    }

    @Test
    public void hasAdultMonkey_MaleAdultExists_True () {
        Monkey monkey = new Monkey(Gender.MALE);
        kingdom.addAdultMonkey(monkey, monkey.getGender());

        boolean hasAdult = kingdom.hasAdultMonkey(Gender.MALE);

        assertTrue(hasAdult);
    }

    @Test
    public void hasAdultMonkey_FemaleAdultExistsMaleNeeded_False () {
        Monkey monkey = new Monkey(Gender.FEMALE);
        kingdom.addAdultMonkey(monkey, monkey.getGender());

        boolean hasAdult = kingdom.hasAdultMonkey(Gender.MALE);

        assertFalse(hasAdult);
    }

    @Test
    public void hasAdultMonkey_MaleAdultExistsFemaleNeeded_False () {
        Monkey monkey = new Monkey(Gender.MALE);
        kingdom.addAdultMonkey(monkey, monkey.getGender());

        boolean hasAdult = kingdom.hasAdultMonkey(Gender.FEMALE);

        assertFalse(hasAdult);
    }

    @Test
    public void getAdultMonkey_NoAdults_NoAdultPresent () {
        Optional<Monkey> monkey = kingdom
                .getAdultMonkey(Utils.getRandomGender());

        assertFalse(monkey.isPresent());
    }

    @Test
    public void getAdultMonkey_FemaleAdultExistsFemaleNeeded_FemalePresent () {
        Monkey monkey = new Monkey(Gender.FEMALE);
        kingdom.addAdultMonkey(monkey, monkey.getGender());

        Optional<Monkey> requestedMonkey = kingdom
                .getAdultMonkey(Gender.FEMALE);

        assertTrue(requestedMonkey.isPresent());
        assertEquals(monkey.getMonkeyState(), requestedMonkey.get().getMonkeyState());
        assertEquals(monkey.getGender(), requestedMonkey.get().getGender());
        assertEquals( monkey.getAge(), requestedMonkey.get().getAge());
    }

    @Test
    public void getAdultMonkey_MaleAdultExistsMaleNeeded_ReturnsMale () {
        Monkey monkey = new Monkey(Gender.MALE);
        kingdom.addAdultMonkey(monkey, monkey.getGender());

        Optional<Monkey> requestedMonkey = kingdom
                .getAdultMonkey(Gender.MALE);

        assertTrue(requestedMonkey.isPresent());
        assertEquals(monkey.getMonkeyState(), requestedMonkey.get().getMonkeyState());
        assertEquals(monkey.getGender(), requestedMonkey.get().getGender());
        assertEquals( monkey.getAge(), requestedMonkey.get().getAge());
    }

    @Test
    public void getAdultMonkey_FemaleAdultExistsMaleNeeded_NotPresent () {
        Monkey monkey = new Monkey(Gender.FEMALE);
        kingdom.addAdultMonkey(monkey, monkey.getGender());

        Optional<Monkey> requestedMonkey = kingdom
                .getAdultMonkey(Gender.MALE);

        assertFalse(requestedMonkey.isPresent());
    }

    @Test
    public void getAdultMonkey_MaleAdultExistsFemaleNeeded_NotPresent () {
        Monkey monkey = new Monkey(Gender.MALE);
        kingdom.addAdultMonkey(monkey, monkey.getGender());

        Optional<Monkey> requestedMonkey = kingdom
                .getAdultMonkey(Gender.FEMALE);

        assertFalse(requestedMonkey.isPresent());
    }

    @Test
    public void removeAdultMonkey_MaleAdultExists_MonkeyRemoved () {
        Monkey monkey = new Monkey(Gender.MALE);
        kingdom.addAdultMonkey(monkey, monkey.getGender());

        kingdom.removeAdultMonkey(monkey, monkey.getGender());

        assertEquals(0, kingdom.getMonkeysCount());
    }

    @Test
    public void removeAdultMonkey_FemaleAdultExists_MonkeyRemoved () {
        Monkey monkey = new Monkey(Gender.FEMALE);
        kingdom.addAdultMonkey(monkey, monkey.getGender());

        kingdom.removeAdultMonkey(monkey, monkey.getGender());

        assertEquals(0, kingdom.getMonkeysCount());
    }

    @Test
    public void removeAdultMonkey_MarriedMonkeyExists_MonkeyNotRemoved () {
        Monkey monkey = new Monkey(Gender.MARRIED);
        kingdom.addNewMonkey(monkey);

        kingdom.removeAdultMonkey(monkey, monkey.getGender());

        assertEquals(1, kingdom.getMonkeysCount());
    }

    @Test
    public void pickTransition_BornMonkeyDeathZero_ReturnBornTransition () {
        MonkeyKingdom monkeyKingdom = createEmptyKingdom(0, Math.random(), 1);
        Monkey monkey = createMonkey(Utils.getRandomGender(), MonkeyState.BORN, 0);
        monkeyKingdom.addNewMonkey(monkey);

        Optional<Transition> transition = monkeyKingdom
                .pickTransition(
                        SystemTransitions
                                .getAllPossibleStates(monkey.getMonkeyState()), monkey);

        assertTrue(transition.isPresent());
        assertEquals(MonkeyState.BORN, transition.get().getEndState());
    }

    @Test
    public void pickTransition_BornMonkeyDeathOne_ReturnDeadTransition () {
        MonkeyKingdom monkeyKingdom = createEmptyKingdom(1, Math.random(), 1);
        Monkey monkey = createMonkey(Utils.getRandomGender(), MonkeyState.BORN, 0);
        monkeyKingdom.addNewMonkey(monkey);

        Optional<Transition> transition = monkeyKingdom
                .pickTransition(
                        SystemTransitions
                                .getAllPossibleStates(monkey.getMonkeyState()), monkey);

        assertTrue(transition.isPresent());
        assertEquals(MonkeyState.DEAD, transition.get().getEndState());
    }

    @Test
    public void pickTransition_BornMonkeyAgeBelowAdult_ReturnTransitionNotAdult () {
        MonkeyKingdom monkeyKingdom = createEmptyKingdom(Math.random(), Math.random(), 1);
        Monkey monkey = createMonkey(Utils.getRandomGender(), MonkeyState.BORN, 0);
        monkeyKingdom.addNewMonkey(monkey);

        Optional<Transition> transition = monkeyKingdom
                .pickTransition(
                        SystemTransitions
                                .getAllPossibleStates(monkey.getMonkeyState()), monkey);

        assertTrue(transition.isPresent());
        assertNotEquals(MonkeyState.ADULT, transition.get().getEndState());
    }

    @Test
    public void pickTransition_BornMonkeyAgeAboveAdult_ReturnTransitionNotBorn () {
        MonkeyKingdom monkeyKingdom = createEmptyKingdom(Math.random(), Math.random(), 1);
        Monkey monkey = createMonkey(Utils.getRandomGender(), MonkeyState.BORN, 2);
        monkeyKingdom.addNewMonkey(monkey);

        Optional<Transition> transition = monkeyKingdom
                .pickTransition(
                        SystemTransitions
                                .getAllPossibleStates(monkey.getMonkeyState()), monkey);

        assertTrue(transition.isPresent());
        assertNotEquals(MonkeyState.BORN, transition.get().getEndState());
    }

    @Test
    public void pickTransition_BornMonkeyWithAdultAgeDeathZero_ReturnAdultTransition () {
        MonkeyKingdom monkeyKingdom = createEmptyKingdom(0, Math.random(), 1);
        Monkey monkey = createMonkey(Utils.getRandomGender(), MonkeyState.BORN, 2);
        monkeyKingdom.addNewMonkey(monkey);

        Optional<Transition> transition = monkeyKingdom
                .pickTransition(
                        SystemTransitions
                                .getAllPossibleStates(monkey.getMonkeyState()), monkey);

        assertTrue(transition.isPresent());
        assertEquals(MonkeyState.ADULT, transition.get().getEndState());
    }

    @Test
    public void pickTransition_BornMonkeyWithAdultAgeDeathOne_ReturnDeadTransition () {
        MonkeyKingdom monkeyKingdom = createEmptyKingdom(1, Math.random(), 1);
        Monkey monkey = createMonkey(Utils.getRandomGender(), MonkeyState.BORN, 2);
        monkeyKingdom.addNewMonkey(monkey);

        Optional<Transition> transition = monkeyKingdom
                .pickTransition(
                        SystemTransitions
                                .getAllPossibleStates(monkey.getMonkeyState()), monkey);

        assertTrue(transition.isPresent());
        assertEquals(MonkeyState.DEAD, transition.get().getEndState());
    }

    @Test
    public void pickTransition_AdultMonkeyNoPartnerDeathZero_ReturnAdultTransition () {
        MonkeyKingdom monkeyKingdom = createEmptyKingdom(0, Math.random(), 1);
        Monkey monkey = createMonkey(Gender.MALE, MonkeyState.ADULT, 2);
        monkeyKingdom.addNewMonkey(monkey);

        Optional<Transition> transition = monkeyKingdom
                .pickTransition(
                        SystemTransitions
                                .getAllPossibleStates(monkey.getMonkeyState()), monkey);

        assertTrue(transition.isPresent());
        assertEquals(MonkeyState.ADULT, transition.get().getEndState());
    }

    @Test
    public void pickTransition_AdultMonkeyNoPartnerDeathOne_ReturnDeadTransition () {
        MonkeyKingdom monkeyKingdom = createEmptyKingdom(1, Math.random(), 1);
        Monkey monkey = createMonkey(Gender.MALE, MonkeyState.ADULT, 2);
        monkeyKingdom.addNewMonkey(monkey);

        Optional<Transition> transition = monkeyKingdom
                .pickTransition(
                        SystemTransitions
                                .getAllPossibleStates(monkey.getMonkeyState()), monkey);

        assertTrue(transition.isPresent());
        assertEquals(MonkeyState.DEAD, transition.get().getEndState());
    }

    @Test
    public void pickTransition_AdultMonkeyPartnerExistsDeathOne_ReturnDeadTransition () {
        MonkeyKingdom monkeyKingdom = createEmptyKingdom(1, Math.random(), 1);
        Monkey monkey = createMonkey(Gender.MALE, MonkeyState.ADULT, 2);
        Monkey monkeyToMarry = createMonkey(Gender.FEMALE, MonkeyState.ADULT, 2);
        monkeyKingdom.addNewMonkey(monkey);
        monkeyKingdom.addAdultMonkey(monkeyToMarry, monkeyToMarry.getGender());

        Optional<Transition> transition = monkeyKingdom
                .pickTransition(
                        SystemTransitions
                                .getAllPossibleStates(monkey.getMonkeyState()), monkey);

        assertTrue(transition.isPresent());
        assertEquals(MonkeyState.DEAD, transition.get().getEndState());

    }

    @Test
    public void pickTransition_AdultMonkeyPartnerExistsDeathZero_ReturnMarriedTransition () {
        MonkeyKingdom monkeyKingdom = createEmptyKingdom(0, Math.random(), 1);
        Monkey monkey = createMonkey(Gender.MALE, MonkeyState.ADULT, 2);
        Monkey monkeyToMarry = createMonkey(Gender.FEMALE, MonkeyState.ADULT, 2);
        monkeyKingdom.addNewMonkey(monkey);
        monkeyKingdom.addAdultMonkey(monkeyToMarry, monkeyToMarry.getGender());

        Optional<Transition> transition = monkeyKingdom
                .pickTransition(
                        SystemTransitions
                                .getAllPossibleStates(monkey.getMonkeyState()), monkey);

        assertTrue(transition.isPresent());
        assertEquals(MonkeyState.MARRIED, transition.get().getEndState());
    }

    @Test
    public void pickTransition_AdultMonkeyPartnerExists_ReturnTransitionNotAdult () {
        MonkeyKingdom monkeyKingdom = createEmptyKingdom(Math.random(), Math.random(), 1);
        Monkey monkey = createMonkey(Gender.MALE, MonkeyState.ADULT, 2);
        Monkey monkeyToMarry = createMonkey(Gender.FEMALE, MonkeyState.ADULT, 2);
        monkeyKingdom.addNewMonkey(monkey);
        monkeyKingdom.addAdultMonkey(monkeyToMarry, monkeyToMarry.getGender());

        Optional<Transition> transition = monkeyKingdom
                .pickTransition(
                        SystemTransitions
                                .getAllPossibleStates(monkey.getMonkeyState()), monkey);

        assertTrue(transition.isPresent());
        assertNotEquals(MonkeyState.ADULT, transition.get().getEndState());
    }

    @Test
    public void pickTransition_AdultMonkeyNoPartner_ReturnTransitionNotMarried () {
        MonkeyKingdom monkeyKingdom = createEmptyKingdom(Math.random(), Math.random(), 1);
        Monkey monkey = createMonkey(Gender.MALE, MonkeyState.ADULT, 2);
        monkeyKingdom.addNewMonkey(monkey);

        Optional<Transition> transition = monkeyKingdom
                .pickTransition(
                        SystemTransitions
                                .getAllPossibleStates(monkey.getMonkeyState()), monkey);

        assertTrue(transition.isPresent());
        assertNotEquals(MonkeyState.MARRIED, transition.get().getEndState());
    }

    @Test
    public void getPopulationCount_EmptyKingdom_ReturnZero () {
        int populationCount = kingdom.getPopulationCount();

        assertEquals(0, populationCount);
    }

    @Test
    public void getPopulationCount_kingdomHasMonkeys_ReturnValidSize () {
        kingdom.addNewMonkey(new Monkey(Gender.MALE));

        int populationCount = kingdom.getPopulationCount();

        assertEquals(1, populationCount);
    }

    @Test
    public void getPopulationCount_kingdomHasMarriedMonkeys_ReturnDoubleMonkeys () {
        kingdom.addNewMonkey(createMonkey(Gender.MARRIED, MonkeyState.MARRIED, 2));

        int populationCount = kingdom.getPopulationCount();

        assertEquals(2, populationCount);
    }


    private MonkeyKingdom createEmptyKingdom
            (double deathProbability
                    , double reproductionProbability
                    , int adultAge) {
        PopulationParameters parameters = new PopulationParameters
                .ParametersBuilder()
                .setDeathProbability(deathProbability)
                .setReproductionProbability(reproductionProbability)
                .setFemalesNumber(0)
                .setMalesNumber(0)
                .setAdulthoodTimeInYears(adultAge)
                .build();
        return new MonkeyKingdom(parameters);
    }

    private Monkey createMonkey (Gender gender, MonkeyState state, int age) {
        SystemTransitions.getInstance();
        Monkey monkey = new Monkey(gender);
        monkey.setMonkeyState(state);
        monkey.setAge(age);
        return monkey;
    }
}
