import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player;
    Property property1;
    Property property2;
    StationAndUtility stationAndUtility;

    @BeforeEach
    void setUp() {
        player = new HumanPlayer("Boot Player", Token.BOOT, new ArrayList<>(), new ArrayList<>(), new Circle(), new Text(), new Text());
        property1 = new Property("Property 1", ColorOfSet.BLUE, 25, 5, 10, 20, 40, 80, 160);
        property2 = new Property("Property 2", ColorOfSet.RED, 10, 2, 4, 8, 16, 32, 64);
        stationAndUtility = new StationAndUtility("StationAndUtility", 30, ColorOfSet.STATION);
    }

    @Test
    void testBuyProperty() {
        assertFalse(player.buyProperty(property1));
        player.setPassedGo(true);
        assertTrue(player.buyProperty(property1));
        assertFalse(player.buyProperty(property1));
        player.setMoney(new Cash(9));
        assertFalse(player.buyProperty(property2));
        player.setMoney(new Cash(10));
        assertTrue(player.buyProperty(property2));
    }

    @Test
    void testSellProperty() {
        player.setPassedGo(true);
        player.buyProperty(property1);
        player.sellProperty(property1);
        assertNull(property1.getOwner());
        assertEquals(1500, player.getMoney().getAmount());
        assertEquals(0, player.getProperties().size());
        player.buyProperty(property1);
        player.buyAHouse(property1);
        assertEquals(1425, player.getMoney().getAmount());
        player.sellProperty(property1);
        assertEquals(1500, player.getMoney().getAmount());
    }

    @Test
    void testSetAndGetHasCard() {
        assertFalse(player.isHasCard());
        player.setHasCard(true);
        assertTrue(player.isHasCard());
        player.setHasCard(false);
        assertFalse(player.isHasCard());
    }

    @Test
    void testBuyAndSellAHouseAndHotel() {
        player.setPassedGo(true);
        player.buyAHouse(property1);
        assertEquals(1, property1.getHouseCount());
        assertEquals(1450, player.getMoney().getAmount());
        player.sellAHouse(property1);
        assertEquals(1500, player.getMoney().getAmount());
        property1.setHouseCount(4);
        player.buyAHouse(property1);
        assertEquals(1450, player.getMoney().getAmount());
        player.sellAHotel(property1);
        assertEquals(1700, player.getMoney().getAmount());
    }

    @Test
    void testBuyAndSellStaUti() {
        assertTrue(player.buyStaUti(stationAndUtility));
        assertFalse(player.buyStaUti(stationAndUtility));
        assertEquals(1470, player.getMoney().getAmount());
        assertEquals(player, stationAndUtility.getOwner());
        player.sellStaUti(stationAndUtility);
        assertEquals(1500, player.getMoney().getAmount());
        assertNull(stationAndUtility.getOwner());
    }

    @Test
    void testGetName() {
        assertEquals("Boot Player", player.getName());
    }

    @Test
    void testSetAndGetToken() {
        assertEquals(Token.BOOT, player.getToken());
        player.setToken(Token.CAT);
        assertEquals(Token.CAT, player.getToken());
    }

    @Test
    void testSetAndGetMoney() {
        assertEquals(1500, player.getMoney().getAmount());
        player.setMoney(new Cash(50));
        assertEquals(50, player.getMoney().getAmount());
    }

    @Test
    void testGetProperties() {
        assertEquals(0, player.getProperties().size());
        player.setPassedGo(true);
        player.buyProperty(property1);
        assertEquals(1, player.getProperties().size());
    }

    @Test
    void testGetStationAndUtilities() {
        assertEquals(0, player.getStationAndUtilities().size());
        player.buyStaUti(stationAndUtility);
        assertEquals(1, player.getStationAndUtilities().size());
    }

    @Test
    void testSetAndGetLocation() {
        assertEquals(0, player.getLocation());
        player.setLocation(5);
        assertEquals(5, player.getLocation());
    }

    @Test
    void testGetPlayerName() {
        assertEquals("Boot Player", player.getPlayerName().getText());
    }

    @Test
    void testJail() {
        assertFalse(player.isInJail());
        player.setInJail();
        assertEquals(3, player.getJailTurn());
        assertTrue(player.isInJail());
        player.setJailTurn(7);
        assertEquals(7, player.getJailTurn());
        player.setOutJail();
        assertEquals(0, player.getJailTurn());
        assertFalse(player.isInJail());
        assertEquals(10, player.getLocation());
    }

    @Test
    void testSetAndIsPassedGo() {
        assertFalse(player.isPassedGo());
        player.setPassedGo(true);
        assertTrue(player.isPassedGo());
    }

    @Test
    void testUpdateAndGetMoney() {
        assertEquals("£1500", player.getPlayerMoney().getText());
        player.setMoney(new Cash(500));
        assertEquals("£1500", player.getPlayerMoney().getText());
        player.updateMoney();
        assertEquals("£500", player.getPlayerMoney().getText());
    }

    @Test
    void testMortgageProperty() {
        player.setPassedGo(true);
        player.buyProperty(property2);
        player.mortgageProperty(property2);
        assertEquals(1495, player.getMoney().getAmount());
        assertTrue(property2.isUnderMortgage());
    }

    @Test
    void testMortgageStationAndUtility() {
        player.buyStaUti(stationAndUtility);
        player.mortgageStationAndUtility(stationAndUtility);
        assertEquals(1485, player.getMoney().getAmount());
        assertTrue(stationAndUtility.isUnderMortgage());
    }
}