package BackEnd.Pathfinding.Heuristics;

import BackEnd.Geometry.Node;
import BackEnd.Graph.BaseLayer;
import BackEnd.Graph.SpaceTimeGrid;

import java.util.List;

public interface Heuristic {
    void findDistanceToEndForAllNodes(SpaceTimeGrid spaceTimeGrid, Node endNode);
}
