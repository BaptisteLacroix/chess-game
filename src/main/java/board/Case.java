package board;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Case extends Label {
    private int x;
    private int y;
    private int value;
    private ImageView image;
    private String name;

    public Case(int x, int y, int value, ImageView image, String name) {
        super();
        this.x = x;
        this.y = y;
        this.value = value;
        this.image = image;
        this.name = name;
        init();
    }

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

    public void setNewPosition(int x, int y) {
        this.x = x;
        this.y = y;
        if (this.image != null) {
            image.setFitHeight(500 / (double) 8);
            image.setFitWidth(500 / (double) 8);
        }
        super.setGraphic(image);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public ImageView getImageView() {
        return image;
    }

    public void setImageView(ImageView image) {
        this.image = image;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String toString() {
        return "Case name : " + this.name + ": " + this.x + " " + this.y + " " + this.value;
    }
}
