import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerScoreTest {

    private PlayerScore playerScore;

    @Before
    public void setup() {
        playerScore = new PlayerScore();
    }

    @Test
    public void playerScoreShouldInitiallyBeAtZero() {
        assertEquals(0, playerScore.getCurrentGamePointsCount());
        assertEquals(0, playerScore.getGameWonsCount());
    }

    @Test
    public void shouldIncreaseGamePointWhenPlayerWinsAPoint() {
        playerScore.wonGamePoint();
        assertEquals(1, playerScore.getCurrentGamePointsCount());
    }

    @Test
    public void shouldIncreaseGameWonCountAndResetCurrentGamePointsWhenPlayerWinsAGame() {
        playerScore.wonCurrentGame();
        assertEquals(1, playerScore.getGameWonsCount());
        assertEquals(0, playerScore.getCurrentGamePointsCount());
    }

    @Test
    public void shouldResetCurrentGameScoreWithoutModifyingGamesWonCount() {

        // Given
        playerScore.wonCurrentGame();
        playerScore.wonGamePoint();
        playerScore.wonGamePoint();
        assertEquals(2, playerScore.getCurrentGamePointsCount());
        assertEquals(1, playerScore.getGameWonsCount());

        // When
        playerScore.resetCurrentGameScore();

        // Then
        assertEquals(0, playerScore.getCurrentGamePointsCount());
        assertEquals(1, playerScore.getGameWonsCount());
    }

}