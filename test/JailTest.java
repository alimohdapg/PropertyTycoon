import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JailTest
{
    private Jail jail;
    private Player player1;

    @BeforeEach
    public void setup()
    {
        jail = new Jail("Jail", 40);
        player1 = new HumanPlayer(Token.IRON, null);
    }

    @Test
    public void getPlayersInJailTest()
    {
        assertEquals(0, jail.getPlayersInJail().size());
    }

    @Test
    public void addAPlayerTest()
    {
        jail.addAPlayer(player1);
        assertTrue(jail.getPlayersInJail().contains(player1));
    }

    @Test
    public void removeAPlayerTest()
    {
        jail.addAPlayer(player1);
        jail.removeAPlayer(player1);
        assertEquals(0, jail.getPlayersInJail().size());
    }
}