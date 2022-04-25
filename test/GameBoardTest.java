import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard(new ArrayList<Player>(Arrays.asList(
                new HumanPlayer("Boot Player", Token.BOOT, new ArrayList<>(), new ArrayList<>(), new Circle(), new Text(), new Text()),
                new HumanPlayer("Cat Player", Token.CAT, new ArrayList<>(), new ArrayList<>(), new Circle(), new Text(), new Text()),
                new HumanPlayer("Hat Stand Player", Token.HATSTAND, new ArrayList<>(), new ArrayList<>(), new Circle(), new Text(), new Text())
        )));
        gameBoard.endTurn();
    }

    @Test
    void testFillBoardSpaces() {
        assertTrue(gameBoard.getBoardSpaces().get(0) instanceof Default);
        assertTrue(gameBoard.getBoardSpaces().get(2) instanceof Default);
        assertTrue(gameBoard.getBoardSpaces().get(7) instanceof Default);
        assertTrue(gameBoard.getBoardSpaces().get(5) instanceof StationAndUtility);
        assertTrue(gameBoard.getBoardSpaces().get(12) instanceof StationAndUtility);
        assertTrue(gameBoard.getBoardSpaces().get(3) instanceof Property);
        assertTrue(gameBoard.getBoardSpaces().get(8) instanceof Property);
        assertEquals("jail", gameBoard.getBoardSpaces().get(40).getName());
    }

    @Test
    void testUpdate() {
        gameBoard.update();
        int initialSum = gameBoard.getDiceRollsSum();
        assertEquals(initialSum, gameBoard.getCurrentPlayer().getLocation());
        gameBoard.update();
        assertEquals(initialSum + gameBoard.getDiceRollsSum(), gameBoard.getCurrentPlayer().getLocation());
    }

    @Test
    void testEndTurn() {
        Player almostBankruptPlayer = gameBoard.getCurrentPlayer();
        almostBankruptPlayer.getMoney().setAmount(0);
        // returning to the player
        gameBoard.endTurn();
        gameBoard.endTurn();
        gameBoard.endTurn(); // they don't get removed as their money is still 0 (bankrupt is < 0)
        assertTrue(gameBoard.getPlayers().contains(almostBankruptPlayer));
        Player bankruptPlayer = gameBoard.getCurrentPlayer();
        bankruptPlayer.getMoney().setAmount(-1);
        // returning to the player
        gameBoard.endTurn();
        gameBoard.endTurn();
        gameBoard.endTurn(); // they get removed here since their money is < 0
        assertFalse(gameBoard.getPlayers().contains(bankruptPlayer));
    }

    @Test
    void testCheckNumStaUti() {
        Player player1 = gameBoard.getPlayers().get(0);
        StationAndUtility stationPlaceHolder = (StationAndUtility) gameBoard.getBoardSpaces().get(5);
        assertEquals(0, gameBoard.checkNumStaUti(stationPlaceHolder, player1));
        player1.buyStaUti(stationPlaceHolder);
        assertEquals(1, gameBoard.checkNumStaUti(stationPlaceHolder, player1));
        player1.buyStaUti((StationAndUtility) gameBoard.getBoardSpaces().get(15));
        assertEquals(2, gameBoard.checkNumStaUti(stationPlaceHolder, player1));
        player1.buyStaUti((StationAndUtility) gameBoard.getBoardSpaces().get(25));
        assertEquals(3, gameBoard.checkNumStaUti(stationPlaceHolder, player1));
        player1.buyStaUti((StationAndUtility) gameBoard.getBoardSpaces().get(35));
        assertEquals(4, gameBoard.checkNumStaUti(stationPlaceHolder, player1));

        Player player2 = gameBoard.getPlayers().get(1);
        StationAndUtility utilityPlaceHolder = (StationAndUtility) gameBoard.getBoardSpaces().get(12);
        assertEquals(0, gameBoard.checkNumStaUti(utilityPlaceHolder, player1));
        player1.buyStaUti(utilityPlaceHolder);
        assertEquals(1, gameBoard.checkNumStaUti(utilityPlaceHolder, player1));
        player1.buyStaUti((StationAndUtility) gameBoard.getBoardSpaces().get(28));
        assertEquals(2, gameBoard.checkNumStaUti(utilityPlaceHolder, player1));
    }

    @Test
    void testCheckBuyHouse() {
        Player player = gameBoard.getPlayers().get(gameBoard.getPlayers().size() - 1);
        player.setPassedGo(true);
        Property propertyToBuildOn = (Property) gameBoard.getBoardSpaces().get(6);
        Property sameColorProperty1 = (Property) gameBoard.getBoardSpaces().get(8);
        Property sameColorProperty2 = (Property) gameBoard.getBoardSpaces().get(9);
        assertFalse(gameBoard.checkBuyHouse(propertyToBuildOn, player));
        player.buyProperty(propertyToBuildOn);
        player.buyProperty(sameColorProperty1);
        // player must own all properties of the same color before buying a house
        assertFalse(gameBoard.checkBuyHouse(propertyToBuildOn, player));
        player.buyProperty(sameColorProperty2);
        assertTrue(gameBoard.checkBuyHouse(propertyToBuildOn, player));
        propertyToBuildOn.buyHouse();
        // house difference between the properties will be > 1
        assertFalse(gameBoard.checkBuyHouse(propertyToBuildOn, player));
        sameColorProperty1.buyHouse();
        sameColorProperty2.buyHouse();
        // difference is acceptable
        assertTrue(gameBoard.checkBuyHouse(propertyToBuildOn, player));
    }

    @Test
    void testGetDiceRollsSum() {
        gameBoard.update();
        assertEquals(gameBoard.getDice1Number() + gameBoard.getDice2Number(), gameBoard.getDiceRollsSum());
        gameBoard.update();
        assertEquals(gameBoard.getDice1Number() + gameBoard.getDice2Number(), gameBoard.getDiceRollsSum());
    }

    @Test
    void testGetCurrentPlayerPosition() {
        assertEquals(0, gameBoard.getCurrentPlayerPosition());
        gameBoard.update();
        assertTrue(1 < gameBoard.getCurrentPlayerPosition() && gameBoard.getCurrentPlayerPosition() < 13);
        gameBoard.update();
        assertTrue(3 < gameBoard.getCurrentPlayerPosition() && gameBoard.getCurrentPlayerPosition() < 25);
        gameBoard.update();
        assertTrue((5 < gameBoard.getCurrentPlayerPosition() && gameBoard.getCurrentPlayerPosition() < 37) ||
                gameBoard.getCurrentPlayerPosition() == 40);
        gameBoard.update();
        assertTrue((7 < gameBoard.getCurrentPlayerPosition() && gameBoard.getCurrentPlayerPosition() < 39) ||
                gameBoard.getCurrentPlayerPosition() == 40);
    }

    @Test
    void testGetCurrentPlayer() {
        assertEquals(gameBoard.getPlayers().get(gameBoard.getPlayers().size() - 1), gameBoard.getCurrentPlayer());
        gameBoard.endTurn();
        assertEquals(gameBoard.getPlayers().get(gameBoard.getPlayers().size() - 2), gameBoard.getCurrentPlayer());
        gameBoard.endTurn();
        assertEquals(gameBoard.getPlayers().get(gameBoard.getPlayers().size() - 3), gameBoard.getCurrentPlayer());
    }

    @Test
    void testUpdateAllPlayers() {
        Player player1 = gameBoard.getPlayers().get(0);
        player1.getMoney().setAmount(2000);
        assertEquals("£1500", player1.getPlayerMoney().getText());
        gameBoard.updateAllPlayers();
        assertEquals("£2000", player1.getPlayerMoney().getText());
        Player player2 = gameBoard.getPlayers().get(1);
        player1.getMoney().setAmount(5000);
        player2.getMoney().setAmount(3000);
        assertEquals("£2000", player1.getPlayerMoney().getText());
        assertEquals("£1500", player2.getPlayerMoney().getText());
        gameBoard.updateAllPlayers();
        assertEquals("£5000", player1.getPlayerMoney().getText());
        assertEquals("£3000", player2.getPlayerMoney().getText());
    }

    @Test
    void testAddToFreeParking() {
        assertEquals(0, gameBoard.getFreeParkingSum());
        gameBoard.addToFreeParking(300);
        assertEquals(300, gameBoard.getFreeParkingSum());
        gameBoard.addToFreeParking(-500);
        assertEquals(-200, gameBoard.getFreeParkingSum());
    }
}