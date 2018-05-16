package BackEnd.Pathfinding.Heuristics;

import BackEnd.Geometry.Node.Node;
import BackEnd.Graph.SpaceTimeGrid;

public interface Heuristic {
    void findDistanceToEndForAllNodes(SpaceTimeGrid spaceTimeGrid, Node endNode);
}
