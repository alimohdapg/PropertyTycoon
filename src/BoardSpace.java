/**
 * A board space class representing the spaces on the board.
 */
public abstract class BoardSpace {

    private String name;

    /**
     * Generate a new BoardSpace object.
     *
     * @param name The BoardSpace's name
     */
    public BoardSpace(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the BoardSpace.
     *
     * @return The BoardSpace's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the BoardSpace.
     *
     * @param name The new name of the BoardSpace.
     */
    public void setName(String name) {
        this.name = name;
    }
}
