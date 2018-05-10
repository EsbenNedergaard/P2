package BackEnd.Pathfinding.Heuristics;

import BackEnd.Geometry.Node;
import BackEnd.Graph.BaseLayer;

import java.util.List;

public interface Heuristic {
    void setDistanceToEnd(List<Node> allNodes, Node endNode);
}
