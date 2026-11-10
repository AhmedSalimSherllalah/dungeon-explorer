import java.util.ArrayList;
import java.util.List;

// AI-ASSISTED IMPLEMENTATION
// Prompted AI to generate an Observer pattern event system for a Java text game.
// AI generated the listener interface and EventSystem class structure.
// I modified the event types to match my game and integrated it with Game.java.

/**
 * DESIGN PATTERN: Observer
 * Notifies all registered listeners when a game event fires.
 * Supported events: ENEMY_DEFEATED, ITEM_COLLECTED, PUZZLE_SOLVED, PLAYER_DIED
 */
public class EventSystem {
    private List<GameEventListener> listeners = new ArrayList<>();

    public void addListener(GameEventListener listener) { listeners.add(listener); }

    public void notifyListeners(String eventType, String message) {
        for (GameEventListener l : listeners) l.onEvent(eventType, message);
    }
}
