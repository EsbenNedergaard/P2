package GraphicalWarehouse.GraphicalObjects;

import Geometry.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.List;
import static Warehouse.GUIWarehouse.TILE_SIZE;

public class OrderPickerGraphics extends Rectangle {

    private final static double UPDATE_VALUE = 30.0;
    private final static double MOVE_DISTANCE_PER_UPDATE = TILE_SIZE / UPDATE_VALUE;

    // Others
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
    // For every call it will movePicker towards the end of the route
    // Return true if the movePicker was successful and false when the route is finished
    public boolean movePicker(final int UPDATE_COUNTER) {

        if(routeList.size() > 0) {

            // If the picker is at the target point then movePicker on to next
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
            return false;
        }
    }

    private Node getLastPointPosition() {
        return this.routeList.get(indexOfLastPoint);
    }

    // Returns the node were the picker should movePicker towards
    private Node getTargetNode() {
        int indexOfTargetNode = indexOfLastPoint + 1;

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
