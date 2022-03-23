import java.util.ArrayList;

public abstract class Player {

    private String name;
    private Token token;
    private Cash money;
    private ArrayList<Property> properties;
    private int location;

    public Player(String name, Token token, Cash money, ArrayList<Property> properties) {
        this.name = name;
        this.token = token;
        this.money = money;
        this.properties = properties;
        location = 0;
    }

    public String getName() {
        return name;
    }

    public Cash getMoney() {
        return money;
    }

    public void setMoney(Cash money) {
        this.money = money;
    }

    /**
     * A player buys a property if one has enough cash.
     *
     * @param property the property that a player wants to buy
     */
    public void buyProperty(Property property) {
        if (money.getAmount() >= property.getHouseCost())
        {
            money.subtractAmount(property.getHouseCost());
            properties.add(property);
        }
    }

    /**
     * A player can sell a property, and the original price will be added back.
     *
     * @param property the property that a player wants to sell
     */
    public void sellProperty(Property property) {
        if (properties.contains(property))
        {
            money.addAmount(property.getHouseCost());
        }
    }

    public Token getToken() {
        return token;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    /**
     * Retrieve player's current location on the game board
     *
     * @return location as int type (0 to 39)
     */
    public int getLocation() {
        return location;
    }

    /**
     * Sets the location value to the new value passed into
     *
     * @param location location as int type (0 to 39)
     */
    public void setLocation(int location) {
        this.location = location;
    }
}
