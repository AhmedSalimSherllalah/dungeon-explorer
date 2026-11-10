/** A weak goblin enemy. */
public class Goblin extends Enemy {
    public Goblin() { super("Goblin", 30); }

    @Override
    public void attack(Player player) {
        System.out.println("The Goblin scratches you!");
        player.takeDamage(10);
    }
}
