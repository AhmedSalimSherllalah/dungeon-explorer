/**
 * A riddle puzzle. Wrong answers deal damage to the player.
 */
public class Puzzle {
    private static final int WRONG_ANSWER_DAMAGE = 15;

    private String question;
    private String answer;
    private boolean solved;

    public Puzzle(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.solved = false;
    }

    /**
     * Attempts the puzzle. Deals damage on wrong answer.
     * @return true if correct
     */
    public boolean attempt(String playerAnswer, Player player) {
        if (playerAnswer.trim().equalsIgnoreCase(answer)) {
            solved = true;
            System.out.println("Correct! The door swings open.");
            return true;
        }
        System.out.println("Wrong! The runes shock you for " + WRONG_ANSWER_DAMAGE + " damage!");
        player.takeDamage(WRONG_ANSWER_DAMAGE);
        return false;
    }

    public void display() {
        System.out.println("\n*** RIDDLE ***");
        System.out.println(question);
    }

    public boolean isSolved() { return solved; }
}
