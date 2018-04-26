package WarehouseSimulation.GraphicalObjects;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;
import static Warehouse.GUIWarehouse.TILE_SIZE;

public class OrderPickerGraphic extends Circle {

    private final static double UPDATE_VALUE = 25;
    private final static double MOVE_DISTANCE_PER_UPDATE = TILE_SIZE / UPDATE_VALUE;

    private List<Node> routeList;
    private int indexOfLastPoint;


    public OrderPickerGraphic(List<Node> routeList) {
        // Set design
        setRadius(TILE_SIZE / 2.5);

        setFill(Color.valueOf("#2d79f7"));

        // Set route list
        this.routeList = routeList;

        // When the object is first created, the index must be 1
        this.indexOfLastPoint = 1;

        relocate(2.5, 2.5);

        setTranslateX(routeList.get(0).getX() * TILE_SIZE);
        setTranslateY(routeList.get(0).getY() * TILE_SIZE);
    }


    // Call this in a update method
    // For every call it will move towards the end of the route
    // Return true if the move was successful and false when the route is finished
    public boolean move(final int UPDATE_COUNTER) {

        if (indexOfLastPoint < routeList.size()) {

            if (changeInXCoordinate()) {
                // Then move current picker in X direction
                if (getLastPointPosition().getX() < getTargetNode().getX()) {
                    setTranslateX(getTranslateX() + MOVE_DISTANCE_PER_UPDATE);
                }  else {
                    setTranslateX(getTranslateX() - MOVE_DISTANCE_PER_UPDATE);
                }

            } else if (changeInYCoordinate()) {
                // Then move current picker in Y direction
                if (getLastPointPosition().getY() < getTargetNode().getY()) {
                    setTranslateY(getTranslateY() + MOVE_DISTANCE_PER_UPDATE);
                } else {
                    setTranslateY(getTranslateY() - MOVE_DISTANCE_PER_UPDATE);
                }

            }
            // The else block is where it would be when in waiting position

            // If the picker is at the target point then move on to next target
            if (UPDATE_COUNTER % UPDATE_VALUE == 0) {
                // Force picker to target point when moving on to next
                setTranslateX(getTargetNode().getXPixels());
                setTranslateY(getTargetNode().getYPixels());
                indexOfLastPoint++;
            }

            return true;

        } else {
            // The route is done
            return false;
        }
    }

    private Node getLastPointPosition() {
        return this.routeList.get(indexOfLastPoint - 1);
    }

    // Returns the node were the picker should move towards
    private Node getTargetNode() {
        int indexOfTargetNode = indexOfLastPoint;

        if (indexOfTargetNode >= routeList.size())
            throw new IndexOutOfBoundsException("Index out of bound");

        return this.routeList.get(indexOfTargetNode);
    }

    Point2D getCurrentPosition() {
        return new Point2D((int) getTranslateX() / TILE_SIZE, (int) getTranslateY() / TILE_SIZE);
    }

    // Return true if two points has different x values
    private boolean changeInXCoordinate() {
        return getLastPointPosition().getXPixels() != getTargetNode().getXPixels() &&
               getLastPointPosition().getYPixels() == getTargetNode().getYPixels();
    }

    // Return true if two points has different y values
    private boolean changeInYCoordinate() {
        return getLastPointPosition().getYPixels() != getTargetNode().getYPixels() &&
               getLastPointPosition().getXPixels() == getTargetNode().getXPixels();
    }

}
