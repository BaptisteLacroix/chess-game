package pieces;

import board.Case;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Background;

import java.util.ArrayList;
import java.util.List;

public class Knight {
    private static final String red = "-fx-background-color: red;";

    public static Boolean checkTheWhiteMove(Dragboard db, Case source, Case piece) {
        return ((db.getDragViewOffsetX() == source.getX() - 2 && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() <= 0) || // Haut Gauche
                (db.getDragViewOffsetX() == source.getX() - 2 && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() <= 0) || // Haut Bas
                (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() - 2 && piece.getValue() <= 0) || // Gauche Haut
                (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() - 2 && piece.getValue() <= 0) || // Gauche Bas
                (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() + 2 && piece.getValue() <= 0) || // Droite haut
                (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() + 2 && piece.getValue() <= 0) || // Droite Bas
                (db.getDragViewOffsetX() == source.getX() + 2 && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() <= 0) || // Bas Gauche
                (db.getDragViewOffsetX() == source.getX() + 2 && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() <= 0)) /* Bas Droite */;
    }

    public static void whiteKnightMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        // Haut Droite et Gauche
        whiteKnightMoveUp(liste_cases, piece);

        // Droite Haut et Bas
        whiteKnightMoveLeft(liste_cases, piece);

        // Bas Droite et Gauche
        whiteKnightMoveDown(liste_cases, piece);

