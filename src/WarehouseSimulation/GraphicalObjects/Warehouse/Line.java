package WarehouseSimulation.GraphicalObjects.Warehouse;

import BackEnd.Geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class Line extends Rectangle {

    public Line(String backgroundColor, Point2D position) {
        setPosition(position);
        setGraphics(backgroundColor);
    }

    private void setPosition(Point2D position) {
        relocate(TILE_SIZE - getLineWidth(), 0);
        setTranslateX(position.getXPixels());
        setTranslateY(position.getYPixels());
    }

    private void setGraphics(String backgroundColor) {
        setWidth(getLineWidth());
        setHeight(TILE_SIZE);
        setFill(Color.valueOf(backgroundColor));
    }

    private double getLineWidth() {
        return TILE_SIZE / 8;
    }

}
