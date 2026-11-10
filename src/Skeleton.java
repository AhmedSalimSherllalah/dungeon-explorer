/** A medium skeleton enemy. */
public class Skeleton extends Enemy {
    public Skeleton() { super("Skeleton", 55); }

    @Override
    public void attack(Player player) {
        System.out.println("The Skeleton rattles its bones and swings a rusty sword at you!");
        player.takeDamage(15);
    }
}
