package BackEnd.Pathfinding.Heuristics;

import BackEnd.Geometry.Node;
import BackEnd.Graph.BaseLayer;

import java.util.List;

public interface Heuristic {
    void findDistanceToEndForAllNodes(List<Node> allNodes, Node endNode);
}
