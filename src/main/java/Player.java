import java.util.Arrays;

public enum Player {

    PLAYER_1("Player 1"),
    PLAYER_2("Player 2");

    private String playerName;

    Player(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public static Player fromName(String name) {
        return Arrays.stream(values())
                .filter(player -> player.getPlayerName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
