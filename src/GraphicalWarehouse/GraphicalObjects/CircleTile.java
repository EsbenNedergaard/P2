package GraphicalWarehouse.GraphicalObjects;

import BackEnd.Geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

/* THIS IS A GRAPHICAL OBJECT WHICH REPRESENTS A LINE */

public class CircleTile extends Circle {

    public CircleTile(Point2D point) {
        double radius = 2;
        // Pixel width of tile
        setRadius(radius);
        setFill(Color.valueOf("#3b3b3b"));

        double relocateValue = (TILE_SIZE / 2) - radius;

        // Graphical position of tile
        relocate(point.getXPixels() + relocateValue, point.getYPixels() + relocateValue);

    }

}
