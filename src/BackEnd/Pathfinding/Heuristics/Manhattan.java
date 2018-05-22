package BackEnd.Pathfinding.Heuristics;

import BackEnd.Geometry.Node.Node;
import BackEnd.Graph.SpaceTimeGrid;

public class Manhattan implements Heuristic {
    @Override
    public void findDistanceToEndForAllNodes(SpaceTimeGrid spaceTimeGrid, Node endNode) {
        for (Node node : spaceTimeGrid.getAllNodes()) {
            int xDistance = Math.abs(endNode.getX() - node.getX());
            int yDistance = Math.abs(endNode.getY() - node.getY());

            int distanceToEnd = xDistance + yDistance;
            node.setDistanceToEnd(distanceToEnd);
        }
    }
}
