/**
 *
 * @author Leon
 */
public class Dice
{
    
    private int dice;
    private static final int dice_min = 1;
    private static final int dice_max = 7;

    /**
     * Constructor
     *
     * @param dice the value of dice
     */
    public Dice(int dice   ) {
        this.dice = dice;
    }

    /**
     * Get the value of dice
     *
     * @return the value of dice
     */
    public int getDice() {
        return dice;
    }

    /**
     * Set the value of dice
     *
     * @param dice new value of dice
     */
    public void setDice(int dice) {
        this.dice = dice;
    }
    
     /**
     * Get a random number from an interval
     * 
     * @param min the minimum value of the interval
     * @param max the maximum value of the interval
     * @return a random number
     */
    public static int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    /**
     * Roll dice and set the value of the object dice
     * 
     * @param d a dice object
     */
    public static void rollDice(Dice d) {
        //generates random integers 1 to 6
        d.setDice(randomNumber(dice_min, dice_max));
    }
}
