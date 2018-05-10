package BackEnd.Pathfinding.PathFinders;

import BackEnd.Geometry.Node;
import BackEnd.Graph.SpaceTimeGrid;

public class ShortestPathFinder extends PathFinder {
    public ShortestPathFinder(SpaceTimeGrid spaceTimeGrid) {
        super(spaceTimeGrid);
    }

    void updateNeighbourDistanceFromStart(Node current, Node neighbour) {
        if (current.getDistanceFromStart() + 1 < neighbour.getDistanceFromStart()) {
            neighbour.setCameFrom(current);
            if(current.getX() == neighbour.getX() && current.getY() == neighbour.getY()) {
                neighbour.setDistanceFromStart(current.getDistanceFromStart()); //Here we don't punish for waiting.
            } else {
                neighbour.setDistanceFromStart(current.getDistanceFromStart() + 1);
            }
        }
    }

}
