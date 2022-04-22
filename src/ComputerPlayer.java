import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ComputerPlayer extends Player {

    /**
     * Constructs a new ComputerPlayer Object
     *
     * @param name                The player's name.
     * @param token               The player's icon.
     * @param properties          The properties the player currently owns.
     * @param stationAndUtilities The stations and utilities the player currently owns.
     * @param playerToken         The GUI's representation of the player's token.
     * @param playerName          The GUI's representation of the player's name.
     * @param playerMoney         The GUI's representation of the player's money.
     */
    public ComputerPlayer(String name, Token token, ArrayList<Property> properties, ArrayList<StationAndUtility> stationAndUtilities, Circle playerToken, Text playerName, Text playerMoney) {
        super(name, token, new Cash(1500), properties, stationAndUtilities, playerToken, playerName, playerMoney);

    }
}
