/**
 * Abstract base class for all items.
 * Demonstrates abstraction - subclasses must implement use().
 */
public abstract class Item {
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void use(Player player);

    public String getName() { return name; }
    public String getDescription() { return description; }

    @Override
    public String toString() { return name + " - " + description; }
}
