package WarehouseSimulation.GraphicalObjects;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import static Warehouse.GUIWarehouse.TILE_SIZE;

// Pane with a circle and text inside
public class PaneCircleText extends StackPane {

    public PaneCircleText(Circle circle, Text text) {
        text.setTranslateX(circle.getTranslateX());
        text.setTranslateY(circle.getTranslateY());
        relocatePane(circle.getRadius());
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setFill(Color.valueOf("white"));
        getChildren().addAll(circle, text);
    }

    private void relocatePane(double radius) {
        double relocateValue = (TILE_SIZE / 2) - radius;
        relocate(relocateValue, relocateValue);
    }

}
