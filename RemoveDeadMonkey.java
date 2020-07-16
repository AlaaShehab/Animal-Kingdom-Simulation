import Utils.*;

public class RemoveDeadMonkey implements Action {
    @Override
    public void executeAction(MonkeyKingdom kingdom, Monkey monkey) {
        kingdom.removeMonkey(monkey);
    }
}

class AddNewbornToKingdom implements Action {
    @Override
    public void executeAction(MonkeyKingdom kingdom, Monkey monkey) {
        Monkey newborn = new Monkey(Utils.randomlyPickGender());
        kingdom.addNewMonkey(newborn);
    }
}

class HandleMonkeyMarriage implements Action {
    @Override
    public void executeAction(MonkeyKingdom kingdom, Monkey monkey) {
        Monkey toBeMarried = null;
        if (monkey.getGender() == Gender.FEMALE) {
            toBeMarried = kingdom.getAdultMales().isEmpty()
                    ? null : kingdom.getAdultMales().get(0);
        } else {
            toBeMarried = kingdom.getAdultFemales().isEmpty()
                    ? null : kingdom.getAdultFemales().get(0);
        }
        if (toBeMarried == null) {
            //Throw exception
            return;
        }
        kingdom.removeMonkey(toBeMarried);
        monkey.setGender(Gender.MARRIED);
    }
}

class HandleNewAdultsAddedToSystem implements Action {
    @Override
    public void executeAction(MonkeyKingdom kingdom, Monkey monkey) {
        if (monkey.getGender() == Gender.FEMALE) {
            kingdom.getAdultFemales().add(monkey);
        } else {
            kingdom.getAdultMales().add(monkey);
        }
    }
}

class NoActionTransitions implements Action {
    @Override
    public void executeAction(MonkeyKingdom kingdom, Monkey monkey) {

    }
}