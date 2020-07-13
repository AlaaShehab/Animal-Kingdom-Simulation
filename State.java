public interface State {
    void handlingAging(Species species);
    void handleDeath(Species species);
    void handleBeingAdult(Species species);
    void handleHavingNewBorn (Species species);
}
