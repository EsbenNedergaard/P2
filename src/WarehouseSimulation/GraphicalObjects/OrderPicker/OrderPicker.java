package WarehouseSimulation.GraphicalObjects.OrderPicker;

import BackEnd.Geometry.Node.Node;
import BackEnd.Geometry.Point2D;
import WarehouseSimulation.GraphicalObjects.Colors.Colors;
import javafx.scene.shape.Rectangle;

import java.util.List;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public abstract class OrderPicker extends Rectangle implements MovingObject {

    private final static double PICKER_SPEED_IN_MPS = 2;
    private final static int SCREEN_HZ = 60; /* change this to screen HZ */
    private List<Node> routeList;
    private double moveDistancePerUpdate;
    private int scaleFactorMoveDistance;
    private int indexOfTargetNode;

    @Override
    public void setStartLocation() {
        relocate(2.5, 2.5);
        setTranslateX(routeList.get(0).getX() * TILE_SIZE);
        setTranslateY(routeList.get(0).getY() * TILE_SIZE);
    }

    @Override
    public double calculateMoveDistancePerUpdate(int scaleFactor) {
        return (PICKER_SPEED_IN_MPS * TILE_SIZE * 1 / SCREEN_HZ) * scaleFactor;
    }

    @Override
    public boolean move(int UPDATE_COUNTER) {
        if (!routeIsDone()) {
            if (moveIsVertical())
                moveVertical();
            else if (moveIsHorizontal())
                moveHorizontal();
            else
                waiting();

            if (isAtTargetNode(UPDATE_COUNTER)) {
                forcePickerToTargetNode();
                moveOnToNextTargetNode();
            }
            return true;
        }
        return false;
    }

}
