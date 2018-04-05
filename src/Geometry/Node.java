package Geometry;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Node extends Point2D {
    private static final int INFINITY = 1000000;
    private int distanceFromStart;
    private int distanceToEnd;
    private Node cameFrom;
    private ArrayList<Node> neighbourNodes;
    String nodeType;

    public Node(Point2D p) {
        super(p);
    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public ArrayList<Node> getNeighbourNodes() {
        return neighbourNodes;
    }

    public Node getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(Node cameFrom) {
        this.cameFrom = cameFrom;
    }

    public void setDistanceFromStart(int distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    public void setDistanceToInf() {
        this.distanceFromStart = INFINITY;
    }

    public void setDistanceToEnd(Node endNode) {
        int Xdistance = Math.abs(endNode.getX() - this.getX());
        int Ydistance = Math.abs(endNode.getY() - this.getY());

        this.distanceToEnd = Xdistance + Ydistance;
    }

    public void setNeighbourNodes(ArrayList<Node> allNodes) {
        neighbourNodes = new ArrayList<Node>();
        for (Node node : allNodes) {
            if (isNeighbour(node)) {
                neighbourNodes.add(node);
            }
        }
    }

    private boolean isNeighbour(Node node) {
        if (this.getX() == (node.getX() + 1) && this.getY() == node.getY()) {
            return true;
        } else if (this.getX() == (node.getX() - 1) && this.getY() == node.getY()) {
            return true;
        } else if (this.getX() == node.getX() && this.getY() == (node.getY() + 1)) {
            return true;
        } else if (this.getX() == node.getX() && this.getY() == (node.getY() - 1)) {
            return true;
        }
        return false;
    }

    public int getTotalDistance() {
        return distanceFromStart + distanceToEnd;
    }

}
