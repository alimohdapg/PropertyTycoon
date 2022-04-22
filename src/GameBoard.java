import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * A class representing and tracking the game's board and state.
 *
 * @author Ali Ahmed, Hanzhen Gong, Leon Feng, Ekin Zorel, Kieran Young
 */
public class GameBoard {

    private final ArrayList<Player> players;
    private final Stack<Player> playerTurns;
    private final ArrayList<BoardSpace> boardSpaces;
    private final Dice dice1;
    private final Dice dice2;
    private final FileIO fileIO;
    private int samePlayerCounter;
    private Player currentPlayer;
    private int freeParkingSum;

    /**
     * Constructs a new GameBoard object.
     *
     * @param players An array consisting of the players who will be playing the game.
     */
    public GameBoard(ArrayList<Player> players) {
        this.players = players;
        playerTurns = new Stack<>();
        Collections.reverse(players);
        for (int i = 0; i < 100000; i++) {
            playerTurns.addAll(players);
        }
        this.boardSpaces = new ArrayList<>();
        dice1 = new Dice();
        dice2 = new Dice();
        fileIO = new FileIO();
        fillBoardSpaces();
        freeParkingSum = 0;
    }

    /**
     * Fills the boardSpaces ArrayList with default board-spaces, stations, utilities, properties, and jail.
     **/
    private void fillBoardSpaces() {
        for (int i = 0; i < fileIO.BoardData.size(); i++) {
            if (fileIO.BoardData.get(i).get(5).equals("No")) {
                boardSpaces.add(new Default(fileIO.BoardData.get(i).get(2)));
            } else if (fileIO.BoardData.get(i).get(3).equals("Station") ||
                    fileIO.BoardData.get(i).get(3).equals("Utilities")) {
                boardSpaces.add(new StationAndUtility(fileIO.BoardData.get(i).get(1),
                        Integer.parseInt(fileIO.BoardData.get(i).get(7)),
                        ColorOfSet.findAndCreateColor(fileIO.BoardData.get(i).get(3))
                ));
            } else {
                boardSpaces.add(new Property(fileIO.BoardData.get(i).get(1),
                        ColorOfSet.findAndCreateColor(fileIO.BoardData.get(i).get(3)),
                        Integer.parseInt(fileIO.BoardData.get(i).get(7)),
                        Integer.parseInt(fileIO.BoardData.get(i).get(8)),
                        Integer.parseInt(fileIO.BoardData.get(i).get(10)),
                        Integer.parseInt(fileIO.BoardData.get(i).get(11)),
                        Integer.parseInt(fileIO.BoardData.get(i).get(12)),
                        Integer.parseInt(fileIO.BoardData.get(i).get(13)),
                        Integer.parseInt(fileIO.BoardData.get(i).get(14))
                ));
            }
        }
        boardSpaces.add(new Default("jail"));
    }

    /**
     * The player throws the dice and goes to the corresponding location.
     */
    public void update() {
        // Roll dice
        dice1.rollDice();
        dice2.rollDice();
        if (currentPlayer.isInJail()) {
            currentPlayer.setJailTurn(currentPlayer.getJailTurn() - 1);
            if (currentPlayer.getJailTurn() <= 0) {
                currentPlayer.setOutJail();
            }
        }
        // Initial pass of go
        if (currentPlayer.getLocation() + dice1.getNumber() + dice2.getNumber() > 39) {
            if (!currentPlayer.isPassedGo()) {
                currentPlayer.setPassedGo(true);
            }
            currentPlayer.getMoney().addAmount(200);
        }
        if (!currentPlayer.isInJail()) {
            currentPlayer.setLocation(
                    (currentPlayer.getLocation() + dice1.getNumber() + dice2.getNumber()) % 40
            );
        }
        // Checks if a double has been rolled
        if (dice1.getNumber() == dice2.getNumber()) {
            playerTurns.push(currentPlayer);
        } else {
            samePlayerCounter = 0;
        }
        // Go to jail if doubles have been rolled three times in a row
        if (samePlayerCounter == 2) {
            currentPlayer.setLocation(40);
            currentPlayer.setInJail();
        }
        // Go to jail board-space
        if ((currentPlayer.getLocation()) == 30) {
            currentPlayer.setLocation(40);
            currentPlayer.setInJail();
        }
        // Square 4 income tax fine
        if ((currentPlayer.getLocation()) == 4) {
            currentPlayer.getMoney().subtractAmount(200);
            freeParkingSum += 200;
        }
        // Square 38 income/super tax fine
        if (((currentPlayer).getLocation()) == 38) {
            currentPlayer.getMoney().subtractAmount(100);
            freeParkingSum += 100;
        }
        // Free parking board-space get the money and set the sum to 0
        if (((currentPlayer).getLocation()) == 20) {
            currentPlayer.getMoney().addAmount(freeParkingSum);
            freeParkingSum = 0;
        }
        // Paying rent, getting the rent paid
        if (boardSpaces.get(currentPlayer.getLocation()) instanceof Property &&
                ((Property) boardSpaces.get(currentPlayer.getLocation())).getOwner() != null) {
            Property propertyLandedOn = (Property) boardSpaces.get(currentPlayer.getLocation());
            currentPlayer.getMoney().subtractAmount(propertyLandedOn.getRent());
            propertyLandedOn.getOwner().getMoney().addAmount(propertyLandedOn.getRent());

        }
    }

