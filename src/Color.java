/**
 * An enum class representing different property colors.
 *
 * @author Ali Ahmed
 */
public enum Color {

    BROWN("brown"),
    BLUE("blue"),
    PURPLE("purple"),
    ORANGE("orange"),
    RED("red"),
    YELLOW("yellow"),
    GREEN("green"),
    DEEPBLUE("deep blue");

    private final String representation;

    Color(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
