package GraphicalWarehouse.GraphicalObjects;

import Geometry.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.List;
import static Warehouse.GUIWarehouse.TILE_SIZE;

public class OrderPickerGraphics extends Rectangle {

    private final static double UPDATE_VALUE = 30.0;
    private final static double MOVE_DISTANCE_PER_UPDATE = TILE_SIZE / UPDATE_VALUE;

    private List<Node> routeList;
    // This is actually the point were it came from
    private int indexOfLastPoint;

    public OrderPickerGraphics(List<Node> routeList) {
        // Set design
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);
        setFill(Color.valueOf("#000"));

        // Set route list
        this.routeList = routeList;

        // When the object is first created, the index must be 0
        this.indexOfLastPoint = 0;

        relocate(routeList.get(0).getX() * TILE_SIZE, routeList.get(0).getY() * TILE_SIZE);

    }

    // Call this in a update method
    // For every call it will move towards the end of the route
    // Return true if the move was successful and false when the route is finished
    public boolean move(final int UPDATE_COUNTER) {

        if(indexOfLastPoint < routeList.size() - 2) {

            // If the picker is at the target point then move on to next target
            if(UPDATE_COUNTER % UPDATE_VALUE == 0)
                indexOfLastPoint++;

            if(changeInXCoordinate()) {

                if(getLastPointPosition().getX() < getTargetNode().getX())
                    setTranslateX(getTranslateX() + MOVE_DISTANCE_PER_UPDATE);
                else
                    setTranslateX(getTranslateX() - MOVE_DISTANCE_PER_UPDATE);

            } else {

                if(getLastPointPosition().getY() < getTargetNode().getY())
                    setTranslateY(getTranslateY() + MOVE_DISTANCE_PER_UPDATE);
                else
                    setTranslateY(getTranslateY() - MOVE_DISTANCE_PER_UPDATE);

            }

            return true;

        } else {
            // The route is done
            return false;
        }
    }

    private Node getLastPointPosition() {
        return this.routeList.get(indexOfLastPoint);
    }

    // Returns the node were the picker should move towards
    private Node getTargetNode() {
        int indexOfTargetNode = indexOfLastPoint + 1;

        if(indexOfTargetNode >= routeList.size())
            throw new IndexOutOfBoundsException("Index out of bound");

        return this.routeList.get(indexOfTargetNode);
    }

    private boolean inWaitingPosition() {
        return getLastPointPosition().getXPixels() == getTargetNode().getXPixels() &&
               getLastPointPosition().getYPixels() == getTargetNode().getYPixels();
    }

    private boolean changeInXCoordinate() {
        return getLastPointPosition().getXPixels() != getTargetNode().getXPixels() &&
               getLastPointPosition().getYPixels() == getTargetNode().getYPixels();
    }

    private boolean changeInYCoordinate() {
        return getLastPointPosition().getYPixels() != getTargetNode().getYPixels() &&
               getLastPointPosition().getXPixels() == getTargetNode().getXPixels();
    }


}
