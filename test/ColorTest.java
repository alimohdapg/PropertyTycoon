import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    public void testFindAndCreateColor() {
        assertEquals(ColorOfSet.findAndCreateColor("Brown"), ColorOfSet.BROWN);
        assertEquals(ColorOfSet.findAndCreateColor("Blue"), ColorOfSet.BLUE);
        assertEquals(ColorOfSet.findAndCreateColor("Purple"), ColorOfSet.PURPLE);
        assertEquals(ColorOfSet.findAndCreateColor("Orange"), ColorOfSet.ORANGE);
        assertEquals(ColorOfSet.findAndCreateColor("Red"), ColorOfSet.RED);
        assertEquals(ColorOfSet.findAndCreateColor("Yellow"), ColorOfSet.YELLOW);
        assertEquals(ColorOfSet.findAndCreateColor("Green"), ColorOfSet.GREEN);
        assertEquals(ColorOfSet.findAndCreateColor("Deep blue"), ColorOfSet.DEEPBLUE);
        assertEquals(ColorOfSet.findAndCreateColor("Deep blue"), ColorOfSet.DEEPBLUE);
        assertEquals(ColorOfSet.findAndCreateColor("Station"), ColorOfSet.STATION);
        assertEquals(ColorOfSet.findAndCreateColor("Utilities"), ColorOfSet.UTILITIES);
    }

    @Test
    public void testBrown() {
        ColorOfSet color = ColorOfSet.BROWN;
        assertEquals("brown", color.toString());
    }

    @Test
    public void testBlue() {
        ColorOfSet color = ColorOfSet.BLUE;
        assertEquals("blue", color.toString());
    }

    @Test
    public void testPurple() {
        ColorOfSet color = ColorOfSet.PURPLE;
        assertEquals("purple", color.toString());
    }

    @Test
    public void testOrange() {
        ColorOfSet color = ColorOfSet.ORANGE;
        assertEquals("orange", color.toString());
    }

    @Test
    public void testRed() {
        ColorOfSet color = ColorOfSet.RED;
        assertEquals("red", color.toString());
    }

    @Test
    public void testYellow() {
        ColorOfSet color = ColorOfSet.YELLOW;
        assertEquals("yellow", color.toString());
    }

    @Test
    public void testGreen() {
        ColorOfSet color = ColorOfSet.GREEN;
        assertEquals("green", color.toString());
    }

    @Test
    public void testDeepBlue() {
        ColorOfSet color = ColorOfSet.DEEPBLUE;
        assertEquals("deep blue", color.toString());
    }

    @Test
    public void testStation() {
        ColorOfSet color = ColorOfSet.STATION;
        assertEquals("station", color.toString());
    }

    @Test
    public void testUtilities() {
        ColorOfSet color = ColorOfSet.UTILITIES;
        assertEquals("utilities", color.toString());
    }
}