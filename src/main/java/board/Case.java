package board;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * This class is used to create a case object that is used to display the cards on the board
 *
 * @author Lacroix Baptiste
 */
public class Case extends Label {
    /**
     * The x coordinate of the case.
     */
    private int x;
    /**
     * The y coordinate of the case.
     */
    private int y;
    /**
     * The value of the case.
     */
    private int value;
    /**
     * The image of the case.
     */
    private ImageView image;

    /**
     * The constructor of the class Case. It initializes the variables x, y, value and image.
     * @param x The x coordinate of the case.
     * @param y The y coordinate of the case.
     * @param value The value of the case.
     * @param image The image of the case.
     */
    public Case(int x, int y, int value, ImageView image) {
        super();
        this.x = x;
        this.y = y;
        this.value = value;
        this.image = image;
        init();
    }

    /**
     * If the sum of the x and y coordinates of the square is even, the square is colored green, otherwise it is colored
     * white
     */
    private void init() {
        Color couleur;
        if ((this.x + this.y) % 2 == 0) {
            couleur = Color.rgb(113, 140, 81);
        } else {
            couleur = Color.WHITE;
        }
        Background b = new Background(new BackgroundFill(couleur, CornerRadii.EMPTY, null));
        super.setBackground(b);
    }

    /**
     * It sets the new position of the piece
     *
     * @param x the x coordinate of the new position
     * @param y The y coordinate of the new position
     */
    public void setNewPosition(int x, int y) {
        this.x = x;
        this.y = y;
        if (this.image != null) {
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
        }
        super.setGraphic(image);
    }

    /**
     * This function returns the value of the x variable.
     *
     * @return The value of the x variable.
     */
    public int getX() {
        return x;
    }

    /**
     * This function returns the value of the y variable.
     *
     * @return The y value of the point.
     */
    public int getY() {
        return y;
    }

    /**
     * This function returns the value of the variable value.
     *
     * @return The value of the variable value.
     */
    public int getValue() {
        return value;
    }

    /**
     * This function returns the ImageView object that is used to display the image.
     *
     * @return The imageView
     */
    public ImageView getImageView() {
        return image;
    }

    /**
     * This function sets the imageView to the imageView that is passed in.
     *
     * @param image The image to be displayed
     */
    public void setImageView(ImageView image) {
        this.image = image;
    }

    /**
     * This function sets the value of the variable value to the value of the parameter value.
     *
     * @param value The value of the card.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * It returns a string that contains the coordinates of the case and its value
     *
     * @return The value of the case.
     */
    public String toString() {
        return "Case coordinate : " + this.x + " " + this.y + " " + this.value;
    }
}
