/**
 * A cash class representing the cash the bank/each player holds.
 *
 * @author Ali Ahmed
 */
public class Cash {

    private int amount;

    /**
     * Constructs a new Cash object with the passed amount.
     *
     * @param amount The amount of cash used to construct the object.
     */
    public Cash(int amount){
        this.amount = amount;
    }

    /**
     * Retrieves the value of the amount instance variable.
     *
     * @return The amount instance variable.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount instance variable to the new amount passed.
     *
     * @param amount New amount value.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Sets the amount instance variable to the original amount + the new amount passed.
     *
     * @param amount The value to increase the amount instance variable by.
     */
    public void addAmount(int amount) {
        this.amount += amount;
    }

    /**
     * Sets the amount instance variable to the original amount - the new amount passed.
     *
     * @param amount The value to decrease the amount instance variable by.
     */
    public void subtractAmount(int amount) {
        this.amount -= amount;
    }
}
