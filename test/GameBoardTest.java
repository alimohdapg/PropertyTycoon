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
    void setUp(){
        gameBoard = new GameBoard(new ArrayList<Player>(Arrays.asList(
                new HumanPlayer("Boot Player", Token.BOOT, new ArrayList<>(), new ArrayList<>(), new Circle(), new Text(), new Text()),
                new HumanPlayer("Cat Player", Token.CAT, new ArrayList<>(), new ArrayList<>(), new Circle(), new Text(), new Text()),
                new HumanPlayer("Hat Stand Player", Token.HATSTAND, new ArrayList<>(),  new ArrayList<>(), new Circle(),  new Text(), new Text())
        )));
    }

    @Test
    void testFillBoardSpaces(){
        assertTrue(gameBoard.getBoardSpaces().get(0) instanceof Default);
        assertTrue(gameBoard.getBoardSpaces().get(2) instanceof Default);
        assertTrue(gameBoard.getBoardSpaces().get(7) instanceof Default);
        assertTrue(gameBoard.getBoardSpaces().get(5) instanceof StationAndUtility);
        assertTrue(gameBoard.getBoardSpaces().get(12) instanceof StationAndUtility);
        assertTrue(gameBoard.getBoardSpaces().get(3) instanceof Property);
        assertTrue(gameBoard.getBoardSpaces().get(8) instanceof Property);
        //assertTrue(gameBoard.getBoardSpaces().get(40) instanceof Jail);
    }

    @Test
    void testUpdate() {
        gameBoard.update();
        int player1Position = gameBoard.getDiceRollsSum();
        assertEquals(player1Position, gameBoard.getCurrentPlayerPosition());
        assertEquals(gameBoard.getPlayers().get(0), gameBoard.getCurrentPlayer());
        gameBoard.update();
        assertEquals(gameBoard.getDiceRollsSum(), gameBoard.getCurrentPlayerPosition());
        assertEquals(gameBoard.getPlayers().get(1), gameBoard.getCurrentPlayer());
        gameBoard.update();
        assertEquals(gameBoard.getDiceRollsSum(), gameBoard.getCurrentPlayerPosition());
        assertEquals(gameBoard.getPlayers().get(2), gameBoard.getCurrentPlayer());
        gameBoard.update();
        assertEquals(gameBoard.getDiceRollsSum() + player1Position, gameBoard.getCurrentPlayerPosition());
        assertEquals(gameBoard.getPlayers().get(3), gameBoard.getCurrentPlayer());
    }


    @Test
    void testGetCurrentPlayerPosition() {
        gameBoard.update();
        assertEquals(((HumanPlayer) gameBoard.getPlayers().get(0)).getLocation(), gameBoard.getCurrentPlayerPosition());
        gameBoard.update();
        assertEquals(((HumanPlayer) gameBoard.getPlayers().get(1)).getLocation(), gameBoard.getCurrentPlayerPosition());
        gameBoard.update();
        assertEquals(((HumanPlayer) gameBoard.getPlayers().get(2)).getLocation(), gameBoard.getCurrentPlayerPosition());
        gameBoard.update();
        assertEquals(((HumanPlayer) gameBoard.getPlayers().get(0)).getLocation(), gameBoard.getCurrentPlayerPosition());
    }

    @Test
    void testGetCurrentPlayer() {
        gameBoard.update();
        assertEquals(gameBoard.getPlayers().get(0), gameBoard.getCurrentPlayer());
        gameBoard.update();
        assertEquals(gameBoard.getPlayers().get(1), gameBoard.getCurrentPlayer());
        gameBoard.update();
        assertEquals(gameBoard.getPlayers().get(2), gameBoard.getCurrentPlayer());
        gameBoard.update();
        assertEquals(gameBoard.getPlayers().get(0), gameBoard.getCurrentPlayer());
    }

}