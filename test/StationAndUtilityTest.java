import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class StationAndUtilityTest {

    private StationAndUtility station;
    private StationAndUtility utility;

    @BeforeEach
    void setUp() {
        station = new StationAndUtility("Test Station", 50, ColorOfSet.STATION);
        utility = new StationAndUtility("Test Utility", 100, ColorOfSet.UTILITIES);
    }

    @Test
    void getColorOfSet() {
        assertEquals(ColorOfSet.STATION, station.getColor());
        assertEquals(ColorOfSet.UTILITIES, utility.getColor());
    }

    @Test
    void testGetCost() {
        assertEquals(50, station.getCost());
        assertEquals(100, utility.getCost());
    }

    @Test
    void testSetAndGetOwner() {
        assertNull(station.getOwner());
        Player owner = new HumanPlayer("Owner", Token.BOOT, new ArrayList<>(), new ArrayList<>(), new Circle(), new Text(), new Text());
        station.setOwner(owner);
        assertNotNull(station.getOwner());
        assertEquals(owner, station.getOwner());
        station.setOwner(null);
        assertNull(station.getOwner());
    }

    @Test
    void testGetStationRent() {
        Player player1 = new HumanPlayer("Player 1", Token.BOOT, new ArrayList<>(), new ArrayList<>(), new Circle(), new Text(), new Text());
        Player player2 = new HumanPlayer("Player 2", Token.HATSTAND, new ArrayList<>(), new ArrayList<>(), new Circle(), new Text(), new Text());
        GameBoard gameBoard = new GameBoard(new ArrayList<Player>(Arrays.asList(player1, player2)));
        StationAndUtility stationToTest = (StationAndUtility) gameBoard.getBoardSpaces().get(5);
        player1.buyStaUti(stationToTest);
        assertEquals(25, stationToTest.getStationRent(gameBoard.checkNumStaUti(stationToTest, player1)));
        player1.buyStaUti((StationAndUtility) gameBoard.getBoardSpaces().get(15));
        assertEquals(50, stationToTest.getStationRent(gameBoard.checkNumStaUti(stationToTest, player1)));
        player1.buyStaUti((StationAndUtility) gameBoard.getBoardSpaces().get(25));
        assertEquals(100, stationToTest.getStationRent(gameBoard.checkNumStaUti(stationToTest, player1)));
        player1.buyStaUti((StationAndUtility) gameBoard.getBoardSpaces().get(35));
        assertEquals(200, stationToTest.getStationRent(gameBoard.checkNumStaUti(stationToTest, player1)));
    }

    @Test
    void testGetUtilityRent() {
        Player player1 = new HumanPlayer("Player 1", Token.BOOT, new ArrayList<>(), new ArrayList<>(), new Circle(), new Text(), new Text());
        Player player2 = new HumanPlayer("Player 2", Token.HATSTAND, new ArrayList<>(), new ArrayList<>(), new Circle(), new Text(), new Text());
        GameBoard gameBoard = new GameBoard(new ArrayList<Player>(Arrays.asList(player1, player2)));
        StationAndUtility utilityToTest = (StationAndUtility) gameBoard.getBoardSpaces().get(12);
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        dice1.rollDice();
        dice2.rollDice();
        int diceSum = dice1.getNumber() + dice2.getNumber();
        assertEquals(-999999, utilityToTest.getUtilityRent(gameBoard.checkNumStaUti(utilityToTest, player1), diceSum));
        player1.buyStaUti(utilityToTest);
        dice1.rollDice();
        dice2.rollDice();
        diceSum = dice1.getNumber() + dice2.getNumber();
        assertEquals(diceSum * 4, utilityToTest.getUtilityRent(gameBoard.checkNumStaUti(utilityToTest, player1), diceSum));
        player1.buyStaUti((StationAndUtility) gameBoard.getBoardSpaces().get(28));
        dice1.rollDice();
        dice2.rollDice();
        diceSum = dice1.getNumber() + dice2.getNumber();
        assertEquals(diceSum * 10, utilityToTest.getUtilityRent(gameBoard.checkNumStaUti(utilityToTest, player1), diceSum));
    }

    @Test
    void testSetAndIsUnderMortgage() {
        assertFalse(station.isUnderMortgage());
        station.setUnderMortgage(true);
        assertTrue(station.isUnderMortgage());
        station.setUnderMortgage(false);
        assertFalse(station.isUnderMortgage());
    }
}