/** A weapon that boosts player attack. */
public class Weapon extends Item {
    private static final int ATTACK_BONUS = 7;

    public Weapon(String name) {
        super(name, "Boosts attack by " + ATTACK_BONUS);
    }

    @Override
    public void use(Player player) {
        player.increaseAttack(ATTACK_BONUS);
        System.out.println("You equipped " + getName() + "! It feels powerful in your hands. Attack +" + ATTACK_BONUS);
    }
}
