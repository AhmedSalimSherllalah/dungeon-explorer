/** A potion that restores player health. */
public class Potion extends Item {
    private static final int HEAL_AMOUNT = 50;

    public Potion() {
        super("Health Potion", "A magical potion that restores " + HEAL_AMOUNT + " HP - use wisely!");
    }

    @Override
    public void use(Player player) {
        player.heal(HEAL_AMOUNT);
        System.out.println("You gulp down the potion! A warm glow fills your body, restoring " + HEAL_AMOUNT + " HP!");
    }
}
