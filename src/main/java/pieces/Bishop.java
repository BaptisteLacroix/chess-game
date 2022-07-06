package pieces;

import board.Case;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

import java.util.ArrayList;
import java.util.List;

public class Bishop {
    private static final String red = "-fx-background-color: red;";

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

    private static void whiteBishopMoveDownLeft(List<ArrayList<Case>> liste_cases, Case piece) {
        // x = x + n
        // y = y - n
        int n = 1;
        boolean adversaire = false;
        while (!adversaire && piece.getX() + n < 8 && piece.getY() - n > 0) {
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

    private static void resetWhiteBishopMoveDownLeft(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        // x = x + n
        // y = y - n
        int n = 1;
        boolean adversaire = false;
        while (!adversaire && piece.getX() + n < 8 && piece.getY() - n > 0) {
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

    private static void blackBishopMoveDownLeft(List<ArrayList<Case>> liste_cases, Case piece) {
        // x = x + n
        // y = y - n
        int n = 1;
        boolean adversaire = false;
        while (!adversaire && piece.getX() + n < 8 && piece.getY() - n > 0) {
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
