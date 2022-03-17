import java.util.ArrayList;

public class Bank extends Player {

    public Bank(String name, Token token, ArrayList<Property> properties) {
        super(name, token, new Cash(50000), properties);
    }

}
