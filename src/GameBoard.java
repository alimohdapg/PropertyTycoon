import java.util.*;

/**
 * A gameboard class representing and tracking the game's state.
 *
 * @author Ali Ahmed & Hanzhen Gong
 */
public class GameBoard {

    private final Player[] players;
    private final Stack<Player> playerTurns;
    private final ArrayList<BoardSpace> boardSpaces;
    private boolean currentPlayerInJail;
    private final Dice dice1;
    private final Dice dice2;
    private final FileIO fileIO;
    private int samePlayerCounter;
    private Player currentPlayer;
    private int freeParkingSum;
    private boolean paidFine;

    /**
     * Constructs a new Gameboard object.
     *
     * @param players An array consisting of the players who will be playing the game.
     */
    public GameBoard(Player[] players) {
        this.players = players;
        playerTurns = new Stack<>();
        List<Player> playersList = Arrays.asList(players);
        Collections.reverse(playersList);
        playerTurns.addAll(playersList);
        this.boardSpaces = new ArrayList<>();
        currentPlayerInJail = false;
        dice1 = new Dice();
        dice2 = new Dice();
        fileIO = new FileIO();
        fillBoardSpaces();
        paidFine = false;
    }

    /**
     * Fills the boardSpaces ArrayList with default board-spaces, stations, utilities, properties, and jail.
     **/
    private void fillBoardSpaces() {
        for (int i = 0; i < fileIO.BoardData.size(); i++) {
            if (fileIO.BoardData.get(i).get(5).equals("No")) {
                boardSpaces.add(new Default(fileIO.BoardData.get(i).get(2)));
            } else if (fileIO.BoardData.get(i).get(3).equals("Station") || fileIO.BoardData.get(i).get(3).equals("Utilities")) {
                boardSpaces.add(new StationAndUtility(fileIO.BoardData.get(i).get(1),
                        Integer.parseInt(fileIO.BoardData.get(i).get(7)),
                        Color.findAndCreateColor(fileIO.BoardData.get(i).get(2))
                ));
            } else {
                boardSpaces.add(new Property(fileIO.BoardData.get(i).get(1),
                        Color.findAndCreateColor(fileIO.BoardData.get(i).get(2)),
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
        boardSpaces.add(new Jail("Jail"));
    }

    /**
     * The next player will throw the dice and go to the corresponding location;
     * however, if the player ends up with three doubles in a row, the player will be sent to the jail.
     * <p>
     * Updated by Hanzhen Gong
     */
    public void update() {
        //goToNextTurn(); moved to new function
        /**
        if (currentPlayer != null && currentPlayer == playerTurns.peek()) {
            samePlayerCounter++;
        }
        currentPlayer = playerTurns.pop();
        if (playerTurns.isEmpty()) {
            playerTurns.addAll(Arrays.asList(players));
        }
        // if player is in jail, and it's their turn then present the opportunity to get out of jail either by paying
        // a fine or by using the card.
        if (checkInJail(currentPlayer)){
            // TODO make frontend ask player to either use card or pay fine or stay in jail
        }**/
        // roll dice
        int num1 = dice1.rollDice();
        int num2 = dice2.rollDice();
        // Initial pass of go
        if ((currentPlayer).getLocation() + dice1.getNumber() + dice2.getNumber() > 39){
            if (!(currentPlayer).isPassedGo()){
                (currentPlayer).setPassedGo(true);
            }
            currentPlayer.getMoney().addAmount(200);
        }
        (currentPlayer).setLocation(
                ((currentPlayer).getLocation() + dice1.getNumber() + dice2.getNumber()) % 40
        );
        if (dice1.getNumber() == dice2.getNumber()) {
            playerTurns.push(currentPlayer);
        } else {
            samePlayerCounter = 0;
        }
        // Go to jail same dice three times
        if (samePlayerCounter == 2) {
            (currentPlayer).setLocation(40);
        }
        // Go to jail board-space
        if (((currentPlayer).getLocation()) == 30) {
            (currentPlayer).setLocation(40);
        }
        // Square 4 income tax fine
        if (((currentPlayer).getLocation()) == 4) {
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
    }

    public void endTurn() {
        goToNextTurn();
        if (currentPlayer != null && currentPlayer == playerTurns.peek()) {
            samePlayerCounter++;
        }
        currentPlayer = playerTurns.pop();
        if (playerTurns.isEmpty()) {
            playerTurns.addAll(Arrays.asList(players));
        }
        // if player is in jail, and it's their turn then present the opportunity to get out of jail either by paying
        // a fine or by using the card.
        if (checkInJail(currentPlayer)){
            // TODO make frontend ask player to either use card or pay fine or stay in jail
        }
    }

    /**
     * Go to next turn at the beginning of each "update"
     * <p>
     * Calls the checkInJail function inside to know if the current player is in jail
     * If yes, then decrease his/her prison term, and then go to next player
     */
    private void goToNextTurn() {
        if (checkInJail(currentPlayer)) {
            // update prison term
            Jail jail = (Jail) boardSpaces.get(boardSpaces.size() - 1);
            jail.minusPrisonTerm(currentPlayer);

            // then go to next player
            playerTurns.pop();
        }
    }

    /**
     * Send prisoner to Jail
     */
    public void updateJail() {
        // TODO either pay fine or use get out of jail card before being placed in jail.
        if (checkPayFine()) {
            // probably will improve,
            // because of the problem of updating dara from backend to frontend (several times)
            ((HumanPlayer) currentPlayer).setLocation(10);
        }
    }

    /**
     * @param cur_player get current player
     * @return if the player is in Jail or not
     */
    private Boolean checkInJail(Player cur_player) {
        Jail jail = (Jail) boardSpaces.get(boardSpaces.size() - 1);
        Set<Player> p_inJail = jail.getPlayersInJail();
        return p_inJail.contains(cur_player);
    }

    /**
     * check if one wants to pay fine when he/she be sent to Jail
     *
     * @return if pay
     */
    public Boolean checkPayFine() {
        return paidFine;
    }

    /**
     * Check in order to know the rent of Station and Utility
     *
     * @param stationAndUtility s/u
     * @param owner             player
     * @return num of s/u
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
     * Need to check !!EVERY TIME!! before buying a house:
     * 1. if player own all properties in the same color set
     * 2. if the difference of num_houses in each property (same color set) < 1
     *
     * @param property prop
     * @param owner    player
     * @return if one can buy a house
     */
    public Boolean checkBuyHouse(Property property, Player owner) {
        ArrayList<Property> properties = owner.getProperties();
        ArrayList<Integer> houseCount = new ArrayList<>();
        int numProps = 0;
        boolean ownsAll = false;
        boolean diffOK = true;
        boolean canBuy = false;

        // record num of props and houses in each prop
        for (Property p : properties) {
            if (p.getColor() == property.getColor()) {
                houseCount.add(p.getHouseCount());
                numProps++;
            }
        }

        // check if one owns all props in same color
        if (property.getColor() == Color.BROWN || property.getColor() == Color.DEEPBLUE) {
            if (numProps == 2) ownsAll = true;
        } else {
            if (numProps == 3) ownsAll = true;
        }

        // check house diff < 1
        for (Integer i : houseCount) {
            if (property.getHouseCount() > i) diffOK = false;
        }

        // final check
        if (ownsAll && diffOK) canBuy = true;

        return canBuy;
    }

    // This part is considering to be deleted.
    // -------------------------------- POTLUCK PART ------------------------------------------

//    public void potLuckPlayerReceivesMoney(Player player, int amountOfMoney) {
//        Cash currentCash = player.getMoney();
//        currentCash.addAmount(amountOfMoney);
//        player.setMoney(currentCash);
//    }
//
//
//    public void potLuckPlayerSetLocation(Player player, int location) {
//
//    }
//
//    public void potLuckReceiveMoneyFromOthers(Player player, int amountOfMoney) {
//        int total = 0;
//        for (Player current : players) {
//            if (current != player) {
//                Cash currentMoney = current.getMoney();
//                currentMoney.subtractAmount(amountOfMoney);
//                player.setMoney(currentMoney);
//
//                total += amountOfMoney;
//            }
//        }
//
//        Cash playerMoney = player.getMoney();
//        playerMoney.addAmount(total);
//        player.setMoney(playerMoney);
//    }
//
//    public void potLuckGetOutOfJail(Player player) {
//
//    }

    // ------------------------------------------------------------------------------------------

    /**
     * Get sum of two dices
     *
     * @return sum
     */
    public int getDiceRollsSum() {
        return dice1.getNumber() + dice2.getNumber();
    }

    /**
     * Retrieves the current number on dice 1
     *
     * @return Number on dice 1
     */
    public int getDice1Number() {
        return dice1.getNumber();
    }

    /**
     * Retrieves the current number on dice 2
     *
     * @return Number on dice 2
     */
    public int getDice2Number() {
        return dice2.getNumber();
    }

    /**
     * Returns the current player's position
     *
     * @return Current player's position on the board
     */
    public int getCurrentPlayerPosition() {
        return ((HumanPlayer) currentPlayer).getLocation();
    }

    /**
     * Returns the current player
     *
     * @return Current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the player array used to store all the players.
     *
     * @return players array
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Return board spaces
     *
     * @return board spaces
     */
    public ArrayList<BoardSpace> getBoardSpaces() {
        return boardSpaces;
    }

    public void updateAllPlayers() {
        for(int i = 0; i < players.length; i++) {
            players[i].updateMoney();
        }
    }
}
