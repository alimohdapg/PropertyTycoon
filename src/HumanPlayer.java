import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class HumanPlayer extends Player {

    private boolean inJail;
    private boolean passedGo;

    /**
     * Constructs a new HumanPlayer Object
     *
     * @param token Player's icon
     * @param properties The property player currently have
     */
    public HumanPlayer(String name, Token token, ArrayList<Property> properties, ArrayList<StationAndUtility> stationAndUtilities, Circle playerToken, Text playerName, Text playerMoney) {
        super(name, token, new Cash(1500), properties, stationAndUtilities, playerToken, playerName, playerMoney);
        inJail = false;
        passedGo = false;
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

