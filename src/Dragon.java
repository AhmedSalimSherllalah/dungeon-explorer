/** The Dragon final boss. */
public class Dragon extends Enemy {
    public Dragon() { super("Dragon", 100); }

    @Override
    public void attack(Player player) {
        System.out.println("The Dragon lets out a deafening roar and BREATHES FIRE at you!");
        player.takeDamage(25);
    }
}
