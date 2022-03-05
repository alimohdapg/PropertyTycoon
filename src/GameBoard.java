/**
 * A gameboard class representing and tracking the game's state.
 *
 * @author Ali Ahmed
 */
public class GameBoard {

    private final Player[] players;
    private int currentPlayerTurn;
    private final Dice dice1;
    private final Dice dice2;

    /**
     * Constructs a new Gameboard object.
     *
     * @param players An array consisting of the players who will be playing the game.
     */
    public GameBoard(Player[] players){
        this.players = players;
        currentPlayerTurn = -1;
        dice1 = new Dice();
        dice2 = new Dice();
    }

    /**
     * Updates the current game state.
     */
    public void update(){
        goToNextTurn();
        dice1.rollDice();
        dice2.rollDice();
        ((HumanPlayer) players[currentPlayerTurn]).setLocation(
                (((HumanPlayer) players[currentPlayerTurn]).getLocation() + dice1.getNumber() + dice2.getNumber()) % 40
        );
    }

    private void goToNextTurn(){
        if (currentPlayerTurn == players.length - 1){
            currentPlayerTurn = 0;
        } else {
            currentPlayerTurn++;
        }
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
    public int getCurrentPlayerPosition(){
        return ((HumanPlayer) players[currentPlayerTurn]).getLocation();
    }

    /**
     * Returns the current player.
     *
     * @return Current player.
     */
    public Player getCurrentPlayer(){
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
}
