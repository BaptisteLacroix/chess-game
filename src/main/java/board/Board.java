package board;

import java.util.ArrayList;

import pieces.Knight;
import pieces.Pawn;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import pieces.Rook;


/**
 * It creates a board with the pieces in their initial positions
 *
 * @author Lacroix Baptiste
 */
public class Board extends VBox {
    /**
     * It creates an ArrayList of ArrayList of Case objects.
     */
    private ArrayList<ArrayList<Case>> liste_cases = new ArrayList<>();
    /**
     * It creates a new HBox object that will be used to add the cases to the board.
     */
    private HBox board = new HBox();
    private final DataFormat dataFormat = new DataFormat("DragBlock");
    private int xDetected;
    private int yDetected;
    private static final String yellow = "-fx-background-color: yellow;";
    private static final String red = "-fx-background-color: red;";

    // It creates a board with the pieces in their initial positions
    public Board(int witdh, int height) {
        super();
        this.setHeight(height);
        this.setWidth(witdh);
        // Couleur marron
        this.setStyle("-fx-background-color: rgb(59,54,51); -fx-margin: 100px");

        init();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.setDragAndDrop();
                this.defineDepotZone();
            }
        }
        print();
    }

    /**
     * It creates a board with the pieces in their initial positions<br>
     * [<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;[-5, -2, -3, -800, -9, -3, -2, -5]<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;[-1, -1, -1, -1, -1, -1, -1, -1]<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;[0, 0, 0, 0, 0, 0, 0, 0]<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;[0, 0, 0, 0, 0, 0, 0, 0]<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;[0, 0, 0, 0, 0, 0, 0, 0]<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;[0, 0, 0, 0, 0, 0, 0, 0]<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;[1, 1, 1, 1, 1, 1, 1, 1]<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;[5, 2, 3, 800, 9, 3, 2, 5]<br>
     * ]
     */
    private void init() {
        for (int i = 1; i <= 8; i++) {
            ArrayList<Case> ligneActuelle = new ArrayList();
            board = new HBox();
            board.setAlignment(Pos.CENTER);
            if (i == 1) {
                intializeLinesBlack(i, ligneActuelle);
            } else if (i == 2) {
                initializeBlackPawnCase(i, ligneActuelle);
            } else if (i == 7) {
                initializeWhitePawnCase(i, ligneActuelle);
            } else if (i == 8) {
                intializeLinesWhite(i, ligneActuelle);
            } else {
                initializeEmptyCase(i, ligneActuelle);
            }
            liste_cases.add(ligneActuelle);
            this.getChildren().add(board);
        }
    }

    /**
     * It creates a new case, sets the image of the case to a pawn, and adds the case to the current line
     *
     * @param i             the line number
     * @param ligneActuelle the current line of the board
     */
    private void initializeWhitePawnCase(int i, ArrayList<Case> ligneActuelle) {
        for (int j = 1; j <= 8; j++) {
            Case c = new Case(i, j, 1, new ImageView(new Image("file:resources/white_pieces/pawn_white.png")), "pawn_white" + j);
            Background color = c.getBackground();
            c.setOnMouseEntered(event -> {
                c.setStyle(yellow);
                Pawn.whitePawnMoves(this.liste_cases, c);
            });
            c.setOnMouseExited(event -> {
                c.setStyle(color.toString());
                Pawn.resetWhitePawnMoves(this.liste_cases, c, color);
            });
            setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/pawn_white.png")), c);
        }
    }

    /**
     * It creates a new case, sets the image of the case to a black pawn, and adds the case to the current line
     *
     * @param i             the line number
     * @param ligneActuelle the current line of the board
     */
    private void initializeBlackPawnCase(int i, ArrayList<Case> ligneActuelle) {
        for (int j = 1; j <= 8; j++) {
            Case c = new Case(i, j, -1, new ImageView(new Image("file:resources/black_pieces/pawn_black.png")), "pawn_black" + j);
            Background color = c.getBackground();
            c.setOnMouseEntered(event -> {
                c.setStyle(yellow);
                Pawn.blackPawnMoves(this.liste_cases, c);
            });
            c.setOnMouseExited(event -> {
                c.setStyle(color.toString());
                Pawn.resetBlackPawnMoves(this.liste_cases, c, color);
            });
            setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/pawn_black.png")), c);
        }
    }

    /**
     * It creates a new case, sets the image of the case to a transparent image, and adds it to the list of cases
     *
     * @param i             the line number
     * @param ligneActuelle the ArrayList of Case objects that will be added to the ArrayList of ArrayList of Case objects
     */
    private void initializeEmptyCase(int i, ArrayList<Case> ligneActuelle) {
        for (int j = 1; j <= 8; j++) {
            Case c = new Case(i, j, 0, null, "empty" + j);
            setCase(ligneActuelle, null, c);
        }
    }

    /**
     * It initializes the white pieces on the board
     *
     * @param i             the line number
     * @param ligneActuelle the line we're currently working on
     */
    private void intializeLinesWhite(int i, ArrayList<Case> ligneActuelle) {
        Case c = new Case(i, 1, 5, new ImageView(new Image("file:resources/white_pieces/rook_white.png")), "rook_white1");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/rook_white.png")), c);

        c = new Case(i, 2, 2, new ImageView(new Image("file:resources/white_pieces/knight_white.png")), "knight_white1");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/knight_white.png")), c);

        c = new Case(i, 3, 3, new ImageView(new Image("file:resources/white_pieces/bishop_white.png")), "bishop_white1");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/bishop_white.png")), c);

        c = new Case(i, 4, 9, new ImageView(new Image("file:resources/white_pieces/queen_white.png")), "queen_white");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/queen_white.png")), c);

        c = new Case(i, 5, 800, new ImageView(new Image("file:resources/white_pieces/king_white.png")), "king_white");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/king_white.png")), c);

        c = new Case(i, 6, 3, new ImageView(new Image("file:resources/white_pieces/bishop_white.png")), "bishop_white2");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/bishop_white.png")), c);

        c = new Case(i, 7, 2, new ImageView(new Image("file:resources/white_pieces/knight_white.png")), "knight_white2");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/knight_white.png")), c);

        c = new Case(i, 8, 5, new ImageView(new Image("file:resources/white_pieces/rook_white.png")), "rook_white2");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/rook_white.png")), c);
    }

    /**
     * It initializes the black pieces on the board
     *
     * @param i             the line number
     * @param ligneActuelle the line we're currently working on
     */
    private void intializeLinesBlack(int i, ArrayList<Case> ligneActuelle) {
        Case c = new Case(i, 1, -5, new ImageView(new Image("file:resources/black_pieces/rook_black.png")), "rook_black1");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/rook_black.png")), c);

        c = new Case(i, 2, -2, new ImageView(new Image("file:resources/black_pieces/knight_black.png")), "knight_black1");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/knight_black.png")), c);

        c = new Case(i, 3, -3, new ImageView(new Image("file:resources/black_pieces/bishop_black.png")), "bishop_black1");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/bishop_black.png")), c);

        c = new Case(i, 4, -9, new ImageView(new Image("file:resources/black_pieces/queen_black.png")), "queen_black");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/queen_black.png")), c);

        c = new Case(i, 5, -800, new ImageView(new Image("file:resources/black_pieces/king_black.png")), "king_black");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/king_black.png")), c);

        c = new Case(i, 6, -3, new ImageView(new Image("file:resources/black_pieces/bishop_black.png")), "bishop_black2");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/bishop_black.png")), c);

        c = new Case(i, 7, -2, new ImageView(new Image("file:resources/black_pieces/knight_black.png")), "knight_black2");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/knight_black.png")), c);

        c = new Case(i, 8, -5, new ImageView(new Image("file:resources/black_pieces/rook_black.png")), "rook_black2");
        setMouseEvent(c);
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/rook_black.png")), c);
    }

    /**
     * It sets the mouse event for a Case object
     *
     * @param c the Case object that is being set
     */
    private void setMouseEvent(Case c) {
        Background color = c.getBackground();
        c.setOnMouseEntered(event -> {
            c.setStyle(yellow);
            // this.blackPawnMoves(c);
        });
        c.setOnMouseExited(event -> {
            c.setStyle(color.toString());
            // this.resetBlackPawnMoves(c, color);
        });
    }

    /**
     * It sets the size of the image and adds it to the board
     *
     * @param ligneActuelle the current line of the board
     * @param image         the image to be displayed on the case
     * @param c             the case we want to add to the board
     */
    private void setCase(ArrayList<Case> ligneActuelle, ImageView image, Case c) {
        setSize(image, c);
        ligneActuelle.add(c);
        board.getChildren().add(c);
    }

    /**
     * It sets the size of the image to the size of the case
     *
     * @param image the image to be displayed
     * @param c     the case of the board
     */
    private void setSize(ImageView image, Case c) {
        double cote = this.getHeight();
        if (image == null) {
            c.setPrefSize(cote / 8, cote / 8);
        } else {
            image.setFitHeight(cote / 8);
            image.setFitWidth(cote / 8);
            c.setGraphic(image);
            c.setPrefWidth(cote / 8);
            c.setPrefHeight(cote / 8);
        }
    }


    /**
     * It sets the drag and drop functionality for the chess board
     */
    private void setDragAndDrop() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Case piece = (Case) ((HBox) this.getChildren().get(i)).getChildren().get(j);
                piece.setOnDragDetected(event -> {
                    if (piece.getImageView() != null) {
                        Dragboard db = piece.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent content = new ClipboardContent();
                        ImageView image = piece.getImageView();
                        image.setPreserveRatio(true);
                        content.putImage(image.getImage());
                        content.put(this.dataFormat, piece.getValue());
                        xDetected = piece.getX();
                        yDetected = piece.getY();
                        db.setContent(content);
                        db.setDragView(piece.snapshot(null, null));
                        event.consume();
                    }
                });
            }
        }
    }


    /**
     * It defines the depot zone for the pieces
     */
    private void defineDepotZone() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Case piece = (Case) ((HBox) this.getChildren().get(i)).getChildren().get(j);
                Background color = piece.getBackground();
                piece.setOnDragOver(event -> {
                    Dragboard db = event.getDragboard();
                    if (db.hasContent(this.dataFormat)) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                });

                piece.setOnDragDropped(dragEvent -> {
                    Dragboard db = dragEvent.getDragboard();
                    if (db.hasContent(this.dataFormat)) {
                        String value = db.getContent(this.dataFormat).toString();
                        if (Integer.parseInt(value) > 0) {
                            this.chooseWhitePieceToDrop((Case) dragEvent.getGestureSource(), piece, db);
                        } else {
                            this.chooseBlackPieceToDrop((Case) dragEvent.getGestureSource(), piece, db);
                        }
                        // Drop Complete
                        dragEvent.setDropCompleted(true);
                    }
                    dragEvent.consume();
                });

                piece.setOnDragExited(event -> {
                    piece.setStyle(color.toString());
                    event.consume();
                });

                setDragAndDrop();
            }
        }
    }

    private void chooseWhitePieceToDrop(Case source, Case piece, Dragboard db) {
        db.setDragViewOffsetX(piece.getX());
        db.setDragViewOffsetY(piece.getY());
        switch (source.getValue()) {
            case 1:
                if ((db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() && piece.getValue() == 0)
                        || ((db.getDragViewOffsetX() == source.getX() - 2 && db.getDragViewOffsetY() == source.getY() && piece.getValue() == 0) &&
                        (((Case) ((HBox) this.getChildren().get(source.getX() - 2)).getChildren().get(source.getY() - 1)).getValue() == 0)) ||
                        ((db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() - 1 ||
                                db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() + 1) && piece.getValue() < 0)) {
                    this.setWhiteDropPiece(piece, db);
                }
                break;
            case 2:
                if ((db.getDragViewOffsetX() == source.getX() - 2 && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() <= 0) || // Haut Gauche
                        (db.getDragViewOffsetX() == source.getX() - 2 && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() <= 0) || // Haut Bas
                        (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() - 2 && piece.getValue() <= 0) || // Gauche Haut
                        (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() - 2 && piece.getValue() <= 0) || // Gauche Bas
                        (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() + 2 && piece.getValue() <= 0) || // Droite haut
                        (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() + 2 && piece.getValue() <= 0) || // Droite Bas
                        (db.getDragViewOffsetX() == source.getX() + 2 && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() <= 0) || // Bas Gauche
                        (db.getDragViewOffsetX() == source.getX() + 2 && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() <= 0)) /* Bas Droite */ {
                    this.setWhiteDropPiece(piece, db);
                }
                break;
            case 3:
                throw new UnsupportedOperationException("3 : Not supported yet.");
            case 5:
                if ((db.getDragViewOffsetX() == source.getX())) { // Gauche Droite
                    boolean isValid = true;
                    int ligne = 0;
                    if (db.getDragViewOffsetY() > source.getY()) {
                        while (isValid && (source.getY() + ligne) < db.getDragViewOffsetY()) { // Droite
                            if (((Case) ((HBox) this.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() + ligne)).getValue() > 0 ||
                                    (((Case) ((HBox) this.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() + ligne)).getValue() < 0 &&
                                            (source.getY() + ligne + 1) != db.getDragViewOffsetY())) {
                                isValid = false;
                            }
                            ligne++;
                        }
                    } else {
                        ligne = 1;
                        while (isValid && (source.getY() - ligne) > db.getDragViewOffsetY()) { // Gauche
                            if (((Case) ((HBox) this.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() - ligne - 1)).getValue() > 0 ||
                                    (((Case) ((HBox) this.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() - ligne - 1)).getValue() < 0 &&
                                            (source.getY() - ligne + 1) != db.getDragViewOffsetY())) {
                                isValid = false;
                            }
                            ligne++;
                        }
                    }
                    if (isValid) {
                        this.setWhiteDropPiece(piece, db);
                    }

                } else if (db.getDragViewOffsetY() == source.getY()) { // Haut Bas
                    boolean isValid = true;
                    int colonne = 0;
                    if (db.getDragViewOffsetX() > source.getX()) {
                        while (isValid && (source.getX() + colonne) < db.getDragViewOffsetX()) { // Bas
                            if (((Case) ((HBox) this.getChildren().get(source.getX() + colonne)).getChildren().get(source.getY() - 1)).getValue() > 0 ||
                                    (((Case) ((HBox) this.getChildren().get(source.getX() + colonne)).getChildren().get(source.getY() - 1)).getValue() < 0 &&
                                            (source.getX() + colonne + 1) != db.getDragViewOffsetX())) {
                                isValid = false;
                            }
                            colonne++;
                        }
                    } else {
                        colonne = 1;
                        while (isValid && (source.getX() - colonne) > db.getDragViewOffsetX()) { // Haut
                            if (((Case) ((HBox) this.getChildren().get(source.getX() - colonne - 1)).getChildren().get(source.getY() - 1)).getValue() > 0 ||
                                    (((Case) ((HBox) this.getChildren().get(source.getX() - colonne - 1)).getChildren().get(source.getY() - 1)).getValue() < 0 &&
                                            (source.getX() - colonne + 1) != db.getDragViewOffsetX())) {
                                isValid = false;
                            }
                            colonne++;
                        }
                    }
                    if (isValid) {
                        this.setWhiteDropPiece(piece, db);
                    }
                }
                break;
            case 9:
                throw new UnsupportedOperationException("9 : Not supported yet.");
            case 800:
                throw new UnsupportedOperationException("800 : Not supported yet.");
            default:
                throw new UnsupportedOperationException("Default : Not supported yet.");
        }
    }

    private void chooseBlackPieceToDrop(Case source, Case piece, Dragboard db) {
        db.setDragViewOffsetX(piece.getX());
        db.setDragViewOffsetY(piece.getY());
        switch (source.getValue()) {
            case -1:
                if ((db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() && piece.getValue() == 0)
                        || (piece.getX() < 8 && (db.getDragViewOffsetX() == source.getX() + 2 && db.getDragViewOffsetY() == source.getY() && piece.getValue() == 0) &&
                        ((Case) ((HBox) this.getChildren().get(source.getX())).getChildren().get(source.getY() - 1)).getValue() == 0) ||
                        (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() - 1 ||
                                db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() + 1) && piece.getValue() > 0) {
                    this.setBlackDropPiece(piece, db);
                }
                break;
            case -2:
                if ((db.getDragViewOffsetX() == source.getX() - 2 && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() >= 0) || // Haut Gauche
                        (db.getDragViewOffsetX() == source.getX() - 2 && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() >= 0) || // Haut Bas
                        (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() - 2 && piece.getValue() >= 0) || // Gauche Haut
                        (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() - 2 && piece.getValue() >= 0) || // Gauche Bas
                        (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() + 2 && piece.getValue() >= 0) || // Droite haut
                        (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() + 2 && piece.getValue() >= 0) || // Droite Bas
                        (db.getDragViewOffsetX() == source.getX() + 2 && db.getDragViewOffsetY() == source.getY() - 1 && piece.getValue() >= 0) || // Bas Gauche
                        (db.getDragViewOffsetX() == source.getX() + 2 && db.getDragViewOffsetY() == source.getY() + 1 && piece.getValue() >= 0)) /* Bas Droite */ {
                    this.setBlackDropPiece(piece, db);
                }
                break;
            case -3:
                throw new UnsupportedOperationException("-3 : Not supported yet.");
            case -5:
                if ((db.getDragViewOffsetX() == source.getX())) { // Gauche Droite
                    boolean isValid = true;
                    int ligne = 0;
                    if (db.getDragViewOffsetY() > source.getY()) {
                        while (isValid && (source.getY() + ligne) < db.getDragViewOffsetY()) { // Droite
                            if (((Case) ((HBox) this.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() + ligne)).getValue() < 0 ||
                                    (((Case) ((HBox) this.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() + ligne)).getValue() > 0 &&
                                            (source.getY() + ligne + 1) != db.getDragViewOffsetY())) {
                                isValid = false;
                            }
                            ligne++;
                        }
                    } else {
                        ligne = 1;
                        while (isValid && (source.getY() - ligne) > db.getDragViewOffsetY()) { // Gauche
                            if (((Case) ((HBox) this.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() - ligne - 1)).getValue() < 0 ||
                                    (((Case) ((HBox) this.getChildren().get(source.getX() - 1)).getChildren().get(source.getY() - ligne - 1)).getValue() > 0 &&
                                            (source.getY() - ligne + 1) != db.getDragViewOffsetY())) {
                                isValid = false;
                            }
                            ligne++;
                        }
                    }
                    if (isValid) {
                        this.setBlackDropPiece(piece, db);
                    }

                } else if (db.getDragViewOffsetY() == source.getY()) { // Haut Bas
                    boolean isValid = true;
                    int colonne = 0;
                    if (db.getDragViewOffsetX() > source.getX()) {
                        while (isValid && (source.getX() + colonne) < db.getDragViewOffsetX()) { // Bas
                            if (((Case) ((HBox) this.getChildren().get(source.getX() + colonne)).getChildren().get(source.getY() - 1)).getValue() < 0 ||
                                    (((Case) ((HBox) this.getChildren().get(source.getX() + colonne)).getChildren().get(source.getY() - 1)).getValue() > 0 &&
                                            (source.getX() + colonne + 1) != db.getDragViewOffsetX())) {
                                isValid = false;
                            }
                            colonne++;
                        }
                    } else {
                        colonne = 1;
                        while (isValid && (source.getX() - colonne) > db.getDragViewOffsetX()) { // Haut
                            if (((Case) ((HBox) this.getChildren().get(source.getX() - colonne - 1)).getChildren().get(source.getY() - 1)).getValue() < 0 ||
                                    (((Case) ((HBox) this.getChildren().get(source.getX() - colonne - 1)).getChildren().get(source.getY() - 1)).getValue() > 0 &&
                                            (source.getX() - colonne + 1) != db.getDragViewOffsetX())) {
                                isValid = false;
                            }
                            colonne++;
                        }
                    }
                    if (isValid) {
                        this.setBlackDropPiece(piece, db);
                    }
                }
                break;
            case -9:
                throw new UnsupportedOperationException("-9 : Not supported yet.");
            case -800:
                throw new UnsupportedOperationException("-800 : Not supported yet.");
            default:
                throw new UnsupportedOperationException("Default : Not supported yet.");
        }
    }

    /**
     * It's a function that allows the player to move a piece on the board
     *
     * @param piece the case where the piece is dropped
     * @param db    The Dragboard object that contains the data being dragged.
     */
    private void setBlackDropPiece(Case piece, Dragboard db) {
        if (piece.getValue() == 0) {

            ImageView imageDB = null;
            if (piece.getImageView() != null) {
                imageDB = new ImageView(piece.getImageView().getImage());
            }

            // Set the piece on the board
            db.setDragViewOffsetX(piece.getX());
            db.setDragViewOffsetY(piece.getY());
            db.setDragView(db.getImage());

            // New position of the piece
            String name = piece.getName();
            ImageView imagePiece = new ImageView(db.getDragView());
            piece.setImageView(imagePiece);
            piece.setValue((int) db.getContent(this.dataFormat));
            piece.setName(((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).getName());
            piece.setNewPosition((int) db.getDragViewOffsetX(), (int) db.getDragViewOffsetY());
            Background color = piece.getBackground();
            this.setColorMoves(color, piece);

            ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1).setOnMouseEntered(event -> Pawn.resetWhitePawnMoves(this.liste_cases, (Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1), color));

            // Old position of the piece
            ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setName(name);
            ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setImageView(imageDB);
            ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setValue(0);
            ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setNewPosition(xDetected, yDetected);

        } else if (piece.getValue() > 0) {

            // Set the piece on the board
            db.setDragViewOffsetX(piece.getX());
            db.setDragViewOffsetY(piece.getY());
            db.setDragView(db.getImage());


            // New position of the piece
            ImageView imagePiece = new ImageView(db.getDragView());
            piece.setImageView(imagePiece);
            piece.setValue((int) db.getContent(this.dataFormat));
            piece.setName(((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).getName());
            piece.setNewPosition((int) db.getDragViewOffsetX(), (int) db.getDragViewOffsetY());
            Background color = piece.getBackground();
            this.setColorMoves(color, piece);
            ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1).setOnMouseEntered(event -> Pawn.resetWhitePawnMoves(this.liste_cases, (Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1), color));


            // Old position of the piece
            setVoidCaseToNull();
        }
        this.defineDepotZone();
    }

    /**
     * It's a function that allows the player to move a piece on the board
     *
     * @param piece the case where the piece is dropped
     * @param db    The Dragboard object that contains the data being dragged.
     */
    private void setWhiteDropPiece(Case piece, Dragboard db) {
        if (piece.getValue() == 0) {

            ImageView imageDB = null;
            if (piece.getImageView() != null) {
                imageDB = new ImageView(piece.getImageView().getImage());
            }

            // Set the piece on the board
            db.setDragViewOffsetX(piece.getX());
            db.setDragViewOffsetY(piece.getY());
            db.setDragView(db.getImage());


            // New position of the piece
            String name = piece.getName();
            ImageView imagePiece = new ImageView(db.getDragView());
            piece.setImageView(imagePiece);
            piece.setValue((int) db.getContent(this.dataFormat));
            piece.setName(((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).getName());
            piece.setNewPosition((int) db.getDragViewOffsetX(), (int) db.getDragViewOffsetY());
            Background color = piece.getBackground();
            this.setColorMoves(color, piece);

            ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1).setOnMouseEntered(event -> Pawn.resetWhitePawnMoves(this.liste_cases, (Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1), color));


            // Old position of the piece
            ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setName(name);
            ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setImageView(imageDB);
            ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setValue(0);
            ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setNewPosition(xDetected, yDetected);

        } else if (piece.getValue() < 0) {

            // Set the piece on the board
            db.setDragViewOffsetX(piece.getX());
            db.setDragViewOffsetY(piece.getY());
            db.setDragView(db.getImage());


            // New position of the piece
            ImageView imagePiece = new ImageView(db.getDragView());
            piece.setImageView(imagePiece);
            piece.setValue((int) db.getContent(this.dataFormat));
            piece.setName(((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).getName());
            piece.setNewPosition((int) db.getDragViewOffsetX(), (int) db.getDragViewOffsetY());
            Background color = piece.getBackground();
            this.setColorMoves(color, piece);
            ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1).setOnMouseEntered(event -> Pawn.resetWhitePawnMoves(this.liste_cases, (Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1), color));


            // Old position of the piece
            setVoidCaseToNull();
        }
        this.defineDepotZone();
    }

    /**
     * It sets the image of the case to null, sets the value of the case to 0 and sets the new position of the case to the
     * position of the case that was clicked
     */
    private void setVoidCaseToNull() {
        ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setImageView(null);
        ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setValue(0);
        ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setNewPosition(xDetected, yDetected);
    }

    /**
     * It sets the mouse events for each piece, so that when the mouse enters the piece, the possible moves for that piece
     * are highlighted, and when the mouse exits the piece, the possible moves are reset to their original color
     *
     * @param color the color of the case
     * @param piece the piece that is being moved
     */
    private void setColorMoves(Background color, Case piece) {
        switch (piece.getValue()) {
            case -1:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Pawn.blackPawnMoves(this.liste_cases, piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Pawn.resetBlackPawnMoves(this.liste_cases, piece, color);
                });
                break;
            case -2:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Knight.blackKnightMoves(this.liste_cases, piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Knight.resetBlackKnightMoves(this.liste_cases, piece, color);
                });
                break;
            case -3:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    this.blackBishopMoves(piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    this.resetBlackBishopMoves(piece, color);
                });
                break;
            case -5:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Rook.blackRookMoves(this.liste_cases, piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Rook.resetBlackRookMoves(this.liste_cases, piece, color);
                });
                break;
            case -9:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    this.blackQueenMoves(piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    this.resetBlackQueenMoves(piece, color);
                });
                break;
            case -800:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    this.blackKingMoves(piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    this.resetBlackKingMoves(piece, color);
                });
                break;
            case 1:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Pawn.whitePawnMoves(this.liste_cases, piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Pawn.resetWhitePawnMoves(this.liste_cases, piece, color);
                });
                break;
            case 2:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Knight.whiteKnightMoves(this.liste_cases, piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Knight.resetWhiteKnightMoves(this.liste_cases, piece, color);
                });
                break;
            case 3:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    this.whiteBishopMoves(piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    this.resetWhiteBishopMoves(piece, color);
                });
                break;
            case 5:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Rook.whiteRookMoves(this.liste_cases, piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Rook.resetWhiteRookMoves(this.liste_cases, piece, color);
                });
                break;
            case 9:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    this.whiteQueenMoves(piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    this.resetWhiteQueenMoves(piece, color);
                });
                break;
            default:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    this.whiteKingMoves(piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    this.resetWhiteKingMoves(piece, color);
                });
                break;

        }
    }

    private void whiteBishopMoves(Case piece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void resetWhiteBishopMoves(Case piece, Background color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void whiteQueenMoves(Case piece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void resetWhiteQueenMoves(Case piece, Background color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void whiteKingMoves(Case piece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void resetWhiteKingMoves(Case piece, Background color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void blackBishopMoves(Case piece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void resetBlackBishopMoves(Case piece, Background color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void blackQueenMoves(Case piece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void resetBlackQueenMoves(Case piece, Background color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void blackKingMoves(Case piece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void resetBlackKingMoves(Case piece, Background color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * It prints the coordinates of each case in the list of cases
     */
    private void print() {
        for (ArrayList<Case> liste_case : liste_cases) {
            for (Case aCase : liste_case) {
                System.out.print("(" + aCase.getX() + "," + aCase.getY() + "),");
            }
            System.out.println();
        }
        System.out.println();
    }
}
