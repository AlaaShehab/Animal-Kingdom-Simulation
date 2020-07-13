import java.util.concurrent.TimeUnit;

public class Timer {
    private int day = 0;
    private int year = 0;

    private int yearFrequencyInDays;
    private int dayFrequencyInSeconds;

    private boolean stopTimer;

    Timer (int yearFrequencyInDays, int dayFrequencyInSeconds) {
        this.dayFrequencyInSeconds = dayFrequencyInSeconds;
        this.yearFrequencyInDays = yearFrequencyInDays;
        stopTimer = false;
    }

    public void start () {
        new Thread(() -> {
            while (!stopTimer) {
                try {
                    TimeUnit.SECONDS.sleep(dayFrequencyInSeconds);
                } catch (InterruptedException e) {
                    //Log
                }
                day++;
                year = day % yearFrequencyInDays;
            }
        }).start();
    }

    public void stop () {
        stopTimer = true;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }
}
