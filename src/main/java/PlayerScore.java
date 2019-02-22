public class PlayerScore {

    private Player player;

    private int currentGamePointsCount;
    private int setWonsCount;

    public PlayerScore(Player player) {
        this.player = player;
        this.currentGamePointsCount = 0;
        this.setWonsCount = 0;
    }

    public int getCurrentGamePointsCount() {
        return currentGamePointsCount;
    }

    public int getSetWonsCount() {
        return setWonsCount;
    }

}