    /**
     * Increases the samePlayerCounter if the next player is the same one as the current player.
     * If the player is bankrupt it removes them from the players list.
     * Ensures that the next player is still in the game (not bankrupt).
     */
    public void endTurn() {
        if (currentPlayer != null && currentPlayer == playerTurns.peek()) {
            samePlayerCounter++;
        }
        do {
            currentPlayer = playerTurns.pop();
        } while (!players.contains(currentPlayer));
        if (currentPlayer.getMoney().getAmount() < 0) {
            players.remove(currentPlayer);
        }
        while (!players.contains(currentPlayer)) {
            currentPlayer = playerTurns.pop();
        }
    }

    /**
     * Check in order to know the rent of the Station or Utility.
     *
     * @param stationAndUtility Station or utility to be checked.
     * @param owner             The owner of the station or utility.
     * @return The number of station or utilities in the same set.
     */
    public int checkNumStaUti(StationAndUtility stationAndUtility, Player owner) {
        ArrayList<StationAndUtility> stationAndUtilities = owner.getStationAndUtilities();
        int num = 0;
        for (StationAndUtility su : stationAndUtilities) {
            if (su.getColor() == stationAndUtility.getColor()) num++;
        }
        return num;
    }

    /**
     * Checks if tne player can buy a house on the given property.
     *
     * @param property Property that the house will be built.
     * @param owner    The property's owner.
     * @return True if a house can be bought.
     */
    public Boolean checkBuyHouse(Property property, Player owner) {
        ArrayList<Property> properties = owner.getProperties();
        ArrayList<Integer> houseCount = new ArrayList<>();
        int numProps = 0;
        boolean ownsAll = false;
        boolean diffOK = true;
        boolean canBuy = false;
        // Record num of props and houses in each prop
        for (Property p : properties) {
            if (p.getColor() == property.getColor()) {
                houseCount.add(p.getHouseCount());
                numProps++;
            }
        }
        // Check if the owner owns all properties of the same color
        if (property.getColor() == ColorOfSet.BROWN || property.getColor() == ColorOfSet.DEEPBLUE) {
            if (numProps == 2) ownsAll = true;
        } else {
            if (numProps == 3) ownsAll = true;
        }
        // Check if the house difference is < 1
        for (Integer i : houseCount) {
            if (property.getHouseCount() > i) {
                diffOK = false;
                break;
            }
        }
        // Final check
        if (ownsAll && diffOK) canBuy = true;
        return canBuy;
    }

    /**
     * Get the sum of the two dice.
     *
     * @return Sum of the two dice.
     */
    public int getDiceRollsSum() {
        return dice1.getNumber() + dice2.getNumber();
    }

    /**
     * Retrieves the current number on dice 1.
     *
     * @return Number on dice 1.
     */
    public int getDice1Number() {
        return dice1.getNumber();
    }

    /**
     * Retrieves the current number on dice 2.
     *
     * @return Number on dice 2.
     */
    public int getDice2Number() {
        return dice2.getNumber();
    }

    /**
     * Returns the current player's position on the board.
     *
     * @return The current player's position on the board.
     */
    public int getCurrentPlayerPosition() {
        return currentPlayer.getLocation();
    }

    /**
     * Returns the current player.
     *
     * @return Current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the player array used to store all the players.
     *
     * @return Players array.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the board spaces arraylist.
     *
     * @return The board spaces arraylist.
     */
    public ArrayList<BoardSpace> getBoardSpaces() {
        return boardSpaces;
    }

    /**
     * Updates the GUI elements showing each player's money.
     */
    public void updateAllPlayers() {
        for (Player player : players) {
            player.updateMoney();
        }
    }

    /**
     * Returns the freeParkingSum field.
     *
     * @return The freeParkingSum field
     */
    public int getFreeParkingSum() {
        return freeParkingSum;
    }

    public void addToFreeParking(int amount) { freeParkingSum += amount; }
}
