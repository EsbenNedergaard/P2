package Geometry;

import Exceptions.NodeDoesNotExistException;
import Exceptions.RouteNotPossibleException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {
    private SpaceTimeGrid spaceTimeGrid;
    private List<Node> closedSet;
    private PriorityQueue<Node> openSet;
    private Node startNode;
    private Node endNode;
    private int startTime;
    private final int PICK_TIME = 5;

    public PathFinder(SpaceTimeGrid spaceTimeGrid) {
        this.spaceTimeGrid = spaceTimeGrid;
        this.closedSet = new ArrayList<>();

        //We set the openSet to in worst case be cable of containing all nodes
        this.openSet = new PriorityQueue<>(spaceTimeGrid.getAllNodes().size(), new NodeComparator());
    }

    public SpaceTimeGrid getSpaceTimeGrid() {
        return spaceTimeGrid;
    }

    public int getPICK_TIME() {
        return PICK_TIME;
    }

    public void removeRoute(List<Node> route) {
        spaceTimeGrid.removeRoute(route);
    }

    public List<Node> findShortestRoute(Point2D start, Point2D end, int startTime) throws RouteNotPossibleException {
        this.startNode =  new Node(start);
        this.endNode = new Node (end);
        this.startTime = startTime;
        //TODO: Lav noget med startTime cast af exception her
        this.checkStartAndEndNode();

        //Sets starting values to all nodes
        this.setStartValues();

        //Runs till all nodes have been visited or till we find the end node
        while (!openSet.isEmpty()) {
            //Retrieves next node to visit and adds it to the closed set
            Node current = openSet.poll();
            closedSet.add(current);

            //We have reached the destination
            if (current.getX() == endNode.getX() && current.getY() == endNode.getY()) {
                if(checkIfValidEndPoint(current)) {
                    Node previous = current;
                    Node next;
                    /*Adding pickTime, to do this we need to make curr+1 come from curr, and curr+2 from curr+1 ...,
                      then in the end we set endNode to be curr+PICKTIME, (Notice, that we start at i = 1) */
                    for(int i = 1; i < PICK_TIME + 1; i++) {
                        next = spaceTimeGrid.getNodePointer(current.getX(), current.getY(), current.getTime() + i);
                        next.setCameFrom(previous);
                        previous = next;
                    }
                    endNode = previous;
                    break;
                }
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
        return constructPath(startNode, endNode);
    }

    private boolean checkIfValidEndPoint(Node current) {
        /*We use try catch to check if all the nodes we need to pick exist in the graph, or if they have been
          removed by other routes */
        for(int i = 1; i < PICK_TIME + 1; i++) {
            try {
                spaceTimeGrid.getNodePointer(current.getX(), current.getY(), current.getTime() + i);
            } catch (NodeDoesNotExistException e) {
                return false;
            }
        }
        return true;
    }

    private void checkStartAndEndNode() throws RouteNotPossibleException {
        //We check that the start point is inside the grid
        if ((startNode.getX() < 0 || startNode.getY() < 0) || (spaceTimeGrid.getBaseLayer().getMaxX() < startNode.getX() || spaceTimeGrid.getBaseLayer().getMaxY() < startNode.getY())) {
            throw new RouteNotPossibleException("The start point was placed outside the SpaceTimeGrid");
        }
        else if ((endNode.getX() < 0 || endNode.getY() < 0) || (spaceTimeGrid.getBaseLayer().getMaxX() < endNode.getX() || spaceTimeGrid.getBaseLayer().getMaxY() < endNode.getY() )) {
            throw new RouteNotPossibleException("The end point was placed outside the SpaceTimeGrid");
        }
        else {
            for(Node obstacle : spaceTimeGrid.getBaseLayer().getStationaryObstacles()){
                if (obstacle.getX() == startNode.getX() && obstacle.getY() == startNode.getY()) {
                    throw new RouteNotPossibleException("The start point was placed on top of a permanent obstacle");
                }
                else if (obstacle.getX() == endNode.getX() && obstacle.getY() == endNode.getY()) {
                    throw new RouteNotPossibleException("The end point was placed on top of a permanent obstacle");
                }
            }
        }
    }

    private void setStartValues() {
        //Makes sure the sets are empty before the algorithm begins
        openSet.clear();
        closedSet.clear();

        //We set the start-point to reference the first layer.
        startNode.setNodeLayer(spaceTimeGrid.getNodeLayerList().get(startTime));

        for (Node node : spaceTimeGrid.getAllNodes()) {
            if (node.equals(startNode)) {
                //First node gets distance 0, other nodes get distance infinity
                node.setDistanceFromStart(0);
                openSet.add(node);
            } else {
                node.setDistanceToInf();
            }
            //All nodes gets an estimated distance to the end node
            node.setDistanceToEnd(endNode);
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
