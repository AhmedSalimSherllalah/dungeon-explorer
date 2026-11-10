/**
 * DESIGN PATTERN: Factory
 * Creates enemy objects without exposing subclass details to the caller.
 */
public class EnemyFactory {

    /**
     * Creates an enemy by type name.
     * @param type "goblin", "skeleton", or "dragon"
     * @return a fully configured Enemy object
     */
    public static Enemy createEnemy(String type) {
        switch (type.toLowerCase()) {
            case "goblin":   return new Goblin();
            case "skeleton": return new Skeleton();
            case "dragon":   return new Dragon();
            default: throw new IllegalArgumentException("Unknown enemy: " + type);
        }
    }
}
