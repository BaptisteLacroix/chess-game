package pieces;

import board.Case;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Queen {

    public static boolean checkTheWhiteMove(Dragboard db, VBox vBox, Case source) {
        return (Rook.checkTheWhiteMove(db, vBox, source) || Bishop.checkTheWhiteMove(db, vBox, source));
    }
    
    public static void whiteQueenMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        Bishop.whiteBishopMoves(liste_cases, piece);
        Rook.whiteRookMoves(liste_cases, piece);
    }
    
    public static void resetWhiteQueenMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        Bishop.resetWhiteBishopMoves(liste_cases, piece, color);
        Rook.resetWhiteRookMoves(liste_cases, piece, color);
    }

    public static boolean checkTheBlackMove(Dragboard db, VBox vBox, Case source) {
        return (Rook.checkTheBlackMove(db, vBox, source) || Bishop.checkTheBlackMove(db, vBox, source));
    }

    public static void blackQueenMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        Bishop.blackBishopMoves(liste_cases, piece);
        Rook.blackRookMoves(liste_cases, piece);
    }

    public static void resetBlackQueenMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        Bishop.resetBlackBishopMoves(liste_cases, piece, color);
        Rook.resetBlackRookMoves(liste_cases, piece, color);
    }
}
