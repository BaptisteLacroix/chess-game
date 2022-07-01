package fenetre;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import board.Board;

public class Fenetre extends Application {

    public void start(Stage primaryStage) throws Exception {
        // construction du stage
        primaryStage.setTitle("Chess Game");
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        // creations de backgrounds
        Background b2 = new Background(new BackgroundFill(Color.rgb(120, 120, 220), CornerRadii.EMPTY, null));
        // construction de la scene
        BorderPane root = new BorderPane();
        root.setBackground(b2);
        Board plateau = new Board(500, 500);
        plateau.setAlignment(Pos.CENTER);
        root.setCenter(plateau);
        // affichage
        Scene primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    public static void lancement(String[] args) {
        Application.launch(args);
    }

}
