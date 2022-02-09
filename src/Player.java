import java.util.ArrayList;

public abstract class Player {

    private Token token;
    private int money;
    private ArrayList<Property> properties;

    public Player(Token token, int money, ArrayList<Property> properties) {
        this.token = token;
        this.money = money;
        this.properties = properties;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void buyProperty(Property property) {

    }

    public void sellProperty() {

    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
