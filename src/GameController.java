import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The controller class for the main game scene
 *
 * @author Kieran Young
 */

public class GameController {

    @FXML
    private Circle playerOneToken, playerTwoToken;

    @FXML Text playerOneName, playerOneMoney, playerTwoName, playerTwoMoney;

    @FXML
    private AnchorPane dice_roll_pane, property_info, property_info_buy, buy_property_pane;

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
    private Text property_info_name, currentTurnText, property_info_rent, property_info_rent_set, property_info_rent_1house,
            property_info_rent_2house, property_info_rent_3house, property_info_rent_4house, property_info_rent_hotel,
            property_owner, property_houses, property_info_cost, property_info_current;

    @FXML
    private Text property_info_name1, property_info_rent1, property_info_rent_set1, property_info_rent_1house1,
            property_info_rent_2house1, property_info_rent_3house1, property_info_rent_4house1, property_info_rent_hotel1;

    @FXML
    private Text notEnoughMoney;

    @FXML
    private Image diceimg1, diceimg2, diceimg3, diceimg4, diceimg5, diceimg6;

    @FXML
    private ImageView dice1, dice2;

    @FXML
    private Rectangle property_info_color, property_info_color1;

    @FXML
    private Text jailText;

    @FXML
    private Button jailUseCard, jailPay50;

    private Property current_property;

    private int current_pos, currentSelectedProperty;

    private Player playerOne, playerTwo, currentPlayer;

    private GameBoard gameBoard;

    private boolean turnInProgress;
    private boolean canEndTurn;
    private boolean canRoll;

    private FileIO fileIO;
    /**
     * Default function, runs on launch. Initialises the array of positional elements
     */
    public void initialize() {
        //File io
        fileIO = new FileIO();

        //Object arrays for GUI elements
        pos_array = new ArrayList<>();
        text_array = new ArrayList<>();

        //Currently contains dice roll
        dice_roll_pane.setVisible(false);

        //Initiliase the array of GUI objects
        Collections.addAll(pos_array, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22,
                p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40);
        Collections.addAll(text_array, a0_text, a1_text, a2_text, a3_text, a4_text, a5_text, a6_text, a7_text, a8_text, a9_text, a10_text,
                a11_text, a12_text, a13_text, a14_text, a15_text, a16_text, a17_text, a18_text, a19_text, a20_text, a21_text,
                a22_text, a23_text, a24_text, a25_text, a26_text, a27_text, a28_text, a29_text, a30_text, a31_text, a32_text,
                a33_text, a34_text, a35_text, a36_text, a37_text, a38_text, a39_text);

        //Load dice images
        diceimg1 = new Image("/img/dice_faces/d1.png");
        diceimg2 = new Image("/img/dice_faces/d2.png");
        diceimg3 = new Image("/img/dice_faces/d3.png");
        diceimg4 = new Image("/img/dice_faces/d4.png");
        diceimg5 = new Image("/img/dice_faces/d5.png");
        diceimg6 = new Image("/img/dice_faces/d6.png");

        //Initialise players
        playerOne = new HumanPlayer("Cat Player", Token.CAT, new ArrayList<Property>(), new ArrayList<StationAndUtility>(), playerOneToken, playerOneName, playerOneMoney);
        playerTwo = new HumanPlayer("Iron Player", Token.IRON, new ArrayList<Property>(), new ArrayList<StationAndUtility>(), playerTwoToken, playerTwoName, playerTwoMoney);

        //Create gameBoard instance
        gameBoard = new GameBoard(new Player[]{playerOne, playerTwo});
        gameBoard.endTurn();

        current_pos = gameBoard.getCurrentPlayerPosition();
        currentPlayer = gameBoard.getCurrentPlayer();
        currentTurnText.setText(currentPlayer.getName() + "'s turn");

        //Boolean flags
        turnInProgress = false;
        canEndTurn = false;
        canRoll = true;

        //Load board data
        getTileNames();

        //Updates player details on screen
        gameBoard.updateAllPlayers();
    }

