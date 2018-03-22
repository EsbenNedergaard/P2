package GraphicalWarehouse.GraphicalObjects;

import Geometry.Point2D;
import GraphicalWarehouse.GraphicalWarehouse;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class Tile extends Rectangle {

    public Tile(Point2D point) {
        // Pixel width of tile
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        // Graphical position of tile
        relocate(point.getXPixels(), point.getYPixels());
        setDesign();
    }

    public void setDesign() {
        // Design options
        setFill(Color.TRANSPARENT);
        setStroke(Color.valueOf("#b7b7b7"));
        setStrokeWidth(0.5);
    }

}

