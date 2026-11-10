/**
 * DESIGN PATTERN: Observer (interface)
 * Any class that wants to react to game events implements this interface.
 */
public interface GameEventListener {
    void onEvent(String eventType, String message);
}
