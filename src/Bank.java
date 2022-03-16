import java.util.ArrayList;

public class Bank extends Player {

    public Bank(Token token, ArrayList<Property> properties) {
        super(token, new Cash(50000), properties);
    }

}
