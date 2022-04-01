import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ComputerPlayer extends Player {

    private boolean inJail;
    private boolean passGo;

    public ComputerPlayer(String name, Token token, Cash money, ArrayList<Property> properties, ArrayList<StationAndUtility> stationAndUtilities, Circle playerToken, Text playerName, Text playerMoney) {
        super(name, token, money, properties, stationAndUtilities, playerToken, playerName, playerMoney);
        inJail = false;
        passGo = false;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public boolean isPassGo() {
        return passGo;
    }

    public void setPassGo(boolean passGo) {
        this.passGo = passGo;
    }
}
