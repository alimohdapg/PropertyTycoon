import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class HumanPlayer extends Player {

    private int location;
    private boolean inJail;
    private boolean passedGo;

    /**
     * Constructs a new HumanPlayer Object
     *
     * @param token Player's icon
     * @param properties The property player currently have
     */
    public HumanPlayer(String name, Token token, ArrayList<Property> properties, Circle playerToken, Text playerName, Text playerMoney) {
        super(name, token, new Cash(1500), properties, playerToken, playerName, playerMoney);
        location = 0;
        inJail = false;
        passedGo = false;
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

    /**
     * Get player's current status
     *
     * @return whether in Jail or not
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * Sets player's current status
     *
     * @param inJail True for "player is in Jail"
     */
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    /**
     * Get player's current status
     *
     * @return whether player has completed at least one round
     */
    public boolean isPassedGo() {
        return passedGo;
    }

    /**
     * Sets player's current status
     *
     * @param passedGo True for "player has completed at least one round"
     */
    public void setPassedGo(boolean passedGo) {
        this.passedGo = passedGo;
    }
}

