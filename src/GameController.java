import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
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

    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private Stage stage;

    @FXML
    private Circle playerOneToken;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private Circle p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22,
        p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39;

    @FXML
    private ArrayList<Circle> pos_array;

    private int current_pos;

    /**
     * Default function, runs on launch. Initialises the array of positional elements
     */
    public void initialize() {
        pos_array = new ArrayList<>();
        Collections.addAll(pos_array, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22,
                p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39);
        current_pos = 0;
    }

    /**
     * Testing feature, moves the player around the board
     *
     * @throws IOException
     */
    public void testButtonClicked() throws IOException {
        System.out.println("Current pos 1: " + current_pos);
        if(current_pos == 39) {
            current_pos = 0;
        } else {
            current_pos++;

        }
        playerOneToken.setLayoutX(pos_array.get(current_pos).getLayoutX());
        playerOneToken.setLayoutY(pos_array.get(current_pos).getLayoutY());
        System.out.println("Current pos 2: " + current_pos);

    }

}
