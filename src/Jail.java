import java.util.ArrayList;

/**
 * This class represents a jail which contains a list of players;
 * players currently in the jail will be ignored in the next round.
 *
 * @author Hanzhen Gong
 */
public class Jail extends BoardSpace {

    private ArrayList<Player> playersInJail;

    public Jail(String name)
    {
        super(name);
        playersInJail = new ArrayList<>();
    }

    public ArrayList<Player> getPlayersInJail()
    {
        return playersInJail;
    }

    public void addAPlayer(Player player)
    {
        playersInJail.add(player);
    }

    public void removeAPlayer(Player player)
    {
        playersInJail.remove(player);
    }
}
