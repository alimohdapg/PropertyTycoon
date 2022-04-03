import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main java class, used for running the program.
 *
 * @author Kieran Young
 */

public class Main extends Application {

    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    /**
     * Begins the program by loading the menu.
     *
     * @param primaryStage The starting stage that the program will load into.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui/menu.fxml"));
        primaryStage.setTitle("Property Tycoon");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Main method
     *
     */
    public static void main(String[] args) {
        launch(args);
    }
}
