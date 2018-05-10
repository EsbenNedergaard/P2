package BackEnd.Pathfinding.PathFinders;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.NodeComparator;
import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Pathfinding.PickingRoute;
import Exceptions.NodeDoesNotExistException;
import Exceptions.NodeLayerDoesNotExistException;
import Exceptions.RouteNotPossibleException;
import BackEnd.Graph.SpaceTimeGrid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class FastestPathFinder extends PathFinder{
    public FastestPathFinder(SpaceTimeGrid spaceTimeGrid) {
        super(spaceTimeGrid);
    }

    @Override
    void updateNeighbourDistanceFromStart(Node current, Node neighbour) {
        //Update distance through neighbour
        if (current.getDistanceFromStart() + 1 < neighbour.getDistanceFromStart()) {
            //A better path exists.
            neighbour.setCameFrom(current);
            neighbour.setDistanceFromStart(current.getDistanceFromStart() + 1); //We increase the distance to the start with 1
        }
    }
}
