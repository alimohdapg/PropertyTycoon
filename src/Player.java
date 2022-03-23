import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;


public abstract class Player {

    private String name;
    private Token token;
    private Cash money;
    private ArrayList<Property> properties;

    @FXML
    private Circle playerToken;

    @FXML
    private Text playerName, playerMoney;

    public Player(String name, Token token, Cash money, ArrayList<Property> properties, Circle playerToken, Text playerName, Text playerMoney) {
        this.name = name;
        this.token = token;
        this.money = money;
        this.properties = properties;
        this.playerToken = playerToken;
        this.playerName = playerName;
        this.playerMoney = playerMoney;
        this.playerName.setText(name);
        this.playerMoney.setText(("£" + Integer.toString(money.getAmount())));
    }

    public Circle getBoardToken(){ return playerToken; }

    public Text getPlayerName() { return playerName; }
    public Text getPlayerMoney() { return playerMoney; }

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
     * A player can sell an entire property, any houses or hotel will be sold, and the original price will be added back.
     *
     * @param property the property that a player wants to sell
     */
    public void sellProperty(Property property)
    {

        int moneyBack = property.getCost();
        if (property.doesHaveHotel())
        {
            moneyBack += property.getHouseCost() * 5;
        }
        else
        {
            moneyBack += property.getHouseCount() * property.getHouseCost();
        }
        money.addAmount(moneyBack);
        properties.remove(property);
    }

    /**
     * A player can sell a single house from a property
     *
     * @param property the property where a player wants to sell a house
     */
    public void sellAHouse(Property property)
    {
        if (property.getHouseCount() > 0)
        {
            money.addAmount(property.getHouseCost());
            property.setHouseCount(property.getHouseCount() - 1);
        }
    }


    /**
     * A plater can sell a single hotel from a property
     *
     * @param property a property where a player wants to sell a hotel
     */
    public void sellAHotel(Property property)
    {
        if (property.doesHaveHotel())
        {
            money.addAmount(property.getHouseCost() * 5);
            property.sellHotel();
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
}
