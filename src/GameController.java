import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The controller class for the main game scene
 *
 * @author Kieran Young
 */

public class GameController {

    @FXML
    private Circle playerOneToken, playerTwoToken, playerThreeToken, playerFourToken, playerFiveToken, p0, p1, p2, p3,
            p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26,
            p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40;;

    @FXML
    private Pane player_1, player_2, player_3, player_4, player_5;

    @FXML Text playerOneName, playerOneMoney, playerTwoName, playerTwoMoney, playerThreeName, playerThreeMoney, playerFourName, playerFourMoney,
            playerFiveName, playerFiveMoney, FreeParking, a0_text, a1_text, a2_text, a3_text, a4_text, a5_text,
            a6_text, a7_text, a8_text, a9_text, a10_text, a11_text, a12_text, a13_text, a14_text, a15_text, a16_text,
            a17_text, a18_text, a19_text, a20_text, a21_text, a22_text, a23_text, a24_text, a25_text, a26_text,
            a27_text, a28_text, a29_text, a30_text, a31_text, a32_text, a33_text, a34_text, a35_text, a36_text,
            a37_text, a38_text, a39_text, property_info_name, currentTurnText, property_info_rent,
            property_info_rent_set, property_info_rent_1house, property_info_rent_2house, property_info_rent_3house,
            property_info_rent_4house, property_info_rent_hotel, property_owner, property_houses, property_info_cost,
            property_info_current, property_info_name1, property_info_rent1, property_info_rent_set1,
            property_info_rent_1house1, property_info_rent_2house1, property_info_rent_3house1,
            property_info_rent_4house1, property_info_rent_hotel1, property_info_cost1, notEnoughMoney, jailText,
            fine_text, ptex1, ptex2, ptex3, ptex4, ptex5, ptex6, ptex7, crtext, ptex11, ptex12, ptex13, ptex14, ptex15,
            ptex16, ptex17, rentPrice, rentOwner, PLDescription, PLAction, OKDescription, OKAction, bidList;

    @FXML
    private AnchorPane dice_roll_pane, property_info, property_info_buy, buy_property_pane, fine_pane, jail_pane,
    PLPane, OKPane, playerSelectPane, buy_property_pane_info, auctionPane, rentPane;

    @FXML
    private TextField tbox1, tbox2, tbox3, tbox4, tbox5, bidInput;

    @FXML
    private CheckBox check1, check2, check3, check4, check5;

    @FXML
    private ArrayList<Circle> pos_array;

    @FXML
    private ArrayList<Text> text_array;

    @FXML
    private Button buyHouseBtn, btnSellProp, btnSellHouse, btnMortgage, jailUseCard, jailPay50, rollButton;

    @FXML
    private Image diceimg1, diceimg2, diceimg3, diceimg4, diceimg5, diceimg6;

    @FXML
    private ImageView dice1, dice2;

    @FXML
    private Rectangle property_info_color, property_info_color1;

    private ArrayList<Player> playerList, playerList_Auction;

    private Property current_property;

    private int current_pos, currentSelectedProperty, max_bid, max_bid_pos, bid_pos;

    private Player playerOne, playerTwo, playerThree, playerFour, playerFive, currentPlayer;

    private GameBoard gameBoard;
    private OpportunityKnock opportunityKnocks;
    private PotLuck potLuck;

    private boolean turnInProgress;
    private boolean canEndTurn;
    private boolean canRoll;

    private FileIO fileIO;

    private int[] bids;

    /**
     * Default function, runs on launch. Initialises the array of positional elements
     */
    public void initialize() {
        playerSelectPane.setVisible(true);
    }

