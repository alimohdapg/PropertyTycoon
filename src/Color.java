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

    /**
     * Takes as input a string and finds the corresponding enum representation that it matches, and returns that enum.
     *
     * @param color String color name to match.
     * @return A Color enum matching the input string.
     */
    public static Color findAndCreateColor(String color){
        color = color.toLowerCase();
        Color[] colors = new Color[]{BROWN, BLUE, PURPLE, ORANGE, RED, YELLOW, GREEN, DEEPBLUE};
        for (Color c : colors) {
            if (color.equals(c.representation)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return representation;
    }
}