        // Gauche Haut et Bas
        whiteKnightMoveRight(liste_cases, piece);
    }

    private static void whiteKnightMoveUp(List<ArrayList<Case>> liste_cases, Case piece) {
        if (piece.getX() > 2 && piece.getY() > 1) { // Case gauche possible
            if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() < 0) {
                // case gauche ennemie
                liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setStyle(red); // Case gauche ennemie
            }
            if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() == 0) {
                // case gauche libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setGraphic(newImage); // Case gauche libre
            }
        }
        if (piece.getX() > 2 && piece.getY() < 8) {
            if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() < 0) {
                // Case droite ennemie
                liste_cases.get(piece.getX() - 3).get(piece.getY()).setStyle(red); // Case droite ennemie
            }
            if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() == 0) {
                // Case droite libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 3).get(piece.getY()).setGraphic(newImage); // Case droite libre
            }
        }
    }

    private static void whiteKnightMoveLeft(List<ArrayList<Case>> liste_cases, Case piece) {
        if (piece.getY() > 2 && piece.getX() > 1) { // Case haut possible
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() < 0) {
                // case haut ennemie
                liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setStyle(red); // Case haut ennemie
            }
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() == 0) {
                // case haut libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setGraphic(newImage); // Case haut libre
            }
        }
        if (piece.getY() > 2 && piece.getX() < 8) {
            if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() < 0) {
                // Case bas ennemie
                liste_cases.get(piece.getX()).get(piece.getY() - 3).setStyle(red); // Case bas ennemie
            }
            if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() == 0) {
                // Case bas libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX()).get(piece.getY() - 3).setGraphic(newImage); // Case bas libre
            }
        }
    }

    private static void whiteKnightMoveRight(List<ArrayList<Case>> liste_cases, Case piece) {
        if (piece.getY() < 7 && piece.getX() > 1) { // Case haut possible
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() < 0) {
                // case haut ennemie
                liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setStyle(red); // Case haut ennemie
            }
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() == 0) {
                // case haut libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setGraphic(newImage); // Case haut libre
            }
        }
        if (piece.getY() < 7 && piece.getX() < 7) {
            if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() < 0) {
                // Case bas ennemie
                liste_cases.get(piece.getX()).get(piece.getY() + 1).setStyle(red); // Case bas ennemie
            }
            if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() == 0) {
                // Case bas libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX()).get(piece.getY() + 1).setGraphic(newImage); // Case bas libre
            }
        }
    }

    private static void whiteKnightMoveDown(List<ArrayList<Case>> liste_cases, Case piece) {
        if (piece.getX() < 7 && piece.getY() > 1) {
            if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() < 0) {
                // case gauche ennemie
                liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setStyle(red); // Case gauche ennemie
            }
            if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() == 0) {
                // case gauche libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setGraphic(newImage); // Case gauche libre
            }
        }
        if (piece.getX() < 7 && piece.getY() < 8) {
            if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() < 0) {
                // Case droite ennemie
                liste_cases.get(piece.getX() + 1).get(piece.getY()).setStyle(red); // Case droite ennemie
            }
            if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() == 0) {
                // Case droite libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() + 1).get(piece.getY()).setGraphic(newImage); // Case droite libre
            }
        }
    }

    public static void resetWhiteKnightMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // Haut Droite et Gauche
        resetWhiteKnightMoveUp(liste_cases, piece, color);

        // Droite Haut et Bas
        resetWhiteKnightMoveLeft(liste_cases, piece, color);

        // Bas Droite et Gauche
        resetWhiteKnightMoveDown(liste_cases, piece, color);

        // Gauche Haut et Bas
        resetWhiteKnightMoveRight(liste_cases, piece, color);
    }

    private static void resetWhiteKnightMoveUp(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        if (piece.getX() > 2 && piece.getY() > 1) { // Case gauche possible
            if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() < 0) {
                // case gauche ennemie
                liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setStyle(color.toString()); // Case gauche ennemie
            }
            if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() == 0) {
                // case gauche libre
                liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setGraphic(null); // Case gauche libre
            }
        }
        if (piece.getX() > 2 && piece.getY() < 8) {
            if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() < 0) {
                // Case droite ennemie
                liste_cases.get(piece.getX() - 3).get(piece.getY()).setStyle(color.toString()); // Case droite ennemie
            }
            if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() == 0) {
                // Case droite libre
                liste_cases.get(piece.getX() - 3).get(piece.getY()).setGraphic(null); // Case droite libre
            }
        }
    }

    private static void resetWhiteKnightMoveLeft(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        if (piece.getY() > 2 && piece.getX() > 1) { // Case haut possible
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() < 0) {
                // case haut ennemie
                liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setStyle(color.toString()); // Case haut ennemie
            }
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() == 0) {
                // case haut libre
                liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setGraphic(null); // Case haut libre
            }
        }
        if (piece.getY() > 2 && piece.getX() < 8) {
            if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() < 0) {
                // Case bas ennemie
                liste_cases.get(piece.getX()).get(piece.getY() - 3).setStyle(color.toString()); // Case bas ennemie
            }
            if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() == 0) {
                // Case bas libre
                liste_cases.get(piece.getX()).get(piece.getY() - 3).setGraphic(null); // Case bas libre
            }
        }
    }

    private static void resetWhiteKnightMoveRight(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        if (piece.getY() < 7 && piece.getX() > 1) { // Case haut possible
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() < 0) {
                // case haut ennemie
                liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setStyle(color.toString()); // Case haut ennemie
            }
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() == 0) {
                // case haut libre
                liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setGraphic(null); // Case haut libre
            }
        }
        if (piece.getY() < 7 && piece.getX() < 7) {
            if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() < 0) {
                // Case bas ennemie
                liste_cases.get(piece.getX()).get(piece.getY() + 1).setStyle(color.toString()); // Case bas ennemie
            }
            if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() == 0) {
                // Case bas libre
                liste_cases.get(piece.getX()).get(piece.getY() + 1).setGraphic(null); // Case bas libre
            }
        }
    }

    private static void resetWhiteKnightMoveDown(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        if (piece.getX() < 7 && piece.getY() > 1) { // Case gauche possible
            if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() < 0) {
                // case gauche ennemie
                liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setStyle(color.toString()); // Case gauche ennemie
            }
            if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() == 0) {
                // case gauche libre
                liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setGraphic(null); // Case gauche libre
            }
        }
        if (piece.getX() < 7 && piece.getY() < 8) {
            if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() < 0) {
                // Case droite ennemie
                liste_cases.get(piece.getX() + 1).get(piece.getY()).setStyle(color.toString()); // Case droite ennemie
            }
            if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() == 0) {
                // Case droite libre
                liste_cases.get(piece.getX() + 1).get(piece.getY()).setGraphic(null); // Case droite libre
            }
        }
    }

    public static boolean checkTheBlackMove(Dragboard db, Case source, Case piece) {
        return ((db.getDragViewOffsetX() == source.getX() - 2 && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() >= 0) || // Haut Gauche
                (db.getDragViewOffsetX() == source.getX() - 2 && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() >= 0) || // Haut Bas
                (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() - 2 && piece.getValue() >= 0) || // Gauche Haut
                (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() - 2 && piece.getValue() >= 0) || // Gauche Bas
                (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() + 2 && piece.getValue() >= 0) || // Droite haut
                (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() + 2 && piece.getValue() >= 0) || // Droite Bas
                (db.getDragViewOffsetX() == source.getX() + 2 && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() >= 0) || // Bas Gauche
                (db.getDragViewOffsetX() == source.getX() + 2 && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() >= 0)) /* Bas Droite */;
    }

    public static void blackKnightMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        // Haut Droite et Gauche
        blackKnightMoveUp(liste_cases, piece);

        // Droite Haut et Bas
        blackKnightMoveRight(liste_cases, piece);

        // Bas Droite et Gauche
        blackKnightMoveDown(liste_cases, piece);

        // Gauche Haut et Bas
        blackKnightMoveLeft(liste_cases, piece);
    }

    private static void blackKnightMoveUp(List<ArrayList<Case>> liste_cases, Case piece) {
        if (piece.getX() > 2 && piece.getY() > 1) { // Case gauche possible
            if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() > 0) {
                // case gauche ennemie
                liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setStyle(red); // Case gauche ennemie
            }
            if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() == 0) {
                // case gauche libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setGraphic(newImage); // Case gauche libre
            }
        }
        if (piece.getX() > 2 && piece.getY() < 8) {
            if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() > 0) {
                // Case droite ennemie
                liste_cases.get(piece.getX() - 3).get(piece.getY()).setStyle(red); // Case droite ennemie
            }
            if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() == 0) {
                // Case droite libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 3).get(piece.getY()).setGraphic(newImage); // Case droite libre
            }
        }
    }

    private static void blackKnightMoveLeft(List<ArrayList<Case>> liste_cases, Case piece) {
        if (piece.getY() > 2 && piece.getX() > 1) { // Case haut possible
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() > 0) {
                // case haut ennemie
                liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setStyle(red); // Case haut ennemie
            }
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() == 0) {
                // case haut libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setGraphic(newImage); // Case haut libre
            }
        }
        if (piece.getY() > 2 && piece.getX() < 8) {
            if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() > 0) {
                // Case bas ennemie
                liste_cases.get(piece.getX()).get(piece.getY() - 3).setStyle(red); // Case bas ennemie
            }
            if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() == 0) {
                // Case bas libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX()).get(piece.getY() - 3).setGraphic(newImage); // Case bas libre
            }
        }
    }

    private static void blackKnightMoveRight(List<ArrayList<Case>> liste_cases, Case piece) {
        if (piece.getY() < 7 && piece.getX() > 1) { // Case haut possible
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() > 0) {
                // case haut ennemie
                liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setStyle(red); // Case haut ennemie
            }
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() == 0) {
                // case haut libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setGraphic(newImage); // Case haut libre
            }
        }
        if (piece.getY() < 7 && piece.getX() < 7) {
            if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() > 0) {
                // Case bas ennemie
                liste_cases.get(piece.getX()).get(piece.getY() + 1).setStyle(red); // Case bas ennemie
            }
            if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() == 0) {
                // Case bas libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX()).get(piece.getY() + 1).setGraphic(newImage); // Case bas libre
            }
        }
    }

    private static void blackKnightMoveDown(List<ArrayList<Case>> liste_cases, Case piece) {
        if (piece.getX() < 7 && piece.getY() > 1) {
            if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() > 0) {
                // case gauche ennemie
                liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setStyle(red); // Case gauche ennemie
            }
            if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() == 0) {
                // case gauche libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setGraphic(newImage); // Case gauche libre
            }
        }
        if (piece.getX() < 7 && piece.getY() < 8) {
            if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() > 0) {
                // Case droite ennemie
                liste_cases.get(piece.getX() + 1).get(piece.getY()).setStyle(red); // Case droite ennemie
            }
            if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() == 0) {
                // Case droite libre
                ImageView newImage = new ImageView("file:./resources/dot.png");
                newImage.setFitHeight(500 / (double) 8);
                newImage.setFitWidth(500 / (double) 8);
                liste_cases.get(piece.getX() + 1).get(piece.getY()).setGraphic(newImage); // Case droite libre
            }
        }
    }

    public static void resetBlackKnightMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // Haut Droite et Gauche
        resetBlackKnightMoveUp(liste_cases, piece, color);

        // Droite Haut et Bas
        resetBlackKnightMoveRight(liste_cases, piece, color);

        // Bas Droite et Gauche
        resetBlackKnightMoveDown(liste_cases, piece, color);

        // Gauche Haut et Bas
        resetBlackKnightMoveLeft(liste_cases, piece, color);
    }

    private static void resetBlackKnightMoveUp(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        if (piece.getX() > 2 && piece.getY() > 1) { // Case gauche possible
            if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() > 0) {
                // case gauche ennemie
                liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setStyle(color.toString()); // Case gauche ennemie
            }
            if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() == 0) {
                // case gauche libre
                liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setGraphic(null); // Case gauche libre
            }
        }
        if (piece.getX() > 2 && piece.getY() < 8) {
            if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() > 0) {
                // Case droite ennemie
                liste_cases.get(piece.getX() - 3).get(piece.getY()).setStyle(color.toString()); // Case droite ennemie
            }
            if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() == 0) {
                // Case droite libre
                liste_cases.get(piece.getX() - 3).get(piece.getY()).setGraphic(null); // Case droite libre
            }
        }
    }

    private static void resetBlackKnightMoveLeft(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        if (piece.getY() > 2 && piece.getX() > 1) { // Case haut possible
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() > 0) {
                // case haut ennemie
                liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setStyle(color.toString()); // Case haut ennemie
            }
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() == 0) {
                // case haut libre
                liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setGraphic(null); // Case haut libre
            }
        }
        if (piece.getY() > 2 && piece.getX() < 8) {
            if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() > 0) {
                // Case bas ennemie
                liste_cases.get(piece.getX()).get(piece.getY() - 3).setStyle(color.toString()); // Case bas ennemie
            }
            if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() == 0) {
                // Case bas libre
                liste_cases.get(piece.getX()).get(piece.getY() - 3).setGraphic(null); // Case bas libre
            }
        }
    }

    private static void resetBlackKnightMoveRight(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        if (piece.getY() < 7 && piece.getX() > 1) { // Case haut possible
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() > 0) {
                // case haut ennemie
                liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setStyle(color.toString()); // Case haut ennemie
            }
            if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() == 0) {
                // case haut libre
                liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setGraphic(null); // Case haut libre
            }
        }
        if (piece.getY() < 7 && piece.getX() < 7) {
            if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() > 0) {
                // Case bas ennemie
                liste_cases.get(piece.getX()).get(piece.getY() + 1).setStyle(color.toString()); // Case bas ennemie
            }
            if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() == 0) {
                // Case bas libre
                liste_cases.get(piece.getX()).get(piece.getY() + 1).setGraphic(null); // Case bas libre
            }
        }
    }

    private static void resetBlackKnightMoveDown(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        if (piece.getX() < 7 && piece.getY() > 1) { // Case gauche possible
            if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() > 0) {
                // case gauche ennemie
                liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setStyle(color.toString()); // Case gauche ennemie
            }
            if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() == 0) {
                // case gauche libre
                liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setGraphic(null); // Case gauche libre
            }
        }
        if (piece.getX() < 7 && piece.getY() < 8) {
            if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() > 0) {
                // Case droite ennemie
                liste_cases.get(piece.getX() + 1).get(piece.getY()).setStyle(color.toString()); // Case droite ennemie
            }
            if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() == 0) {
                // Case droite libre
                liste_cases.get(piece.getX() + 1).get(piece.getY()).setGraphic(null); // Case droite libre
            }
        }
    }
}
