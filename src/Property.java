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

    public Property(String name, int location, Color color, int cost, int rentDefault, int rent1House, int rent2House,
                    int rent3House, int rent4House, int rentHotel) {
        super(name, location);
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

    public Color getColor() {
        return color;
    }

    public int getCost() {
        return cost;
    }

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

    public void buyHouse() {
        if (houseCount < 4) {
            houseCount++;
        }
    }

    public void buyHotel() {
        hasHotel = true;
    }

    public int getRent() {
        if (houseCount == 1) {
            return rent1House;
        } else if (houseCount == 2) {
            return rent2House;
        } else if (houseCount == 3) {
            return rent3House;
        } else if (houseCount == 4) {
            return rent4House;
        } else if (hasHotel) {
            return rentHotel;
        }
        return rentDefault;
    }
}
