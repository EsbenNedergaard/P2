package BackEnd.Pathfinding.PathFinders;

import BackEnd.Geometry.Node;
import BackEnd.Graph.SpaceTimeGrid;

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
