// Dungeon Explorer - CS1OP Coursework by Ahmed Sherllalah
import java.util.Scanner;

/**ahj
 * Main game class. Runs a simple stage-by-stage adventure.
 * Each stage gives the player exactly 2 choices.
 * Uses all three design patterns: Singleton, Factory, Observer.
 */
public class Game {

    private Player player;
    private Scanner scanner;
    private GameManager gameManager;
    private EventSystem eventSystem;

    public Game() {
        scanner = new Scanner(System.in);
        gameManager = GameManager.getInstance();
        eventSystem = new EventSystem();

        // Observer: score tracker listener
        eventSystem.addListener((eventType, message) -> {
            if (eventType.equals("ENEMY_DEFEATED")) gameManager.incrementScore(50);
            if (eventType.equals("PUZZLE_SOLVED"))  gameManager.incrementScore(100);
            if (eventType.equals("ITEM_COLLECTED"))  gameManager.incrementScore(10);
        });

        // Observer: log listener
        eventSystem.addListener((eventType, message) ->
            System.out.println("[" + eventType + "] " + message));
    }

    /** Starts the game. */
    public void start() {
        printBanner();
        System.out.print("Enter your name: ");
        System.out.flush();
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Hero";
        player = new Player(name);

        System.out.println("\nWelcome, " + player.getName() + "! Your quest: reach the Dragon and escape the dungeon.");
        System.out.println("You have " + player.getHealth() + " HP and " + player.getAttack() + " attack.\n");

        stageOne();
    }

    // ─── STAGE 1: Dungeon Entrance ───────────────────────────────────────────

    private void stageOne() {
        System.out.println("=====================================");
        System.out.println("  STAGE 1: Dungeon Entrance");
        System.out.println("=====================================");
        System.out.println("You step into a dark, damp dungeon. The smell of mould fills the air.");
        System.out.println("A snarling Goblin blocks your path, eyeing you hungrily.");
        System.out.println("You notice a shiny sword on the ground next to it.");
        System.out.println();
        System.out.println("What do you do?");
        System.out.println("  1. Fight the Goblin");
        System.out.println("  2. Sneak past and grab the sword");

        String choice = getChoice("1", "2");

        if (choice.equals("1")) {
            // Choice 1: fight the goblin
            Enemy goblin = EnemyFactory.createEnemy("goblin");
            System.out.println("\nYou charge at the Goblin!");
            fightEnemy(goblin);
            if (!player.isAlive()) { endGame(); return; }
            System.out.println("You find a sword on the ground and pick it up!");
            Item sword = new Weapon("Iron Sword");
            player.addItem(sword);
            sword.use(player);
            eventSystem.notifyListeners("ITEM_COLLECTED", "Iron Sword picked up.");

        } else {
            // Choice 2: sneak past
            System.out.println("\nYou creep past the Goblin and grab the sword!");
            System.out.println("The Goblin spots you and scratches your arm on the way past!");
            player.takeDamage(10);
            if (!player.isAlive()) { endGame(); return; }
            Item sword = new Weapon("Iron Sword");
            player.addItem(sword);
            sword.use(player);
            eventSystem.notifyListeners("ITEM_COLLECTED", "Iron Sword picked up.");
        }

        System.out.println();
        stageTwo();
    }

    // ─── STAGE 2: Puzzle Chamber ─────────────────────────────────────────────

    private void stageTwo() {
        System.out.println("=====================================");
        System.out.println("  STAGE 2: Puzzle Chamber");
        System.out.println("=====================================");
        System.out.println("A locked door blocks the way. Ancient runes glow on the wall.");
        System.out.println("A mysterious stranger offers you help — for a price.");
        System.out.println();
        System.out.println("What do you do?");
        System.out.println("  1. Solve the riddle yourself");
        System.out.println("  2. Pay the stranger 20 HP to tell you the answer");

        String choice = getChoice("1", "2");

        Puzzle puzzle = new Puzzle(
            "I have cities but no houses.\n" +
            "I have mountains but no trees.\n" +
            "I have water but no fish.\n" +
            "What am I?",
            "map"
        );

        if (choice.equals("1")) {
            // Choice 1: solve it yourself
            puzzle.display();
            boolean solved = false;
            while (!solved) {
                System.out.print("Your answer: ");
                String answer = scanner.nextLine().trim();
                if (answer.isEmpty()) {
                    System.out.println("You must give an answer!");
                    continue;
                }
                solved = puzzle.attempt(answer, player);
                if (!player.isAlive()) { endGame(); return; }
                if (!solved) System.out.println("Try again...");
            }
            eventSystem.notifyListeners("PUZZLE_SOLVED", "Puzzle Chamber solved.");

        } else {
            // Choice 2: pay the stranger
            System.out.println("\nThe stranger whispers: 'The answer is... map.'");
            System.out.println("Then stabs you with a rusty dagger as payment!");
            player.takeDamage(20);
            if (!player.isAlive()) { endGame(); return; }
            puzzle.attempt("map", player); // auto-solve
            eventSystem.notifyListeners("PUZZLE_SOLVED", "Puzzle Chamber solved (with help).");
        }

        System.out.println();
        stageThree();
    }

    // ─── STAGE 3: Treasure Room ──────────────────────────────────────────────

