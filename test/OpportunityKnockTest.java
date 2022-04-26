import jdk.nashorn.internal.parser.Token;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class OpportunityKnockTest
{
    private OpportunityKnock opportunityKnock;

    @BeforeEach
    public void setup()
    {
        opportunityKnock = new OpportunityKnock("OK");
    }

    @Test
    public void testOpportunityKnock()
    {
        assertEquals("OK", opportunityKnock.getName());



        LinkedList<String> checkCard = new LinkedList<>();
        LinkedList<String> checkNum = new LinkedList<>();
        for (int i = 0; i < 16; i++)
        {
            ArrayList<String> info = opportunityKnock.getNextCard();
            checkCard.addLast(info.get(0));
            checkNum.addLast(info.get(1));

            if (checkCard.getLast().equals("16"))
            {
                opportunityKnock.addGetOutOfJailFreeBack();
            }
        }

        for (int i = 0; i < 16; i++)
        {
            ArrayList<String> info = opportunityKnock.getNextCard();
            assertEquals(checkCard.removeFirst(), info.get(0));
            assertEquals(checkNum.removeFirst(), info.get(1));
        }

    }

}