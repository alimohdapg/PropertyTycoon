import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * A class which represents a player, extended by HumanPlayer and ComputerPlayer.
 */
public abstract class Player {

    private final String name;
    private Token token;
    private Cash money;
    private final ArrayList<Property> properties;
    private final ArrayList<StationAndUtility> stationAndUtilities;
    private int location;
    private boolean inJail;
    private boolean hasCard;
    private int jailTurn;
    private boolean passedGo;

    @FXML
    private final Circle playerToken;
    @FXML
    private final Text playerName;
    @FXML
    private final Text playerMoney;

    /**
     * Constructs a new HumanPlayer Object
     *
     * @param name                The player's name.
     * @param token               The player's icon.
     * @param money               The player's money.
     * @param properties          The properties the player currently owns.
     * @param stationAndUtilities The stations and utilities the player currently owns.
     * @param playerToken         The GUI's representation of the player's token.
     * @param playerName          The GUI's representation of the player's name.
     * @param playerMoney         The GUI's representation of the player's money.
     */
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
        this.playerMoney.setText(("£" + money.getAmount()));
        this.jailTurn = 0;
        inJail = false;
        passedGo = false;
        hasCard = false;
    }

    /**
     * A player buys a property if one has enough cash.
     *
     * @param property the property that a player wants to buy.
     * @return True if a property is bought, false otherwise.
     */
    public boolean buyProperty(Property property) {
        if (passedGo && money.getAmount() >= property.getHouseCost() && property.getOwner() == null) {
            money.subtractAmount(property.getCost());
            this.playerMoney.setText(("£" + money.getAmount()));
            properties.add(property);
            property.setOwner(this);
            return true;
        } else {
            System.out.println("Error, the player doesn't have enough money!");
            return false;
        }
    }

    /**
     * Work in progress for the auction
     * @param property
     * @param bid
     * @return
     */
    public boolean auctionProperty(Property property, int bid) {
        money.subtractAmount(bid);
        this.playerMoney.setText(("£" + Integer.toString(money.getAmount())));
        properties.add(property);
        property.setOwner(this);
        return true;
    }

    /**
     * A player can sell an entire property, any houses or hotel will be sold, and the original price will be added back.
     *
     * @param property The property that a player wants to sell.
     */
    public void sellProperty(Property property) {
        int moneyBack = property.getCost();
        if (property.doesHaveHotel()) {
            moneyBack += property.getHouseCost() * 5;
        } else {
            moneyBack += property.getHouseCount() * property.getHouseCost();
        }
        if (!property.isUnderMortgage()) {
            money.addAmount(moneyBack);
        } else {
            money.addAmount(moneyBack / 2);
        }
        property.setOwner(null);
        properties.remove(property);
    }

    /**
     * A player can buy a single house/hotel from a property.
     *
     * @param property The property where player wants to buy a house.
     * @return True if a house is bought, false otherwise.
     */
    public boolean buyAHouse(Property property) {
        if (money.getAmount() > property.getHouseCost()) {
            if (property.getHouseCost() < 4) {
                property.buyHouse();
            } else {
                property.buyHotel();
            }
            money.subtractAmount(property.getHouseCost());
            System.out.println("Bought house on " + property.getName());
            return true;
        } else {
            System.out.println("Error, the player doesn't have enough money!");
            return false;
        }
    }

    /**
     * Returns whether the player has a card.
     *
     * @return True if the player has a card, false otherwise.
     */
    public boolean isHasCard() {
        return hasCard;
    }

    /**
     * Sets the boolean value of having a card.
     *
     * @param hasCard The boolean value to set the hasCard field to.
     */
    public void setHasCard(boolean hasCard) {
        this.hasCard = hasCard;
    }

    /**
     * A player can sell a single house from a property.
     *
     * @param property the property where a player wants to sell a house.
     */
    public void sellAHouse(Property property) {
        if (property.getHouseCount() > 0) {
            money.addAmount(property.getHouseCost());
            property.setHouseCount(property.getHouseCount() - 1);
        } else {
            System.out.println("Error, the property doesn't have a house now!");
        }
    }

    /**
     * A player can sell a single hotel from a property.
     *
     * @param property a property where a player wants to sell a hotel.
     */
    public void sellAHotel(Property property) {
        if (property.doesHaveHotel()) {
            money.addAmount(property.getHouseCost() * 5);
            property.sellHotel();
        } else {
            System.out.println("Error, the property doesn't have a hotel now!");
        }
    }

    /**
     * A player can buy a station or utility if one has enough cash.
     *
     * @param stationAndUtility A station/utility that a player wants to buy.
     * @return True of the station or utility is bought, false otherwise.
     */
    public boolean buyStaUti(StationAndUtility stationAndUtility) {
        if (money.getAmount() >= stationAndUtility.getCost() && stationAndUtility.getOwner() == null) {
            money.subtractAmount(stationAndUtility.getCost());
            stationAndUtilities.add(stationAndUtility);
            return true;
        } else {
            System.out.println("Error, the player doesn't have enough money!");
            return false;
        }
    }

    /**
     * A player can sell a station or utility.
     *
     * @param stationAndUtility A station/utility that a player wants to sell.
     */
    public void sellStaUti(StationAndUtility stationAndUtility) {
        if (stationAndUtilities.size() > 0) {
            if (!stationAndUtility.isUnderMortgage()) {
                money.addAmount(stationAndUtility.getCost());
            } else {
                money.addAmount(stationAndUtility.getCost() / 2);
            }
            stationAndUtility.setOwner(null);
            stationAndUtilities.remove(stationAndUtility);
        } else {
            System.out.println("Error, the StaUti ArrayList is empty!");
        }
    }

    /**
     * Get player's name.
     *
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get player's token.
     *
     * @return The player's token.
     */
    public Token getToken() {
        return token;
    }

    /**
     * Set player's token.
     *
     * @param token The player's token.
     */
    public void setToken(Token token) {
        this.token = token;
    }

    /**
     * Get player's cash.
     *
     * @return The player's cash.
     */
    public Cash getMoney() {
        return money;
    }

    /**
     * Set player's cash.
     *
     * @param money The player's cash.
     */
    public void setMoney(Cash money) {
        this.money = money;
    }

    /**
     * Get player's properties.
     *
     * @return The properties ArrayList.
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * Get player's Station and Utilities.
     *
     * @return The StationAndUtilities ArrayList
     */
    public ArrayList<StationAndUtility> getStationAndUtilities() {
        return stationAndUtilities;
    }

    /**
     * Retrieve player's current location on the game board.
     *
     * @return The player's location.
     */
    public int getLocation() {
        return location;
    }

    /**
     * Sets the location value to the new passed value.
     *
     * @param location The player's new location.
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Get player token on board.
     *
     * @return The player's token on the board.
     */
    public Circle getBoardToken() {
        return playerToken;
    }

    /**
     * Get player name on board.
     *
     * @return The player's name on the board.
     */
    public Text getPlayerName() {
        return playerName;
    }

    /**
     * Get player money on board.
     *
     * @return The player's money on the board.
     */
    public Text getPlayerMoney() {
        return playerMoney;
    }

    /**
     * Get player's current status.
     *
     * @return Whether the player in Jail or not.
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * Puts the player in jail.
     */
    public void setInJail() {
        setJailTurn(3);
        inJail = true;
    }

    /**
     * Removes the player from jail.
     */
    public void setOutJail() {
        setJailTurn(0);
        inJail = false;
        setLocation(10);
    }

    /**
     * Returns the value of the jailTurn field.
     *
     * @return The value of the jailTurn field
     */
    public int getJailTurn() {
        return jailTurn;
    }

    /**
     * Sets the value of the jailTurn field.
     *
     * @param jailTurn The new value of the jailTurn field.
     */
    public void setJailTurn(int jailTurn) {
        this.jailTurn = jailTurn;
    }

    /**
     * Get player's current status.
     *
     * @return Whether the player has completed at least one round.
     */
    public boolean isPassedGo() {
        return passedGo;
    }

    /**
     * Sets player's current status.
     *
     * @param passedGo True for "player has completed at least one round".
     */
    public void setPassedGo(boolean passedGo) {
        this.passedGo = passedGo;
    }

    /**
     * Updates the GUI element corresponding to money.
     */
    public void updateMoney() {
        this.playerMoney.setText(("£" + money.getAmount()));
    }

    /**
     * Mortgage an owned property which is not currently under mortgage.
     *
     * @param property The property to be mortgaged.
     */
    public void mortgageProperty(Property property) {
        if (properties.contains(property) && !property.isUnderMortgage()) {
            int value = property.getCost() + property.getHouseCount() * property.getHouseCost();
            money.addAmount(value / 2);
            property.setUnderMortgage(true);
        }
    }

    /**
     * Mortgage an owned stationAndUtility which is not currently under mortgage.
     *
     * @param stationAndUtility The station or utility to be mortgaged.
     */
    public void mortgageStationAndUtility(StationAndUtility stationAndUtility) {
        if (stationAndUtilities.contains(stationAndUtility) && !stationAndUtility.isUnderMortgage()) {
            money.addAmount(stationAndUtility.getCost() / 2);
            stationAndUtility.setUnderMortgage(true);
        }
    }
}
