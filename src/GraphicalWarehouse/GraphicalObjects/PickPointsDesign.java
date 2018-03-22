package GraphicalWarehouse.GraphicalObjects;

import Geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class PickPointsDesign extends Circle {
    public PickPointsDesign(Point2D pickPoint) {
        setRadius(TILE_SIZE * 0.3);
        setTranslateX(pickPoint.getXPixels() + 0.5 * TILE_SIZE);
        setTranslateY(pickPoint.getYPixels() + 0.5 * TILE_SIZE);

        setOpacity(0.5);

        setFill(Color.valueOf("blue"));
    }
}
