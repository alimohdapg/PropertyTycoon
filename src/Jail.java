import java.util.HashMap;
import java.util.Set;

/**
 * This class represents a jail which contains a list of players;
 * players currently in the jail will be ignored in the next round.
 *
 * @author Hanzhen Gong & Leon
 */
public class Jail extends BoardSpace {

    private HashMap<Player, Integer> playersInJail;

    public Jail(String name)
    {
        super(name);
        playersInJail = new HashMap<>();

    }

    public Set<Player> getPlayersInJail()
    {
        return playersInJail.keySet();
    }

    public void addAPlayer(Player player)
    {
        playersInJail.put(player, 3);
    }

    public void removeAPlayer(Player player)
    {
        playersInJail.remove(player);
        player.setLocation(10);
    }

    public void minusPrisonTerm (Player player) {
        playersInJail.replace(player, playersInJail.get(player)-1);
        if (playersInJail.get(player) == 0) {
            removeAPlayer(player);
        }
    }
}
