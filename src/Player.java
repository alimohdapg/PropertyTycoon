import java.util.ArrayList;

public abstract class Player {

    private Token token;
    private Cash money;
    private ArrayList<Property> properties;

    public Player(Token token, Cash money, ArrayList<Property> properties) {
        this.token = token;
        this.money = money;
        this.properties = properties;
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

    public void setToken(Token token) {
        this.token = token;
    }
}
