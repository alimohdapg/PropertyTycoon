import java.util.ArrayList;

public class HumanPlayer extends Player {

    private int location;
    private boolean inJail;
    private boolean passedGo;

    public HumanPlayer(Token token, int money, ArrayList<Property> properties) {
        super(token, 1500, properties);
        location = 0;
        inJail = false;
        passedGo = false;
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

