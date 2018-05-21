package BackEnd.Pathfinding.PathFinders;

import BackEnd.Exceptions.NodeLayerDoesNotExistException;
import BackEnd.Exceptions.PathNotPossibleException;
import BackEnd.Geometry.Node.Node;

public class PathFinderStartValueChecker {
    static public void checkValues(PathFinder pathFinder) {
        checkStartNode(pathFinder);
        checkEndNode(pathFinder);
        checkStartTime(pathFinder);
    }

    static private void checkStartNode(PathFinder pathFinder) {
        //We check that the startNode is inside the timeLayer for our startTime
        for (Node n : pathFinder.getSpaceTimeGrid().getNodeLayerPointer(pathFinder.getStartTime()).getNodeList()) {
            if (n.getX() == pathFinder.getStartNode().getX() && n.getY() == pathFinder.getStartNode().getY()) {
                return;
            }
        }
        throw new PathNotPossibleException("The start point was placed outside the SpaceTimeGrid: (" + pathFinder.getStartNode().getX() + ";" + pathFinder.getStartNode().getY() + ")");
    }

    static private void checkEndNode(PathFinder pathFinder) {
        //We check that the endNode is inside the grid
        for (Node n : pathFinder.getSpaceTimeGrid().getBaseLayer().getNodeListWithoutObstacles()) {
            if (n.getX() == pathFinder.getEndNode().getX() && n.getY() == pathFinder.getEndNode().getY()) {
                return;
            }
        }
        throw new PathNotPossibleException("The end point was placed outside the SpaceTimeGrid: (" + pathFinder.getEndNode().getX() + ";" + pathFinder.getEndNode().getY() + ")");
    }

    static private void checkStartTime(PathFinder pathFinder) {
        try {
            pathFinder.getSpaceTimeGrid().getNodeLayerPointer(pathFinder.getStartTime());
        } catch (NodeLayerDoesNotExistException e) {
            throw new PathNotPossibleException("There is no NodeLayer with the same time as the startTime :" + pathFinder.getStartTime());
        }
    }

}
