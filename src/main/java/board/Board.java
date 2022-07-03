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
    private ArrayList<ArrayList<Case>> liste_cases = new ArrayList();
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
                if (db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() && piece.getValue() == 0) {
                    this.setWhiteDropPiece(piece, db);
                } else if (db.getDragViewOffsetX() == source.getX() - 2 && db.getDragViewOffsetY() == source.getY() && piece.getValue() == 0) {
                    this.setWhiteDropPiece(piece, db);
                } else if ((db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() - 1 ||
                        db.getDragViewOffsetX() == source.getX() - 1 && db.getDragViewOffsetY() == source.getY() + 1 ) && piece.getValue() < 0) {
                    this.setWhiteDropPiece(piece, db);
                }
                break;

        }
    }

    private void chooseBlackPieceToDrop(Case source, Case piece, Dragboard db) {
        db.setDragViewOffsetX(piece.getX());
        db.setDragViewOffsetY(piece.getY());
        switch (source.getValue()) {
            case -1:
                System.out.println(piece.getX() + " " + piece.getY());
                System.out.println(db.getDragViewOffsetX() + " " + db.getDragViewOffsetY());
                System.out.println((source.getX() + 2) + " " + (source.getY() - 1));
                System.out.println((source.getX() + 2) + " " + (source.getY() + 1) + "\n");
                if (db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() && piece.getValue() == 0) {
                    this.setWhiteDropPiece(piece, db);
                } else if (db.getDragViewOffsetX() == source.getX() + 2 && db.getDragViewOffsetY() == source.getY() && piece.getValue() == 0) {
                    this.setWhiteDropPiece(piece, db);
                } else if ((db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() - 1 ||
                        db.getDragViewOffsetX() == source.getX() + 1 && db.getDragViewOffsetY() == source.getY() + 1 ) && piece.getValue() > 0) {
                    this.setWhiteDropPiece(piece, db);
                }
                break;

        }
    }

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

    private void setVoidCaseToNull() {
        ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setImageView(null);
        ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setValue(0);
        ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setNewPosition(xDetected, yDetected);
    }


    private void setColorMoves(Background color, Case piece) {
        switch (piece.getValue()) {
            case -1:
                piece.setOnMouseEntered(event -> this.blackPawnMoves(piece));
                piece.setOnMouseExited(event -> this.resetBlackPawnMoves(piece, color));
                break;
            case -2:
                piece.setOnMouseEntered(event -> this.blackKnightMoves(piece));
                piece.setOnMouseExited(event -> this.resetBlackKnightMoves(piece, color));
                break;
            case -3:
                piece.setOnMouseEntered(event -> this.blackBishopMoves(piece));
                piece.setOnMouseExited(event -> this.resetBlackBishopMoves(piece, color));
                break;
            case -5:
                piece.setOnMouseEntered(event -> this.blackRookMoves(piece));
                piece.setOnMouseExited(event -> this.resetBlackRookMoves(piece, color));
                break;
            case -9:
                piece.setOnMouseEntered(event -> this.blackQueenMoves(piece));
                piece.setOnMouseExited(event -> this.resetBlackQueenMoves(piece, color));
                break;
            case -800:
                piece.setOnMouseEntered(event -> this.blackKingMoves(piece));
                piece.setOnMouseExited(event -> this.resetBlackKingMoves(piece, color));
                break;
            case 1:
                piece.setOnMouseEntered(event -> this.whitePawnMoves(piece));
                piece.setOnMouseExited(event -> this.resetWhitePawnMoves(piece, color));
                break;
            case 2:
                piece.setOnMouseEntered(event -> this.whiteKnightMoves(piece));
                piece.setOnMouseExited(event -> this.resetWhiteKnightMoves(piece, color));
                break;
            case 3:
                piece.setOnMouseEntered(event -> this.whiteBishopMoves(piece));
                piece.setOnMouseExited(event -> this.resetWhiteBishopMoves(piece, color));
                break;
            case 5:
                piece.setOnMouseEntered(event -> this.whiteRookMoves(piece));
                piece.setOnMouseExited(event -> this.resetWhiteRookMoves(piece, color));
                break;
            case 9:
                piece.setOnMouseEntered(event -> this.whiteQueenMoves(piece));
                piece.setOnMouseExited(event -> this.resetWhiteQueenMoves(piece, color));
                break;
            default:
                piece.setOnMouseEntered(event -> this.whiteKingMoves(piece));
                piece.setOnMouseExited(event -> this.resetWhiteKingMoves(piece, color));
                break;

        }
    }

    private void whiteRookMoves(Case piece) {
    }

    private void resetWhiteRookMoves(Case piece, Background color) {
    }

    private void whiteKnightMoves(Case piece) {
    }

    private void resetWhiteKnightMoves(Case piece, Background color) {
    }

    private void whiteBishopMoves(Case piece) {
    }

    private void resetWhiteBishopMoves(Case piece, Background color) {
    }

    private void whiteQueenMoves(Case piece) {
    }

    private void resetWhiteQueenMoves(Case piece, Background color) {
    }

    private void whiteKingMoves(Case piece) {
    }

    private void resetWhiteKingMoves(Case piece, Background color) {
    }

    private void whitePawnMoves(Case piece) {
        ImageView image = new ImageView("file:./resources/dot.png");
        image.setFitHeight(500 / (double) 8);
        image.setFitWidth(500 / (double) 8);

        ImageView image2 = new ImageView("file:./resources/dot.png");
        image2.setFitHeight(500 / (double) 8);
        image2.setFitWidth(500 / (double) 8);

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

    private void resetWhitePawnMoves(Case piece, Background color) {
        ImageView image = new ImageView("file:./resources/dot.png");
        image.setFitHeight(500 / (double) 8);
        image.setFitWidth(500 / (double) 8);

        ImageView image2 = new ImageView("file:./resources/dot.png");
        image2.setFitHeight(500 / (double) 8);
        image2.setFitWidth(500 / (double) 8);

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

    private void blackPawnMoves(Case piece) {

        ImageView image = new ImageView("file:./resources/dot.png");
        image.setFitHeight(500 / (double) 8);
        image.setFitWidth(500 / (double) 8);

        ImageView image2 = new ImageView("file:./resources/dot.png");
        image2.setFitHeight(500 / (double) 8);
        image2.setFitWidth(500 / (double) 8);

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

    private void print() {
        for (ArrayList<Case> liste_case : liste_cases) {
            for (Case aCase : liste_case) {
                System.out.print("(" + aCase.getX() + "," + aCase.getY() + "),");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void blackRookMoves(Case piece) {
    }

    private void resetBlackRookMoves(Case piece, Background color) {
    }

    private void blackKnightMoves(Case piece) {
    }

    private void resetBlackKnightMoves(Case piece, Background color) {
    }

    private void blackBishopMoves(Case piece) {
    }

    private void resetBlackBishopMoves(Case piece, Background color) {
    }

    private void blackQueenMoves(Case piece) {
    }

    private void resetBlackQueenMoves(Case piece, Background color) {
    }

    private void blackKingMoves(Case piece) {
    }

    private void resetBlackKingMoves(Case piece, Background color) {
    }
}