    private void stageThree() {
        System.out.println("=====================================");
        System.out.println("  STAGE 3: Treasure Room");
        System.out.println("=====================================");
        System.out.println("You find a room with two things: a health potion and a powerful axe.");
        System.out.println("You can only carry one.");
        player.showStats();
        System.out.println();
        System.out.println("What do you take?");
        System.out.println("  1. Health Potion (restores 40 HP)");
        System.out.println("  2. Battle Axe (+5 attack)");

        String choice = getChoice("1", "2");

        if (choice.equals("1")) {
            Item potion = new Potion();
            player.addItem(potion);
            potion.use(player);
            eventSystem.notifyListeners("ITEM_COLLECTED", "Health Potion collected.");
        } else {
            Item axe = new Weapon("Battle Axe");
            player.addItem(axe);
            axe.use(player);
            eventSystem.notifyListeners("ITEM_COLLECTED", "Battle Axe collected.");
        }

        System.out.println();
        stageFour();
    }

    // ─── STAGE 4: Dragon's Lair ──────────────────────────────────────────────

    private void stageFour() {
        System.out.println("=====================================");
        System.out.println("  STAGE 4: Dragon's Lair - FINAL BOSS");
        System.out.println("=====================================");
        System.out.println("The ground shakes violently. The air is thick with smoke.");
        System.out.println("A massive Dragon rises from a pile of gold and stares you down.");
        System.out.println("You notice a glowing weak spot on its left wing.");
        player.showStats();
        System.out.println();
        System.out.println("How do you fight?");
        System.out.println("  1. Attack the weak spot (high risk, high reward - double damage but Dragon attacks first)");
        System.out.println("  2. Fight normally (safe and steady)");

        String choice = getChoice("1", "2");

        Enemy dragon = EnemyFactory.createEnemy("dragon");

        if (choice.equals("1")) {
            System.out.println("\nYou lunge for the weak spot!");
            System.out.println("The Dragon swings its tail and hits you first!");
            dragon.attack(player);
            if (!player.isAlive()) { endGame(); return; }
            System.out.println("But you land a CRITICAL HIT on the weak spot!");
            dragon.takeDamage(player.getAttack() * 2);
        } else {
            System.out.println("\nYou take a careful stance and strike!");
            dragon.takeDamage(player.getAttack());
        }

        // Continue the fight until one of them dies
        while (dragon.isAlive() && player.isAlive()) {
            System.out.println();
            System.out.println("Continue fighting?");
            System.out.println("  1. Attack");
            System.out.println("  2. Check your stats");
            String fightChoice = getChoice("1", "2");
            if (fightChoice.equals("1")) {
                fightRound(dragon);
            } else {
                player.showStats();
            }
        }

        if (!player.isAlive()) { endGame(); return; }

        System.out.println("\nThe Dragon collapses with a thunderous crash!");
        eventSystem.notifyListeners("ENEMY_DEFEATED", "Dragon defeated!");
        gameManager.setGameOver(true);
        endGame();
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────

    /**
     * Runs a full combat loop against an enemy until one side is dead.
     * @param enemy the enemy to fight
     */
    private void fightEnemy(Enemy enemy) {
        System.out.println("Fighting " + enemy.getName() + "...\n");
        while (enemy.isAlive() && player.isAlive()) {
            System.out.println("  1. Attack   2. Check stats");
            String choice = getChoice("1", "2");
            if (choice.equals("1")) {
                fightRound(enemy);
            } else {
                player.showStats();
            }
        }
        if (player.isAlive() && !enemy.isAlive()) {
            System.out.println("You defeated the " + enemy.getName() + "!");
            eventSystem.notifyListeners("ENEMY_DEFEATED", enemy.getName() + " defeated.");
            gameManager.incrementScore(50);
        }
    }

    /** One round of combat: player attacks, then enemy attacks back if alive. */
    private void fightRound(Enemy enemy) {
        enemy.takeDamage(player.getAttack());
        if (enemy.isAlive()) {
            enemy.attack(player);
        }
        gameManager.incrementTurns();
    }

    /**
     * Prompts until the player enters one of the valid choices.
     * Handles invalid input gracefully.
     */
    private String getChoice(String... validChoices) {
        while (true) {
            System.out.print("> ");
            System.out.flush();
            String input = scanner.nextLine().trim();
            for (String valid : validChoices) {
                if (input.equals(valid)) return input;
            }
            System.out.println("Invalid choice. Please enter " + String.join(" or ", validChoices) + ".");
        }
    }

    private void printBanner() {
        System.out.println("=====================================");
        System.out.println("      DUNGEON EXPLORER v2.0         ");
        System.out.println("   A CS1OP Text Adventure Game      ");
        System.out.println("=====================================");
    }

    /** Prints the end screen. */
    private void endGame() {
        System.out.println("\n=====================================");
        if (!player.isAlive()) {
            eventSystem.notifyListeners("PLAYER_DIED", player.getName() + " has fallen.");
            System.out.println("         GAME OVER :(               ");
            System.out.println("  " + player.getName() + " has been defeated.");
        } else {
            System.out.println("         YOU WIN! :)                ");
            System.out.println("  " + player.getName() + " escapes the dungeon!");
        }
        System.out.println("=====================================");
        System.out.println("Final Score: " + gameManager.getScore());
        System.out.println("Turns taken: " + gameManager.getTurns());
    }
}
