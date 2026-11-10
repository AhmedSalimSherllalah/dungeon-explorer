import java.util.ArrayList;
import java.util.List;

/**
 * Represents the player character.
 * Manages health, attack, and inventory.
 */
public class Player {
    private static final int MAX_HEALTH = 100;

    private String name;
    private int health;
    private int attack;
    private List<Item> inventory;

    public Player(String name) {
        this.name = name;
        this.health = MAX_HEALTH;
        this.attack = 10;
        this.inventory = new ArrayList<>();
    }

    /** Deals damage to the player. Minimum 1 damage always applied. */
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
        System.out.println("You took " + damage + " damage! HP: " + health + "/" + MAX_HEALTH);
    }

    /** Heals the player, capped at max health. */
    public void heal(int amount) {
        health = Math.min(MAX_HEALTH, health + amount);
        System.out.println("HP: " + health + "/" + MAX_HEALTH);
    }

    /** Adds an item to inventory. */
    public void addItem(Item item) { inventory.add(item); }

    /** Uses an item by name. Returns true if found and used. */
    public boolean useItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.use(this);
                inventory.remove(item);
                return true;
            }
        }
        return false;
    }

    /** Increases attack stat. */
    public void increaseAttack(int amount) { attack += amount; }

    /** Returns true if player is alive. */
    public boolean isAlive() { return health > 0; }

    /** Prints inventory contents. */
    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Item item : inventory) System.out.println("  - " + item);
        }
    }

    /** Prints player stats. */
    public void showStats() {
        System.out.println("======= STATS =======");
        System.out.println("Name:   " + name);
        System.out.println("HP:     " + health + "/" + MAX_HEALTH);
        System.out.println("Attack: " + attack);
        System.out.println("=====================");
}
    

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttack() { return attack; }
    public int getMaxHealth() { return MAX_HEALTH; }
    public List<Item> getInventory() { return inventory; }
}
