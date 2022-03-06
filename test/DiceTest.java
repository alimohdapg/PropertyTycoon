import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest
{
    private Dice dice;

    @BeforeEach
    public void setup()
    {
        dice = new Dice();
    }

    @Test
    public void testGetNumber()
    {
        assertEquals(0, dice.getNumber());
    }

    @Test
    public void testRollDice()
    {
        int number = dice.rollDice();
        assertTrue(0 <= number && number <= 6);
    }
}