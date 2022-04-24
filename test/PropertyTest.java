import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    private Property property1;
    private Property property2;
    private Property property3;
    private Property property4;

    @BeforeEach
    void setUp() {
        property1 = new Property("Test Property 1", ColorOfSet.RED, 20, 10, 20,
                30, 40, 50, 100);
        property2 = new Property("Test Property 2", ColorOfSet.GREEN, 50, 25, 50,
                75, 100, 125, 250);
        property3 = new Property("Test Property 3", ColorOfSet.BLUE, 100, 50, 100,
                150, 200, 250, 500);
        property4 = new Property("Test Property 4", ColorOfSet.PURPLE, 200, 100, 200,
                400, 600, 800, 1600);
    }

    @Test
    void getColorOfSet() {
        assertEquals(ColorOfSet.RED, property1.getColor());
        assertEquals(ColorOfSet.GREEN, property2.getColor());
        assertEquals(ColorOfSet.BLUE, property3.getColor());
        assertEquals(ColorOfSet.PURPLE, property4.getColor());
    }

    @Test
    void getCost() {
        assertEquals(20, property1.getCost());
        assertEquals(50, property2.getCost());
        assertEquals(100, property3.getCost());
        assertEquals(200, property4.getCost());
    }

    @Test
    void getHouseCost() {
        assertEquals(150, property1.getHouseCost());
        assertEquals(200, property2.getHouseCost());
        assertEquals(50, property3.getHouseCost());
        assertEquals(100, property4.getHouseCost());
    }

    @Test
    void buyHouse() {
        assertEquals(0, property1.getHouseCount());
        property1.buyHouse();
        assertEquals(1, property1.getHouseCount());
        property1.buyHouse();
        assertEquals(2, property1.getHouseCount());
        property1.buyHouse();
        assertEquals(3, property1.getHouseCount());
        property1.buyHouse();
        assertEquals(4, property1.getHouseCount());
        property1.buyHouse();
        assertEquals(4, property1.getHouseCount());
    }

    @Test
    void buyHotel() {
        assertFalse(property1.doesHaveHotel());
        property1.buyHouse();
        property1.buyHouse();
        property1.buyHouse();
        property1.buyHotel();
        assertFalse(property1.doesHaveHotel());
        property1.buyHouse();
        property1.buyHotel();
        assertTrue(property1.doesHaveHotel());
    }

    @Test
    void getRent() {
        assertEquals(10, property1.getRent());
        property1.buyHouse();
        assertEquals(20, property1.getRent());
        property1.buyHouse();
        assertEquals(30, property1.getRent());
        property1.buyHouse();
        assertEquals(40, property1.getRent());
        property1.buyHouse();
        assertEquals(50, property1.getRent());
        property1.buyHouse();
        assertEquals(50, property1.getRent());
        property1.buyHotel();
        assertEquals(100, property1.getRent());
    }

    @Test
    void setHouseCount() {
        assertEquals(0, property1.getHouseCount());
        property1.setHouseCount(5);
        assertEquals(5, property1.getHouseCount());
    }

    @Test
    void testSetAndGetHouseCount() {
        assertEquals(0, property1.getHouseCount());
        property1.setHouseCount(3);
        assertEquals(3, property1.getHouseCount());
        property1.setHouseCount(1);
        assertEquals(1, property1.getHouseCount());
    }

    @Test
    void testSellAndDoesHaveHotel() {
        assertFalse(property1.doesHaveHotel());
        property1.buyHouse();
        property1.buyHouse();
        property1.buyHouse();
        property1.buyHouse();
        property1.buyHotel();
        assertTrue(property1.doesHaveHotel());
        property1.sellHotel();
        assertFalse(property1.doesHaveHotel());
    }

    @Test
    void testSetAndIsUnderMortgage() {
        assertFalse(property1.isUnderMortgage());
        property1.setUnderMortgage(true);
        assertTrue(property1.isUnderMortgage());
        property1.setUnderMortgage(false);
        assertFalse(property1.isUnderMortgage());
    }

    @Test
    void testSetAndGetOwner() {
        assertNull(property1.getOwner());
        Player owner = new HumanPlayer("Owner", Token.BOOT, new ArrayList<>(), new ArrayList<>(), new Circle(), new Text(), new Text());
        property1.setOwner(owner);
        assertNotNull(property1.getOwner());
        assertEquals(owner, property1.getOwner());
        property1.setOwner(null);
        assertNull(property1.getOwner());
    }
}