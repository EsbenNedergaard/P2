package Geometry;

import Exceptions.RouteNotPossibleException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {
    private SpaceTimeGrid spaceTimeGrid;
    private List<Node> closedSet;
    private PriorityQueue<Node> openSet;

    public PathFinder(SpaceTimeGrid spaceTimeGrid) {
        this.spaceTimeGrid = spaceTimeGrid;
        this.closedSet = new ArrayList<>();

        //We set the openSet to in worst case be cable of containing all nodes
        this.openSet = new PriorityQueue<>(spaceTimeGrid.getAllNodes().size(), new NodeComparator());
    }

    public List<Node> findShortestRoute(Node start, Node end) throws RouteNotPossibleException {
        //TODO: Lav noget så RouteNotPossibleException bliver castet på at start eller end ligger på et permanent obstacle
        this.checkStartAndEndNode(start, end);

        //Sets starting values to all nodes
        this.setStartValues(start, end);

        //Runs till all nodes have been visited or till we find the end node
        while (!openSet.isEmpty()) {
            //Retrieves next node to visit and adds it to the closed set
            Node current = openSet.poll();
            closedSet.add(current);

            //We have reached the destination
            if (current.getX() == end.getX() && current.getY() == end.getY()) {
                end = current;
                break;
            }


            if (spaceTimeGrid.getMaxTime() <= (current.getTime() + 1)){
                throw new RouteNotPossibleException("Did not find a route in the given time");
            }

            //Checks if there exists a better path through current node to its neighbours
            for (Node neighbour : current.getNeighbourNodes()) {
                //We check if the current node is in the already checked nodes (closed set)
                if (closedSet.contains(neighbour)) {
                    continue;
                }

                //A better path exists
                if (current.getDistanceFromStart() + 1 < neighbour.getDistanceFromStart()) {
                    neighbour.setCameFrom(current);
                    neighbour.setDistanceFromStart(current.getDistanceFromStart() + 1);
                }
                if (!openSet.contains(neighbour)) {
                    openSet.add(neighbour);
                }
            }
        }
        return constructPath(start, end);
    }

    private void checkStartAndEndNode(Node start, Node end) throws RouteNotPossibleException {
        //We check that the start point is inside the grid
        if ((start.getX() < 0 || start.getY() < 0) || (spaceTimeGrid.getBaseLayer().getMaxX() < start.getX() || spaceTimeGrid.getBaseLayer().getMaxY() < start.getY())) {
            throw new RouteNotPossibleException("The start point was placed outside the SpaceTimeGrid");
        }
        else if ((end.getX() < 0 || end.getY() < 0) || (spaceTimeGrid.getBaseLayer().getMaxX() < end.getX() || spaceTimeGrid.getBaseLayer().getMaxY() < end.getY() )) {
            throw new RouteNotPossibleException("The end point was placed outside the SpaceTimeGrid");
        }
        else {
            for(Node obstacle : spaceTimeGrid.getBaseLayer().getStationaryObstacles()){
                if (obstacle.getX() == start.getX() && obstacle.getY() == start.getY()) {
                    throw new RouteNotPossibleException("The start point was placed on top of a permanent obstacle");
                }
                else if (obstacle.getX() == end.getX() && obstacle.getY() == end.getY()) {
                    throw new RouteNotPossibleException("The end point was placed on top of a permanent obstacle");
                }
            }
        }
    }

    private void setStartValues(Node start, Node end) {
        //Makes sure the sets are empty before the algorithm begins
        openSet.clear();
        closedSet.clear();

        //We set the start-point to reference the first layer.
        start.setNodeLayer(spaceTimeGrid.getNodeLayerList().get(0));

        for (Node node : spaceTimeGrid.getAllNodes()) {
            if (node.equals(start)) {
                //First node gets distance 0, other nodes get distance infinity
                node.setDistanceFromStart(0);
                openSet.add(node);
            } else {
                node.setDistanceToInf();
            }
            //All nodes gets an estimated distance to the end node
            node.setDistanceToEnd(end);
        }
    }

    //Constructs the shortest route as a list of nodes
    private List<Node> constructPath(Node start, Node end) {
        ArrayList<Node> path = new ArrayList<>();

        //First node is the destination
        Node next = end;

        //Backtracks till we meet the start node
        while (!next.equals(start)) {
            path.add(next);
            next = next.getCameFrom();
        }
        path.add(start);

        //Reverses the list so that end node is the last element
        Collections.reverse(path);

        return path;
    }
}
