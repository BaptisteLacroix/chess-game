package pieces;

import board.Case;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Rook {
    private static final String red = "-fx-background-color: red;";

    public static boolean checkTheWhiteMove(Dragboard db, VBox vBox, Case source) {
        if ((db.getDragViewOffsetX() == source.getX())) { // Gauche Droite
            boolean isValid = true;
            int ligne = 0;
            if (db.getDragViewOffsetY() > source.getY()) {
                while (isValid && (source.getY() + ligne) < db.getDragViewOffsetY()) { // Droite
                    if (((Case) ((HBox) vBox.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() + ligne)).getValue() > 0 ||
                            (((Case) ((HBox) vBox.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() + ligne)).getValue() < 0 &&
                                    (source.getY() + ligne + 1) != db.getDragViewOffsetY())) {
                        isValid = false;
                    }
                    ligne++;
                }
            } else {
                ligne = 1;
                while (isValid && (source.getY() - ligne) > db.getDragViewOffsetY()) { // Gauche
                    if (((Case) ((HBox) vBox.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() - ligne - 1)).getValue() > 0 ||
                            (((Case) ((HBox) vBox.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() - ligne - 1)).getValue() < 0 &&
                                    (source.getY() - ligne + 1) != db.getDragViewOffsetY())) {
                        isValid = false;
                    }
                    ligne++;
                }
            }
            return isValid;
        } else if (db.getDragViewOffsetY() == source.getY()) { // Haut Bas
            boolean isValid = true;
            int colonne = 0;
            if (db.getDragViewOffsetX() > source.getX()) {
                while (isValid && (source.getX() + colonne) < db.getDragViewOffsetX()) { // Bas
                    if (((Case) ((HBox) vBox.getChildren().get(source.getX() + colonne)).getChildren().get(source.getY() - 1)).getValue() > 0 ||
                            (((Case) ((HBox) vBox.getChildren().get(source.getX() + colonne)).getChildren().get(source.getY() - 1)).getValue() < 0 &&
                                    (source.getX() + colonne + 1) != db.getDragViewOffsetX())) {
                        isValid = false;
                    }
                    colonne++;
                }
            } else {
                colonne = 1;
                while (isValid && (source.getX() - colonne) > db.getDragViewOffsetX()) { // Haut
                    if (((Case) ((HBox) vBox.getChildren().get(source.getX() - colonne - 1)).getChildren().get(source.getY() - 1)).getValue() > 0 ||
                            (((Case) ((HBox) vBox.getChildren().get(source.getX() - colonne - 1)).getChildren().get(source.getY() - 1)).getValue() < 0 &&
                                    (source.getX() - colonne + 1) != db.getDragViewOffsetX())) {
                        isValid = false;
                    }
                    colonne++;
                }
            }
            return isValid;
        }
        return false;
    }

    /**
     * This function will check all the possible moves of a white rook.
     *
     * @param piece the piece we want to move
     */
    public static void whiteRookMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        // Colonne Bas
        whiteRookMoveDown(liste_cases, piece);

        // Ligne droite
        whiteRookMoveRight(liste_cases, piece);

        // Colonne Haut
        whiteRookMoveUp(liste_cases, piece);

        // Ligne Gauche
        whiteRookMoveLeft(liste_cases, piece);
    }

    /**
     * It resets the rook's moves
     *
     * @param piece the piece we want to reset the moves for
     * @param color the color of the piece
     */
    public static void resetWhiteRookMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // Colonne Bas
        resetWhiteRookMoveDown(liste_cases, piece, color);

        // Ligne droite
        resetWhiteRookMoveRight(liste_cases, piece, color);

        // Colonne Haut
        resetWhiteRookMoveUp(liste_cases, piece, color);

        // Ligne Gauche
        resetWhiteRookMoveLeft(liste_cases, piece, color);
    }

    /**
     * It checks if the white rook can move down, and if it can, it highlights the possible moves
     *
     * @param piece the piece that is being moved
     */
    private static void whiteRookMoveDown(List<ArrayList<Case>> liste_cases, Case piece) {
        int colonne = 0;
        boolean adversaire = false;
        while (piece.getX() + colonne < 8 && !adversaire) {
            if (liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).getValue() < 0) { // Adversaire
                liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).getValue() > 0) { // Allié
                adversaire = true;
            } else { // Libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).setGraphic(newImage);
                colonne++;
            }
        }
    }

    /**
     * It checks if the white rook can move up, and if it can, it highlights the possible moves
     *
     * @param piece the piece that is being moved
     */
    private static void whiteRookMoveUp(List<ArrayList<Case>> liste_cases, Case piece) {
        int colonne = 1;
        boolean adversaire = false;
        while (piece.getX() - 1 - colonne >= 0 && !adversaire) {
            if (liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).getValue() < 0) { // Adversaire
                liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).getValue() > 0) { // Allié
                adversaire = true;
            } else { // Libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).setGraphic(newImage);
                colonne++;
            }
        }
    }

    /**
     * It checks if the white rook can move right, and if it can, it highlights the possible moves
     *
     * @param piece the piece that is selected
     */
    private static void whiteRookMoveRight(List<ArrayList<Case>> liste_cases, Case piece) {
        int ligne = 0;
        boolean adversaire = false;
        while (piece.getY() + ligne < 8 && !adversaire) {
            if (liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).getValue() < 0) { // Adversaire
                liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).getValue() > 0) { // Allié
                adversaire = true;
            } else { // Libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).setGraphic(newImage);
                ligne++;
            }
        }
    }

    /**
     * It checks if the white rook can move left, and if it can, it highlights the possible moves
     *
     * @param piece the piece that is selected
     */
    private static void whiteRookMoveLeft(List<ArrayList<Case>> liste_cases, Case piece) {
        int ligne = 1;
        boolean adversaire = false;
        while (piece.getY() - 1 - ligne >= 0 && !adversaire) {
            if (liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).getValue() < 0) { // Adversaire
                liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).getValue() > 0) { // Allié
                adversaire = true;
            } else { // Libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).setGraphic(newImage);
                ligne++;
            }
        }
    }

    /**
     * It resets the rook's moves
     *
     * @param piece the case where the piece is
     * @param color the color of the piece
     */
    private static void resetWhiteRookMoveDown(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        int colonne = 0;
        boolean adversaire = false;
        while (piece.getX() + colonne < 8 && !adversaire) {
            if (liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).getValue() < 0) { // Adversaire
                liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).getValue() > 0) { // Allié
                adversaire = true;
            } else { // Libre
                liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).setGraphic(null);
                colonne++;
            }
        }
    }

    /**
     * It resets the rook's moves
     *
     * @param piece the case where the piece is
     * @param color the color of the piece
     */
    private static void resetWhiteRookMoveUp(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        int colonne = 1;
        boolean adversaire = false;
        while (piece.getX() - 1 - colonne >= 0 && !adversaire) {
            if (liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).getValue() < 0) { // Adversaire
                liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).getValue() > 0) { // Allié
                adversaire = true;
            } else { // Libre
                liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).setGraphic(null);
                colonne++;
            }
        }
    }

    /**
     * It resets the rook's moves
     *
     * @param piece the case where the piece is
     * @param color the color of the piece
     */
    private static void resetWhiteRookMoveRight(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        int ligne = 0;
        boolean adversaire = false;
        while (piece.getY() + ligne < 8 && !adversaire) {
            if (liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).getValue() < 0) { // Adversaire
                liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).getValue() > 0) { // Allié
                adversaire = true;
            } else { // Libre
                liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).setGraphic(null);
                ligne++;
            }
        }
    }

    /**
     * It resets the rook's moves
     *
     * @param piece the case where the piece is
     * @param color the color of the piece
     */
    private static void resetWhiteRookMoveLeft(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        int ligne = 1;
        boolean adversaire = false;
        while (piece.getY() - 1 - ligne >= 0 && !adversaire) {
            if (liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).getValue() < 0) { // Adversaire
                liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).getValue() > 0) { // Allié
                adversaire = true;
            } else { // Libre
                liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).setGraphic(null);
                ligne++;
            }
        }
    }


    public static boolean checkTheBlackMove(Dragboard db, VBox vBox, Case source) {
        if ((db.getDragViewOffsetX() == source.getX())) { // Gauche Droite
            boolean isValid = true;
            int ligne = 0;
            if (db.getDragViewOffsetY() > source.getY()) {
                while (isValid && (source.getY() + ligne) < db.getDragViewOffsetY()) { // Droite
                    if (((Case) ((HBox) vBox.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() + ligne)).getValue() < 0 ||
                            (((Case) ((HBox) vBox.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() + ligne)).getValue() > 0 &&
                                    (source.getY() + ligne + 1) != db.getDragViewOffsetY())) {
                        isValid = false;
                    }
                    ligne++;
                }
            } else {
                ligne = 1;
                while (isValid && (source.getY() - ligne) > db.getDragViewOffsetY()) { // Gauche
                    if (((Case) ((HBox) vBox.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() - ligne - 1)).getValue() < 0 ||
                            (((Case) ((HBox) vBox.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() - ligne - 1)).getValue() > 0 &&
                                    (source.getY() - ligne + 1) != db.getDragViewOffsetY())) {
                        isValid = false;
                    }
                    ligne++;
                }
            }
            return isValid;

        } else if (db.getDragViewOffsetY() == source.getY()) { // Haut Bas
            boolean isValid = true;
            int colonne = 0;
            if (db.getDragViewOffsetX() > source.getX()) {
                while (isValid && (source.getX() + colonne) < db.getDragViewOffsetX()) { // Bas
                    if (((Case) ((HBox) vBox.getChildren().get(source.getX() + colonne)).getChildren().get(source.getY() - 1)).getValue() < 0 ||
                            (((Case) ((HBox) vBox.getChildren().get(source.getX() + colonne)).getChildren().get(source.getY() - 1)).getValue() > 0 &&
                                    (source.getX() + colonne + 1) != db.getDragViewOffsetX())) {
                        isValid = false;
                    }
                    colonne++;
                }
            } else {
                colonne = 1;
                while (isValid && (source.getX() - colonne) > db.getDragViewOffsetX()) { // Haut
                    if (((Case) ((HBox) vBox.getChildren().get(source.getX() - colonne - 1)).getChildren().get(source.getY() - 1)).getValue() < 0 ||
                            (((Case) ((HBox) vBox.getChildren().get(source.getX() - colonne - 1)).getChildren().get(source.getY() - 1)).getValue() > 0 &&
                                    (source.getX() - colonne + 1) != db.getDragViewOffsetX())) {
                        isValid = false;
                    }
                    colonne++;
                }
            }
            return isValid;
        }
        return false;
    }

    public static void blackRookMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        // Colonne Bas
        blackRookMoveDown(liste_cases, piece);

        // Ligne droite
        blackRookMoveRight(liste_cases, piece);

        // Colonne Haut
        blackRookMoveUp(liste_cases, piece);

        // Ligne Gauche
        blackRookMoveLeft(liste_cases, piece);
    }

    private static void blackRookMoveDown(List<ArrayList<Case>> liste_cases, Case piece) {
        int colonne = 0;
        boolean adversaire = false;
        while (piece.getX() + colonne < 8 && !adversaire) {
            if (liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).getValue() > 0) { // Adversaire
                liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).getValue() < 0) { // Allié
                adversaire = true;
            } else { // Libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).setGraphic(newImage);
                colonne++;
            }
        }
    }

    private static void blackRookMoveUp(List<ArrayList<Case>> liste_cases, Case piece) {
        int colonne = 1;
        boolean adversaire = false;
        while (piece.getX() - 1 - colonne >= 0 && !adversaire) {
            if (liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).getValue() > 0) { // Adversaire
                liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).getValue() < 0) { // Allié
                adversaire = true;
            } else { // Libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).setGraphic(newImage);
                colonne++;
            }
        }
    }

    private static void blackRookMoveRight(List<ArrayList<Case>> liste_cases, Case piece) {
        int ligne = 0;
        boolean adversaire = false;
        while (piece.getY() + ligne < 8 && !adversaire) {
            if (liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).getValue() > 0) { // Adversaire
                liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).getValue() < 0) { // Allié
                adversaire = true;
            } else { // Libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).setGraphic(newImage);
                ligne++;
            }
        }
    }

    private static void blackRookMoveLeft(List<ArrayList<Case>> liste_cases, Case piece) {
        int ligne = 1;
        boolean adversaire = false;
        while (piece.getY() - 1 - ligne >= 0 && !adversaire) {
            if (liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).getValue() > 0) { // Adversaire
                liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).setStyle(red);
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).getValue() < 0) { // Allié
                adversaire = true;
            } else { // Libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).setGraphic(newImage);
                ligne++;
            }
        }
    }

    public static void resetBlackRookMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // Colonne Bas
        resetBlackRookMoveDown(liste_cases, piece, color);

        // Ligne droite
        resetBlackRookMoveRight(liste_cases, piece, color);

        // Colonne Haut
        resetBlackRookMoveUp(liste_cases, piece, color);

        // Ligne Gauche
        resetBlackRookMoveLeft(liste_cases, piece, color);
    }

    private static void resetBlackRookMoveDown(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        int colonne = 0;
        boolean adversaire = false;
        while (piece.getX() + colonne < 8 && !adversaire) {
            if (liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).getValue() > 0) { // Adversaire
                liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).getValue() < 0) { // Allié
                adversaire = true;
            } else { // Libre
                liste_cases.get(piece.getX() + colonne).get(piece.getY() - 1).setGraphic(null);
                colonne++;
            }
        }
    }

    private static void resetBlackRookMoveUp(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        int colonne = 1;
        boolean adversaire = false;
        while (piece.getX() - 1 - colonne >= 0 && !adversaire) {
            if (liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).getValue() > 0) { // Adversaire
                liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).getValue() < 0) { // Allié
                adversaire = true;
            } else { // Libre
                liste_cases.get(piece.getX() - 1 - colonne).get(piece.getY() - 1).setGraphic(null);
                colonne++;
            }
        }
    }

    private static void resetBlackRookMoveRight(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        int ligne = 0;
        boolean adversaire = false;
        while (piece.getY() + ligne < 8 && !adversaire) {
            if (liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).getValue() > 0) { // Adversaire
                liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).getValue() < 0) { // Allié
                adversaire = true;
            } else { // Libre
                liste_cases.get(piece.getX() - 1).get(piece.getY() + ligne).setGraphic(null);
                ligne++;
            }
        }
    }

    private static void resetBlackRookMoveLeft(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        int ligne = 1;
        boolean adversaire = false;
        while (piece.getY() - 1 - ligne >= 0 && !adversaire) {
            if (liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).getValue() > 0) { // Adversaire
                liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).setStyle(color.toString());
                adversaire = true;
            } else if (liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).getValue() < 0) { // Allié
                adversaire = true;
            } else { // Libre
                liste_cases.get(piece.getX() - 1).get(piece.getY() - 1 - ligne).setGraphic(null);
                ligne++;
            }
        }
    }
}
