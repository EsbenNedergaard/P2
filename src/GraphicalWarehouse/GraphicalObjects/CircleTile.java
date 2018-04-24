package GraphicalWarehouse.GraphicalObjects;

import Geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

/* THIS IS A GRAPHICAL OBJECT WHICH REPRESENTS A LINE */

public class CircleTile extends Circle {

    public CircleTile(Point2D point) {

        // Pixel width of tile
        setRadius(TILE_SIZE / 4.8);
        setFill(Color.valueOf("#4ed480"));
        setOpacity(1);

        // Graphical position of tile
        relocate(point.getXPixels() + 7.5, point.getYPixels() + 7.5);

    }

}
