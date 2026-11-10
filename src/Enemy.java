/**
 * Abstract base class for all enemies.
 * Each subclass overrides attack() with unique behaviour (polymorphism).
 */
public abstract class Enemy {
    private String name;
    private int health;
    private int maxHealth;

    public Enemy(String name, int health) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
    }

    /** Subclasses implement their own attack style. */
    public abstract void attack(Player player);

    /** Reduces enemy health by the given damage. */
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
        System.out.println(name + " took " + damage + " damage! HP: " + health + "/" + maxHealth);
    }

    public boolean isAlive() { return health > 0; }
    public String getName() { return name; }
    public int getHealth() { return health; }
}
