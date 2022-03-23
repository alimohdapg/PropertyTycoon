import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
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
    private AnchorPane dialogue_pane;

    @FXML
    private Circle p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22,
        p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40;

    @FXML
    private Text a0_text, a1_text, a2_text, a3_text, a4_text, a5_text, a6_text, a7_text, a8_text, a9_text, a10_text,
        a11_text, a12_text, a13_text, a14_text, a15_text, a16_text, a17_text, a18_text, a19_text, a20_text, a21_text,
        a22_text, a23_text, a24_text, a25_text, a26_text, a27_text, a28_text, a29_text, a30_text, a31_text, a32_text,
        a33_text, a34_text, a35_text, a36_text, a37_text, a38_text, a39_text;

    @FXML
    private ArrayList<Circle> pos_array;

    @FXML
    private ArrayList<Text> text_array;

    @FXML
    private Text player1_money;

    @FXML
    private Image diceimg1, diceimg2, diceimg3, diceimg4, diceimg5, diceimg6;

    @FXML
    private ImageView dice1, dice2, dice3, dice4, dice5, dice6;

    private int current_pos;

    private Player player1;
    private Player[] players;
    private GameBoard gameBoard;

    private boolean turnInProgress;

    /**
     * Default function, runs on launch. Initialises the array of positional elements
     */
    public void initialize() {
        pos_array = new ArrayList<>();
        text_array = new ArrayList<>();

        //Currently contains dice roll
        dialogue_pane.setVisible(false);

        //Initiliase the array of GUI objects
        Collections.addAll(pos_array, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22,
                p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40);
        Collections.addAll(text_array, a0_text, a1_text, a2_text, a3_text, a4_text, a5_text, a6_text, a7_text, a8_text, a9_text, a10_text,
                a11_text, a12_text, a13_text, a14_text, a15_text, a16_text, a17_text, a18_text, a19_text, a20_text, a21_text,
                a22_text, a23_text, a24_text, a25_text, a26_text, a27_text, a28_text, a29_text, a30_text, a31_text, a32_text,
                a33_text, a34_text, a35_text, a36_text, a37_text, a38_text, a39_text);

        diceimg1 = new Image("/img/dice_faces/d1.png");
        diceimg2 = new Image("/img/dice_faces/d2.png");
        diceimg3 = new Image("/img/dice_faces/d3.png");
        diceimg4 = new Image("/img/dice_faces/d4.png");
        diceimg5 = new Image("/img/dice_faces/d5.png");
        diceimg6 = new Image("/img/dice_faces/d6.png");

        //Set default position
        current_pos = 0;

        player1 = new HumanPlayer("Cat Player", Token.CAT, new ArrayList<Property>());
        players = new Player[1];
        players[0] = player1;

        //Setup money
        player1_money.setText("£"+Integer.toString(player1.getMoney().getAmount()));

        //Create gameBoard instance
        gameBoard = new GameBoard(players);
        turnInProgress = false;

        //Load board data
        getTileNames();
    }

    /**
     * Testing feature, takes turn
     *
     */
    public void testButtonClicked() {
        if(!turnInProgress) {
            turnInProgress = true;
            gameBoard.update();
            //Updates current_pos with the new player position
            current_pos = gameBoard.getCurrentPlayerPosition();
            Integer[][] dice_rolls = gameBoard.getDiceRolls();
            displayDice(dice_rolls);
            dialogue_pane.setVisible(true);

            //Sets the player token on the GUI to the new location
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning!");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            alert.setContentText("A turn is already in progress!");
            alert.showAndWait();
        }
    }

    /**
     * Confirms the dice roll, updates position
     */
    public void confirm_rollClicked() {
        playerOneToken.setLayoutX(pos_array.get(current_pos).getLayoutX());
        playerOneToken.setLayoutY(pos_array.get(current_pos).getLayoutY());
        turnInProgress = false;
        dialogue_pane.setVisible(false);
    }

    /**
     * Updates the visual UI of the dice roll
     *
     * @param rolls, the array containing the dice rolls for this turn
     */
    public void displayDice(Integer[][] rolls) {
        if(rolls[0][0] != null) {
            dice1.setImage(getDiceImage(rolls[0][0]));
            dice1.setVisible(true);
        } else { dice1.setVisible(false); }
        if(rolls[0][1] != null) {
            dice2.setImage(getDiceImage(rolls[0][1]));
            dice2.setVisible(true);
        } else { dice2.setVisible(false); }
        if(rolls[1][0] != null) {
            dice3.setImage(getDiceImage(rolls[1][0]));
            dice3.setVisible(true);
        } else { dice3.setVisible(false); }
        if(rolls[1][1] != null) {
            dice4.setImage(getDiceImage(rolls[1][1]));
            dice4.setVisible(true);
        } else { dice4.setVisible(false); }
        if(rolls[2][0] != null) {
            dice5.setImage(getDiceImage(rolls[2][0]));
            dice5.setVisible(true);
        } else { dice5.setVisible(false); }
        if(rolls[2][1] != null) {
            dice6.setImage(getDiceImage(rolls[2][1]));
            dice6.setVisible(true);
        } else { dice6.setVisible(false); }
    }

    /**
     * Returns the correct dice image
     * @param i the integer value that represents the roll number
     * @return the image of the corresponding dice roll
     */
    private Image getDiceImage(Integer i) {
        switch(i) {
            case 1:
                return diceimg1;
            case 2:
                return diceimg2;
            case 3:
                return diceimg3;
            case 4:
                return diceimg4;
            case 5:
                return diceimg5;
            case 6:
                return diceimg6;
            default:
                return null;
        }
    }

    /**
     * Updates the board tile names using data from the gameboard
     */
    public void getTileNames() {
        int[] property_indexes = new int[]{1,3,6,8,9,11,13,14,16,18,19,21,23,24,26,27,29,31,32,34,37,39};
        int iter = 0;
        ArrayList<BoardSpace> board_spaces = gameBoard.getBoardSpaces();
        for (BoardSpace current : board_spaces) {
            if (current instanceof Property) {
                System.out.println(text_array.get(property_indexes[iter]).getText());
                text_array.get(property_indexes[iter]).setText(current.getName());
                iter++;
            }

        }
    }

}
