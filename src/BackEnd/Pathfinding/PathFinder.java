package BackEnd.Pathfinding;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.NodeComparator;
import BackEnd.Geometry.Point2D;
import Exceptions.NodeDoesNotExistException;
import Exceptions.NodeLayerDoesNotExistException;
import Exceptions.RouteNotPossibleException;
import BackEnd.Graph.SpaceTimeGrid;

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
    private int pickTime;


    public PathFinder(SpaceTimeGrid spaceTimeGrid) {
        this.spaceTimeGrid = spaceTimeGrid;
        this.closedSet = new ArrayList<>();

        //We set the openSet to in worst case be cable of containing all nodes
        this.openSet = new PriorityQueue<>(spaceTimeGrid.getAllNodes().size(), new NodeComparator());
    }

    public SpaceTimeGrid getSpaceTimeGrid() {
        return spaceTimeGrid;
    }

    public void removeRoute(List<Node> route) {
        spaceTimeGrid.removeRoute(route);
    }

    public PickingRoute findShortestRoute(Point2D start, Point2D end, int startTime) throws RouteNotPossibleException {
        this.startNode =  new Node(start);
        this.endNode = new Node (end);
        this.startTime = startTime;
        this.pickTime = PickingRoute.PICK_TIME;

        //Checks and then sets starting values
        this.checkInitialValues();
        this.setStartValues();

        //We calculate the path
        this.calculatePath();

        return constructPath();
    }

    private void calculatePath() {
        //Runs till all nodes have been visited or till we find the end node
        while (!openSet.isEmpty()) {
            //Retrieves next node to visit and adds it to the closed set
            Node current = openSet.poll();
            closedSet.add(current);

            //We have reached the destination
            if (current.getX() == endNode.getX() && current.getY() == endNode.getY()) {
                if(checkIfValidEndPoint(current)) {
                    endNode = current;
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
    }

    //Constructs the shortest route as a list of nodes
    private PickingRoute constructPath() {
        PickingRoute path = new PickingRoute();
        //First node is the destination
        Node next = this.endNode;

        //Backtracks till we meet the start node
        while (!next.equals(this.startNode)) {
            path.addNodeToRoute(next);
            next = next.getCameFrom();
        }
        path.addNodeToRoute(this.startNode);
        //Reverses the list so that end node is the last element
        Collections.reverse(path.getRoute());
        return path;
    }


    private void setStartValues() {
        //Makes sure the sets are empty before the algorithm begins
        openSet.clear();
        closedSet.clear();

        //We set the start-point to reference the correct layer.
        startNode.setNodeLayer(spaceTimeGrid.getNodeLayerList().get(startTime));

        //First node gets distance 0, other nodes get distance infinity
        for (Node node : spaceTimeGrid.getAllNodes()) {
            if (node.equals(startNode)) {
                node.setDistanceFromStart(0);
                openSet.add(node);
            } else {
                node.setDistanceToInf();
            }
            // All nodes gets an estimated distance to the end node
            node.setDistanceToEnd(endNode);
        }
    }

    private void checkInitialValues() {
        this.checkStartNode();
        this.checkEndNode();
        this.checkStartTime();
    }

    private void checkStartNode()  {
        //We check that the startNode is inside the timeLayer for our startTime
        for (Node n : spaceTimeGrid.getNodeLayerPointer(startTime).getNodeList()) {
            if (n.getX() == startNode.getX() && n.getY() == startNode.getY()) {
                return;
            }
        }
        throw new RouteNotPossibleException("The start point was placed outside the SpaceTimeGrid");
    }
    private void checkEndNode() {
        //We check that the endNode is inside the grid
        for(Node n : spaceTimeGrid.getBaseLayer().getNodeListWithoutObstacles()) {
            if (n.getX() == endNode.getX() && n.getY() == endNode.getY()) {
                return;
            }
        }
        throw new RouteNotPossibleException("The start point was placed outside the SpaceTimeGrid");
    }

    private void checkStartTime() {
        try {
            spaceTimeGrid.getNodeLayerPointer(startTime);
        } catch (NodeLayerDoesNotExistException e) {
            throw new RouteNotPossibleException("There is no NodeLayer with the same time as the startTime :" + startTime);
        }
    }

    /*We use try catch to check if all the nodes we need to pick exist in the graph, or if they have been
    removed by other routes */
    private boolean checkIfValidEndPoint(Node current) {
        /*We add one to PICK_TIME because we need to make sure the next route also has a point to start on*/
        for(int i = 0; i < pickTime + 1; i++) {
            try {
                /*We add one extra to the time because we start at i=0, which does not help very much, because we
                 * already know this node exists otherwise our pathFinder could not have gotten over to it*/
                spaceTimeGrid.getNodePointer(current.getX(), current.getY(), (current.getTime() + i) + 1);
            } catch (NodeDoesNotExistException e) {
                return false;
            }
        }
        return true;
    }
}
