import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    GameBoard gameBoard;

    @BeforeEach
    void setUp(){
        gameBoard = new GameBoard(new Player[]{
                new HumanPlayer(Token.BOOT, 1500, new ArrayList<>()),
                new HumanPlayer(Token.CAT, 1500, new ArrayList<>()),
                new HumanPlayer(Token.HATSTAND, 1500, new ArrayList<>())},
                new ArrayList<>()
        );
    }

    @Test
    void testUpdate() {
        gameBoard.update();
        int player1Position = gameBoard.getDice1Number() + gameBoard.getDice2Number();
        assertEquals(player1Position, gameBoard.getCurrentPlayerPosition());
        assertEquals(gameBoard.getPlayers()[0], gameBoard.getCurrentPlayer());
        gameBoard.update();
        assertEquals(gameBoard.getDice1Number() + gameBoard.getDice2Number(), gameBoard.getCurrentPlayerPosition());
        assertEquals(gameBoard.getPlayers()[1], gameBoard.getCurrentPlayer());
        gameBoard.update();
        assertEquals(gameBoard.getDice1Number() + gameBoard.getDice2Number(), gameBoard.getCurrentPlayerPosition());
        assertEquals(gameBoard.getPlayers()[2], gameBoard.getCurrentPlayer());
        gameBoard.update();
        assertEquals(player1Position + gameBoard.getDice1Number() + gameBoard.getDice2Number(), gameBoard.getCurrentPlayerPosition());
        assertEquals(gameBoard.getPlayers()[0], gameBoard.getCurrentPlayer());
    }

    @Test
    void testGetDiceTotal() {
        gameBoard.update();
        assertEquals(gameBoard.getDice1Number() + gameBoard.getDice2Number(), gameBoard.getCurrentPlayerPosition());
    }

    @Test
    void testGetCurrentPlayerPosition() {
        gameBoard.update();
        assertEquals(((HumanPlayer) gameBoard.getPlayers()[0]).getLocation(), gameBoard.getCurrentPlayerPosition());
        gameBoard.update();
        assertEquals(((HumanPlayer) gameBoard.getPlayers()[1]).getLocation(), gameBoard.getCurrentPlayerPosition());
        gameBoard.update();
        assertEquals(((HumanPlayer) gameBoard.getPlayers()[2]).getLocation(), gameBoard.getCurrentPlayerPosition());
        gameBoard.update();
        assertEquals(((HumanPlayer) gameBoard.getPlayers()[0]).getLocation(), gameBoard.getCurrentPlayerPosition());
    }

    @Test
    void testGetCurrentPlayer() {
        gameBoard.update();
        assertEquals(gameBoard.getPlayers()[0], gameBoard.getCurrentPlayer());
        gameBoard.update();
        assertEquals(gameBoard.getPlayers()[1], gameBoard.getCurrentPlayer());
        gameBoard.update();
        assertEquals(gameBoard.getPlayers()[2], gameBoard.getCurrentPlayer());
        gameBoard.update();
        assertEquals(gameBoard.getPlayers()[0], gameBoard.getCurrentPlayer());
    }
}