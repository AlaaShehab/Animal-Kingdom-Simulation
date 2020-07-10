public interface State {
    void updateAge(Kingdom population);
    void handleDeath(Kingdom population);
    void handleGettingMarried(Kingdom population);
    void handleBeingAdult(Kingdom population);
    void handleHavingNewBorn (Kingdom population);
}
