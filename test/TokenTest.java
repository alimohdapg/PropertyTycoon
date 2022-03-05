import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TokenTest
{
    @Test
    public void testBoot()
    {
        Token token = Token.BOOT;
        assertEquals("boot", token.toString());
    }

    @Test
    public void testSmartphone()
    {
        Token token = Token.SMARTPHONE;
        assertEquals("smartphone", token.toString());
    }

    @Test
    public void testShip()
    {
        Token token = Token.SHIP;
        assertEquals("ship", token.toString());
    }

    @Test
    public void testHatstand()
    {
        Token token = Token.HATSTAND;
        assertEquals("hatstand", token.toString());
    }

    @Test
    public void testCat()
    {
        Token token = Token.CAT;
        assertEquals("cat", token.toString());
    }

    @Test
    public void testIron()
    {
        Token token = Token.IRON;
        assertEquals("iron", token.toString());
    }
}