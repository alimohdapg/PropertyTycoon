/**
 * An enum class representing different property colors.
 *
 * @author Ali Ahmed
 */
public enum ColorOfSet {

    BROWN("brown"),
    BLUE("blue"),
    PURPLE("purple"),
    ORANGE("orange"),
    RED("red"),
    YELLOW("yellow"),
    GREEN("green"),
    DEEPBLUE("deep blue"),
    STATION("station"),
    UTILITIES("utilities");

    private final String representation;

    /**
     * Constructs a new ColorOfSet object.
     *
     * @param representation The string representation of the ColorOfSet object.
     */
    ColorOfSet(String representation) {
        this.representation = representation;
    }

    /**
     * Takes as input a string and finds the corresponding enum representation that it matches, and returns that enum.
     *
     * @param color String color name to match.
     * @return A Color enum matching the input string.
     */
    public static ColorOfSet findAndCreateColor(String color) {
        color = color.toLowerCase();
        ColorOfSet[] colors = new ColorOfSet[]{BROWN, BLUE, PURPLE, ORANGE, RED, YELLOW, GREEN, DEEPBLUE, STATION, UTILITIES};
        for (ColorOfSet c : colors) {
            if (color.equals(c.representation)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Returns the string representation of the ColorOfSet object.
     *
     * @return The string representation of the ColorOfSet object.
     */
    @Override
    public String toString() {
        return representation;
    }
}
