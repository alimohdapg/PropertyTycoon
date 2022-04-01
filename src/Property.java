/**
 * A class representing properties on the board.
 *
 * @author Ali Ahmed & Hanzhen Gong
 */
public class Property extends BoardSpace {

    private final Color color;
    private final int cost;
    private final int rentDefault;
    private final int rent1House;
    private final int rent2House;
    private final int rent3House;
    private final int rent4House;
    private final int rentHotel;
    private int houseCount;
    private boolean hasHotel;

    /**
     * Constructs a new property object.
     *
     * @param name        The property's name.
     * @param color       The property's color.
     * @param cost        The cost of buying the property.
     * @param rentDefault The default rent when the property has no houses or hotels built.
     * @param rent1House  The rent when the property has one house.
     * @param rent2House  The rent when the property has two houses.
     * @param rent3House  The rent when the property has three houses.
     * @param rent4House  The rent when the property has four houses.
     * @param rentHotel   The rent when the property has a hotel.
     */
    public Property(String name, Color color, int cost, int rentDefault, int rent1House, int rent2House, int rent3House,
                    int rent4House, int rentHotel) {
        super(name);
        this.color = color;
        this.cost = cost;
        this.rentDefault = rentDefault;
        this.rent1House = rent1House;
        this.rent2House = rent2House;
        this.rent3House = rent3House;
        this.rent4House = rent4House;
        this.rentHotel = rentHotel;
        houseCount = 0;
        hasHotel = false;
    }

    /**
     * Returns the color of the property.
     *
     * @return Color of the property.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the cost of buying the property.
     *
     * @return Cost of buying the property.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Computes the cost of buying a house depending on the color of the property and returns it.
     *
     * @return Cost of buying a house.
     */
    public int getHouseCost() {
        if (color == Color.BROWN || color == Color.BLUE) {
            return 50;
        } else if (color == Color.PURPLE || color == Color.ORANGE) {
            return 100;
        } else if (color == Color.RED || color == Color.YELLOW) {
            return 150;
        } else if (color == Color.GREEN || color == Color.DEEPBLUE) {
            return 200;
        }
        return -1;
    }

    /**
     * If the number of houses is less than 4, this function buys a house by increasing the houseCount field by 1.
     */
    public void buyHouse() {
        if (houseCount < 4) {
            houseCount++;
        }
    }

    /**
     * Buys a hotel by setting the hasHotel field to true.
     */
    public void buyHotel() {
        if (houseCount == 4) {
            hasHotel = true;
            houseCount++;
        } else {
            System.out.println("Error, number of houses exceeds normal!");
        }
    }

    /**
     * Computes the rent of the property depending on how many houses it has or if it has a hotel.
     *
     * @return Rent associated with the property.
     */
    public int getRent() {
        if (hasHotel) {
            return rentHotel;
        } else if (houseCount == 1) {
            return rent1House;
        } else if (houseCount == 2) {
            return rent2House;
        } else if (houseCount == 3) {
            return rent3House;
        } else if (houseCount == 4) {
            return rent4House;
        }
        return rentDefault;
    }

    /**
     * Sets the houseCount field to the given variable.
     *
     * @param houseCount Number of houses to set the houseCount variable to.
     */
    public void setHouseCount(int houseCount) {
        this.houseCount = houseCount;
    }

    /**
     * Returns the number of houses the property currently has.
     *
     * @return The number of houses the property currently has.
     */
    public int getHouseCount() {
        return houseCount;
    }

    /**
     * Returns whether this property has a hotel.
     *
     * @return Value of the hasHotel field.
     */
    public boolean doesHaveHotel() {
        return hasHotel;
    }

    /**
     * Set hasHotel as false
     */
    public void sellHotel()
    {
        hasHotel = false;
    }
}
