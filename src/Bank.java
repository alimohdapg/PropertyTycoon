import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Bank extends Player {

    public Bank(String name, Token token, ArrayList<Property> properties, Circle playerToken, Text playerName, Text playerMoney) {
        super(name, token, new Cash(50000), properties, playerToken, playerName, playerMoney);
    }

}
