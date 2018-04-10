package Geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Graph {
    private ArrayList<Node> closedSet;
    private PriorityQueue<Node> openSet;
    private ArrayList<Node> allNodes;

    public Graph(ArrayList<Node> allNodes) {
        this.allNodes = allNodes;
        openSet = new PriorityQueue<>(allNodes.size(), new NodeComparator());
        closedSet = new ArrayList<>();
        for (Node node : allNodes) {
            node.setNeighbourNodes(allNodes);
        }
    }

    public ArrayList<Node> findShortestRoute(Node start, Node end) {
        //Makes sure the sets are empty before the algorithm begins
        openSet.clear();
        closedSet.clear();

        //Sets starting values to all nodes
        //First node gets distance 0, other nodes get distance infinity
        //All nodes gets an estimated distance to the end node
        for (Node node : allNodes) {
            if (node.equals(start)) {
                node.setDistanceFromStart(0);
                openSet.add(node);
            } else {
                node.setDistanceToInf();
            }
            node.setDistanceToEnd(end);
        }

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

    //Constructs the shortest route as a list of nodes
    private ArrayList<Node> constructPath(Node start, Node end) {

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
