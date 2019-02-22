import java.util.Map;

public class PlayerScore {

    private static final Map<Integer, Integer> TENNIS_POINTS_MAPPING = Map.of(
            1, 15,
            2, 30,
            3, 40
    );

    private int currentGamePointsCount;
    private int gameWonsCount;

    public PlayerScore() {
        this.currentGamePointsCount = 0;
        this.gameWonsCount = 0;
    }

    public int getCurrentGamePointsCount() {
        return currentGamePointsCount;
    }

    public int getGameWonsCount() {
        return gameWonsCount;
    }

    public void wonCurrentGame() {
        this.gameWonsCount++;
        this.resetCurrentGameScore();
    }

    public void wonGamePoint() {
        this.currentGamePointsCount++;
    }

    public void resetCurrentGameScore() {
        this.currentGamePointsCount = 0;
    }

    public int getGamePointsCountInTennisFashion() {
        return TENNIS_POINTS_MAPPING.getOrDefault(currentGamePointsCount, currentGamePointsCount);
    }

}
