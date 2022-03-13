import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The controller class for the main game scene
 *
 * @author Kieran Young
 */

public class GameController {

    @FXML
    private Circle playerOneToken;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private Circle p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22,
        p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40;

    @FXML
    private ArrayList<Circle> pos_array;

    @FXML
    private Text player1_money;

    private int current_pos;

    private Player player1;
    private Player[] players;
    private GameBoard gameBoard;




    /**
     * Default function, runs on launch. Initialises the array of positional elements
     */
    public void initialize() {
        pos_array = new ArrayList<>();
        Collections.addAll(pos_array, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22,
                p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40);
        current_pos = 0;

        player1 = new HumanPlayer(Token.CAT, new ArrayList<Property>());
        players = new Player[1];
        players[0] = player1;
        current_pos = 0;
        player1_money.setText("£"+Integer.toString(player1.getMoney()));
        gameBoard = new GameBoard(players);
    }

    /**
     * Testing feature, moves the player around the board
     *
     * @throws IOException
     */
    public void testButtonClicked() {
        gameBoard.update();
        //Updates current_pos with the new player position
        current_pos = gameBoard.getCurrentPlayerPosition();

        //Sets the player token on the GUI to the new location
        playerOneToken.setLayoutX(pos_array.get(current_pos).getLayoutX());
        playerOneToken.setLayoutY(pos_array.get(current_pos).getLayoutY());
    }

}
