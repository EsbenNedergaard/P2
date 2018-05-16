package BackEnd.Pathfinding.Heuristics;

import BackEnd.Geometry.Node;
import BackEnd.Graph.SpaceTimeGrid;

public interface Heuristic {
    void findDistanceToEndForAllNodes(SpaceTimeGrid spaceTimeGrid, Node endNode);
}
