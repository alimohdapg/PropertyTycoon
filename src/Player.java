import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public abstract class Player {

    private String name;
    private Token token;
    private Cash money;
    private ArrayList<Property> properties;
    private ArrayList<StationAndUtility> stationAndUtilities;
    private int location;

    @FXML
    private Circle playerToken;

    @FXML
    private Text playerName, playerMoney;

    public Player(String name, Token token, Cash money, ArrayList<Property> properties,
                  ArrayList<StationAndUtility> stationAndUtilities, Circle playerToken,
                  Text playerName, Text playerMoney) {
        this.name = name;
        this.token = token;
        this.money = money;
        this.properties = properties;
        this.stationAndUtilities = stationAndUtilities;
        location = 0;

        this.playerToken = playerToken;
        this.playerName = playerName;
        this.playerMoney = playerMoney;
        this.playerName.setText(name);
        this.playerMoney.setText(("£" + Integer.toString(money.getAmount())));
    }

    /**
     * A player buys a property if one has enough cash.
     *
     * @param property the property that a player wants to buy
     */
    public void buyProperty(Property property) {
        if (money.getAmount() >= property.getHouseCost()){
            money.subtractAmount(property.getHouseCost());
            properties.add(property);
        } else {
            System.out.println("Error, the player doesn't have enough money!");
        }
    }

    /**
     * A player can sell an entire property, any houses or hotel will be sold, and the original price will be added back.
     *
     * @param property the property that a player wants to sell
     */
    public void sellProperty(Property property) {

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
     * A player can buy a single house/hotel from a property
     *
     * @param property the property where player wants to buy a house
     */
    public void buyAHouse(Property property) {
        if (money.getAmount() > property.getHouseCost()) {
            if  (property.getHouseCost() < 4) {
                property.buyHouse();
            } else {
                property.buyHotel();
            }
            money.subtractAmount(property.getHouseCost());
        } else {
            System.out.println("Error, the player doesn't have enough money!");
        }
    }

    /**
     * A player can sell a single house from a property
     *
     * @param property the property where a player wants to sell a house
     */
    public void sellAHouse(Property property)
    {
        if (property.getHouseCount() > 0){
            money.addAmount(property.getHouseCost());
            property.setHouseCount(property.getHouseCount() - 1);
        } else {
            System.out.println("Error, the property doesn't have a house now!");
        }
    }

    /**
     * A player can sell a single hotel from a property
     *
     * @param property a property where a player wants to sell a hotel
     */
    public void sellAHotel(Property property)
    {
        if (property.doesHaveHotel()){
            money.addAmount(property.getHouseCost() * 5);
            property.sellHotel();
        } else {
            System.out.println("Error, the property doesn't have a hotel now!");
        }
    }

    /**
     * A player can buy a station or utility if one has enough cash
     *
     * @param stationAndUtility a sta/uti that a player wants to buy
     */
    public void buyStaUti(StationAndUtility stationAndUtility) {
        if (money.getAmount() >= stationAndUtility.getCost()) {
            money.subtractAmount(stationAndUtility.getCost());
            stationAndUtilities.add(stationAndUtility);
        } else {
            System.out.println("Error, the player doesn't have enough money!");
        }
    }

    /**
     * A player can sell a station or utility
     *
     * @param stationAndUtility a sta/uti that a player wants to sell
     */
    public void sellStaUti(StationAndUtility stationAndUtility) {
        if (stationAndUtilities.size() > 0) {
            money.addAmount(stationAndUtility.getCost());
            stationAndUtilities.remove(stationAndUtility);
        } else {
            System.out.println("Error, the StaUti ArrayList is empty!");
        }
    }

    // ------------------------------------------------------------------------------------------

    /**
     * Get player's name
     *
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get player's token
     *
     * @return player's token
     */
    public Token getToken() {
        return token;
    }

    /**
     * Set player's token
     *
     * @param token player's token
     */
    public void setToken(Token token) {
        this.token = token;
    }

    /**
     * Get player's cash
     *
     * @return player's cash
     */
    public Cash getMoney() {
        return money;
    }

    /**
     * Set player's cash
     *
     * @param money player's cash
     */
    public void setMoney(Cash money) {
        this.money = money;
    }

    /**
     * Get player's properties
     *
     * @return properties ArrayList
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * Get player's Station and Utilities
     *
     * @return StaUti ArrayList
     */
    public ArrayList<StationAndUtility> getStationAndUtilities() { return stationAndUtilities; }

    /**
     * Retrieve player's current location on the game board
     *
     * @return location as int type (0 to 39)
     */
    public int getLocation() { return location; }

    /**
     * Sets the location value to the new value passed into
     *
     * @param location location as int type (0 to 39)
     */
    public void setLocation(int location) {this.location = location;}

    /**
     * Get player token on board
     *
     * @return player token on board
     */
    public Circle getBoardToken(){ return playerToken; }

    /**
     * Get player name on board
     *
     * @return player name on board
     */
    public Text getPlayerName() { return playerName; }

    /**
     * Get player money on board
     *
     * @return player money on board
     */
    public Text getPlayerMoney() { return playerMoney; }



}
