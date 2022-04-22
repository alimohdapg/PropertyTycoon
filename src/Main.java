import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The Main java class, used for running the program.
 *
 * @author Kieran Young
 */
public class Main extends Application {

    /**
     * Begins the program by loading the menu.
     *
     * @param primaryStage The starting stage that the program will load into.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui/menu.fxml")));
        primaryStage.setTitle("Property Tycoon");
        int WIDTH = 1280;
        int HEIGHT = 720;
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Main method that starts the game.
     *
     * @param args The command line arguments, unused here.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
