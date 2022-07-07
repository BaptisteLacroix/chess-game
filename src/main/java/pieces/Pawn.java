package pieces;

import board.Case;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Pawn {
    private static final String red = "-fx-background-color: red;";
    private static final ImageView image = new ImageView("file:./resources/dot.png");
    private static final ImageView image2 = new ImageView("file:./resources/dot.png");

    public static boolean checkTheWhiteMove(Dragboard db, VBox vBox, Case source, Case piece) {
        return (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() && piece.getValue() == 0)
                || ((db.getDragViewOffsetX() == source.getX() - 2 && db.getDragViewOffsetY() == source.getY() && piece.getValue() == 0) &&
                (((Case) ((HBox) vBox.getChildren().get(source.getX() - 2)).getChildren().get(source.getY() - 1)).getValue() == 0)) ||
                ((db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() - 1 ||
                        db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() + 1) && piece.getValue() < 0);
    }

    /**
     * This function will check all the possible moves of a white pawn.
     *
     * @param piece the piece that is being moved
     */
    public static void whitePawnMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        if (piece.getX() > 2 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 1).getValue() == 0) {
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            image2.setFitHeight(500 / (double) 8);
            image2.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setGraphic(image);
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 1).setGraphic(image2);
        } else if (piece.getX() > 2 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 1).getValue() < 0) {
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setGraphic(image);
        } else {
            if (piece.getX() > 1 && piece.getY() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).getValue() < 0) {
                liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).setStyle(red);
            }
            if (piece.getX() > 1 && piece.getY() < 8 && liste_cases.get(piece.getX() - 2).get(piece.getY()).getValue() < 0) {
                liste_cases.get(piece.getX() - 2).get(piece.getY()).setStyle(red);
            }
        }
    }

    /**
     * It resets the pawn's moves
     *
     * @param piece the piece we want to reset the moves for
     * @param color the color of the piece
     */
    public static void resetWhitePawnMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        if (piece.getX() > 2 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 1).getValue() == 0) {
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            image2.setFitHeight(500 / (double) 8);
            image2.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setGraphic(null);
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 1).setGraphic(null);
        } else if (piece.getX() > 2 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 1).getValue() < 0) {
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setGraphic(null);
        } else {
            if (piece.getX() > 1 && piece.getY() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).getValue() < 0) {
                liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).setStyle(color.toString());
            }
            if (piece.getX() > 1 && piece.getY() < 8 && liste_cases.get(piece.getX() - 2).get(piece.getY()).getValue() < 0) {
                liste_cases.get(piece.getX() - 2).get(piece.getY()).setStyle(color.toString());
            }
        }
    }

    public static boolean checkTheBlackMove(Dragboard db, VBox vBox, Case source, Case piece) {
        return ((db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() && piece.getValue() == 0)
                || (piece.getX() < 8 && (db.getDragViewOffsetX() == source.getX() + 2 && db.getDragViewOffsetY() == source.getY() && piece.getValue() == 0) &&
                ((Case) ((HBox) vBox.getChildren().get(source.getX())).getChildren().get(source.getY() - 1)).getValue() == 0) ||
                (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() - 1 ||
                        db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() + 1) && piece.getValue() > 0);
    }

    /**
     * This function will check all the possible moves of a black pawn.
     *
     * @param piece the piece that is being moved
     */
    public static void blackPawnMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        if (piece.getX() < 7 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 1).getValue() == 0) {
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            image2.setFitHeight(500 / (double) 8);
            image2.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setGraphic(image);
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 1).setGraphic(image2);

        } else if (piece.getX() < 7 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 1).getValue() > 0) {
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setGraphic(image);
        } else {
            if (piece.getX() < 8 && piece.getY() > 1 && liste_cases.get(piece.getX()).get(piece.getY() - 2).getValue() > 0) {
                liste_cases.get(piece.getX()).get(piece.getY() - 2).setStyle(red);
            }
            if (piece.getX() < 8 && piece.getY() < 8 && liste_cases.get(piece.getX()).get(piece.getY()).getValue() > 0) {
                liste_cases.get(piece.getX()).get(piece.getY()).setStyle(red);
            }
        }
    }

    /**
     * It resets the pawn's moves
     *
     * @param piece the piece that is being moved
     * @param color the color of the pawn
     */
    public static void resetBlackPawnMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        if (piece.getX() < 7 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 1).getValue() == 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setGraphic(null);
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 1).setGraphic(null);

        } else if (piece.getX() < 7 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 1).getValue() != 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setGraphic(null);

        } else {
            if (piece.getX() < 8 && piece.getY() > 1 && liste_cases.get(piece.getX()).get(piece.getY() - 2).getValue() > 0) {
                liste_cases.get(piece.getX()).get(piece.getY() - 2).setStyle(color.toString());
            }
            if (piece.getX() < 8 && piece.getY() < 8 && liste_cases.get(piece.getX()).get(piece.getY()).getValue() > 0) {
                liste_cases.get(piece.getX()).get(piece.getY()).setStyle(color.toString());
            }
        }
    }
}
