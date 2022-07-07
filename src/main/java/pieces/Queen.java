package pieces;

import board.Case;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to create a Queen object that is used to display the pieces on the board.
 *
 * @author Lacroix Baptiste
 */
public class Queen {

    /**
     * It checks if the move is valid for a white queen
     *
     * @param db the Dragboard object
     * @param vBox the VBox that contains the chessboard
     * @param source the source case
     * @return A boolean value.
     */
    public static boolean checkTheWhiteMove(Dragboard db, VBox vBox, Case source) {
        return (Rook.checkTheWhiteMove(db, vBox, source) || Bishop.checkTheWhiteMove(db, vBox, source));
    }
    
    /**
     * It calls the whiteBishopMoves and whiteRookMoves functions, which are defined in the Bishop and Rook classes
     * respectively
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece we want to move
     */
    public static void whiteQueenMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        Bishop.whiteBishopMoves(liste_cases, piece);
        Rook.whiteRookMoves(liste_cases, piece);
    }
    
    /**
     * It resets the moves of the white queen by resetting the moves of the white bishop and the white rook
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece we want to reset the moves for
     * @param color the color of the piece
     */
    public static void resetWhiteQueenMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        Bishop.resetWhiteBishopMoves(liste_cases, piece, color);
        Rook.resetWhiteRookMoves(liste_cases, piece, color);
    }

    /**
     * It checks if the move is valid for the black queen
     *
     * @param db the Dragboard object
     * @param vBox the VBox that contains all the cases
     * @param source the source case
     * @return A boolean value.
     */
    public static boolean checkTheBlackMove(Dragboard db, VBox vBox, Case source) {
        return (Rook.checkTheBlackMove(db, vBox, source) || Bishop.checkTheBlackMove(db, vBox, source));
    }

    /**
     * It calls the blackBishopMoves and blackRookMoves functions, which are defined in the Bishop and Rook classes
     * respectively
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece we want to move
     */
    public static void blackQueenMoves(List<ArrayList<Case>> liste_cases, Case piece) {
        Bishop.blackBishopMoves(liste_cases, piece);
        Rook.blackRookMoves(liste_cases, piece);
    }

    /**
     * It resets the moves of the black queen by resetting the moves of the black bishop and the black rook
     *
     * @param liste_cases the list of all the cases of the board
     * @param piece the piece we want to reset the moves for
     * @param color the color of the piece
     */
    public static void resetBlackQueenMoves(List<ArrayList<Case>> liste_cases, Case piece, Background color) {
        Bishop.resetBlackBishopMoves(liste_cases, piece, color);
        Rook.resetBlackRookMoves(liste_cases, piece, color);
    }
}
