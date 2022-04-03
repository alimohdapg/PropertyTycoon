import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class HumanPlayer extends Player {


    /**
     * Constructs a new HumanPlayer Object
     *
     * @param token Player's icon
     * @param properties The property player currently have
     */
    public HumanPlayer(String name, Token token, ArrayList<Property> properties, ArrayList<StationAndUtility> stationAndUtilities, Circle playerToken, Text playerName, Text playerMoney) {
        super(name, token, new Cash(1500), properties, stationAndUtilities, playerToken, playerName, playerMoney);
    }

}

