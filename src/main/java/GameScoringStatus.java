public enum GameScoringStatus {

    NOT_STARTED, IN_PROGRESS, DEUCE, ADVANTAGE_PLAYER_1, ADVANTAGE_PLAYER_2;

    private String formatted() {
        return this.name().substring(0, 1).toUpperCase() +
                this.name().substring(1).toLowerCase().replace('_', ' ');
    }

    @Override
    public String toString() {
        return this.formatted();
    }
}
