import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyFactoryTest {

    @Test
    void testCreateGoblin() {
        Enemy e = EnemyFactory.createEnemy("goblin");
        assertInstanceOf(Goblin.class, e);
        assertEquals("Goblin", e.getName());
    }

    @Test
    void testCreateSkeleton() {
        Enemy e = EnemyFactory.createEnemy("skeleton");
        assertInstanceOf(Skeleton.class, e);
    }

    @Test
    void testCreateDragon() {
        Enemy e = EnemyFactory.createEnemy("dragon");
        assertInstanceOf(Dragon.class, e);
    }

    @Test
    void testCaseInsensitive() {
        assertInstanceOf(Goblin.class, EnemyFactory.createEnemy("GOBLIN"));
    }

    @Test
    void testDragonStrongerThanGoblin() {
        assertTrue(EnemyFactory.createEnemy("dragon").getHealth() >
                   EnemyFactory.createEnemy("goblin").getHealth());
    }

    @Test
    void testUnknownTypeThrows() {
        assertThrows(IllegalArgumentException.class, () -> EnemyFactory.createEnemy("wizard"));
    }
}
