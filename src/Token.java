/**
 * An enum class represents the game tokens;
 * each player is assigned a game token to move around the game board.
 *
 * @author Hanzhen Gong
 */
public enum Token
{
    BOOT("boot"),
    SMARTPHONE("smartphone"),
    SHIP("ship"),
    HATSTAND("hatstand"),
    CAT("cat"),
    IRON("iron");

    private final String representation;

    Token(String representation)
    {
        this.representation = representation;
    }

    @Override
    public String toString()
    {
        return representation;
    }
}

