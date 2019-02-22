import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TennisMatchScoringTest {

    private static final String PLAYER_1 = "Player 1", PLAYER_2 = "Player 2";

    private TennisMatchScoring tennisMatchScoring;

    private String winnerSequence;
    private String expectedScore;

    public TennisMatchScoringTest(String winnerSequence, String expectedScore) {
        this.winnerSequence = winnerSequence;
        this.expectedScore = expectedScore;
    }

    @Parameterized.Parameters(name = "score(): Winning sequence: \"{0}\", expected score: \"{1}\"")
    public static Collection tennisWinnerSequenceAndExpectedScore() {
        return Arrays.asList(new Object[][]{
                {"", "0-0"},
                {"1", "0-0, 15-0"},
                {"2", "0-0, 0-15"},
                {"121212", "0-0, Deuce"},
                {"1212121", "0-0, Advantage Player 1"},
                {"1212122", "0-0, Advantage Player 2"},
                {"12121211", "1-0, 0-0"},
                {"12121222", "0-1, 0-0"}
        });
    }


    @Before
    public void setup() {
        tennisMatchScoring = new TennisMatchScoring();
    }

    @Test
    public void shouldReturnCorrectScore() {
        // When
        executePointsWonBy(winnerSequence);

        // Then
        assertEquals(expectedScore, tennisMatchScoring.score());
    }

    /**
     * Utility method to execute a sequence of points won by player 1 or 2.
     * We assume the string is valid.
     *
     * @param winnerSequence a sequence of 1 and 2, 1 indicating a point won by player 1 and 2 a point won by player 2.
     *                       The sequence is given in the order the points were played.
     */
    private void executePointsWonBy(String winnerSequence) {
        if (winnerSequence == null) {
            return;
        }
        winnerSequence.chars().forEach(playerId -> tennisMatchScoring.pointWonBy(playerId == 1 ? PLAYER_1 : PLAYER_2));
    }

}