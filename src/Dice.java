import java.util.Random;

/**
 * This class represents a single die,
 * a dice can be thrown to get a number from 1 to 6
 *
 * @author Hanzhen Gong
 */

public class Dice
{
    private int number;
    private Random random;

    public Dice() {
        number = 0;
        random = new Random();
    }

    public int rollDice() {
        number = random.nextInt(6) + 1;
        return number;
    }

    public int getNumber() {
        return number;
    }
}