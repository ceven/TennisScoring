public class TennisMatchScoring {

    private static final int MIN_POINTS_TO_WIN_A_GAME = 4,
            MIN_POINTS_TO_WIN_A_SET = 6,
            MIN_POINTS_TO_WIN_TIE_BREAK = 7;

    private PlayerScore player1Score, player2Score;

    public TennisMatchScoring() {
        this.player1Score = new PlayerScore();
        this.player2Score = new PlayerScore();
    }

    /**
     * @return the score of the current match, displaying the current set score followed by the current game score.
     * E.g. 1-0, 30-40 indicates that player 1 has won the first game and player 2 is leading the second game 40 to 30.
     * NB: If the current game score is nil (0-0) then it is omitted from the score. E.g. 4-2 indicates that the 1st
     * player is leading the match 4 to 2 and no player has scored a point in the next game yet.
     */
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

    /**
     * Update the score of the match after a win from the given player.
     *
     * @param winner the name of the player who won the current point
     */
    public void pointWonBy(final String winner) {

        if (tennisSetIsWon()) {
            return;
        }

        Player winnerPlayer = Player.fromName(winner);

        PlayerScore winnerPlayerScore = getPlayerScore(winnerPlayer);
        winnerPlayerScore.wonGamePoint();

        if (gameIsWon()) {
            winnerPlayerScore.wonCurrentGame();
            player1Score.resetCurrentGameScore();
            player2Score.resetCurrentGameScore();
        }
    }

    private boolean tennisSetIsWon() {
        if (Math.max(player1Score.getGameWonsCount(), player2Score.getGameWonsCount()) >= MIN_POINTS_TO_WIN_A_SET &&
                Math.abs(player1Score.getGameWonsCount() - player2Score.getGameWonsCount()) > 1) {
            // The score is 6-x or x-6 where x <= 4, or 7-5 or 5-7
            return true;
        }
        if (Math.max(player1Score.getGameWonsCount(), player2Score.getGameWonsCount()) > MIN_POINTS_TO_WIN_A_SET &&
                Math.min(player1Score.getGameWonsCount(), player2Score.getGameWonsCount()) == MIN_POINTS_TO_WIN_A_SET) {
            // The score is 6-7 or 7-6
            return true;
        }
        return false;
    }

    private boolean gameIsWon() {
        if (isTieBreak()) {
            return Math.max(player1Score.getCurrentGamePointsCount(), player2Score.getCurrentGamePointsCount())
                    >= MIN_POINTS_TO_WIN_TIE_BREAK &&
                    Math.abs(player1Score.getCurrentGamePointsCount() - player2Score.getCurrentGamePointsCount()) > 1;
        }
        return Math.max(player1Score.getCurrentGamePointsCount(), player2Score.getCurrentGamePointsCount())
                >= MIN_POINTS_TO_WIN_A_GAME &&
                Math.abs(player1Score.getCurrentGamePointsCount() - player2Score.getCurrentGamePointsCount()) > 1;
    }

    private boolean isTieBreak() {
        return player1Score.getGameWonsCount() == 6 && player2Score.getCurrentGamePointsCount() == 6;
    }

    private PlayerScore getPlayerScore(Player player) {
        return player == Player.PLAYER_1 ? player1Score : player2Score;
    }

}
