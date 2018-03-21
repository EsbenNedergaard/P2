package Warehouse;

import Geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class Tile extends Rectangle {

    // TODO: translateX og Y er det grafiske objekts placering
    // TODO: overvej om der alligevel skal være en lokal kopi af produktets
    // TODO: koordinater. Altså, et product øverst i en hylde har (x,y) = (0,0)
    // private int localX, localY;

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

