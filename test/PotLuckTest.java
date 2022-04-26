import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PotLuckTest
{
    private PotLuck potLuck;

    @BeforeEach
    public void setup()
    {
        potLuck = new PotLuck("PL");
    }

    @Test
    public void testOpportunityKnock()
    {
        assertEquals("PL", potLuck.getName());



        LinkedList<String> checkCard = new LinkedList<>();
        LinkedList<String> checkNum = new LinkedList<>();
        for (int i = 0; i < 17; i++)
        {
            ArrayList<String> info = potLuck.getNextCard();
            checkCard.addLast(info.get(0));
            checkNum.addLast(info.get(1));

            if (checkCard.getLast().equals("17"))
            {
                potLuck.addGetOutOfJailFreeBack();
            }
        }

        for (int i = 0; i < 17; i++)
        {
            ArrayList<String> info = potLuck.getNextCard();
            assertEquals(checkCard.removeFirst(), info.get(0));
            assertEquals(checkNum.removeFirst(), info.get(1));
        }

    }
}