    /**
     * Loads all the necessary objects to begin the game and starts the game loop
     */
    public void startGame() {
        playerSelectPane.setVisible(false);

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

        //Load the players
        playerList = new ArrayList<Player>();
        if(check1.isSelected()) {
            playerOne = new HumanPlayer(tbox1.getText(),Token.CAT, new ArrayList<Property>(), new ArrayList<StationAndUtility>(), playerOneToken, playerOneName, playerOneMoney);
            player_1.setVisible(true);
            playerOneToken.setVisible(true);
            playerList.add(playerOne);
        } else { player_1.setVisible(false); playerOneToken.setVisible(false); }
        if(check2.isSelected()) {
            playerTwo = new HumanPlayer(tbox2.getText(),Token.IRON, new ArrayList<Property>(), new ArrayList<StationAndUtility>(), playerTwoToken, playerTwoName, playerTwoMoney);
            player_2.setVisible(true);
            playerTwoToken.setVisible(true);
            playerList.add(playerTwo);
        } else { player_2.setVisible(false); playerTwoToken.setVisible(false); }
        if(check3.isSelected()) {
            playerThree = new HumanPlayer(tbox3.getText(),Token.BOOT, new ArrayList<Property>(), new ArrayList<StationAndUtility>(), playerThreeToken, playerThreeName, playerThreeMoney);
            player_3.setVisible(true);
            playerThreeToken.setVisible(true);
            playerList.add(playerThree);
        } else { player_3.setVisible(false); playerThreeToken.setVisible(false); }
        if(check4.isSelected()) {
            playerFour = new HumanPlayer(tbox4.getText(),Token.HATSTAND, new ArrayList<Property>(), new ArrayList<StationAndUtility>(), playerFourToken, playerFourName, playerFourMoney);
            player_4.setVisible(true);
            playerFourToken.setVisible(true);
            playerList.add(playerFour);
        } else { player_4.setVisible(false); playerFourToken.setVisible(false);}
        if(check5.isSelected()) {
            playerFive = new HumanPlayer(tbox5.getText(),Token.SHIP, new ArrayList<Property>(), new ArrayList<StationAndUtility>(), playerFiveToken, playerFiveName, playerFiveMoney);
            player_5.setVisible(true);
            playerFiveToken.setVisible(true);
            playerList.add(playerFive);
        } else { player_5.setVisible(false); playerFiveToken.setVisible(false); }

        //Create gameBoard instance
        gameBoard = new GameBoard(playerList);
        gameBoard.endTurn();

        //OK/PL
        opportunityKnocks = new OpportunityKnock("OK");
        potLuck = new PotLuck("PL");

        //Current status variables
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
        gameBoard.update();
        currentPlayer = gameBoard.getCurrentPlayer();
        if(!turnInProgress && canRoll) {
            turnInProgress = true;
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
                System.out.println("Current player is in jail");
                jail_pane.setVisible(true);
                rollButton.setVisible(false);
                canRoll = false;
                canEndTurn = true;
            } else {
                rollButton.setVisible(true);
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
        if(currentPlayer.isHasCard()) {
            currentPlayer.setHasCard(false);
            currentPlayer.setOutJail();
            jail_pane.setVisible(false);
            gameBoard.updateAllPlayers();
            canEndTurn = true;
        }
        /*
        if(currentPlayer.isHasCardOK()) {
            currentPlayer.setHasCardOK(false);
            currentPlayer.setOutJail();
            jail_pane.setVisible(false);
            gameBoard.updateAllPlayers();
            canEndTurn = true;

         */
        //opportunityKnocks.addJail??
    }

    /**
     * Method for button pay 50 to be released from jail
     */
    public void pay50 () {
        gameBoard.getCurrentPlayer().getMoney().subtractAmount(50);
        gameBoard.getCurrentPlayer().setOutJail();
        gameBoard.updateAllPlayers();
        jail_pane.setVisible(false);
        current_pos = gameBoard.getCurrentPlayerPosition();
        canEndTurn = true;
    }

    /**
     * Confirms the dice roll, updates position
     */
    public void confirm_rollClicked() {
        current_pos = gameBoard.getCurrentPlayerPosition();
        if(currentPlayer == playerOne) {
            currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX());
            currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY());
        }
        if(currentPlayer == playerTwo) {
            currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX()+10);
            currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY());
        }
        if(currentPlayer == playerThree) {
            currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX());
            currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY()+10);
        }
        if(currentPlayer == playerFour) {
            currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX()-10);
            currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY());
        }
        if(currentPlayer == playerFive) {
            currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX());
            currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY() - 10);
        }


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
        if (current_space instanceof Property && currentPlayer.isPassedGo()) {
            current_space = (Property) board_spaces.get(currentPlayer.getLocation());
            if(((Property) current_space).getOwner() == null) {
                loadProperty_buy(currentPlayer.getLocation());
            } else {
                rentPane.setVisible(true);
                rentPrice.setText("£" + Integer.toString(current_property.getRent()));
                rentOwner.setText(current_property.getOwner().getName());
                canEndTurn = true;
            }
        } else if(current_space instanceof StationAndUtility && currentPlayer.isPassedGo()) {
            loadProperty_buy(currentPlayer.getLocation());
        } else if(currentPlayer.getLocation() == 4 || currentPlayer.getLocation() == 38){
            loadFineScreen();
        } else if(currentPlayer.getLocation() == 2 || currentPlayer.getLocation() == 17 || currentPlayer.getLocation() == 33) {
            loadPotLuck();
        } else if(currentPlayer.getLocation() == 7 || currentPlayer.getLocation() == 22 || currentPlayer.getLocation() == 36) {
            loadOpportunityKnocks();
        } else {
            canEndTurn = true;
        }
        gameBoard.updateAllPlayers();
        updateFreeParking();

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

    //Onclick functions for the properties, stations and utilities
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

    public void displayStation1() { loadProperty(5); }
    public void displayStation2() { loadProperty(15);}
    public void displayStation3() { loadProperty(25);}
    public void displayStation4() { loadProperty(35);}

    public void displayUtility1() { loadProperty(12);}
    public void displayUtility2() { loadProperty(28);}

    /**
     * Loads the selected property on the gameBoard into a card for the user to see
     * @param i the board location
     */
    public void loadProperty(int i) {
        currentSelectedProperty = i;
        property_info.setVisible(true);
        ArrayList<BoardSpace> board_spaces = gameBoard.getBoardSpaces();

        if(board_spaces.get(i) instanceof Property){
            crtext.setText("Current rent:");
            current_property = (Property) board_spaces.get(i);
            Player owner = current_property.getOwner();

            if(owner == currentPlayer) {
                btnSellProp.setOpacity(1);
                btnMortgage.setOpacity(1);
                btnSellProp.setDisable(false);
                btnMortgage.setDisable(false);
                if(current_property.getHouseCount() > 0) {
                    btnSellHouse.setOpacity(1);
                    btnSellHouse.setDisable(false);
                } else {
                    btnSellHouse.setOpacity(0.25);
                    btnSellHouse.setDisable(true);
                }
            } else {
                btnSellProp.setOpacity(0.25);
                btnMortgage.setOpacity(0.25);
                btnSellProp.setDisable(true);
                btnMortgage.setDisable(true);
            }

            if(owner != null) {
                property_owner.setText("Owner: " + owner.getName());
            } else { property_owner.setText("Owner: Vacant"); }
            property_houses.setText("H: " + current_property.getHouseCount());
            property_houses.setVisible(true);
            property_info_rent.setVisible(true);
            property_info_rent_set.setVisible(true);
            property_info_rent_1house.setVisible(true);
            property_info_rent_2house.setVisible(true);
            property_info_rent_3house.setVisible(true);
            property_info_rent_4house.setVisible(true);
            property_info_rent_hotel.setVisible(true);
            buyHouseBtn.setVisible(true);
            ptex1.setVisible(true);
            ptex2.setVisible(true);
            ptex3.setVisible(true);
            ptex4.setVisible(true);
            ptex5.setVisible(true);
            ptex6.setVisible(true);
            ptex7.setVisible(true);

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
        } else if(board_spaces.get(i) instanceof StationAndUtility) {
            StationAndUtility current_stationutil = (StationAndUtility) board_spaces.get(i);

            if(current_stationutil.getColor() == ColorOfSet.STATION) {
                crtext.setText("Current rent:");
                property_houses.setVisible(false);
                Color c = Color.web("#FFFFFF");
                property_info_color.setFill(c);
                property_info_name.setText(current_stationutil.getName());
                property_info_cost.setText("£" + fileIO.BoardData.get(i).get(7));
                property_info_current.setText("£" + current_stationutil.getStationRent(1));//PROBLEM!!
                property_info_rent.setVisible(false);
                property_info_rent_set.setVisible(false);
                property_info_rent_1house.setVisible(false);
                property_info_rent_2house.setVisible(false);
                property_info_rent_3house.setVisible(false);
                property_info_rent_4house.setVisible(false);
                property_info_rent_hotel.setVisible(false);
                ptex1.setVisible(false);
                ptex2.setVisible(false);
                ptex3.setVisible(false);
                ptex4.setVisible(false);
                ptex5.setVisible(false);
                ptex6.setVisible(false);
                ptex7.setVisible(false);
                buyHouseBtn.setVisible(false);
            }else if(current_stationutil.getColor() == ColorOfSet.UTILITIES) {
                crtext.setText("Dice multiplier");
                property_houses.setVisible(false);
                property_info_name.setText(current_stationutil.getName());
                Color c = Color.web("#FFFFFF");
                property_info_color.setFill(c);
                property_info_cost.setText("£" + fileIO.BoardData.get(i).get(7));
                property_info_current.setText("£" + current_stationutil.getUtilityRent(1,1));//PROBLEM!!
                property_info_rent.setVisible(false);
                property_info_rent_set.setVisible(false);
                property_info_rent_1house.setVisible(false);
                property_info_rent_2house.setVisible(false);
                property_info_rent_3house.setVisible(false);
                property_info_rent_4house.setVisible(false);
                property_info_rent_hotel.setVisible(false);
                ptex1.setVisible(false);
                ptex2.setVisible(false);
                ptex3.setVisible(false);
                ptex4.setVisible(false);
                ptex5.setVisible(false);
                ptex6.setVisible(false);
                ptex7.setVisible(false);
                buyHouseBtn.setVisible(false);
            }

        }


    }


    /**
     * Duplicate of loadProperty but for the buy property panel
     * @param i the board location
     */
    public void loadProperty_buy(int i) {
        auctionPane.setVisible(false);
        buy_property_pane.setVisible(true);
        buy_property_pane_info.setVisible(true);
        property_info_buy.setVisible(true);
        ArrayList<BoardSpace> board_spaces = gameBoard.getBoardSpaces();

        if(board_spaces.get(i) instanceof Property) {
            Property current_property = (Property) board_spaces.get(i);

            property_info_name1.setText(fileIO.BoardData.get(i).get(1));
            Color c = Color.web(getHex(i));
            property_info_color1.setFill(c);
            property_info_cost1.setText("£" + fileIO.BoardData.get(i).get(7));

            property_info_rent1.setVisible(true);
            property_info_rent_set1.setVisible(true);
            property_info_rent_1house1.setVisible(true);
            property_info_rent_2house1.setVisible(true);
            property_info_rent_3house1.setVisible(true);
            property_info_rent_4house1.setVisible(true);
            property_info_rent_hotel1.setVisible(true);
            ptex11.setVisible(true);
            ptex12.setVisible(true);
            ptex13.setVisible(true);
            ptex14.setVisible(true);
            ptex15.setVisible(true);
            ptex16.setVisible(true);
            ptex17.setVisible(true);


            property_info_rent1.setText("£" + current_property.getRent());
            property_info_rent_set1.setText("£" + Integer.parseInt(fileIO.BoardData.get(i).get(8)) * 2);

            property_info_rent_1house1.setText("£" + fileIO.BoardData.get(i).get(10));
            property_info_rent_2house1.setText("£" + fileIO.BoardData.get(i).get(11));
            property_info_rent_3house1.setText("£" + fileIO.BoardData.get(i).get(12));
            property_info_rent_4house1.setText("£" + fileIO.BoardData.get(i).get(13));
            property_info_rent_hotel1.setText("£" + fileIO.BoardData.get(i).get(14));
        } else if(board_spaces.get(i) instanceof StationAndUtility) {
            StationAndUtility current_stationutil = (StationAndUtility) board_spaces.get(i);

            property_info_rent1.setVisible(false);
            property_info_rent_set1.setVisible(false);
            property_info_rent_1house1.setVisible(false);
            property_info_rent_2house1.setVisible(false);
            property_info_rent_3house1.setVisible(false);
            property_info_rent_4house1.setVisible(false);
            property_info_rent_hotel1.setVisible(false);
            ptex11.setVisible(false);
            ptex12.setVisible(false);
            ptex13.setVisible(false);
            ptex14.setVisible(false);
            ptex15.setVisible(false);
            ptex16.setVisible(false);
            ptex17.setVisible(false);


            if(current_stationutil.getColor() == ColorOfSet.STATION) {
                Color c = Color.web("#FFFFFF");
                property_info_color1.setFill(c);
                property_info_name1.setText(current_stationutil.getName());
                property_info_cost1.setText("£" + fileIO.BoardData.get(i).get(7));
            } else if(current_stationutil.getColor() == ColorOfSet.UTILITIES) {
                property_info_name1.setText(current_stationutil.getName());
                Color c = Color.web("#FFFFFF");
                property_info_color1.setFill(c);
                property_info_cost1.setText("£" + fileIO.BoardData.get(i).get(7));
            }
        }
    }

    /**
     * Method for the buy property button
     */
    public void buy_property_button_yes() {
        notEnoughMoney.setVisible(false);
        ArrayList<BoardSpace> board_spaces = gameBoard.getBoardSpaces();
        int i = currentPlayer.getLocation();
        if(board_spaces.get(i) instanceof Property) {
            Property current_property = (Property) board_spaces.get(i);
            boolean success = currentPlayer.buyProperty(current_property);
            gameBoard.updateAllPlayers();
            if(success) {
                System.out.println(currentPlayer.getName() + " has bought " + current_property.getName());
                buy_property_pane.setVisible(false);
                canEndTurn = true;
            } else {
                notEnoughMoney.setVisible(true);
            }
        } else if(board_spaces.get(i) instanceof StationAndUtility) {
            StationAndUtility current_stationutil = (StationAndUtility) board_spaces.get(i);
            boolean success = currentPlayer.buyStaUti(current_stationutil);
            gameBoard.updateAllPlayers();
            if(success) {
                System.out.println(currentPlayer.getName() + " has bought " + current_stationutil.getName());
                buy_property_pane.setVisible(false);
                canEndTurn = true;
            } else {
                notEnoughMoney.setVisible(true);
            }
        }

    }

    /**
     * Method for the rejecting the buy property offer
     */
    public void buy_property_button_no() {
        ArrayList<BoardSpace> board_spaces = gameBoard.getBoardSpaces();
        int i = currentPlayer.getLocation();
        if(board_spaces.get(i) instanceof Property) {
            current_property = (Property) board_spaces.get(currentPlayer.getLocation());
            System.out.println(currentPlayer.getName() + " has not bought " + current_property.getName());
        } else if(board_spaces.get(i) instanceof StationAndUtility) {
            StationAndUtility current_stationutil = (StationAndUtility) board_spaces.get(currentPlayer.getLocation());
            System.out.println(currentPlayer.getName() + " has not bought " + current_stationutil.getName());
        }


        //Do not remove these comments. This is the auctioning feature which is not working at the time of completion
        buy_property_pane_info.setVisible(false);
        //auctionPane.setVisible(true);
        //startAuction();
        buy_property_pane.setVisible(false);
        canEndTurn = true;
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
        btnSellHouse.setOpacity(0.25);
        btnSellProp.setOpacity(0.25);
        btnMortgage.setOpacity(0.25);

        btnSellHouse.setDisable(true);
        btnSellProp.setDisable(true);
        btnMortgage.setDisable(true);
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

    /**
     * Updates the free parking text
     */
    public void updateFreeParking() {
        FreeParking.setText("Free Parking: £" + gameBoard.getFreeParkingSum());
    }

    /*
     * Displays the fine screen
     */
    public void loadFineScreen() {
        if(currentPlayer.getLocation() == 4) {
            fine_text.setText("You were fined £200!");
        } else if(currentPlayer.getLocation() == 38) {
            fine_text.setText("You were fined £100!");
        }
        fine_pane.setVisible(true);
    }

    /**
     * On click function for the button confirming the fine paid
     */
    public void confirmFine() {
        fine_pane.setVisible(false);
        canEndTurn = true;
    }

    /**
     * On click function for the button selling a property
     */
    public void buttonSellProp() {
        System.out.println("sold");
        BoardSpace current = gameBoard.getBoardSpaces().get(currentSelectedProperty);
        if(current instanceof Property) {
            current_property = (Property) current;
            currentPlayer.sellProperty(current_property);
            gameBoard.updateAllPlayers();
        }
    }

    /**
     * On click function for the button selling a house
     */
    public void buttonSellHouse() {
        System.out.println("Sold house");
        BoardSpace current = gameBoard.getBoardSpaces().get(currentSelectedProperty);
        if(current instanceof Property) {
            current_property = (Property) current;
            currentPlayer.sellAHouse(current_property);
            gameBoard.updateAllPlayers();
        }
    }

    /**
     * On click function for the button mortgaging a property
     */
    public void buttonMortgage() {
        System.out.println("Mortgaged");
        BoardSpace current = gameBoard.getBoardSpaces().get(currentSelectedProperty);
        if(current instanceof Property) {
            current_property = (Property) current;
            currentPlayer.mortgageProperty(current_property);
            gameBoard.updateAllPlayers();
        }
    }

    /**
     * Loads the potluck screen and pulls a card
     */
    public void loadPotLuck() {
        PLPane.setVisible(true);
        ArrayList<String> info = potLuck.getNextCard();
        int plcardno = Integer.parseInt(info.get(0));
        String desc = fileIO.PotLuckCardData.get(plcardno - 1).get(0);
        PLDescription.setText(desc.substring(3, desc.length()-3));
        PLAction.setText(fileIO.PotLuckCardData.get(plcardno - 1).get(3));
        switch (plcardno) {
            case 1:
            case 2:
            case 4:
            case 5:
            case 9:
            case 13:
            case 15:
                potLuck.playerReceivesMoney(currentPlayer, Integer.parseInt(info.get(1)));
                break;
            case 16:
                potLuck.playerReceivesMoney(currentPlayer, playerList, Integer.parseInt(info.get(1)));
                Cash temp_cash = currentPlayer.getMoney();
                temp_cash.addAmount(Integer.parseInt(info.get(1)));
                currentPlayer.setMoney(temp_cash);
                break;
            case 6:
            case 7:
            case 10:
                potLuck.playerLosesMoney(currentPlayer, Integer.parseInt(info.get(1)));
                break;
            case 11:
            case 12:
                potLuck.playerLosesMoney(currentPlayer, Integer.parseInt(info.get(1)));
                gameBoard.addToFreeParking(Integer.parseInt(info.get(1)));
                break;
            case 17:
                potLuck.getFreeJailCard(currentPlayer);
                break;
            case 3:
            case 8:
            case 14:
                potLuck.setPlayerTo(currentPlayer, Integer.parseInt(info.get(1)));
                current_pos = gameBoard.getCurrentPlayerPosition();
                if(currentPlayer == playerOne) {
                    currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX());
                    currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY());
                }
                if(currentPlayer == playerTwo) {
                    currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX()+10);
                    currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY());
                }
                if(currentPlayer == playerThree) {
                    currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX());
                    currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY()+10);
                }
                if(currentPlayer == playerFour) {
                    currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX()-10);
                    currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY());
                }
                if(currentPlayer == playerFive) {
                    currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX());
                    currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY() - 10);
                }
                break;
        }
    }

    /**
     * Loads the opportunity knocks screen and pulls a card
     */
    public void loadOpportunityKnocks() {
        OKPane.setVisible(true);
        ArrayList<String> info = opportunityKnocks.getNextCard();
        int okcardno = Integer.parseInt(info.get(0));
        String desc = fileIO.PotLuckCardData.get(okcardno - 1).get(0);
        OKDescription.setText(desc.substring(3, desc.length()-3));
        OKAction.setText(fileIO.PotLuckCardData.get(okcardno - 1).get(3));
        switch (okcardno) {
            case 1:
            case 2:
            case 8:
                opportunityKnocks.playerReceiveMoney(currentPlayer, Integer.parseInt(info.get(1)));
                break;
            case 3:
            case 4:
            case 7:
            case 10:
            case 12:
            case 13:
            case 14:
                opportunityKnocks.setPlayerTo(currentPlayer, Integer.parseInt(info.get(1)));
                current_pos = gameBoard.getCurrentPlayerPosition();
                if(currentPlayer == playerOne) {
                    currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX());
                    currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY());
                }
                if(currentPlayer == playerTwo) {
                    currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX()+10);
                    currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY());
                }
                if(currentPlayer == playerThree) {
                    currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX());
                    currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY()+10);
                }
                if(currentPlayer == playerFour) {
                    currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX()-10);
                    currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY());
                }
                if(currentPlayer == playerFive) {
                    currentPlayer.getBoardToken().setLayoutX(pos_array.get(current_pos).getLayoutX());
                    currentPlayer.getBoardToken().setLayoutY(pos_array.get(current_pos).getLayoutY() - 10);
                }
                break;
            case 6:
                opportunityKnocks.playerLoseMoney(currentPlayer, Integer.parseInt(info.get(1)));
                break;
            case 5:
            case 15:
                opportunityKnocks.playerLoseMoney(currentPlayer, Integer.parseInt(info.get(1)));
                gameBoard.addToFreeParking(Integer.parseInt(info.get(1)));
                break;
            case 9:
            case 11:
                opportunityKnocks.playerRepairProperties(currentPlayer, info.get(1));
                break;
            case 16:
                opportunityKnocks.getFreeJailCard(currentPlayer);
                break;
        }
    }

    /**
     * On click function for the button confirming the potluck card
     */
    public void confirmPL() {
        PLPane.setVisible(false);
        canEndTurn = true;
    }

    /**
     * On click function for the button confirming the opportunity knocks card
     */
    public void confirmOK() {
        OKPane.setVisible(false);
        canEndTurn = true;
    }

    /**
     * On click function for the button confirming the rent paid
     */
    public void confirmRent() {
        rentPane.setVisible(false);
    }

    public void startAuction() {
        playerList_Auction = gameBoard.getPlayers();
        bids = new int[playerList_Auction.size()];
        bidList.setText(playerList_Auction.get(bid_pos).getName() + " is bidding.\n");
        max_bid = 0;
        max_bid_pos = 0;
        bid_pos = 0;
    }

    public void endAuction() {
        auctionPane.setVisible(false);
        canEndTurn = true;
        buy_property_pane.setVisible(false);
        gameBoard.updateAllPlayers();
        if(max_bid != 0) {
            playerList_Auction.get(max_bid_pos).auctionProperty(current_property, max_bid);
        }

    }

    public void submitBid() {
        int desired_bid = Integer.parseInt(bidInput.getText());
        System.out.println(bid_pos);
        if (desired_bid <= playerList_Auction.get(bid_pos).getMoney().getAmount()) {
            bids[bid_pos] = desired_bid;
            if(bids[bid_pos] > max_bid) {
                max_bid = bids[bid_pos];
                max_bid_pos = bid_pos;
            }
            String listText = bidList.getText();
            listText += playerList_Auction.get(bid_pos).getName() + " has bid £" + bids[bid_pos] + "\n";
            bidList.setText(listText);
            bid_pos++;

            boolean cond = false;
            while(!cond) {
                if(!playerList_Auction.get(bid_pos).isPassedGo()) {
                    bid_pos++;
                    if(bid_pos >= playerList_Auction.size()) {
                        endAuction();
                    }
                } else {
                    cond = true;
                }
            }

            if(bid_pos >= playerList_Auction.size()) {
                endAuction();
            } else {
                String listText2 = bidList.getText();
                listText2 += playerList_Auction.get(bid_pos).getName() + " is bidding. \n";
                bidList.setText(listText2);
            }
        }
    }

    public void skipBid() {
        String listText = bidList.getText();
        listText += playerList_Auction.get(bid_pos).getName() + " has skipped. \n" + bids[bid_pos];
        bidList.setText(listText);
        bid_pos++;

        if(bid_pos >= playerList_Auction.size()) {
            endAuction();
        } else {
            String listText2 = bidList.getText();
            listText2 += playerList_Auction.get(bid_pos).getName() + " is bidding. \n";
            bidList.setText(listText2);
        }
    }
}
