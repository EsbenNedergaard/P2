package WarehouseSimulation.GraphicalObjects.OrderPicker;

import BackEnd.Geometry.Point2D;

public interface MovingObject {

    void setStartLocation();

    double calculateMoveDistancePerUpdate(int scaleFactor);

    boolean move(final int UPDATE_COUNTER);

    void startOver();

    void setScaleSpeed(int scaleFactor);

    Point2D getCurrentPosition();

}
