package pieces;

import board.Case;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to create a bishop object that is used to display the pieces on the board.
 *
 * @author Lacroix Baptiste
 */
public class Bishop {
    /**
     * the red color value
     */
    private static final String red = "-fx-background-color: red;";

    /**
     * It checks if the move is valid for the white player
     *
     * @param db The Dragboard object
     * @param vbox the VBox containing the HBoxes containing the Cases
     * @param source the case from which the piece is moved
     * @return A boolean value.
     */
    public static Boolean checkTheWhiteMove(Dragboard db, VBox vbox, Case source) {
        if (db.getDragViewOffsetX() == source.getX() - db.getDragViewOffsetY() && db.getDragViewOffsetY() == source.getY() - db.getDragViewOffsetY() ||
                db.getDragViewOffsetX() == source.getX() - db.getDragViewOffsetX() && db.getDragViewOffsetY() == source.getY() + db.getDragViewOffsetX() ||
                db.getDragViewOffsetY() == (source.getY() - (db.getDragViewOffsetX() - source.getX())) ||
                db.getDragViewOffsetX() == (source.getX() + (db.getDragViewOffsetY() - source.getY()))) {
            if (db.getDragViewOffsetX() < source.getX() && db.getDragViewOffsetY() < source.getY()) { // Haut Gauche
                boolean isValid = true;
                int n = 1;
                while (isValid && (source.getX() - n) > db.getDragViewOffsetX() && (source.getY() - n) > db.getDragViewOffsetY()) {
                    if (((Case) ((HBox) vbox.getChildren().get(source.getX() - n - 1)).getChildren().get(source.getY() - n - 1)).getValue() > 0 ||
                            (((Case) ((HBox) vbox.getChildren().get(source.getX() - n - 1)).getChildren().get(source.getY() - n - 1)).getValue() < 0) &&
                                    (source.getX() - n != db.getDragViewOffsetX() && source.getY() - n != db.getDragViewOffsetY())) {
                        isValid = false;
                    }
                    n++;
                }
                return isValid;
            } else if (db.getDragViewOffsetX() < source.getX() && db.getDragViewOffsetY() > source.getY()) { // Haut Droite
                boolean isValid = true;
                int n = 1;
                while (isValid && (source.getX() - n) > db.getDragViewOffsetX() && (source.getY() - n) < db.getDragViewOffsetY()) {
                    if (((Case) ((HBox) vbox.getChildren().get(source.getX() - n - 1)).getChildren().get(source.getY() + n - 1)).getValue() > 0 ||
                            (((Case) ((HBox) vbox.getChildren().get(source.getX() - n - 1)).getChildren().get(source.getY() + n - 1)).getValue() < 0) &&
                                    (source.getX() - n != db.getDragViewOffsetX() && source.getY() - n != db.getDragViewOffsetY())) {
                        isValid = false;
                    }
                    n++;
                }
                return isValid;
            } else if (db.getDragViewOffsetX() > source.getX() && db.getDragViewOffsetY() < source.getY()) { // Bas Gauche
                boolean isValid = true;
                int n = 0;
                while (isValid && (source.getX() - n) < db.getDragViewOffsetX() && (source.getY() - n) > db.getDragViewOffsetY()) {
                    if (((Case) ((HBox) vbox.getChildren().get(source.getX() + n)).getChildren().get(source.getY() - n - 2)).getValue() > 0 ||
                            (((Case) ((HBox) vbox.getChildren().get(source.getX() + n)).getChildren().get(source.getY() - n - 2)).getValue() < 0) &&
                                    (source.getX() + n + 1 != db.getDragViewOffsetX() && source.getY() - n - 1 != db.getDragViewOffsetY())) {
                        isValid = false;
                    }
                    n++;
                }
                return isValid;
            } else if (db.getDragViewOffsetX() > source.getX() && db.getDragViewOffsetY() > source.getY()) { // Bas Droite
                boolean isValid = true;
                int n = 0;
                while (isValid && (source.getX() + n) < db.getDragViewOffsetX() && (source.getY() + n) < db.getDragViewOffsetY()) {
                    System.out.println("------------------------------------------------------");
                    System.out.println((((Case) ((HBox) vbox.getChildren().get(source.getX() + n)).getChildren().get(source.getY() + n)).getValue() < 0) &&
                            (source.getX() - n + 1 != db.getDragViewOffsetX() && source.getY() - n + 1 != db.getDragViewOffsetY()));
                    System.out.println(((Case) ((HBox) vbox.getChildren().get(source.getX() + n)).getChildren().get(source.getY() + n)).getValue() < 0);
                    System.out.println((source.getX() + n + 1) + " != " + db.getDragViewOffsetX() + " && " + (source.getY() + n + 1) + "!=" + db.getDragViewOffsetY());
                    System.out.println("------------------------------------------------------\n");
                    if (((Case) ((HBox) vbox.getChildren().get(source.getX() + n)).getChildren().get(source.getY() + n)).getValue() > 0 ||
                            (((Case) ((HBox) vbox.getChildren().get(source.getX() + n)).getChildren().get(source.getY() + n)).getValue() < 0) &&
                                    (source.getX() + n + 1 != db.getDragViewOffsetX() && source.getY() + n + 1 != db.getDragViewOffsetY())) {
                        isValid = false;
                    }
                    n++;
                }
                return isValid;
            }
        }
        return false;
    }

