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
 * @author lb
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
    private DataFormat dataFormat = new DataFormat("DragBlock");

    private int xDetected;
    private int yDetected;

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
            Case c = new Case(i, j, 1, new ImageView(new Image("file:resources/black_pieces/pawn_black.png")));
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
        Case c = new Case(i, 1, 5, new ImageView(new Image("file:resources/white_pieces/rook_white.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/rook_white.png")), c);

        c = new Case(i, 2, 2, new ImageView(new Image("file:resources/white_pieces/knight_white.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/knight_white.png")), c);

        c = new Case(i, 3, 3, new ImageView(new Image("file:resources/white_pieces/bishop_white.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/bishop_white.png")), c);

        c = new Case(i, 4, 9, new ImageView(new Image("file:resources/white_pieces/queen_white.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/queen_white.png")), c);

        c = new Case(i, 5, 800, new ImageView(new Image("file:resources/white_pieces/king_white.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/king_white.png")), c);

        c = new Case(i, 6, 3, new ImageView(new Image("file:resources/white_pieces/bishop_white.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/bishop_white.png")), c);

        c = new Case(i, 7, 2, new ImageView(new Image("file:resources/white_pieces/knight_white.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/knight_white.png")), c);

        c = new Case(i, 8, 5, new ImageView(new Image("file:resources/white_pieces/rook_white.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/white_pieces/rook_white.png")), c);
    }

    /**
     * It initializes the black pieces on the board
     *
     * @param i             the line number
     * @param ligneActuelle the line we're currently working on
     */
    private void intializeLinesBlack(int i, ArrayList<Case> ligneActuelle) {
        Case c = new Case(i, 1, -5, new ImageView(new Image("file:resources/black_pieces/rook_black.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/rook_black.png")), c);

        c = new Case(i, 2, -2, new ImageView(new Image("file:resources/black_pieces/knight_black.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/knight_black.png")), c);

        c = new Case(i, 3, -3, new ImageView(new Image("file:resources/black_pieces/bishop_black.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/bishop_black.png")), c);

        c = new Case(i, 4, -9, new ImageView(new Image("file:resources/black_pieces/queen_black.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/queen_black.png")), c);

        c = new Case(i, 5, -800, new ImageView(new Image("file:resources/black_pieces/king_black.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/king_black.png")), c);

        c = new Case(i, 6, -3, new ImageView(new Image("file:resources/black_pieces/bishop_black.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/bishop_black.png")), c);

        c = new Case(i, 7, -2, new ImageView(new Image("file:resources/black_pieces/knight_black.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/knight_black.png")), c);

        c = new Case(i, 8, -5, new ImageView(new Image("file:resources/black_pieces/rook_black.png")));
        setCase(ligneActuelle, new ImageView(new Image("file:resources/black_pieces/rook_black.png")), c);
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
                // if (piece.getImageView() != null) {
                    piece.setOnDragDetected(event -> {
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
                    });
                // }
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
                        piece.setStyle("-fx-background-color: yellow;");
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                });

                piece.setOnDragDropped(dragEvent -> {
                    Dragboard db = dragEvent.getDragboard();
                    if (db.hasContent(this.dataFormat)) {
                        System.out.println("---> " + db.getContent(this.dataFormat));


                        // if the piece is dropped on the depot zone, it is removed from the board
                        db.setDragViewOffsetX(piece.getX());
                        db.setDragViewOffsetY(piece.getY());
                        db.setDragView(db.getImage());

                        System.out.println("Nouvelle valeur : " + db.getDragViewOffsetX() + " " + db.getDragViewOffsetY());
                        System.out.println("Ancienne valeur : " + xDetected + " " + yDetected);


                        // New position of the piece
                        ImageView image = new ImageView(db.getDragView());
                        piece.setImageView(image);
                        piece.setNewPosition((int) db.getDragViewOffsetX(), (int) db.getDragViewOffsetY());
                        piece.setValue((int) db.getContent(this.dataFormat));


                        // Old position of the piece
                        ((Case) ((HBox) this.getChildren().get(xDetected + 1)).getChildren().get(yDetected + 1)).setImageView(
                                (ImageView) ((Case) ((HBox) this.getChildren().get((int) db.getDragViewOffsetX() - 1)).getChildren().get((int) db.getDragViewOffsetY() - 1)).getGraphic());

                        ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setValue(
                                ((Case) ((HBox) this.getChildren().get((int) db.getDragViewOffsetX() - 1)).getChildren().get((int) db.getDragViewOffsetY() - 1)).getValue());

                        ((Case) ((HBox) this.getChildren().get(xDetected - 1)).getChildren().get(yDetected - 1)).setNewPosition(xDetected, yDetected);


                        // Drop Complete
                        dragEvent.setDropCompleted(true);
                    }
                    dragEvent.consume();
                });

                piece.setOnDragExited(event -> {
                    piece.setStyle(String.valueOf(color));
                    event.consume();
                });

                setDragAndDrop();
            }
        }
    }
}
