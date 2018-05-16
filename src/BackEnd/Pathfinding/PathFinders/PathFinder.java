package BackEnd.Pathfinding.PathFinders;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.LowestTotalDistanceComparator;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.Heuristics.Heuristic;
import BackEnd.Pathfinding.Heuristics.TrueDistance;
import BackEnd.Pathfinding.PickingRoute;
import Exceptions.RouteNotPossibleException;

import java.util.*;

public class PathFinder {
    private SpaceTimeGrid spaceTimeGrid;
    private List<Node> closedSet;
    private PriorityQueue<Node> openSet;
    private Node startNode;
    private Node endNode;
    private int startTime;
    private int pickTime;
    private Heuristic heuristic;


    public PathFinder(SpaceTimeGrid spaceTimeGrid) {
        this.spaceTimeGrid = spaceTimeGrid;
        this.closedSet = new ArrayList<>();
        this.heuristic = new TrueDistance();
        //We set the openSet to in worst case be cable of containing all nodes
        this.openSet = new PriorityQueue<>(spaceTimeGrid.getAllNodes().size(), new LowestTotalDistanceComparator());
    }

    public void resetSpaceTimeGrid() {
        this.spaceTimeGrid = new SpaceTimeGrid(spaceTimeGrid.getBaseLayer(), spaceTimeGrid.getMaxTime());
    }

    public void changeComparator(Comparator<Node> comparator) {
        this.openSet = new PriorityQueue<>(spaceTimeGrid.getAllNodes().size(), comparator);
    }

    public SpaceTimeGrid getSpaceTimeGrid() {
        return spaceTimeGrid;
    }

    public int getStartTime() {
        return startTime;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void removeRoute(List<Node> route) {
        spaceTimeGrid.removeRoute(route);
    }

    public PickingRoute findFastestPath(Point2D start, Point2D end, int startTime, int pickTime) throws RouteNotPossibleException {
        this.startNode =  new Node(start);
        this.endNode = new Node (end);
        this.startTime = startTime;
        this.pickTime = pickTime;

        //Checks and then sets starting values, and calculate the path
        PathFinderStartValueChecker.checkValues(this);
        this.setStartValues();
        this.calculateFastestPath();

        return constructPath();
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
        }
        // All nodes gets an estimated distance to the end node
        heuristic.findDistanceToEndForAllNodes(spaceTimeGrid, endNode);

        /*for(Node n : this.getSpaceTimeGrid().getAllNodes()) {
            n.setDistanceToEnd(n.getDistanceToEnd() * 1000);
        }*/
    }

    private void calculateFastestPath() {
        //Runs till all nodes have been visited or till we find the end node
        while (!openSet.isEmpty()) {
            Node current = getNextNode();

            if (isEndNode(current)) {
                endNode = current;
                break; //We have reached the destination
            }

            this.checkIfOutOfTime(current);

            //Checks if there exists a better path through current node to its neighbours
            for (Node neighbour : current.getNeighbourNodes()) {
                //We check if the current node is in the already checked nodes (closed set)
                if (closedSet.contains(neighbour)) {
                    continue;
                }
                //Update distance through neighbour
                this.updateNeighbourDistanceFromStart(current, neighbour);

                if (!openSet.contains(neighbour)) {
                    openSet.add(neighbour);
                }
            }
        }
    }

    //Retrieves next node to visit and adds it to the closed set
    private Node getNextNode(){
        Node next = openSet.poll();
        closedSet.add(next);
        return next;
    }

    private Boolean isEndNode(Node current) {
        return current.getX() == endNode.getX() && current.getY() == endNode.getY() && this.checkIfPickingIsPossible(current);
    }

    private void checkIfOutOfTime(Node current){
        if (spaceTimeGrid.getMaxTime() <= (current.getTime() + (pickTime + 1))){
            throw new RouteNotPossibleException("Did not find a route in the given time");
        }
    }

    private void updateNeighbourDistanceFromStart(Node current, Node neighbour) {
        //Update distance through neighbour
        if (current.getDistanceFromStart() + 1 < neighbour.getDistanceFromStart()) {
            //A better path exists.
            neighbour.setCameFrom(current);
            neighbour.setDistanceFromStart(current.getDistanceFromStart() + 1); //We increase the distance to the start with 1
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

    /*We use try catch to check if all the nodes we need to pick exist in the graph, or if they have been
    removed by other routes */
    private boolean checkIfPickingIsPossible(Node possibleEndPoint) {
        boolean found;
        Node current = possibleEndPoint;

        /*We add one to PICK_TIME because we need to make sure the next route also has a point to start on*/
        for(int i = 0; i < pickTime + 1; i++) {
            Node next = spaceTimeGrid.getNodePointer(current.getX(), current.getY(), current.getTime() + 1);
            found = false;
            for(Node neighbourToCurrent : current.getNeighbourNodes()) {
                //We check that the next node exists in the current neighbour list and haven't been removed
                if(next.equals(neighbourToCurrent)) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                return false;
            }
            current = next;
        }
        //We only get here if we get that there is a neighbour below our possible end point for the time required to pick
        return true;
    }
}
