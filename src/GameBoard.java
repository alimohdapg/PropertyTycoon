import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * A gameboard class representing and tracking the game's state.
 *
 * @author Ali Ahmed & Hanzhen Gong
 */
public class GameBoard {

    private final Player[] players;
    private final ArrayList<BoardSpace> boardSpaces;
    private int currentPlayerTurn;
    private boolean currentPlayerInJail;
    private final Dice dice1;
    private final Dice dice2;
    private final FileIO fileIO;
    private final Integer[][] diceRolls;

    /**
     * Constructs a new Gameboard object.
     *
     * @param players An array consisting of the players who will be playing the game.
     */
    public GameBoard(Player[] players) {
        this.players = players;
        this.boardSpaces = new ArrayList<>();
        currentPlayerTurn = -1;
        currentPlayerInJail = false;
        dice1 = new Dice();
        dice2 = new Dice();
        fileIO = new FileIO();
        diceRolls = new Integer[3][2];
        fillBoardSpaces();
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
                        Integer.parseInt(fileIO.BoardData.get(i).get(7))));
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
        goToNextTurn();
        clearDiceRolls();
        Player currentPlayer = players[currentPlayerTurn];

        //TODO: Check if the current player is in the jail
        int round = 0;
        while (round < 3) {
            // roll dice
            int num1 = dice1.rollDice();
            int num2 = dice2.rollDice();
            // store dice value & set location
            diceRolls[round][0] = dice1.getNumber();
            diceRolls[round][1] = dice2.getNumber();
            ((HumanPlayer) currentPlayer).setLocation(
                    (((HumanPlayer) currentPlayer).getLocation() + dice1.getNumber() + dice2.getNumber()) % 40
            );
            if (num1 != num2) return;
            round++;
        }
        currentPlayerInJail = true;
    }

    public void updateJail(){
        // TODO
        if (checkPayFine()) {
            // probably will improve,
            // because of the problem of updating dara from backend to frontend (several times)
            ((HumanPlayer) players[currentPlayerTurn]).setLocation(10);
        } else {
            ((HumanPlayer) players[currentPlayerTurn]).setLocation(40);
        }
        ((HumanPlayer) players[currentPlayerTurn]).setLocation(40);
    }

    

    /**
     * Go to next turn at the beginning of each "update"
     *
     * Calls the checkInJail function inside to know if the current player is in jail
     * If yes, then decrease his/her prison term, and then go to next player
     */
    private void goToNextTurn() {
        if (currentPlayerTurn == players.length - 1) {
            currentPlayerTurn = 0;
        } else {
            currentPlayerTurn++;
        }

        if (checkInJail(currentPlayerTurn)) {
            // update prison term
            Jail jail = (Jail)boardSpaces.get(boardSpaces.size()-1);
            jail.minusPrisonTerm(players[currentPlayerTurn]);

            // then go to next player
            currentPlayerTurn++;
        }
    }

    /**
     * @param cur_turn get current player turn
     * @return if the player is in Jail or not
     */
    private Boolean checkInJail (int cur_turn) {

        Player cur_player = players[cur_turn];
        Jail jail = (Jail)boardSpaces.get(boardSpaces.size()-1);
        Set<Player> p_inJail = jail.getPlayersInJail();

        return p_inJail.contains(cur_player);
    }

    private void clearDiceRolls() {
        for (Integer[] diceRoll : diceRolls){
            Arrays.fill(diceRoll, null);
        }
    }

    public Integer[][] getDiceRolls(){
        return diceRolls;
    }

    public Boolean checkPayFine() {
        return true;
    }

    public int getDiceRollsSum(){
        int sum = 0;
        for (Integer[] twoDiceRolls: getDiceRolls()){
            for (Integer diceRoll: twoDiceRolls){
                if (diceRoll != null) {
                    sum += diceRoll;
                }
            }
        }
        return sum;
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
     * Returns the current player's position.
     *
     * @return Current player's position on the board.
     */
    public int getCurrentPlayerPosition() {
        return ((HumanPlayer) players[currentPlayerTurn]).getLocation();
    }

    /**
     * Returns the current player.
     *
     * @return Current player.
     */
    public Player getCurrentPlayer() {
        return players[currentPlayerTurn];
    }

    /**
     * Returns the player array used to store all the players.
     *
     * @return players array.
     */
    public Player[] getPlayers() {
        return players;
    }

    public ArrayList<BoardSpace> getBoardSpaces() {
        return boardSpaces;
    }

    public void setCurrentPlayerTurn(int currentPlayerTurn) {
        this.currentPlayerTurn = currentPlayerTurn;
    }





    public void potLuckPlayerReceivesMoney(Player player, int amountOfMoney)
    {
        Cash currentCash = player.getMoney();
        currentCash.addAmount(amountOfMoney);
        player.setMoney(currentCash);
    }

    //TODO: Set player to a specific location
    public void potLuckPlayerSetLocation(Player player, int location)
    {

    }
}
