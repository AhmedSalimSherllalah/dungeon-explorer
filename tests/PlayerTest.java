import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() { player = new Player("TestHero"); }

    @Test
    void testInitialHealth() { assertEquals(100, player.getHealth()); }

    @Test
    void testTakeDamage() {
        player.takeDamage(20);
        assertEquals(80, player.getHealth());
    }

    @Test
    void testHealthDoesNotGoBelowZero() {
        player.takeDamage(9999);
        assertEquals(0, player.getHealth());
        assertFalse(player.isAlive());
    }

    @Test
    void testHeal() {
        player.takeDamage(50);
        player.heal(20);
        assertEquals(70, player.getHealth());
    }

    @Test
    void testHealDoesNotExceedMax() {
        player.heal(999);
        assertEquals(player.getMaxHealth(), player.getHealth());
    }

    @Test
    void testPickUpItem() {
        player.addItem(new Weapon("Sword"));
        assertEquals(1, player.getInventory().size());
    }

    @Test
    void testUsePotion() {
        player.takeDamage(50);
        player.addItem(new Potion());
        assertTrue(player.useItem("Health Potion"));
        assertTrue(player.getInventory().isEmpty());
    }

    @Test
    void testUseNonExistentItem() {
        assertFalse(player.useItem("Magic Wand"));
    }

    @Test
    void testIncreaseAttack() {
        int initial = player.getAttack();
        player.increaseAttack(5);
        assertEquals(initial + 5, player.getAttack());
    }
}
