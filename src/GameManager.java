/**
 * DESIGN PATTERN: Singleton
 * Only one instance of GameManager can ever exist.
 * Tracks global game state: score, turns, and game over flag.
 */
public class GameManager {
    private static GameManager instance;

    private int score;
    private int turns;
    private boolean gameOver;
    private boolean playerWon;

    private GameManager() {
        score = 0;
        turns = 0;
        gameOver = false;
        playerWon = false;
    }

    /** Returns the single shared instance. */
    public static GameManager getInstance() {
        if (instance == null) instance = new GameManager();
        return instance;
    }

    public void incrementScore(int amount) { 
        score += amount; 
        System.out.println("Score: " + score);
    }
    public void incrementTurns() { turns++; }
    public void setGameOver(boolean won) { gameOver = true; playerWon = won; }
    public static void resetInstance() { instance = null; }

    public int getScore() { return score; }
    public int getTurns() { return turns; }
    public boolean isGameOver() { return gameOver; }
    public boolean isPlayerWon() { return playerWon; }
}
