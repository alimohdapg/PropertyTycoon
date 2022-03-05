import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    public void testBrown()
    {
        Color color = Color.BROWN;
        assertEquals("brown", color.toString());
    }

    @Test
    public void testBlue()
    {
        Color color = Color.BLUE;
        assertEquals("blue", color.toString());
    }

    @Test
    public void testPurple()
    {
        Color color = Color.PURPLE;
        assertEquals("purple", color.toString());
    }

    @Test
    public void testOrange()
    {
        Color color = Color.ORANGE;
        assertEquals("orange", color.toString());
    }

    @Test
    public void testRed()
    {
        Color color = Color.RED;
        assertEquals("red", color.toString());
    }

    @Test
    public void testYellow()
    {
        Color color = Color.YELLOW;
        assertEquals("yellow", color.toString());
    }

    @Test
    public void testGreen()
    {
        Color color = Color.GREEN;
        assertEquals("green", color.toString());
    }

    @Test
    public void testDeepBlue()
    {
        Color color = Color.DEEPBLUE;
        assertEquals("deep blue", color.toString());
    }
}