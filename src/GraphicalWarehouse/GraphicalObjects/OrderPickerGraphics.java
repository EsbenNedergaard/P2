package GraphicalWarehouse.GraphicalObjects;

import Geometry.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.List;
import static Warehouse.GUIWarehouse.TILE_SIZE;

public class OrderPickerGraphics extends Rectangle {

    private final static double UPDATE_VALUE = 30;
    private final static double MOVE_DISTANCE_PER_UPDATE = TILE_SIZE / UPDATE_VALUE;

    private List<Node> routeList;
    private int indexOfLastPoint;

    public OrderPickerGraphics(List<Node> routeList) {
        // Set design
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);
        setFill(Color.valueOf("#000"));

        // Set route list
        this.routeList = routeList;

        // When the object is first created, the index must be 0
        this.indexOfLastPoint = 1;

        relocate(routeList.get(0).getX() * TILE_SIZE, routeList.get(0).getY() * TILE_SIZE);

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
            if (UPDATE_COUNTER % UPDATE_VALUE == 0)
                indexOfLastPoint++;

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
