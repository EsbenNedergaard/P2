package WarehouseSimulation.GraphicalObjects;

import BackEnd.Geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

/* THIS IS A GRAPHICAL OBJECT WHICH REPRESENTS A LINE */

public class CircleTile extends Circle {

    public CircleTile(Point2D point) {

        // Pixel width of tile
        setRadius(TILE_SIZE / 5.5);
        setFill(Color.valueOf("#3b3b3b"));

        // Graphical position of tile
        relocate(point.getXPixels() + 8, point.getYPixels() + 8);

    }

}
