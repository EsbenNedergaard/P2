package GraphicalWarehouse.Handlers;

import Exceptions.IllegalMoveSpeedArgument;
import Geometry.Node;

import java.util.List;

public class MoveHandler {

    private List<Node> routeNodeList;
    private double moveSpeed; /* In m/s */

    public MoveHandler(List<Node> routeNodeList, double moveSpeed) {
        this.routeNodeList = routeNodeList;

        if(moveSpeed > 0)
            this. moveSpeed = moveSpeed;
        else
            throw new IllegalMoveSpeedArgument();
    }





}
