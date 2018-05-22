package WarehouseSimulation.GraphicalObjects;

import BackEnd.Geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class CircleTile extends Circle {

    public CircleTile(Point2D point, String color, int radius) {
        setRadius(radius);
        setDesign(point, color);
        relocateCircle(radius);
    }

    private void setDesign(Point2D point, String color) {
        setFill(Color.valueOf(color));
        setTranslateX(point.getXPixels());
        setTranslateY(point.getYPixels());
    }

    private void relocateCircle(int radius) {
        double relocateValue = (TILE_SIZE / 2) - radius;
        relocate(relocateValue, relocateValue);
    }

}
