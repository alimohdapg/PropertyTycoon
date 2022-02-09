package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button exitButton, playButton, backButton, fullButton;

    @FXML
    private ImageView gameBoard;

    @FXML
    private AnchorPane menuPane;

    public void exitGame(ActionEvent event) {
        stage = (Stage) menuPane.getScene().getWindow();
        System.out.println("You have closed the game");
        stage.close();
    }

    public void playButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("play.fxml"));
        Stage window = (Stage) playButton.getScene().getWindow();
        window.setScene(new Scene(root, WIDTH, HEIGHT));
    }

    public void backButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root, WIDTH, HEIGHT));
    }

    public void fullGameButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        Stage window = (Stage) fullButton.getScene().getWindow();
        window.setScene(new Scene(root, WIDTH, HEIGHT));
    }

    public void rotateGameBoard() {
        gameBoard.setRotate(gameBoard.getRotate() + 90);
    }


}
