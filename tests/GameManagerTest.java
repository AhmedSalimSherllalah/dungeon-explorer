import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTest {

    @BeforeEach
    void setUp() { GameManager.resetInstance(); }

    @Test
    void testSingleton() {
        assertSame(GameManager.getInstance(), GameManager.getInstance());
    }

    @Test
    void testInitialScore() { assertEquals(0, GameManager.getInstance().getScore()); }

    @Test
    void testIncrementScore() {
        GameManager gm = GameManager.getInstance();
        gm.incrementScore(50);
        gm.incrementScore(50);
        assertEquals(100, gm.getScore());
    }

    @Test
    void testIncrementTurns() {
        GameManager gm = GameManager.getInstance();
        gm.incrementTurns();
        gm.incrementTurns();
        assertEquals(2, gm.getTurns());
    }

    @Test
    void testGameOverWin() {
        GameManager gm = GameManager.getInstance();
        gm.setGameOver(true);
        assertTrue(gm.isGameOver());
        assertTrue(gm.isPlayerWon());
    }

    @Test
    void testGameOverLose() {
        GameManager gm = GameManager.getInstance();
        gm.setGameOver(false);
        assertTrue(gm.isGameOver());
        assertFalse(gm.isPlayerWon());
    }
}
