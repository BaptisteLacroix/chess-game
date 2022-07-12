package pieces;

import board.Case;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Background;

import java.util.ArrayList;
import java.util.List;

public class King {
    private static final String red = "-fx-background-color: red;";

    public static boolean checkTheWhiteMove(Dragboard db, Case source, Case piece) {
        return (((db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() <= 0) || // Haut Gauche
                (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() && piece.getValue() <= 0) || // Haut
                (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() <= 0) || // Haut Droite
                (db.getDragViewOffsetX() == source.getX() && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() <= 0) || // Gauche
                (db.getDragViewOffsetX() == source.getX() && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() <= 0) || // Droite
                (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() <= 0) || // Bas Gauche
                (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() && piece.getValue() <= 0) || // Bas
                (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() <= 0))) /* Bas Droite */;
    }

    public static void whiteKingMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        // Haut Gauche
        King.whiteKingMoveUpLeft(piece, liste_cases);

        // Haut
        King.whiteKingMoveUp(piece, liste_cases);

        // Haut Droite
        King.whiteKingMoveUpRight(piece, liste_cases);

        // Gauche
        King.whiteKingMoveLeft(piece, liste_cases);

        // Droite
        King.whiteKingMoveRight(piece, liste_cases);

        // Bas Gauche
        King.whiteKingMoveDownLeft(piece, liste_cases);

        // Bas
        King.whiteKingMoveDown(piece, liste_cases);

