import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    public void testFindAndCreateColor(){
        assertEquals(Color.findAndCreateColor("Brown"), Color.BROWN);
        assertEquals(Color.findAndCreateColor("Blue"), Color.BLUE);
        assertEquals(Color.findAndCreateColor("Purple"), Color.PURPLE);
        assertEquals(Color.findAndCreateColor("Orange"), Color.ORANGE);
        assertEquals(Color.findAndCreateColor("Red"), Color.RED);
        assertEquals(Color.findAndCreateColor("Yellow"), Color.YELLOW);
        assertEquals(Color.findAndCreateColor("Green"), Color.GREEN);
        assertEquals(Color.findAndCreateColor("Deep blue"), Color.DEEPBLUE);
        assertEquals(Color.findAndCreateColor("Deep blue"), Color.DEEPBLUE);

    }

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