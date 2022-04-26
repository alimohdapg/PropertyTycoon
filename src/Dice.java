import java.util.Random;

/**
 * This class represents a single die,
 * a die can be thrown to get a number from 1 to 6
 *
 * @author Hanzhen Gong
 */

public class Dice {
    private int number;
    private final Random random;

    /**
     * Constructs a new (single) Dice object.
     */
    public Dice() {
        number = 0;
        random = new Random();
    }

    /**
     * Rolls the dice and stores the result in the number field.
     *
     * @return The result of rolling the dice.
     */
    public int rollDice() {
        number = random.nextInt(6) + 1;
        return number;
    }

    /**
     * Returns the number field which stores the result of the last dice roll.
     *
     * @return The number field.
     */
    public int getNumber() {
        return number;
    }
}