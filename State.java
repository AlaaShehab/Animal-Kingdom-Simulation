public interface State {
    void handlingAging(Species population);
    void handleDeath(Species population);
    void handleGettingMarried(Species population);
    void handleBeingAdult(Species population);
    void handleHavingNewBorn (Species population);
}
