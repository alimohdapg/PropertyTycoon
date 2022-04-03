import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The controller class for the main game scene
 *
 * @author Kieran Young
 */

public class GameController {

    @FXML
    private Circle playerOneToken;

    @FXML
    private Circle p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22,
        p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39;

    @FXML
    private ArrayList<Circle> pos_array;

    private int current_pos;

    private GameBoard gameBoard;
    private HumanPlayer player1;
    private Player[] players;

    private int player1pos;

    /**
     * Default function, runs on launch. Initialises the array of positional elements
     */
    public void initialize() {

        pos_array = new ArrayList<>();
        Collections.addAll(pos_array, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22,
                p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39);
        current_pos = 0;

        player1 = new HumanPlayer(Token.CAT, 100, new ArrayList<Property>());
        players = new Player[1];
        players[0] = player1;

        player1pos = 0;

        gameBoard = new GameBoard(players);
    }

    /**
     * Testing feature, moves the player around the board
     *
     * @throws IOException
     */
    public void testButtonClicked() throws IOException {
        if(current_pos == 39) {
            current_pos = 0;
        } else {
            current_pos++;
        }
        playerOneToken.setLayoutX(pos_array.get(current_pos).getLayoutX());
        playerOneToken.setLayoutY(pos_array.get(current_pos).getLayoutY());
    }

    /**
     * Simulates taking a turn
     *
     * @throws IOException
     */
    public void takeTurn() throws InterruptedException {
        gameBoard.update();
        System.out.println("The position after rolling is: " + gameBoard.getCurrentPlayerPosition());
        player1pos = gameBoard.getCurrentPlayerPosition();

        while(current_pos != gameBoard.getCurrentPlayerPosition()) {
            updatePosition(playerOneToken);
        }
    }

    public void updatePosition(Circle token) {
        System.out.println("Moving...");
        if(current_pos == 39) {
            current_pos = 0;
        } else {
            current_pos++;
        }
        token.setLayoutX(pos_array.get(current_pos).getLayoutX());
        token.setLayoutY(pos_array.get(current_pos).getLayoutY());
    }
}


