package WarehouseSimulation.GraphicalObjects.OrderPicker;

public interface MovingObject {

    void setStartLocation();
    double calculateMoveDistancePerUpdate(int scaleFactor);
    boolean move(final int UPDATE_COUNTER);


}
