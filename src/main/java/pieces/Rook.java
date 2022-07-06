package pieces;

import board.Case;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

import java.util.ArrayList;
import java.util.List;

public class Rook {
    private static final String red = "-fx-background-color: red;";

    /**
     * This function will check all the possible moves of a white rook.
     *
     * @param piece the piece we want to move
     */
    public static void whiteRookMoves(ArrayList<ArrayList<Case>> liste_cases, Case piece) {
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
    public static void resetWhiteRookMoves(ArrayList<ArrayList<Case>> liste_cases, Case piece, Background color) {
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
    private static void whiteRookMoveDown(ArrayList<ArrayList<Case>> liste_cases, Case piece) {
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
    private static void whiteRookMoveUp(ArrayList<ArrayList<Case>> liste_cases, Case piece) {
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
    private static void whiteRookMoveRight(ArrayList<ArrayList<Case>> liste_cases, Case piece) {
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
    private static void whiteRookMoveLeft(ArrayList<ArrayList<Case>> liste_cases, Case piece) {
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
    private static void resetWhiteRookMoveDown(ArrayList<ArrayList<Case>> liste_cases, Case piece, Background color) {
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
    private static void resetWhiteRookMoveUp(ArrayList<ArrayList<Case>> liste_cases, Case piece, Background color) {
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
    private static void resetWhiteRookMoveRight(ArrayList<ArrayList<Case>> liste_cases, Case piece, Background color) {
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
    private static void resetWhiteRookMoveLeft(ArrayList<ArrayList<Case>> liste_cases, Case piece, Background color) {
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


    public static void blackRookMoves(ArrayList<ArrayList<Case>> liste_cases, Case piece) {
        // Colonne Bas
        blackRookMoveDown(liste_cases, piece);

        // Ligne droite
        blackRookMoveRight(liste_cases, piece);

        // Colonne Haut
        blackRookMoveUp(liste_cases, piece);

        // Ligne Gauche
        blackRookMoveLeft(liste_cases, piece);
    }

    private static void blackRookMoveDown(ArrayList<ArrayList<Case>> liste_cases, Case piece) {
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

    private static void blackRookMoveUp(ArrayList<ArrayList<Case>> liste_cases, Case piece) {
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

    private static void blackRookMoveRight(ArrayList<ArrayList<Case>> liste_cases, Case piece) {
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

    private static void blackRookMoveLeft(ArrayList<ArrayList<Case>> liste_cases, Case piece) {
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

    public static void resetBlackRookMoves(ArrayList<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // Colonne Bas
        resetBlackRookMoveDown(liste_cases, piece, color);

        // Ligne droite
        resetBlackRookMoveRight(liste_cases, piece, color);

        // Colonne Haut
        resetBlackRookMoveUp(liste_cases, piece, color);

        // Ligne Gauche
        resetBlackRookMoveLeft(liste_cases, piece, color);
    }

    private static void resetBlackRookMoveDown(ArrayList<ArrayList<Case>> liste_cases, Case piece, Background color) {
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

    private static void resetBlackRookMoveUp(ArrayList<ArrayList<Case>> liste_cases, Case piece, Background color) {
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

    private static void resetBlackRookMoveRight(ArrayList<ArrayList<Case>> liste_cases, Case piece, Background color) {
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

    private static void resetBlackRookMoveLeft(ArrayList<ArrayList<Case>> liste_cases, Case piece, Background color) {
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
