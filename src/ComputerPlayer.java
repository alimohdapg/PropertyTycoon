import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ComputerPlayer extends Player {

    private int location;
    private boolean inJail;
    private boolean passGo;

    public ComputerPlayer(String name, Token token, Cash money, ArrayList<Property> properties, Circle playerToken, Text playerName, Text playerMoney) {
        super(name, token, money, properties, playerToken, playerName, playerMoney);
        location = 0;
        inJail = false;
        passGo = false;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
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
