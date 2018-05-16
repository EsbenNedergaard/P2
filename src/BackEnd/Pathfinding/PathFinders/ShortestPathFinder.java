package BackEnd.Pathfinding.PathFinders;

import BackEnd.Geometry.Node;
import BackEnd.Graph.SpaceTimeGrid;

public class ShortestPathFinder extends PathFinder {
    public ShortestPathFinder(SpaceTimeGrid spaceTimeGrid) {
        super(spaceTimeGrid);
    }

    void updateNeighbourDistanceFromStart(Node current, Node neighbour) {
        int punishmentForWaiting = 1;
        int punishmentForMoving = 1000;


        /*if(current.getX() == neighbour.getX() && current.getY() == neighbour.getY()) {
            if(current.getDistanceFromStart() + punishmentForWaiting < neighbour.getDistanceFromStart()) {
                neighbour.setCameFrom(current);
                neighbour.setDistanceFromStart(current.getDistanceFromStart() + punishmentForWaiting); //Here we don't punish for waiting.
            }
        } else if (current.getDistanceFromStart() + punishmentForMoving < neighbour.getDistanceFromStart()){
            neighbour.setCameFrom(current);
            neighbour.setDistanceFromStart(current.getDistanceFromStart() + punishmentForMoving);
        }*/

        if(current.getX() == neighbour.getX() && current.getY() == neighbour.getY()) {
            if(current.getDistanceFromStart() < neighbour.getDistanceFromStart()) {
                neighbour.setCameFrom(current);
                neighbour.setDistanceFromStart(current.getDistanceFromStart()); //Here we don't punish for waiting.
            }
        } else if (current.getDistanceFromStart() + 1 < neighbour.getDistanceFromStart()){
            neighbour.setCameFrom(current);
            neighbour.setDistanceFromStart(current.getDistanceFromStart() + 1);
        }

    }

}
