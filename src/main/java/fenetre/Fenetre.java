package fenetre;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import board.Board;

/**
 * This class is used to create a window object that is used to display the board and the cards on it.
 *
 * @author Lacroix Baptiste
 */
public class Fenetre extends Application {

    /**
     * It creates a window, sets its title, size, and background color, then creates a board and puts it in the center of
     * the window
     *
     * @param primaryStage The stage is the window that will be displayed.
     */
    public void start(Stage primaryStage) {
        // construction du stage
        primaryStage.setTitle("Chess Game");
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        // creations de backgrounds
        // construction de la scene
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: rgb(59,54,51); -fx-margin: 100px");
        Board plateau = new Board(500, 500);
        plateau.setAlignment(Pos.CENTER);
        root.setCenter(plateau);
        // affichage
        Scene primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    /**
     * It launches the application
     */
    public static void lancement(String[] args) {
        Application.launch(args);
    }

}
