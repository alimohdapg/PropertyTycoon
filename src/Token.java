/**
 * An enum class representing the game tokens;
 * each player is assigned a game token to move around the game board.
 *
 * @author Hanzhen Gong
 */
public enum Token {

    BOOT("boot"),
    SMARTPHONE("smartphone"),
    SHIP("ship"),
    HATSTAND("hatstand"),
    CAT("cat"),
    IRON("iron");

    private final String representation;

    /**
     * Constructs a new Token object.
     *
     * @param representation The string representation of the Token object.
     */
    Token(String representation) {
        this.representation = representation;
    }

    /**
     * Returns the string representation of the Token object.
     *
     * @return The string representation of the Token object.
     */
    @Override
    public String toString() {
        return representation;
    }
}

