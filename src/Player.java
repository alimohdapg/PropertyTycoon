import java.util.ArrayList;

public abstract class Player {

    private int money;
    private ArrayList<Property> properties;
    private int location;
    private boolean inJail;
    private boolean passedGo;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void buyProperty(Property property){

    }

    public void sellProperty(){

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

    public boolean isPassedGo() {
        return passedGo;
    }

    public void setPassedGo(boolean passedGo) {
        this.passedGo = passedGo;
    }
}
