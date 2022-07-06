package board;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;


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
    ImageView image = new ImageView("file:./resources/dot.png");
    ImageView image2 = new ImageView("file:./resources/dot.png");

    // It creates a board with the pieces in their initial positions
    public Board(int witdh, int height) {
        super();
        this.setHeight(height);
        this.setWidth(witdh);
        // Couleur marron
        this.setStyle("-fx-background-color: rgb(59,54,51); -fx-margin: 100px");

        image.setFitHeight(500 / (double) 8);
        image.setFitWidth(500 / (double) 8);

        image2.setFitHeight(500 / (double) 8);
        image2.setFitWidth(500 / (double) 8);

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
                this.whitePawnMoves(c);
            });
            c.setOnMouseExited(event -> {
                c.setStyle(color.toString());
                this.resetWhitePawnMoves(c, color);
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
                this.blackPawnMoves(c);
            });
            c.setOnMouseExited(event -> {
                c.setStyle(color.toString());
                this.resetBlackPawnMoves(c, color);
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
                this.setWhiteDropPiece(piece, db);
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
                this.setBlackDropPiece(piece, db);
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

            ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1).setOnMouseEntered(event -> this.resetWhitePawnMoves((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1), color));


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
            ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1).setOnMouseEntered(event -> this.resetWhitePawnMoves((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1), color));


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

            ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1).setOnMouseEntered(event -> this.resetWhitePawnMoves((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1), color));


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
            ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1).setOnMouseEntered(event -> this.resetWhitePawnMoves((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1), color));


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
                    this.blackPawnMoves(piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    this.resetBlackPawnMoves(piece, color);
                });
                break;
            case -2:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    this.blackKnightMoves(piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    this.resetBlackKnightMoves(piece, color);
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
                    this.blackRookMoves(piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    this.resetBlackRookMoves(piece, color);
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
                    this.whitePawnMoves(piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    this.resetWhitePawnMoves(piece, color);
                });
                break;
            case 2:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    this.whiteKnightMoves(piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    this.resetWhiteKnightMoves(piece, color);
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
                    this.whiteRookMoves(piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    this.resetWhiteRookMoves(piece, color);
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

    /**
     * This function will check all the possible moves of a white pawn.
     *
     * @param piece the piece that is being moved
     */
    private void whitePawnMoves(Case piece) {
        if (piece.getX() > 2 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 1).getValue() == 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setGraphic(image);
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 1).setGraphic(image2);
        } else if (piece.getX() > 2 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 1).getValue() < 0) {
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
    private void resetWhitePawnMoves(Case piece, Background color) {
        if (piece.getX() > 2 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 1).getValue() == 0) {
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).setGraphic(null);
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 1).setGraphic(null);
        } else if (piece.getX() > 2 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 1).getValue() < 0) {
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

    /**
     * This function will check all the possible moves of a white rook.
     *
     * @param piece the piece we want to move
     */
    private void whiteRookMoves(Case piece) {
        // Colonne Bas
        this.whiteRookMoveDown(piece);

        // Ligne droite
        this.whiteRookMoveRight(piece);

        // Colonne Haut
        this.whiteRookMoveUp(piece);

        // Ligne Gauche
        this.whiteRookMoveLeft(piece);
    }

    /**
     * It resets the rook's moves
     *
     * @param piece the piece we want to reset the moves for
     * @param color the color of the piece
     */
    private void resetWhiteRookMoves(Case piece, Background color) {
        // Colonne Bas
        this.resetWhiteRookMoveDown(piece, color);

        // Ligne droite
        this.resetWhiteRookMoveRight(piece, color);

        // Colonne Haut
        this.resetWhiteRookMoveUp(piece, color);

        // Ligne Gauche
        this.resetWhiteRookMoveLeft(piece, color);
    }

    /**
     * It checks if the white rook can move down, and if it can, it highlights the possible moves
     *
     * @param piece the piece that is being moved
     */
    private void whiteRookMoveDown(Case piece) {
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
    private void whiteRookMoveUp(Case piece) {
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
    private void whiteRookMoveRight(Case piece) {
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
    private void whiteRookMoveLeft(Case piece) {
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
    private void resetWhiteRookMoveDown(Case piece, Background color) {
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
    private void resetWhiteRookMoveUp(Case piece, Background color) {
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
    private void resetWhiteRookMoveRight(Case piece, Background color) {
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
    private void resetWhiteRookMoveLeft(Case piece, Background color) {
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

    private void whiteKnightMoves(Case piece) {
        // Haut Droite et Gauche
        this.whiteKnightMoveUp(piece);

        // Droite Haut et Bas
        this.whiteKnightMoveRight(piece);

        // Bas Droite et Gauche
        this.whiteKnightMoveDown(piece);

        // Gauche Haut et Bas
        this.whiteKnightMoveLeft(piece);
    }


    private void whiteKnightMoveUp(Case piece) {
        if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() < 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() < 0) {
            // Case droite ennemie et case gauche ennemie
            liste_cases.get(piece.getX() - 3).get(piece.getY()).setStyle(red); // Case droite ennemie
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setStyle(red); // Case gauche ennemie
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() < 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() > 0) {
            // Case droite ennemie et case gauche alliée
            liste_cases.get(piece.getX() - 3).get(piece.getY()).setStyle(red); // Case droite ennemie
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() < 0 && liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() > 0) {
            // Case gauche ennemie et case droite alliée
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setStyle(red); // Case gauche ennemie
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() < 0) {
            // Case droite ennemie
            liste_cases.get(piece.getX() - 3).get(piece.getY()).setStyle(red); // Case droite ennemie
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setGraphic(image);
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() < 0) {
            // Case gauche ennemie
            liste_cases.get(piece.getX() - 3).get(piece.getY()).setGraphic(image); // Case droite vide
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setStyle(red); // Case gauche ennemie
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() == 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() == 0) {
            // Cases vide
            liste_cases.get(piece.getX() - 3).get(piece.getY()).setGraphic(image); // Case droite vide
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setGraphic(image2); // Case gauche vide
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() == 0) {
            // Case droite alliée et case gauche vide
            liste_cases.get(piece.getX() - 3).get(piece.getY()).setGraphic(image2); // Case gauche vide
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() == 0) {
            // Case gauche alliée et case droite vide
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setGraphic(image); // Case droite vide
        }
    }

    private void whiteKnightMoveRight(Case piece) {
        if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() < 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() < 0) {
            // Case basse ennemie et case haute ennemie
            liste_cases.get(piece.getX()).get(piece.getY() + 1).setStyle(red); // Case basse ennemie
            liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setStyle(red); // Case haute ennemie
        } else if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() < 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() > 0) {
            // Case haute ennemie et basse alliée
            liste_cases.get(piece.getX()).get(piece.getY() + 1).setStyle(red);
        } else if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() < 0 && liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() > 0) {
            // Case basse ennemie et case haute alliée
            liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setStyle(red);
        } else if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() < 0) {
            // Case basse ennemie et case haute vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() + 1).setStyle(red); // Case basse ennemie
            liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setGraphic(newImage); // Case haute vide
        } else if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() < 0) {
            // Case haute ennemie et case basse vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() + 1).setGraphic(newImage); // Case basse vide
            liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setStyle(red); // Case haute ennemie
        } else if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() == 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() == 0) {
            // Cases vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            ImageView newImage2 = new ImageView("file:./resources/dot.png");
            newImage2.setFitHeight(500 / (double) 8);
            newImage2.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() + 1).setGraphic(newImage); // Case basse vide
            liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setGraphic(newImage2); // Case haute vide
        } else if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() > 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() == 0) {
            // Case basse alliée et case haute vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setGraphic(newImage); // Case haute vide
        } else if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() == 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() > 0) {
            // Case haute alliée et case basse vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() + 1).setGraphic(newImage); // Case basse vide
        }
    }

    private void whiteKnightMoveLeft(Case piece) {
        if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() < 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() < 0) {
            // Case basse ennemie et case haute ennemie
            liste_cases.get(piece.getX()).get(piece.getY() - 3).setStyle(red); // Case basse ennemie
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setStyle(red); // Case haute ennemie
        } else if (liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() < 0 && liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() > 0) {
            // Case haute ennemie et basse alliée
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setStyle(red); // Case haute ennemie
        } else if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() < 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() > 0) {
            // Case basse ennemie et case haute alliée
            liste_cases.get(piece.getX()).get(piece.getY() - 3).setStyle(red); // Case basse ennemie
        } else if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() < 0) {
            // Case basse ennemie et case haute vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() - 3).setStyle(red); // Case basse ennemie
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setGraphic(newImage); // Case haute vide
        } else if (liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() < 0) {
            // Case haute ennemie et case basse vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() - 3).setGraphic(newImage); // Case basse vide
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setStyle(red); // Case haute ennemie
        } else if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() == 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() == 0) {
            // Cases vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            ImageView newImage2 = new ImageView("file:./resources/dot.png");
            newImage2.setFitHeight(500 / (double) 8);
            newImage2.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() - 3).setGraphic(newImage); // Case basse vide
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setGraphic(newImage2); // Case haute vide
        } else if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() > 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() == 0) {
            // Case basse alliée et case haute vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setGraphic(newImage); // Case haute vide
        } else if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() == 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() > 0) {
            // Case haute alliée et case basse vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX()).get(piece.getY() - 3).setGraphic(newImage); // Case basse vide
        }
    }

    private void whiteKnightMoveDown(Case piece) {
        if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() < 0 && liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() < 0) {
            // Case gauche ennemie et case droite ennemie
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setStyle(red); // Case gauche ennemie
            liste_cases.get(piece.getX() + 1).get(piece.getY()).setStyle(red); // Case droite ennemie
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() < 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() > 0) {
            // Case droite ennemie et case gauche alliée
            liste_cases.get(piece.getX() + 1).get(piece.getY()).setStyle(red);
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() < 0 && liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() > 0) {
            // Case gauche ennemie et case droite alliée
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setStyle(red);
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() < 0) {
            // Case droite ennemie et case gauche vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() + 1).get(piece.getY()).setStyle(red); // Case droite ennemie
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setGraphic(newImage); // Case gauche vide
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() < 0) {
            // Case gauche ennemie et case droite vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() + 1).get(piece.getY()).setGraphic(newImage); // Case droite vide
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setStyle(red); // Case gauche ennemie
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() == 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() == 0) {
            // Cases vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            ImageView newImage2 = new ImageView("file:./resources/dot.png");
            newImage2.setFitHeight(500 / (double) 8);
            newImage2.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() + 1).get(piece.getY()).setGraphic(newImage); // Case droite vide
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setGraphic(newImage2); // Case gauche vide
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() > 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() == 0) {
            // Case droite alliée et case gauche vide
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setGraphic(newImage); // Case droite alliée
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() == 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() > 0) {
            // Case droite vide et case gauche alliée
            ImageView newImage = new ImageView("file:./resources/dot.png");
            newImage.setFitHeight(500 / (double) 8);
            newImage.setFitWidth(500 / (double) 8);
            liste_cases.get(piece.getX() + 1).get(piece.getY()).setGraphic(newImage); // Case droite vide
        }
    }

    private void resetWhiteKnightMoves(Case piece, Background color) {
        // Haut Droite et Gauche
        this.resetWhiteKnightMoveUp(piece, color);

        // Droite Haut et Bas
        this.resetWhiteKnightMoveRight(piece, color);

        // Bas Droite et Gauche
        this.resetWhiteKnightMoveDown(piece, color);

        // Gauche Haut et Bas
        this.resetWhiteKnightMoveLeft(piece, color);
    }

    private void resetWhiteKnightMoveUp(Case piece, Background color) {
        if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() < 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() < 0) {
            // Case gauche ennemie et case droite ennemie
            liste_cases.get(piece.getX() - 3).get(piece.getY()).setStyle(color.toString()); // Case droite ennemie
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setStyle(color.toString()); // Case gauche ennemie
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() < 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() > 0) {
            // Case droite ennemie et case droite Alliée
            liste_cases.get(piece.getX() - 3).get(piece.getY()).setStyle(color.toString()); // Case droite ennemie
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() < 0 && liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() > 0) {
            // Case gauche ennemie et case gauche Alliée
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setStyle(color.toString()); // Case gauche ennemie
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() < 0) {
            // Case Droite ennemie
            liste_cases.get(piece.getX() - 3).get(piece.getY()).setStyle(color.toString()); // Case droite ennemie
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setGraphic(null); // Case gauche vide
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() < 0) {
            // Case Gauche ennemie
            liste_cases.get(piece.getX() - 3).get(piece.getY()).setGraphic(null); // Case droite vide
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setStyle(color.toString()); // Case gauche ennemie
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() == 0 && liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() == 0) {
            // Cases vide
            liste_cases.get(piece.getX() - 3).get(piece.getY()).setGraphic(null); // Case droite vide
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setGraphic(null); // Case gauche vide
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY()).getValue() == 0) {
            // Case droite alliée et case gauche vide
            liste_cases.get(piece.getX() - 3).get(piece.getY()).setGraphic(null); // Case gauche vide
        } else if (liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).getValue() == 0) {
            // Case gauche alliée et case droite vide
            liste_cases.get(piece.getX() - 3).get(piece.getY() - 2).setGraphic(null); // Case droite vide
        }
    }

    private void resetWhiteKnightMoveRight(Case piece, Background color) {
        if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() < 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() < 0) {
            // Case basse ennemie et case haute ennemie
            liste_cases.get(piece.getX()).get(piece.getY() + 1).setStyle(color.toString()); // Case basse ennemie
            liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setStyle(color.toString()); // Case haute ennemie
        } else if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() < 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() > 0) {
            // Case haute ennemie et basse alliée
            liste_cases.get(piece.getX()).get(piece.getY() + 1).setStyle(color.toString());
        } else if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() < 0 && liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() > 0) {
            // Case basse ennemie et case haute alliée
            liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setStyle(color.toString());
        } else if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() < 0) {
            // Case basse ennemie et case haute vide
            liste_cases.get(piece.getX()).get(piece.getY() + 1).setStyle(color.toString()); // Case basse ennemie
            liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setGraphic(null); // Case haute vide
        } else if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() < 0) {
            // Case haute ennemie et case basse vide
            liste_cases.get(piece.getX()).get(piece.getY() + 1).setGraphic(null); // Case basse vide
            liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setStyle(color.toString()); // Case haute ennemie
        } else if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() == 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() == 0) {
            // Cases vide
            liste_cases.get(piece.getX()).get(piece.getY() + 1).setGraphic(null); // Case basse vide
            liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setGraphic(null); // Case haute vide
        } else if (liste_cases.get(piece.getX()).get(piece.getY() + 1).getValue() == 0) {
            // Case basse alliée et case haute vide
            liste_cases.get(piece.getX()).get(piece.getY() + 1).setGraphic(null); // Case basse vide
        } else if (liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).getValue() == 0) {
            // Case haute alliée et case basse vide
            liste_cases.get(piece.getX() - 2).get(piece.getY() + 1).setGraphic(null); // Case haute vide
        }
    }

    private void resetWhiteKnightMoveLeft(Case piece, Background color) {
        if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() < 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() < 0) {
            // Case basse ennemie et case haute ennemie
            liste_cases.get(piece.getX()).get(piece.getY() - 3).setStyle(color.toString()); // Case basse ennemie
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setStyle(color.toString()); // Case haute ennemie
        } else if (liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() < 0 && liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() > 0) {
            // Case haute ennemie et basse alliée
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setStyle(color.toString()); // Case haute ennemie
        } else if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() < 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() > 0) {
            // Case basse ennemie et case haute alliée
            liste_cases.get(piece.getX()).get(piece.getY() - 3).setStyle(color.toString()); // Case basse ennemie
        } else if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() < 0) {
            // Case basse ennemie et case haute vide
            liste_cases.get(piece.getX()).get(piece.getY() - 3).setStyle(color.toString()); // Case basse ennemie
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setGraphic(null); // Case haute vide
        } else if (liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() < 0) {
            // Case haute ennemie et case basse vide
            liste_cases.get(piece.getX()).get(piece.getY() - 3).setGraphic(null); // Case basse vide
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setStyle(color.toString()); // Case haute ennemie
        } else if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() == 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() == 0) {
            // Cases vide
            liste_cases.get(piece.getX()).get(piece.getY() - 3).setGraphic(null); // Case basse vide
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setGraphic(null); // Case haute vide
        } else if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() > 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() == 0) {
            // Case basse alliée et case haute vide
            liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).setGraphic(null); // Case haute vide
        } else if (liste_cases.get(piece.getX()).get(piece.getY() - 3).getValue() == 0 && liste_cases.get(piece.getX() - 2).get(piece.getY() - 3).getValue() > 0) {
            // Case haute alliée et case basse vide
            liste_cases.get(piece.getX()).get(piece.getY() - 3).setGraphic(null); // Case basse vide
        }
    }

    private void resetWhiteKnightMoveDown(Case piece, Background color) {
        if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() < 0 && liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() < 0) {
            // Case gauche ennemie et case droite ennemie
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setStyle(color.toString()); // Case gauche ennemie
            liste_cases.get(piece.getX() + 1).get(piece.getY()).setStyle(color.toString()); // Case droite ennemie
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() < 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() > 0) {
            // Case droite ennemie et case gauche alliée
            liste_cases.get(piece.getX() + 1).get(piece.getY()).setStyle(color.toString());
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() < 0 && liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() > 0) {
            // Case gauche ennemie et case droite alliée
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setStyle(color.toString());
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() < 0) {
            // Case droite ennemie et case gauche vide
            liste_cases.get(piece.getX() + 1).get(piece.getY()).setStyle(color.toString()); // Case droite ennemie
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setGraphic(null); // Case gauche vide
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() < 0) {
            // Case gauche ennemie et case droite vide
            liste_cases.get(piece.getX() + 1).get(piece.getY()).setGraphic(null); // Case droite vide
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setStyle(color.toString()); // Case gauche ennemie
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() == 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() == 0) {
            // Cases vide
            liste_cases.get(piece.getX() + 1).get(piece.getY()).setGraphic(null); // Case droite vide
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setGraphic(null); // Case gauche vide
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() > 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() == 0) {
            // Case droite alliée et case gauche vide
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).setGraphic(null); // Case gauche vide
        } else if (liste_cases.get(piece.getX() + 1).get(piece.getY()).getValue() == 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 2).getValue() > 0) {
            // Case gauche alliée et case droite vide
            liste_cases.get(piece.getX() + 1).get(piece.getY()).setGraphic(null); // Case gauche alliée
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

    /**
     * This function will check all the possible moves of a black pawn.
     *
     * @param piece the piece that is being moved
     */
    private void blackPawnMoves(Case piece) {
        if (piece.getX() < 7 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 1).getValue() == 0) {
            liste_cases.get(piece.getX()).get(piece.getY() - 1).setGraphic(image);
            liste_cases.get(piece.getX() + 1).get(piece.getY() - 1).setGraphic(image2);

        } else if (piece.getX() < 7 && liste_cases.get(piece.getX()).get(piece.getY() - 1).getValue() == 0 && liste_cases.get(piece.getX() + 1).get(piece.getY() - 1).getValue() > 0) {
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
    private void resetBlackPawnMoves(Case piece, Background color) {
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

    private void blackRookMoves(Case piece) {
        // Colonne Bas
        this.blackRookMoveDown(piece);

        // Ligne droite
        this.blackRookMoveRight(piece);

        // Colonne Haut
        this.blackRookMoveUp(piece);

        // Ligne Gauche
        this.blackRookMoveLeft(piece);
    }

    private void blackRookMoveDown(Case piece) {
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

    private void blackRookMoveUp(Case piece) {
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

    private void blackRookMoveRight(Case piece) {
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

    private void blackRookMoveLeft(Case piece) {
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

    private void resetBlackRookMoves(Case piece, Background color) {
        // Colonne Bas
        this.resetBlackRookMoveDown(piece, color);

        // Ligne droite
        this.resetBlackRookMoveRight(piece, color);

        // Colonne Haut
        this.resetBlackRookMoveUp(piece, color);

        // Ligne Gauche
        this.resetBlackRookMoveLeft(piece, color);
    }

    private void resetBlackRookMoveDown(Case piece, Background color) {
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

    private void resetBlackRookMoveUp(Case piece, Background color) {
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

    private void resetBlackRookMoveRight(Case piece, Background color) {
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

    private void resetBlackRookMoveLeft(Case piece, Background color) {
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

    private void blackKnightMoves(Case piece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void resetBlackKnightMoves(Case piece, Background color) {
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
