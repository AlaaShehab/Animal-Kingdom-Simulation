package PopulationManagement;

import Species.Species;
import Species.Monkey;
import States.Married;
import Utlis.Gender;
import Utlis.Timer;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MonkeyKingdom implements Kingdom {
    // TODO instead of having this management class
    // maybe we could create another class and include it in monkey's
    // class so that all the handlers here would be moved to States.State class
    private PopulationParameters parameters;

    private List<Pair<Thread, Species>> males;
    private List<Pair<Thread, Species>> females;
    private List<Pair<Thread, Species>> newBorn;
    private List<Pair<Thread, Species>> married;

    MonkeyKingdom(PopulationParameters parameters) {
        reconfigurePopulationParameters(parameters);
        males = new ArrayList<>();
        females = new ArrayList<>();
        newBorn = new ArrayList<>();
    }

    @Override
    public void reconfigurePopulationParameters(PopulationParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public int getPopulationCount(int yearsPassed) {
        Timer timer = new Timer(parameters);

        for (int i = 0; i < parameters.getMalesNumber(); i++) {
            Species monkey = new Monkey();
            monkey.setGender(Gender.MALE);
            monkey.setDeathProbability(parameters.getDeathProbability());
            monkey.setReproductionProbability(parameters.getReproductionProbability());
            monkey.setTimer(timer);
            newBorn.add(new Pair<>(createNewSpeciesThread(monkey), monkey));
        }
        for (int i = 0; i < parameters.getFemalesNumber(); i++) {
            Species monkey = new Monkey();
            monkey.setGender(Gender.FEMALE);
            monkey.setTimer(timer);
            newBorn.add(new Pair<>(createNewSpeciesThread(monkey), monkey));
        }

        timer.start();
        handleNewBorn(timer);
        handleAdult(timer);
        handleMarriage(timer);

        //TODO let main join threads
        //TODO handles when expected years have passed
        return 0;
    }

    private void handleAdult(Timer timer) {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //Log
                }
                for (int i = 0; i < newBorn.size(); i++) {
                    if (newBorn.get(i).getValue().isAdult()) {
                        // TODO refactor this to statement
                        boolean unused = newBorn.get(i).getValue().getGender()
                                == Gender.MALE ? males.add(newBorn.remove(i--)) :
                                females.add(newBorn.remove(i--));
                    }
                }
            }
        }).start();
    }

    // TODO handle duplication and refactor
    private void handleMarriage(Timer timer) {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //Log
                }
                while (!males.isEmpty() && !females.isEmpty()) {
                    Pair<Thread, Species> male = males.remove(0);
                    Pair<Thread, Species> female = females.remove(0);

                    // TODO handle killing threads
                    Species monkey = new Monkey();
                    monkey.setGender(Gender.UNSPECIFIED);
                    monkey.setState(new Married());
                    monkey.setTimer(timer);
                    married.add(new Pair<>(createNewSpeciesThread(monkey), monkey));
                }
            }
        }).start();
    }

    private void handleNewBorn(Timer timer) {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //Log
                }
                for (int i = 0; i < married.size(); i++) {
                    Species monkey = married.get(i).getValue();
                    Species child = monkey.getChild();
                    while (child != null) {
                        child.setTimer(timer);
                        newBorn.add(new Pair<>(createNewSpeciesThread(child), child));
                        child = monkey.getChild();
                    }
                }
            }
        }).start();
    }

    private Thread createNewSpeciesThread(Species monkey) {
        Thread thread = new Thread(() -> {
            monkey.runMonkeyThread();
        });
        thread.start();
        return thread;
    }
}
