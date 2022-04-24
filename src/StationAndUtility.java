/**
 * A class representing stations and utilities on the board.
 *
 * @author Ali Ahmed {@literal &} Hanzhen Gong
 */
public class StationAndUtility extends BoardSpace {

    private final int cost;
    private boolean underMortgage;
    private final ColorOfSet color;
    private Player owner;

    /**
     * Constructs a new station and utility object.
     *
     * @param name  The station or utility's name.
     * @param cost  The cost of buying the station or utility.
     * @param color The ColorOfSet of this station or utility.
     */
    public StationAndUtility(String name, int cost, ColorOfSet color) {
        super(name);
        this.cost = cost;
        this.color = color;
        underMortgage = false;
        owner = null;
    }

    /**
     * Returns the cost of buying the station or utility.
     *
     * @return Cost of buying the station or utility.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Returns the color of the sta/uti.
     *
     * @return Color of the sta/uti.
     */
    public ColorOfSet getColor() {
        return color;
    }

    /**
     * Get the owner.
     *
     * @return The owner of the station or utility.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Set the owner.
     *
     * @param owner The new owner of the station or utility.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Get rent of station.
     *
     * @param num Number of stations owned (the return of GameBoard.checkNumStaUti).
     * @return Rent value for the station.
     */
    public int getStationRent(int num) {
        if (num == 2) {
            return 50;
        } else if (num == 3) {
            return 100;
        } else if (num == 4) {
            return 200;
        }
        return 25;
    }

    /**
     * Get rent of utility.
     *
     * @param num  Number of utilities owned (the return of GameBoard.checkNumStaUti).
     * @param dice Dice number rolled.
     * @return Rent value for the utility.
     */
    public int getUtilityRent(int num, int dice) {
        switch (num) {
            case 1:
                return dice * 4;
            case 2:
                return dice * 10;
            default:
                return -999999;
        }
    }

    /**
     * Returns whether the station or utility is under mortgage.
     *
     * @return True if this station or utility is under mortgage, false otherwise.
     */
    public boolean isUnderMortgage() {
        return underMortgage;
    }

    /**
     * Sets the underMortgage field to the boolean value given.
     *
     * @param underMortgage The new underMortgage boolean value.
     */
    public void setUnderMortgage(boolean underMortgage) {
        this.underMortgage = underMortgage;
    }
}