    /**
     * Rolls the dice
     */
    public void takeTurn() {
        gameBoard.updateAllPlayers();
        if(!turnInProgress && canRoll) {
            turnInProgress = true;
            gameBoard.update();
            currentPlayer = (HumanPlayer) gameBoard.getCurrentPlayer();
            //Updates current_pos with the new player position
            current_pos = gameBoard.getCurrentPlayerPosition();

            displayDice();
            dice_roll_pane.setVisible(true);
            canRoll = false;

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
     * Ends turn and tells gameBoard to move onto the next player
     */
    public void endTurn() {
        notEnoughMoney.setVisible(false);
        gameBoard.updateAllPlayers();
        if(canEndTurn) {
            gameBoard.endTurn();
            canEndTurn = false;
            canRoll = true;
            currentTurnText.setText(gameBoard.getCurrentPlayer().getName() + "'s turn");
            if(gameBoard.getCurrentPlayer().isInJail()) {
                canRoll = false;
                jailText.setVisible(true);
                jailUseCard.setVisible(true);
                jailPay50.setVisible(true);
            } else {
                jailText.setVisible(false);
                jailUseCard.setVisible(false);
                jailPay50.setVisible(false);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning!");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            alert.setContentText("Cannot End Turn");
            alert.showAndWait();
        }
    }

    /**
     * Method for button onclick uses get out of jail card
     */
    public void useCard () {
        //missing player method for using card
        ;
        gameBoard.updateAllPlayers();
    }

    /**
     * Method for button pay 50 to be released from jail
     */
    public void pay50 () {
        //missing player method for paying 50
        ;
        gameBoard.updateAllPlayers();
    }

    /**
     * Confirms the dice roll, updates position
     */
    public void confirm_rollClicked() {
        current_pos = gameBoard.getCurrentPlayerPosition();
        currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX());
        currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY());

        turnInProgress = false;
        dice_roll_pane.setVisible(false);

        handle_board_space();
    }

    /**
     * Checks the current board space that was landed on and runs the necessary function
     * (Property -> buy/pay rent, Jail -> Go to jail, Station -> ... etc)
     */
    public void handle_board_space() {

        ArrayList<BoardSpace> board_spaces = gameBoard.getBoardSpaces();
        BoardSpace current_space = board_spaces.get(currentPlayer.getLocation());
        if (current_space instanceof Property) {
            loadProperty_buy(currentPlayer.getLocation());
        } else {
            canEndTurn = true;
        }
        gameBoard.updateAllPlayers();

    }

    /**
     * Updates the visual UI of the dice roll
     *
     */
    public void displayDice() {
        dice1.setImage(getDiceImage(gameBoard.getDice1Number()));
        dice1.setVisible(true);
        dice2.setImage(getDiceImage(gameBoard.getDice2Number()));
        dice2.setVisible(true);
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

    public void displayProperty1() { loadProperty(1); }
    public void displayProperty2() { loadProperty(3); }
    public void displayProperty3() { loadProperty(6); }
    public void displayProperty4() { loadProperty(8); }
    public void displayProperty5() { loadProperty(9); }
    public void displayProperty6() { loadProperty(11); }
    public void displayProperty7() { loadProperty(13); }
    public void displayProperty8() { loadProperty(14); }
    public void displayProperty9() { loadProperty(16); }
    public void displayProperty10() { loadProperty(18); }
    public void displayProperty11() { loadProperty(19); }
    public void displayProperty12() { loadProperty(21); }
    public void displayProperty13() { loadProperty(23); }
    public void displayProperty14() { loadProperty(24); }
    public void displayProperty15() { loadProperty(26); }
    public void displayProperty16() { loadProperty(27); }
    public void displayProperty17() { loadProperty(29); }
    public void displayProperty18() { loadProperty(31); }
    public void displayProperty19() { loadProperty(32); }
    public void displayProperty20() { loadProperty(34); }
    public void displayProperty21() { loadProperty(37); }
    public void displayProperty22() { loadProperty(39); }

    /**
     * Loads the selected property on the gameBoard into a card for the user to see
     * @param i the board location
     */
    public void loadProperty(int i) {
        currentSelectedProperty = i;
        property_info.setVisible(true);
        ArrayList<BoardSpace> board_spaces = gameBoard.getBoardSpaces();
        current_property = (Property) board_spaces.get(i);
        property_houses.setText("H: " + current_property.getHouseCount());
        property_info_cost.setText("£" + fileIO.BoardData.get(i).get(7));
        property_info_current.setText("£" + current_property.getRent());
        property_info_name.setText(fileIO.BoardData.get(i).get(1));
        Color c = Color.web(getHex(i));
        property_info_color.setFill(c);

        property_info_rent.setText("£" + fileIO.BoardData.get(i).get(8));
        property_info_rent_set.setText("£" + Integer.parseInt(fileIO.BoardData.get(i).get(8)) * 2);

        property_info_rent_1house.setText("£" + fileIO.BoardData.get(i).get(10));

        property_info_rent_2house.setText("£" + fileIO.BoardData.get(i).get(11));

        property_info_rent_3house.setText("£" + fileIO.BoardData.get(i).get(12));

        property_info_rent_4house.setText("£" + fileIO.BoardData.get(i).get(13));

        property_info_rent_hotel.setText("£" + fileIO.BoardData.get(i).get(14));

    }

    /**
     * Duplicate of loadProperty but for the buy property panel
     * @param i the board location
     */
    public void loadProperty_buy(int i) {
        buy_property_pane.setVisible(true);
        property_info_buy.setVisible(true);
        ArrayList<BoardSpace> board_spaces = gameBoard.getBoardSpaces();
        Property current_property = (Property) board_spaces.get(i);
        current_property.sellHotel();
        current_property.setHouseCount(0);
        property_info_name1.setText(current_property.getName());
        Color c = Color.web(getHex(i));
        property_info_color1.setFill(c);
        property_info_rent1.setText("£" + current_property.getRent());
        property_info_rent_set1.setText("£" + current_property.getRent() * 2);
        current_property.setHouseCount(1);
        property_info_rent_1house1.setText("£" + current_property.getRent());
        current_property.setHouseCount(2);
        property_info_rent_2house1.setText("£" + current_property.getRent());
        current_property.setHouseCount(3);
        property_info_rent_3house1.setText("£" + current_property.getRent());
        current_property.setHouseCount(4);
        property_info_rent_4house1.setText("£" + current_property.getRent());
        current_property.buyHotel();
        property_info_rent_hotel1.setText("£" + current_property.getRent());
    }

    /**
     * Method for the buy property button
     */
    public void buy_property_button_yes() {
        notEnoughMoney.setVisible(false);
        ArrayList<BoardSpace> board_spaces = gameBoard.getBoardSpaces();
        Property current_property = (Property) board_spaces.get(currentPlayer.getLocation());

        boolean success = currentPlayer.buyProperty(current_property);
        gameBoard.updateAllPlayers();
        if(success) {
            System.out.println(currentPlayer.getName() + " has bought " + current_property.getName());
            buy_property_pane.setVisible(false);
            canEndTurn = true;
        } else {
            notEnoughMoney.setVisible(true);
        }

    }

    /**
     * Method for the rejecting the buy property offer
     */
    public void buy_property_button_no() {
        ArrayList<BoardSpace> board_spaces = gameBoard.getBoardSpaces();
        current_property = (Property) board_spaces.get(currentPlayer.getLocation());
        System.out.println(currentPlayer.getName() + " has not bought " + current_property.getName());
        canEndTurn = true;
        buy_property_pane.setVisible(false);
        gameBoard.updateAllPlayers();
    }

    /**
     * Buys a house on the selected tile
     */
    public void buyHouse() {
        boolean success = currentPlayer.buyAHouse((Property) gameBoard.getBoardSpaces().get(currentSelectedProperty));
        gameBoard.updateAllPlayers();
        if(success && gameBoard.checkBuyHouse(current_property, currentPlayer)) {
            property_houses.setText("H: " + current_property.getHouseCount());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning!");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            alert.setContentText("Failed to buy a house!");
            alert.showAndWait();
        }
    }

    /**
     * Method upon clicking the property card to hide it
     */
    public void closeProperty() {
        property_info.setVisible(false);
    }

    /**
     * Method for getting the card colour data
     * @param i the board space
     * @return the hex value representing the colour
     */
    public String getHex(int i){
        switch(i) {
            case 1:
            case 3:
                return "#915336";
            case 6:
            case 8:
            case 9:
                return "#87a5d6";
            case 11:
            case 13:
            case 14:
                return "#ed3878";
            case 16:
            case 18:
            case 19:
                return "#f57f22";
            case 21:
            case 23:
            case 24:
                return "#ef3823";
            case 26:
            case 27:
            case 29:
                return "#fde701";
            case 31:
            case 32:
            case 34:
                return "#10a55b";
            case 37:
            case 39:
                return "#264da1";
        }
        //If fails
        return "#264da1";
    }

}
