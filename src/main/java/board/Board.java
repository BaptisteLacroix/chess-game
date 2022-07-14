package board;

import java.util.ArrayList;

import javafx.event.Event;
import pieces.*;
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
    private final ArrayList<ArrayList<Case>> liste_cases = new ArrayList<>();
    /**
     * It creates a new HBox object that will be used to add the cases to the board.
     */
    private HBox board = new HBox();
    /**
     * It creates a new ArrayList of Piece objects.
     */
    private final DataFormat dataFormat = new DataFormat("DragBlock");
    /**
     * The coordinate of the piece that is being dragged.
     */
    private int xDetected;
    /**
     * The coordinate of the piece that is being dragged.
     */
    private int yDetected;
    /**
     * The value of the yellow color
     */
    private static final String yellow = "-fx-background-color: yellow;";

    private boolean player = true; // True = Blanc / False = Noir
    private boolean math = false;
    private boolean echec = false;

    /**
     * It creates a board with the pieces in their initial positions
     *
     * @param witdh  The width of the board.
     * @param height The height of the board.
     */
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
            Case c = new Case(i, j, 1, new ImageView(new Image("file:resources/white_pieces/pawn_white.png")));
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
            Case c = new Case(i, j, -1, new ImageView(new Image("file:resources/black_pieces/pawn_black.png")));
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
            Case c = new Case(i, j, 0, null);
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
        Case rook = new Case(i, 1, 5, new ImageView(new Image("file:resources/white_pieces/rook_white.png")));
        setWhiteRook(ligneActuelle, rook);

        Case knight = new Case(i, 2, 2, new ImageView(new Image("file:resources/white_pieces/knight_white.png")));
        setWhiteKnight(ligneActuelle, knight);

        Case bishop = new Case(i, 3, 3, new ImageView(new Image("file:resources/white_pieces/bishop_white.png")));
        setWhiteBishop(ligneActuelle, bishop);

        Case queen = new Case(i, 4, 9, new ImageView(new Image("file:resources/white_pieces/queen_white.png")));
        queen.setOnMouseEntered(event -> {
            queen.setStyle(yellow);
            Queen.whiteQueenMoves(this.liste_cases, queen);
            this.echec = Rook.isEchecBlack() || Bishop.isEchecBlack();
        });
        queen.setOnMouseExited(event -> {
            queen.setStyle(queen.getBackground().toString());
            Queen.resetWhiteQueenMoves(this.liste_cases, queen, queen.getBackground());
            this.echec = Rook.isEchecBlack() || Bishop.isEchecBlack();
        });
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/queen_white.png")), queen);

        Case king = new Case(i, 5, 800, new ImageView(new Image("file:resources/white_pieces/king_white.png")));
        king.setOnMouseEntered(event -> {
            king.setStyle(yellow);
            King.whiteKingMoves(this.liste_cases, king);
        });
        king.setOnMouseExited(event -> {
            king.setStyle(king.getBackground().toString());
            King.resetWhiteKingMoves(this.liste_cases, king, king.getBackground());
        });
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/king_white.png")), king);

        Case bishop2 = new Case(i, 6, 3, new ImageView(new Image("file:resources/white_pieces/bishop_white.png")));
        setWhiteBishop(ligneActuelle, bishop2);

        Case knight2 = new Case(i, 7, 2, new ImageView(new Image("file:resources/white_pieces/knight_white.png")));
        setWhiteKnight(ligneActuelle, knight2);

        Case rook2 = new Case(i, 8, 5, new ImageView(new Image("file:resources/white_pieces/rook_white.png")));
        setWhiteRook(ligneActuelle, rook2);
    }

    /**
     * It sets the white rook's moves and the color of the case when the mouse is on it
     *
     * @param ligneActuelle the current line of the board
     * @param rook          the case where the rook is
     */
    private void setWhiteRook(ArrayList<Case> ligneActuelle, Case rook) {
        rook.setOnMouseEntered(event -> {
            rook.setStyle(yellow);
            Rook.whiteRookMoves(this.liste_cases, rook);
            this.echec = Rook.isEchecBlack();
        });
        rook.setOnMouseExited(event -> {
            rook.setStyle(rook.getBackground().toString());
            Rook.resetWhiteRookMoves(this.liste_cases, rook, rook.getBackground());
            this.echec = Rook.isEchecBlack();
        });
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/rook_white.png")), rook);
    }

    /**
     * It sets the white knight's moves and the color of the case when the mouse is on it
     *
     * @param ligneActuelle the current line of the board
     * @param knight        the Case object that is the knight
     */
    private void setWhiteKnight(ArrayList<Case> ligneActuelle, Case knight) {
        knight.setOnMouseEntered(event -> {
            knight.setStyle(yellow);
            Knight.whiteKnightMoves(this.liste_cases, knight);
            this.echec = Knight.isEchecBlack();
        });
        knight.setOnMouseExited(event -> {
            knight.setStyle(knight.getBackground().toString());
            Knight.resetWhiteKnightMoves(this.liste_cases, knight, knight.getBackground());
            this.echec = Knight.isEchecBlack();
        });
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/knight_white.png")), knight);
    }

    /**
     * It sets the bishop's moves and the bishop's image
     *
     * @param ligneActuelle the current line of the board
     * @param bishop        the Case object that will be set as a white bishop
     */
    private void setWhiteBishop(ArrayList<Case> ligneActuelle, Case bishop) {
        bishop.setOnMouseEntered(event -> {
            bishop.setStyle(yellow);
            Bishop.whiteBishopMoves(this.liste_cases, bishop);
            this.echec = Bishop.isEchecBlack();
        });
        bishop.setOnMouseExited(event -> {
            bishop.setStyle(bishop.getBackground().toString());
            Bishop.resetWhiteBishopMoves(this.liste_cases, bishop, bishop.getBackground());
            this.echec = Bishop.isEchecBlack();
        });
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/bishop_white.png")), bishop);
    }

    /**
     * It initializes the black pieces on the board
     *
     * @param i             the line number
     * @param ligneActuelle the line we're currently working on
     */
    private void intializeLinesBlack(int i, ArrayList<Case> ligneActuelle) {
        Case rook = new Case(i, 1, -5, new ImageView(new Image("file:resources/black_pieces/rook_black.png")));
        setBlackRook(ligneActuelle, rook);

        Case knight = new Case(i, 2, -2, new ImageView(new Image("file:resources/black_pieces/knight_black.png")));
        setBlackKnight(ligneActuelle, knight);

        Case bishop = new Case(i, 3, -3, new ImageView(new Image("file:resources/black_pieces/bishop_black.png")));
        setBlackBishop(ligneActuelle, bishop);

        Case queen = new Case(i, 4, -9, new ImageView(new Image("file:resources/black_pieces/queen_black.png")));
        queen.setOnMouseEntered(event -> {
            queen.setStyle(yellow);
            Queen.blackQueenMoves(this.liste_cases, queen);
            this.echec = Rook.isEchecWhite() || Bishop.isEchecWhite();
        });
        queen.setOnMouseExited(event -> {
            queen.setStyle(queen.getBackground().toString());
            Queen.resetBlackQueenMoves(this.liste_cases, queen, queen.getBackground());
            this.echec = Rook.isEchecWhite() || Bishop.isEchecWhite();
        });
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/queen_black.png")), queen);

        Case king = new Case(i, 5, -800, new ImageView(new Image("file:resources/black_pieces/king_black.png")));
        king.setOnMouseEntered(event -> {
            king.setStyle(yellow);
            King.blackKingMoves(this.liste_cases, king);
        });
        king.setOnMouseExited(event -> {
            king.setStyle(king.getBackground().toString());
            King.resetBlackKingMoves(this.liste_cases, king, king.getBackground());
        });
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/king_black.png")), king);

        Case bishop2 = new Case(i, 6, -3, new ImageView(new Image("file:resources/black_pieces/bishop_black.png")));
        setBlackBishop(ligneActuelle, bishop2);

        Case knight2 = new Case(i, 7, -2, new ImageView(new Image("file:resources/black_pieces/knight_black.png")));
        setBlackKnight(ligneActuelle, knight2);

        Case rook2 = new Case(i, 8, -5, new ImageView(new Image("file:resources/black_pieces/rook_black.png")));
        setBlackRook(ligneActuelle, rook2);
    }

    /**
     * It sets the black rook's moves and the color of the case when the mouse is on it
     *
     * @param ligneActuelle the current line of the board
     * @param rook          the case where the rook is
     */
    private void setBlackRook(ArrayList<Case> ligneActuelle, Case rook) {
        rook.setOnMouseEntered(event -> {
            rook.setStyle(yellow);
            Rook.blackRookMoves(this.liste_cases, rook);
            this.echec = Rook.isEchecWhite();
        });
        rook.setOnMouseExited(event -> {
            rook.setStyle(rook.getBackground().toString());
            Rook.resetBlackRookMoves(this.liste_cases, rook, rook.getBackground());
            this.echec = Rook.isEchecWhite();
        });
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/rook_black.png")), rook);
    }

    /**
     * It sets the black knight's moves and the color of the case when the mouse is on it
     *
     * @param ligneActuelle the current line of the board
     * @param knight        the Case object that is the knight
     */
    private void setBlackKnight(ArrayList<Case> ligneActuelle, Case knight) {
        knight.setOnMouseEntered(event -> {
            knight.setStyle(yellow);
            Knight.blackKnightMoves(this.liste_cases, knight);
            this.echec = Knight.isEchecWhite();
        });
        knight.setOnMouseExited(event -> {
            knight.setStyle(knight.getBackground().toString());
            Knight.resetBlackKnightMoves(this.liste_cases, knight, knight.getBackground());
            this.echec = Knight.isEchecWhite();
        });
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/knight_black.png")), knight);
    }

    /**
     * It sets the black bishop's moves and the bishop's image
     *
     * @param ligneActuelle the line of the board where the piece is
     * @param bishop        the Case object that will be set as a black bishop
     */
    private void setBlackBishop(ArrayList<Case> ligneActuelle, Case bishop) {
        bishop.setOnMouseEntered(event -> {
            bishop.setStyle(yellow);
            Bishop.blackBishopMoves(this.liste_cases, bishop);
            this.echec = Bishop.isEchecWhite();
        });
        bishop.setOnMouseExited(event -> {
            bishop.setStyle(bishop.getBackground().toString());
            Bishop.resetBlackBishopMoves(this.liste_cases, bishop, bishop.getBackground());
            this.echec = Bishop.isEchecWhite();
        });
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/bishop_black.png")), bishop);
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
                if (player) {
                    if (piece.getValue() > 0) {
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
                    } else {
                        piece.setOnDragDetected(Event::consume);
                    }
                } else {
                    if (piece.getValue() < 0) {
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
                    } else {
                        piece.setOnDragDetected(Event::consume);
                    }
                }
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

    /**
     * Check what type of piece is being dropped on the board and it checks if the move is legal, and if it is, it sets the piece to the new case
     *
     * @param source The case where the piece is picked up
     * @param piece  The case where the piece is dropped
     * @param db     The Dragboard object that contains the data being transferred.
     */
    private void chooseWhitePieceToDrop(Case source, Case piece, Dragboard db) {
        db.setDragViewOffsetX(piece.getX());
        db.setDragViewOffsetY(piece.getY());
        switch (source.getValue()) {
            case 1: // Pawn
                if (Pawn.checkTheWhiteMove(db, this, source, piece))
                    this.setWhiteDropPiece(piece, db);
                break;
            case 2: // Rook
                if (Boolean.TRUE.equals(Knight.checkTheWhiteMove(db, source, piece)))
                    this.setWhiteDropPiece(piece, db);
                break;
            case 3: // Knight
                if (Boolean.TRUE.equals(Bishop.checkTheWhiteMove(db, this, source)))
                    this.setWhiteDropPiece(piece, db);
                break;
            case 5: // Rook
                if (Rook.checkTheWhiteMove(db, this, source))
                    this.setWhiteDropPiece(piece, db);
                break;
            case 9: // Queen
                if (Queen.checkTheWhiteMove(db, this, source))
                    this.setWhiteDropPiece(piece, db);
                break;
            case 800: // King
                if (King.checkTheWhiteMove(db, source, piece))
                    this.setWhiteDropPiece(piece, db);
                break;
            default: // Error
                throw new UnsupportedOperationException("Default : Not supported yet.");
        }
    }

    /**
     * Check what type of piece is being dropped on the board and it checks if the move is legal, and if it is, it sets the piece to the new case
     *
     * @param source The case where the piece is picked up
     * @param piece  The case where the piece is dropped
     * @param db     The Dragboard object that contains the data being transferred.
     */
    private void chooseBlackPieceToDrop(Case source, Case piece, Dragboard db) {
        db.setDragViewOffsetX(piece.getX());
        db.setDragViewOffsetY(piece.getY());
        switch (source.getValue()) {
            case -1: // Pawn
                if (Pawn.checkTheBlackMove(db, this, source, piece))
                    this.setBlackDropPiece(piece, db);
                break;
            case -2: // Knight
                if (Knight.checkTheBlackMove(db, source, piece))
                    this.setBlackDropPiece(piece, db);
                break;
            case -3: // Bishop
                if (Bishop.checkTheBlackMove(db, this, source))
                    this.setBlackDropPiece(piece, db);
                break;
            case -5: // Rook
                if (Rook.checkTheBlackMove(db, this, source))
                    this.setBlackDropPiece(piece, db);
                break;
            case -9: // Queen
                if (Queen.checkTheBlackMove(db, this, source))
                    this.setBlackDropPiece(piece, db);
                break;
            case -800: // King
                if (King.checkTheBlackMove(db, source, piece))
                    this.setBlackDropPiece(piece, db);
                break;
            default: // Erreur
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
            ImageView imagePiece = new ImageView(db.getDragView());
            piece.setImageView(imagePiece);
            piece.setValue((int) db.getContent(this.dataFormat));
            piece.setNewPosition((int) db.getDragViewOffsetX(), (int) db.getDragViewOffsetY());
            Background color = piece.getBackground();
            this.setColorMoves(color, piece);

            ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1).setOnMouseEntered(event -> Pawn.resetWhitePawnMoves(this.liste_cases, (Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1), color));

            // Old position of the piece
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
            piece.setNewPosition((int) db.getDragViewOffsetX(), (int) db.getDragViewOffsetY());
            Background color = piece.getBackground();
            this.setColorMoves(color, piece);

            ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1).setOnMouseEntered(event -> Pawn.resetWhitePawnMoves(this.liste_cases, (Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1), color));


            // Old position of the piece
            setVoidCaseToNull();
        }
        this.player = !player;
        System.out.println(echec);
        System.out.println(this.echec);
        if (this.echec)
            System.out.println("Echec");
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
            ImageView imagePiece = new ImageView(db.getDragView());
            piece.setImageView(imagePiece);
            piece.setValue((int) db.getContent(this.dataFormat));
            piece.setNewPosition((int) db.getDragViewOffsetX(), (int) db.getDragViewOffsetY());
            Background color = piece.getBackground();
            this.setColorMoves(color, piece);

            ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1).setOnMouseEntered(event -> Pawn.resetWhitePawnMoves(this.liste_cases, (Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1), color));


            // Old position of the piece
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
            piece.setNewPosition((int) db.getDragViewOffsetX(), (int) db.getDragViewOffsetY());
            Background color = piece.getBackground();
            this.setColorMoves(color, piece);

            ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1).setOnMouseEntered(event -> Pawn.resetWhitePawnMoves(this.liste_cases, (Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1), color));

            // Old position of the piece
            setVoidCaseToNull();
        }
        this.player = !player;
        System.out.println(echec);
        System.out.println(this.echec);
        if (this.echec)
            System.out.println("Echec");
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
                    this.echec = Pawn.isEchecWhite();
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Pawn.resetBlackPawnMoves(this.liste_cases, piece, color);
                    this.echec = Pawn.isEchecWhite();
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
                    Bishop.blackBishopMoves(this.liste_cases, piece);
                    this.echec = Bishop.isEchecWhite();
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Bishop.resetBlackBishopMoves(this.liste_cases, piece, color);
                    this.echec = Bishop.isEchecWhite();
                });
                break;
            case -5:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Rook.blackRookMoves(this.liste_cases, piece);
                    this.echec = Rook.isEchecWhite();
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Rook.resetBlackRookMoves(this.liste_cases, piece, color);
                    this.echec = Rook.isEchecWhite();
                });
                break;
            case -9:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Queen.blackQueenMoves(this.liste_cases, piece);
                    this.echec = Rook.isEchecWhite() ||Bishop.isEchecWhite();
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Queen.resetBlackQueenMoves(this.liste_cases, piece, color);
                    this.echec = Rook.isEchecWhite() ||Bishop.isEchecWhite();
                });
                break;
            case -800:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    King.blackKingMoves(this.liste_cases, piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    King.resetBlackKingMoves(this.liste_cases, piece, color);
                });
                break;
            case 1:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Pawn.whitePawnMoves(this.liste_cases, piece);
                    this.echec = Pawn.isEchecBlack();
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Pawn.resetWhitePawnMoves(this.liste_cases, piece, color);
                    this.echec = Pawn.isEchecBlack();
                });
                break;
            case 2:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Knight.whiteKnightMoves(this.liste_cases, piece);
                    this.echec = Knight.isEchecBlack();
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Knight.resetWhiteKnightMoves(this.liste_cases, piece, color);
                    this.echec = Knight.isEchecBlack();
                });
                break;
            case 3:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Bishop.whiteBishopMoves(this.liste_cases, piece);
                    this.echec = Bishop.isEchecBlack();
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Bishop.resetWhiteBishopMoves(this.liste_cases, piece, color);
                    this.echec = Bishop.isEchecBlack();
                });
                break;
            case 5:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Rook.whiteRookMoves(this.liste_cases, piece);
                    this.echec = Rook.isEchecBlack();
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Rook.resetWhiteRookMoves(this.liste_cases, piece, color);
                    this.echec = Rook.isEchecBlack();
                });
                break;
            case 9:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    Queen.whiteQueenMoves(this.liste_cases, piece);
                    this.echec = Rook.isEchecBlack() || Bishop.isEchecBlack();
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    Queen.resetWhiteQueenMoves(this.liste_cases, piece, color);
                    this.echec = Rook.isEchecBlack() || Bishop.isEchecBlack();
                });
                break;
            default:
                piece.setOnMouseEntered(event -> {
                    piece.setStyle(yellow);
                    King.whiteKingMoves(this.liste_cases, piece);
                });
                piece.setOnMouseExited(event -> {
                    piece.setStyle(color.toString());
                    King.resetWhiteKingMoves(this.liste_cases, piece, color);
                });
                break;

        }
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
