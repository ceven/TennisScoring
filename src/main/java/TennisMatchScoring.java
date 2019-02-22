public class TennisMatchScoring {

    private static final int MIN_POINTS_TO_WIN_A_GAME = 4;

    private PlayerScore player1Score, player2Score;

    public TennisMatchScoring() {
        this.player1Score = new PlayerScore();
        this.player2Score = new PlayerScore();
    }

    public String score() {
        final StringBuilder scoreBuilder = new StringBuilder()
                .append(String.format("%s-%s", player1Score.getGameWonsCount(), player2Score.getGameWonsCount()));

        final GameScoringStatus gameScoringStatus = determineGameScoringStatus();

        switch (gameScoringStatus) {
            case NOT_STARTED:
                break;
            case IN_PROGRESS:
                scoreBuilder.append(", ")
                        .append(player1Score.getGamePointsCountInTennisFashion())
                        .append("-")
                        .append(player2Score.getGamePointsCountInTennisFashion());
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

        if (player1Score.getCurrentGamePointsCount() < MIN_POINTS_TO_WIN_A_GAME - 1) {
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

        Player winnerPlayer = Player.fromName(winner);

        PlayerScore winnerPlayerScore = getPlayerScore(winnerPlayer);
        winnerPlayerScore.wonGamePoint();

        if (gameIsWon()) {
            winnerPlayerScore.wonCurrentGame();
            player1Score.resetCurrentGameScore();
            player2Score.resetCurrentGameScore();
        }
    }

    private boolean gameIsWon() {
        return Math.max(player1Score.getCurrentGamePointsCount(), player2Score.getCurrentGamePointsCount())
                >= MIN_POINTS_TO_WIN_A_GAME &&
                Math.abs(player1Score.getCurrentGamePointsCount() - player2Score.getCurrentGamePointsCount()) > 1;
    }

    private PlayerScore getPlayerScore(Player player) {
        return player == Player.PLAYER_1 ? player1Score : player2Score;
    }

}
