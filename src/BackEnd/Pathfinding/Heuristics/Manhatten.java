package BackEnd.Pathfinding.Heuristics;

import BackEnd.Geometry.Node;
import BackEnd.Graph.BaseLayer;

import java.util.List;

public class Manhatten implements Heuristic{
    @Override
    public void findDistanceToEndForAllNodes(List<Node> allNodes, Node endNode) {
        for(Node node : allNodes) {
            int xDistance = Math.abs(endNode.getX() - node.getX());
            int yDistance = Math.abs(endNode.getY() - node.getY());

            int distanceToEnd = xDistance + yDistance;
            node.setDistanceToEnd(distanceToEnd);
        }
    }
}
