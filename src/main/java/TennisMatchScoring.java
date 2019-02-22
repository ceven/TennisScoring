public class TennisMatchScoring {

    private static final int MIN_POINTS_IN_GAME = 3;
    private PlayerScore player1Score, player2Score;

    public TennisMatchScoring() {
        this.player1Score = new PlayerScore(Player.PLAYER_1);
        this.player2Score = new PlayerScore(Player.PLAYER_2);
    }

    public String score() {
        final StringBuilder scoreBuilder = new StringBuilder()
                .append(String.format("%s-%s", player1Score.getSetWonsCount(), player2Score.getSetWonsCount()));

        final GameScoringStatus gameScoringStatus = determineGameScoringStatus();

        switch (gameScoringStatus) {
            case NOT_STARTED:
                break;
            case IN_PROGRESS:
                scoreBuilder.append(", ")
                        .append(player1Score.getCurrentGamePointsCount())
                        .append("-")
                        .append(player2Score.getCurrentGamePointsCount());
                break;
            default:
                scoreBuilder.append(", ")
                        .append(gameScoringStatus.toString());
                break;
        }

        return scoreBuilder.toString();
    }

    private GameScoringStatus determineGameScoringStatus() {

        int diffPoints = player1Score.getCurrentGamePointsCount() - player2Score.getCurrentGamePointsCount();

        if (player1Score.getCurrentGamePointsCount() < MIN_POINTS_IN_GAME &&
                player2Score.getCurrentGamePointsCount() < MIN_POINTS_IN_GAME) {
            if (diffPoints == 0) {
                return GameScoringStatus.NOT_STARTED;
            }
            return GameScoringStatus.IN_PROGRESS;
        }

        if (diffPoints == 0) {
            return GameScoringStatus.DEUCE;
        }

        return diffPoints > 0 ? GameScoringStatus.ADVANTAGE_PLAYER_1 : GameScoringStatus.ADVANTAGE_PLAYER_2;
    }

    public void pointWonBy(final String winner) {
        // TODO
    }

}
