import java.util.ArrayList;

/**
 * A gameboard class representing and tracking the game's state.
 *
 * @author Ali Ahmed & Hanzhen Gong
 */
public class GameBoard {

    private final Player[] players;
    private final ArrayList<BoardSpace> boardSpaces;
    private int currentPlayerTurn;
    private final Dice dice1;
    private final Dice dice2;

    /**
     * Constructs a new Gameboard object.
     *
     * @param players An array consisting of the players who will be playing the game.
     */
    public GameBoard(Player[] players, ArrayList<BoardSpace> boardSpaces)
    {
        this.players = players;
        this.boardSpaces = boardSpaces;
        currentPlayerTurn = -1;
        dice1 = new Dice();
        dice2 = new Dice();
    }

    /**
     * The next player will throw the dice and go to the corresponding location;
     * however, if the player ends up with three doubles in a row, the player will be sent to the jail.
     *
     * Updated by Hanzhen Gong
     */
    public void update(){
        goToNextTurn();
        Player currentPlayer = players[currentPlayerTurn];

        //TODO: Check if the current player is in the jail

        int round  = 0;
        while (round < 3)
        {
            int num1 = dice1.rollDice();
            int num2 = dice2.rollDice();
            if (num1 != num2)
            {
                ((HumanPlayer) currentPlayer).setLocation(
                        (((HumanPlayer) currentPlayer).getLocation() + dice1.getNumber() + dice2.getNumber()) % 40
                );
                return;
            }
            round++;
        }

        ((HumanPlayer) players[currentPlayerTurn]).setLocation(40);
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

    public ArrayList<BoardSpace> getBoardSpaces() {
        return boardSpaces;
    }

    public void setCurrentPlayerTurn(int currentPlayerTurn) {
        this.currentPlayerTurn = currentPlayerTurn;
    }
}
