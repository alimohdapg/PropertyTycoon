/**
 * A class representing stations and utilities on the board.
 *
 * @author Ali Ahmed
 */
public class StationAndUtility extends BoardSpace {

    private final int cost;
    private final Color color;

    /**
     * Constructs a new station and utility object.
     *
     * @param name The station or utility's name.
     * @param cost The cost of buying the station or utility.
     */
    public StationAndUtility(String name, int cost, Color color) {
        super(name);
        this.cost = cost;
        this.color = color;
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
    public Color getColor() {
        return color;
    }

    /**
     * Get rent of station
     *
     * @param num sta owned (the return of GameBoard.checkNumStaUti)
     * @return rent
     */
    public int getStationRent(int num) {

        if (num == 2) {
            return 50;
        }
        else if (num == 3) {
            return 100;
        }
        else if (num == 4) {
            return 200;
        }


        return 25;
    }

    /**
     * Get rent of utility
     *
     * @param num  uti owned (the return of GameBoard.checkNumStaUti)
     * @param dice dice num rolled
     * @return rent
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


}
