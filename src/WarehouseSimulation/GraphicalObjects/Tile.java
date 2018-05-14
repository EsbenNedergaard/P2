package WarehouseSimulation.GraphicalObjects;

import BackEnd.Geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class Tile extends Rectangle {

    public Tile(Point2D point) {
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);
        relocate(point.getXPixels(), point.getYPixels());
        setDesign();
    }

    public void setDesign() {
        setFill(Color.TRANSPARENT);
        setStroke(Color.valueOf("#b7b7b7"));
        setStrokeWidth(0.5);
    }
}