        // Bas Droite
        King.whiteKingMoveDownRight(piece, liste_cases);
    }

    private static void whiteKingMoveUpLeft(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getX() > 1 && piece.getY() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).setGraphic(image);
        } else if (piece.getX() > 1 && piece.getY() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).getValue() < 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).setStyle(red);
        }
    }

    private static void whiteKingMoveUp(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getX() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setGraphic(image);
        } else if (piece.getX() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() < 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setStyle(red);
        }
    }

    private static void whiteKingMoveUpRight(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getX() > 1 && piece.getY() < 8 && liste_cases.get(piece.getX() - 2).get(piece.getY()).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 2).get(piece.getY()).setGraphic(image);
        } else if (piece.getX() > 1 && piece.getY() < 8 && liste_cases.get(piece.getX() - 2).get(piece.getY()).getValue() < 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY()).setStyle(red);
        }
    }

    private static void whiteKingMoveLeft(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getY() > 1 && liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).setGraphic(image);
        } else if (piece.getY() > 1 && liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).getValue() < 0) {
            liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).setStyle(red);
        }
    }

    private static void whiteKingMoveRight(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getY() < 8 && liste_cases.get(piece.getX() - 1).get(piece.getY()).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 1).get(piece.getY()).setGraphic(image);
        } else if (piece.getY() < 8 && liste_cases.get(piece.getX() - 1).get(piece.getY()).getValue() < 0) {
            liste_cases.get(piece.getX() - 1).get(piece.getY()).setStyle(red);
        }
    }

    private static void whiteKingMoveDownLeft(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getX() < 8 && piece.getY() > 1 && liste_cases.get(piece.getX()).get(piece.getY() - 2).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() - 2).setGraphic(image);
        } else if (piece.getX() < 8 && piece.getY() > 1 && liste_cases.get(piece.getX()).get(piece.getY() - 2).getValue() < 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 2).setStyle(red);
        }
    }

    private static void whiteKingMoveDown(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getX() < 8 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setGraphic(image);
        } else if (piece.getX() < 8 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() < 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setStyle(red);
        }
    }

    private static void whiteKingMoveDownRight(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getX() < 8 && piece.getY() < 8 && liste_cases.get(piece.getX()).get(piece.getY()).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY()).setGraphic(image);
        } else if (piece.getX() < 8 && piece.getY() < 8 && liste_cases.get(piece.getX()).get(piece.getY()).getValue() < 0) {
            liste_cases.get(piece.getX()).get(piece.getY()).setStyle(red);
        }
    }

    public static void resetWhiteKingMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // Haut Gauche
        King.resetWhiteKingMoveUpLeft(piece, liste_cases, color);

        // Haut
        King.resetWhiteKingMoveUp(piece, liste_cases, color);

        // Haut Droite
        King.resetWhiteKingMoveUpRight(piece, liste_cases, color);

        // Gauche
        King.resetWhiteKingMoveLeft(piece, liste_cases, color);

        // Droite
        King.resetWhiteKingMoveRight(piece, liste_cases, color);

        // Bas Gauche
        King.resetWhiteKingMoveDownLeft(piece, liste_cases, color);

        // Bas
        King.resetWhiteKingMoveDown(piece, liste_cases, color);

        // Bas Droite
        King.resetWhiteKingMoveDownRight(piece, liste_cases, color);
    }

    private static void resetWhiteKingMoveUpLeft(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getX() > 1 && piece.getY() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).getValue() == 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).setGraphic(null);
        } else if (piece.getX() > 1 && piece.getY() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).getValue() < 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).setStyle(color.toString());
        }
    }

    private static void resetWhiteKingMoveUp(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getX() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() == 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setGraphic(null);
        } else if (piece.getX() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() < 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setStyle(color.toString());
        }
    }

    private static void resetWhiteKingMoveUpRight(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getX() > 1 && piece.getY() < 8 && liste_cases.get(piece.getX() - 2).get(piece.getY()).getValue() == 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY()).setGraphic(null);
        } else if (piece.getX() > 1 && piece.getY() < 8 && liste_cases.get(piece.getX() - 2).get(piece.getY()).getValue() < 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY()).setStyle(color.toString());
        }
    }

    private static void resetWhiteKingMoveLeft(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getY() > 1 && liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).getValue() == 0) {
            liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).setGraphic(null);
        } else if (piece.getY() > 1 && liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).getValue() < 0) {
            liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).setStyle(color.toString());
        }
    }

    private static void resetWhiteKingMoveRight(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getY() < 8 && liste_cases.get(piece.getX() - 1).get(piece.getY()).getValue() == 0) {
            liste_cases.get(piece.getX() - 1).get(piece.getY()).setGraphic(null);
        } else if (piece.getY() < 8 && liste_cases.get(piece.getX() - 1).get(piece.getY()).getValue() < 0) {
            liste_cases.get(piece.getX() - 1).get(piece.getY()).setStyle(color.toString());
        }
    }

    private static void resetWhiteKingMoveDownLeft(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getX() < 8 && piece.getY() > 1 && liste_cases.get(piece.getX()).get(piece.getY() - 2).getValue() == 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 2).setGraphic(null);
        } else if (piece.getX() < 8 && piece.getY() > 1 && liste_cases.get(piece.getX()).get(piece.getY() - 2).getValue() < 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 2).setStyle(color.toString());
        }
    }

    private static void resetWhiteKingMoveDown(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getX() < 8 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() == 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setGraphic(null);
        } else if (piece.getX() < 8 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() < 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setStyle(color.toString());
        }
    }

    private static void resetWhiteKingMoveDownRight(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getX() < 8 && piece.getY() < 8 && liste_cases.get(piece.getX()).get(piece.getY()).getValue() == 0) {
            liste_cases.get(piece.getX()).get(piece.getY()).setGraphic(null);
        } else if (piece.getX() < 8 && piece.getY() < 8 && liste_cases.get(piece.getX()).get(piece.getY()).getValue() < 0) {
            liste_cases.get(piece.getX()).get(piece.getY()).setStyle(color.toString());
        }
    }

    public static boolean checkTheBlackMove(Dragboard db, Case source, Case piece) {
        return ((db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() >= 0) || // Haut Gauche
                (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() && piece.getValue() >= 0) || // Haut
                (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() >= 0) || // Haut Droite
                (db.getDragViewOffsetX() == source.getX() && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() >= 0) || // Gauche
                (db.getDragViewOffsetX() == source.getX() && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() >= 0) || // Droite
                (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() >= 0) || // Bas Gauche
                (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() && piece.getValue() >= 0) || // Bas
                (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() >= 0)) /* Bas Droite */;
    }

    public static void blackKingMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        // Haut Gauche
        King.blackKingMoveUpLeft(piece, liste_cases);

        // Haut
        King.blackKingMoveUp(piece, liste_cases);

        // Haut Droite
        King.blackKingMoveUpRight(piece, liste_cases);

        // Gauche
        King.blackKingMoveLeft(piece, liste_cases);

        // Droite
        King.blackKingMoveRight(piece, liste_cases);

        // Bas Gauche
        King.blackKingMoveDownLeft(piece, liste_cases);

        // Bas
        King.blackKingMoveDown(piece, liste_cases);

        // Bas Droite
        King.blackKingMoveDownRight(piece, liste_cases);
    }

    private static void blackKingMoveUpLeft(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getX() > 1 && piece.getY() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).setGraphic(image);
        } else if (piece.getX() > 1 && piece.getY() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).getValue() > 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).setStyle(red);
        }
    }

    private static void blackKingMoveUp(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getX() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setGraphic(image);
        } else if (piece.getX() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() > 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setStyle(red);
        }
    }

    private static void blackKingMoveUpRight(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getX() > 1 && piece.getY() < 8 && liste_cases.get(piece.getX() - 2).get(piece.getY()).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 2).get(piece.getY()).setGraphic(image);
        } else if (piece.getX() > 1 && piece.getY() < 8 && liste_cases.get(piece.getX() - 2).get(piece.getY()).getValue() > 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY()).setStyle(red);
        }
    }

    private static void blackKingMoveLeft(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getY() > 1 && liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).setGraphic(image);
        } else if (piece.getY() > 1 && liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).getValue() > 0) {
            liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).setStyle(red);
        }
    }

    private static void blackKingMoveRight(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getY() < 8 && liste_cases.get(piece.getX() - 1).get(piece.getY()).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 1).get(piece.getY()).setGraphic(image);
        } else if (piece.getY() < 8 && liste_cases.get(piece.getX() - 1).get(piece.getY()).getValue() > 0) {
            liste_cases.get(piece.getX() - 1).get(piece.getY()).setStyle(red);
        }
    }

    private static void blackKingMoveDownLeft(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getX() < 8 && piece.getY() > 1 && liste_cases.get(piece.getX()).get(piece.getY() - 2).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() - 2).setGraphic(image);
        } else if (piece.getX() < 8 && piece.getY() > 1 && liste_cases.get(piece.getX()).get(piece.getY() - 2).getValue() > 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 2).setStyle(red);
        }
    }

    private static void blackKingMoveDown(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getX() < 8 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setGraphic(image);
        } else if (piece.getX() < 8 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() > 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setStyle(red);
        }
    }

    private static void blackKingMoveDownRight(Case piece, List<ArrayList<Case>> liste_cases) {
        if (piece.getX() < 8 && piece.getY() < 8 && liste_cases.get(piece.getX()).get(piece.getY()).getValue() == 0) {
            ImageView image = new ImageView("file:./resources/dot.png");
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY()).setGraphic(image);
        } else if (piece.getX() < 8 && piece.getY() < 8 && liste_cases.get(piece.getX()).get(piece.getY()).getValue() > 0) {
            liste_cases.get(piece.getX()).get(piece.getY()).setStyle(red);
        }
    }

    public static void resetBlackKingMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // Haut Gauche
        King.resetBlackKingMoveUpLeft(piece, liste_cases, color);

        // Haut
        King.resetBlackKingMoveUp(piece, liste_cases, color);

        // Haut Droite
        King.resetBlackKingMoveUpRight(piece, liste_cases, color);

        // Gauche
        King.resetBlackKingMoveLeft(piece, liste_cases, color);

        // Droite
        King.resetBlackKingMoveRight(piece, liste_cases, color);

        // Bas Gauche
        King.resetBlackKingMoveDownLeft(piece, liste_cases, color);

        // Bas
        King.resetBlackKingMoveDown(piece, liste_cases, color);

        // Bas Droite
        King.resetBlackKingMoveDownRight(piece, liste_cases, color);
    }

    private static void resetBlackKingMoveUpLeft(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getX() > 1 && piece.getY() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).getValue() == 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).setGraphic(null);
        } else if (piece.getX() > 1 && piece.getY() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).getValue() > 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 2).setStyle(color.toString());
        }
    }

    private static void resetBlackKingMoveUp(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getX() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() == 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setGraphic(null);
        } else if (piece.getX() > 1 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() > 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setStyle(color.toString());
        }
    }

    private static void resetBlackKingMoveUpRight(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getX() > 1 && piece.getY() < 8 && liste_cases.get(piece.getX() - 2).get(piece.getY()).getValue() == 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY()).setGraphic(null);
        } else if (piece.getX() > 1 && piece.getY() < 8 && liste_cases.get(piece.getX() - 2).get(piece.getY()).getValue() > 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY()).setStyle(color.toString());
        }
    }

    private static void resetBlackKingMoveLeft(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getY() > 1 && liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).getValue() == 0) {
            liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).setGraphic(null);
        } else if (piece.getY() > 1 && liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).getValue() > 0) {
            liste_cases.get(piece.getX() - 1).get(piece.getY() - 2).setStyle(color.toString());
        }
    }

    private static void resetBlackKingMoveRight(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getY() < 8 && liste_cases.get(piece.getX() - 1).get(piece.getY()).getValue() == 0) {
            liste_cases.get(piece.getX() - 1).get(piece.getY()).setGraphic(null);
        } else if (piece.getY() < 8 && liste_cases.get(piece.getX() - 1).get(piece.getY()).getValue() > 0) {
            liste_cases.get(piece.getX() - 1).get(piece.getY()).setStyle(color.toString());
        }
    }

    private static void resetBlackKingMoveDownLeft(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getX() < 8 && piece.getY() > 1 && liste_cases.get(piece.getX()).get(piece.getY() - 2).getValue() == 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 2).setGraphic(null);
        } else if (piece.getX() < 8 && piece.getY() > 1 && liste_cases.get(piece.getX()).get(piece.getY() - 2).getValue() > 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 2).setStyle(color.toString());
        }
    }

    private static void resetBlackKingMoveDown(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getX() < 8 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() == 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setGraphic(null);
        } else if (piece.getX() < 8 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() > 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setStyle(color.toString());
        }
    }

    private static void resetBlackKingMoveDownRight(Case piece, List<ArrayList<Case>> liste_cases, Background color) {
        if (piece.getX() < 8 && piece.getY() < 8 && liste_cases.get(piece.getX()).get(piece.getY()).getValue() == 0) {
            liste_cases.get(piece.getX()).get(piece.getY()).setGraphic(null);
        } else if (piece.getX() < 8 && piece.getY() < 8 && liste_cases.get(piece.getX()).get(piece.getY()).getValue() > 0) {
            liste_cases.get(piece.getX()).get(piece.getY()).setStyle(color.toString());
        }
    }
}
