import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name, Token token, ArrayList<Property> properties, ArrayList<StationAndUtility> stationAndUtilities, Circle playerToken, Text playerName, Text playerMoney) {
        super(name, token, new Cash(1500), properties, stationAndUtilities, playerToken, playerName, playerMoney);

    }
}