    /**
     * It highlights the valid moves for the white player
     *
     * @param liste_cases The list of all the cases on the board.
     * @param piece the piece we want to move
     */
    public static void whiteBishopMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        // Haut Droite et Gauche
        whiteBishopMoveUpLeft(liste_cases, piece);

        // Droite Haut et Bas
        whiteBishopMoveUpRight(liste_cases, piece);

        // Bas Droite et Gauche
        whiteBishopMoveDownLeft(liste_cases, piece);

        // Gauche Haut et Bas
        whiteBishopMoveDownRight(liste_cases, piece);
    }

    /**
     * It checks if the bishop can move up and left, and if it can, It highlights the valid moves for the white player
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece we want to move
     */
    private static void whiteBishopMoveUpLeft(List<ArrayList<Case>> liste_cases, Case piece) {
        // x = x - n
        // y = y - n
        int n = 2;
        boolean adversaire = false;
        while (!adversaire && piece.getX() - n >= 0 && piece.getY() - n >= 0) {
            if (liste_cases.get(piece.getX() - n).get(piece.getY() - n).getValue() < 0) {
                liste_cases.get(piece.getX() - n).get(piece.getY() - n).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - n).get(piece.getY() - n).getValue() > 0) {
                adversaire = true;
            } else {
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - n).get(piece.getY() - n).setGraphic(newImage);
                n++;
            }
        }
    }

    /**
     * It checks if the bishop can move up and right, and if it can, It highlights the valid moves for the white player
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece we want to move
     */
    private static void whiteBishopMoveUpRight(List<ArrayList<Case>> liste_cases, Case piece) {
        // x = x - n
        // y = y + n
        int n = 1;
        boolean adversaire = false;
        while (!adversaire && piece.getX() - n > 0 && piece.getY() + n <= 8) {
            if (liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).getValue() < 0) {
                liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).getValue() > 0) {
                adversaire = true;
            } else {
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).setGraphic(newImage);
                n++;
            }
        }
    }

    /**
     * It checks if the bishop can move down and left, and if it can, It highlights the valid moves for the white player
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece we want to move
     */
    private static void whiteBishopMoveDownLeft(List<ArrayList<Case>> liste_cases, Case piece) {
        // x = x + n
        // y = y - n
        int n = 1;
        boolean adversaire = false;
        while (!adversaire && piece.getX() + n <= 8 && piece.getY() - n > 0) {
            if (liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).getValue() < 0) {
                liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).getValue() > 0) {
                adversaire = true;
            } else {
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).setGraphic(newImage);
                n++;
            }
        }
    }

    /**
     * It checks if the bishop can move down and right, and if it can, It highlights the valid moves for the white player
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece we want to move
     */
    private static void whiteBishopMoveDownRight(List<ArrayList<Case>> liste_cases, Case piece) {
        // x = x + n
        // y = y + n
        int n = 0;
        boolean adversaire = false;
        while (!adversaire && piece.getX() + n < 8 && piece.getY() + n < 8) {
            if (liste_cases.get(piece.getX() + n).get(piece.getY() + n).getValue() < 0) {
                liste_cases.get(piece.getX() + n).get(piece.getY() + n).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() + n).get(piece.getY() + n).getValue() > 0) {
                adversaire = true;
            } else {
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() + n).get(piece.getY() + n).setGraphic(newImage);
                n++;
            }
        }
    }

    /**
     * It resets the highlights' bishop's moves
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece we want to move
     * @param color the color of the piece
     */
    public static void resetWhiteBishopMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // Haut Droite et Gauche
        resetWhiteBishopMoveUpLeft(liste_cases, piece, color);

        // Droite Haut et Bas
        resetWhiteBishopMoveUpRight(liste_cases, piece, color);

        // Bas Droite et Gauche
        resetWhiteBishopMoveDownLeft(liste_cases, piece, color);

        // Gauche Haut et Bas
        resetWhiteBishopMoveDownRight(liste_cases, piece, color);
    }

    /**
     * It resets the highlights' bishop's moves
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece that is being moved
     * @param color the color of the piece
     */
    private static void resetWhiteBishopMoveUpLeft(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // x = x - n
        // y = y - n
        int n = 2;
        boolean adversaire = false;
        while (!adversaire && piece.getX() - n >= 0 && piece.getY() - n >= 0) {
            if (liste_cases.get(piece.getX() - n).get(piece.getY() - n).getValue() < 0) {
                liste_cases.get(piece.getX() - n).get(piece.getY() - n).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - n).get(piece.getY() - n).getValue() > 0) {
                adversaire = true;
            } else {
                liste_cases.get(piece.getX() - n).get(piece.getY() - n).setGraphic(null);
                n++;
            }
        }
    }

    /**
     * It resets the highlights' bishop's moves
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece that is being moved
     * @param color the color of the piece
     */
    private static void resetWhiteBishopMoveUpRight(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // x = x - n
        // y = y + n
        int n = 1;
        boolean adversaire = false;
        while (!adversaire && piece.getX() - n > 0 && piece.getY() + n <= 8) {
            if (liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).getValue() < 0) {
                liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).getValue() > 0) {
                adversaire = true;
            } else {
                liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).setGraphic(null);
                n++;
            }
        }
    }

    /**
     * It resets the highlights' bishop's moves
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece that is being moved
     * @param color the color of the piece
     */
    private static void resetWhiteBishopMoveDownLeft(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // x = x + n
        // y = y - n
        int n = 1;
        boolean adversaire = false;
        while (!adversaire && piece.getX() + n <= 8 && piece.getY() - n > 0) {
            if (liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).getValue() < 0) {
                liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).getValue() > 0) {
                adversaire = true;
            } else {
                liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).setGraphic(null);
                n++;
            }
        }
    }

    /**
     * It resets the highlights' bishop's moves
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece that is being moved
     * @param color the color of the piece
     */
    private static void resetWhiteBishopMoveDownRight(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // x = x + n
        // y = y + n
        int n = 0;
        boolean adversaire = false;
        while (!adversaire && piece.getX() + n < 8 && piece.getY() + n < 8) {
            if (liste_cases.get(piece.getX() + n).get(piece.getY() + n).getValue() < 0) {
                liste_cases.get(piece.getX() + n).get(piece.getY() + n).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() + n).get(piece.getY() + n).getValue() > 0) {
                adversaire = true;
            } else {
                liste_cases.get(piece.getX() + n).get(piece.getY() + n).setGraphic(null);
                n++;
            }
        }
    }

    /**
     * It checks if the move is valid for the black player
     *
     * @param db The Dragboard object
     * @param vBox the VBox containing the HBoxes containing the Cases
     * @param source the case from which the piece is moved
     * @return A boolean value.
     */
    public static boolean checkTheBlackMove(Dragboard db, VBox vBox, Case source) {
        if (db.getDragViewOffsetX() == source.getX() - db.getDragViewOffsetY() && db.getDragViewOffsetY() == source.getY() - db.getDragViewOffsetY() ||
                db.getDragViewOffsetX() == source.getX() - db.getDragViewOffsetX() && db.getDragViewOffsetY() == source.getY() + db.getDragViewOffsetX() ||
                db.getDragViewOffsetY() == (source.getY() - (db.getDragViewOffsetX() - source.getX())) ||
                db.getDragViewOffsetX() == (source.getX() + (db.getDragViewOffsetY() - source.getY()))) {
            if (db.getDragViewOffsetX() < source.getX() && db.getDragViewOffsetY() < source.getY()) { // Haut Gauche
                boolean isValid = true;
                int n = 1;
                while (isValid && (source.getX() - n) > db.getDragViewOffsetX() && (source.getY() - n) > db.getDragViewOffsetY()) {
                    if (((Case) ((HBox) vBox.getChildren().get(source.getX() - n - 1)).getChildren().get(source.getY() - n - 1)).getValue() < 0 ||
                            (((Case) ((HBox) vBox.getChildren().get(source.getX() - n - 1)).getChildren().get(source.getY() - n - 1)).getValue() > 0) &&
                                    (source.getX() - n != db.getDragViewOffsetX() && source.getY() - n != db.getDragViewOffsetY())) {
                        isValid = false;
                    }
                    n++;
                }
                return isValid;
            } else if (db.getDragViewOffsetX() < source.getX() && db.getDragViewOffsetY() > source.getY()) { // Haut Droite
                boolean isValid = true;
                int n = 1;
                while (isValid && (source.getX() - n) > db.getDragViewOffsetX() && (source.getY() - n) < db.getDragViewOffsetY()) {
                    if (((Case) ((HBox) vBox.getChildren().get(source.getX() - n - 1)).getChildren().get(source.getY() + n - 1)).getValue() < 0 ||
                            (((Case) ((HBox) vBox.getChildren().get(source.getX() - n - 1)).getChildren().get(source.getY() + n - 1)).getValue() > 0) &&
                                    (source.getX() - n != db.getDragViewOffsetX() && source.getY() - n != db.getDragViewOffsetY())) {
                        isValid = false;
                    }
                    n++;
                }
                return isValid;
            } else if (db.getDragViewOffsetX() > source.getX() && db.getDragViewOffsetY() < source.getY()) { // Bas Gauche
                boolean isValid = true;
                int n = 0;
                while (isValid && (source.getX() - n) < db.getDragViewOffsetX() && (source.getY() - n) > db.getDragViewOffsetY()) {
                    if (((Case) ((HBox) vBox.getChildren().get(source.getX() + n)).getChildren().get(source.getY() - n - 2)).getValue() < 0 ||
                            (((Case) ((HBox) vBox.getChildren().get(source.getX() + n)).getChildren().get(source.getY() - n - 2)).getValue() > 0) &&
                                    (source.getX() + n + 1 != db.getDragViewOffsetX() && source.getY() - n - 1 != db.getDragViewOffsetY())) {
                        isValid = false;
                    }
                    n++;
                }
                return isValid;
            } else if (db.getDragViewOffsetX() > source.getX() && db.getDragViewOffsetY() > source.getY()) { // Bas Droite
                boolean isValid = true;
                int n = 0;
                while (isValid && (source.getX() + n) < db.getDragViewOffsetX() && (source.getY() + n) < db.getDragViewOffsetY()) {
                    if (((Case) ((HBox) vBox.getChildren().get(source.getX() + n)).getChildren().get(source.getY() + n)).getValue() < 0 ||
                            (((Case) ((HBox) vBox.getChildren().get(source.getX() + n)).getChildren().get(source.getY() + n)).getValue() > 0) &&
                                    (source.getX() - n + 1 != db.getDragViewOffsetX() && source.getY() - n + 1 != db.getDragViewOffsetY())) {
                        isValid = false;
                    }
                    n++;
                }
                return isValid;
            }
        }
        return false;
    }

    /**
     * It highlights the valid moves for the black player
     *
     * @param liste_cases The list of all the cases on the board.
     * @param piece the piece we want to move
     */
    public static void blackBishopMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        // Haut Droite et Gauche
        blackBishopMoveUpLeft(liste_cases, piece);

        // Droite Haut et Bas
        blackBishopMoveUpRight(liste_cases, piece);

        // Bas Droite et Gauche
        blackBishopMoveDownLeft(liste_cases, piece);

        // Gauche Haut et Bas
        blackBishopMoveDownRight(liste_cases, piece);
    }

    /**
     * It checks if the black bishop can move up and left, and if it can, it highlights the possible moves
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece we want to move
     */
    private static void blackBishopMoveUpLeft(List<ArrayList<Case>> liste_cases, Case piece) {
        // x = x - n
        // y = y - n
        int n = 2;
        boolean adversaire = false;
        while (!adversaire && piece.getX() - n >= 0 && piece.getY() - n >= 0) {
            if (liste_cases.get(piece.getX() - n).get(piece.getY() - n).getValue() > 0) {
                liste_cases.get(piece.getX() - n).get(piece.getY() - n).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - n).get(piece.getY() - n).getValue() < 0) {
                adversaire = true;
            } else {
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - n).get(piece.getY() - n).setGraphic(newImage);
                n++;
            }
        }
    }

    /**
     * It checks if the black bishop can move up and right, and if it can, it highlights the possible moves
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece we want to move
     */
    private static void blackBishopMoveUpRight(List<ArrayList<Case>> liste_cases, Case piece) {
        // x = x - n
        // y = y + n
        int n = 1;
        boolean adversaire = false;
        while (!adversaire && piece.getX() - n > 0 && piece.getY() + n <= 8) {
            if (liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).getValue() > 0) {
                liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).getValue() < 0) {
                adversaire = true;
            } else {
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).setGraphic(newImage);
                n++;
            }
        }
    }

    /**
     * It checks if the black bishop can move down and left, and if it can, it highlights the possible moves
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece that is being moved
     */
    private static void blackBishopMoveDownLeft(List<ArrayList<Case>> liste_cases, Case piece) {
        // x = x + n
        // y = y - n
        int n = 1;
        boolean adversaire = false;
        while (!adversaire && piece.getX() + n <= 8 && piece.getY() - n > 0) {
            if (liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).getValue() > 0) {
                liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).getValue() < 0) {
                adversaire = true;
            } else {
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).setGraphic(newImage);
                n++;
            }
        }
    }

    /**
     * It checks if the bishop can move down and right, and if it can, it highlights the possible moves
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece that is being moved
     */
    private static void blackBishopMoveDownRight(List<ArrayList<Case>> liste_cases, Case piece) {
        // x = x + n
        // y = y + n
        int n = 0;
        boolean adversaire = false;
        while (!adversaire && piece.getX() + n < 8 && piece.getY() + n < 8) {
            if (liste_cases.get(piece.getX() + n).get(piece.getY() + n).getValue() > 0) {
                liste_cases.get(piece.getX() + n).get(piece.getY() + n).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() + n).get(piece.getY() + n).getValue() < 0) {
                adversaire = true;
            } else {
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() + n).get(piece.getY() + n).setGraphic(newImage);
                n++;
            }
        }
    }

    /**
     * It resets the moves of the black bishop
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece we want to move
     * @param color the color of the piece
     */
    public static void resetBlackBishopMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // Haut Droite et Gauche
        resetBlackBishopMoveUpLeft(liste_cases, piece, color);

        // Droite Haut et Bas
        resetBlackBishopMoveUpRight(liste_cases, piece, color);

        // Bas Droite et Gauche
        resetBlackBishopMoveDownLeft(liste_cases, piece, color);

        // Gauche Haut et Bas
        resetBlackBishopMoveDownRight(liste_cases, piece, color);
    }

    /**
     * It resets the moves of the black bishop
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece that is being moved
     * @param color the color of the piece
     */
    private static void resetBlackBishopMoveUpLeft(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // x = x - n
        // y = y - n
        int n = 2;
        boolean adversaire = false;
        while (!adversaire && piece.getX() - n >= 0 && piece.getY() - n >= 0) {
            if (liste_cases.get(piece.getX() - n).get(piece.getY() - n).getValue() > 0) {
                liste_cases.get(piece.getX() - n).get(piece.getY() - n).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - n).get(piece.getY() - n).getValue() < 0) {
                adversaire = true;
            } else {
                liste_cases.get(piece.getX() - n).get(piece.getY() - n).setGraphic(null);
                n++;
            }
        }
    }

    /**
     * It resets the moves of the black bishop
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece that is being moved
     * @param color the color of the piece
     */
    private static void resetBlackBishopMoveUpRight(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // x = x - n
        // y = y + n
        int n = 1;
        boolean adversaire = false;
        while (!adversaire && piece.getX() - n > 0 && piece.getY() + n <= 8) {
            if (liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).getValue() > 0) {
                liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).getValue() < 0) {
                adversaire = true;
            } else {
                liste_cases.get(piece.getX() - n - 1).get(piece.getY() + n - 1).setGraphic(null);
                n++;
            }
        }
    }

    /**
     * It resets the moves of the black bishop
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece that is being moved
     * @param color the color of the piece
     */
    private static void resetBlackBishopMoveDownLeft(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // x = x + n
        // y = y - n
        int n = 1;
        boolean adversaire = false;
        while (!adversaire && piece.getX() + n < 8 && piece.getY() - n > 0) {
            if (liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).getValue() > 0) {
                liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).getValue() < 0) {
                adversaire = true;
            } else {
                liste_cases.get(piece.getX() + n - 1).get(piece.getY() - n - 1).setGraphic(null);
                n++;
            }
        }
    }

    /**
     * It resets the moves of the black bishop
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece that is being moved
     * @param color the color of the piece
     */
    private static void resetBlackBishopMoveDownRight(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // x = x + n
        // y = y + n
        int n = 0;
        boolean adversaire = false;
        while (!adversaire && piece.getX() + n < 8 && piece.getY() + n < 8) {
            if (liste_cases.get(piece.getX() + n).get(piece.getY() + n).getValue() > 0) {
                liste_cases.get(piece.getX() + n).get(piece.getY() + n).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() + n).get(piece.getY() + n).getValue() < 0) {
                adversaire = true;
            } else {
                liste_cases.get(piece.getX() + n).get(piece.getY() + n).setGraphic(null);
                n++;
            }
        }
    }
}
