import java.util.Random;

/**
 * @author Hanzhen Gong
 */

public class Dice {

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