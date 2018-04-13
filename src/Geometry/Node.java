package Geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node extends Point2D {
    private static final int INFINITY = 1000000;
    private int distanceFromStart;
    private int distanceToEnd;
    private Node cameFrom;
    private ArrayList<Node> neighbourNodes;
    private NodeLayer timeLayer;
    private String nodeType; //TODO: Make this into enums

    public Node(Point2D p) {
        super(p);
        this.nodeType = "walkable";
        this.neighbourNodes = new ArrayList<>();
    }

    boolean isNeighbour(Node node) {
        if (this.getTime() + 1 == node.getTime()) {
            if (this.getX() == node.getX() + 1 && this.getY() == node.getY()) {
                return true;
            } else if (this.getX() == node.getX() - 1 && this.getY() == node.getY()) {
                return true;
            } else if (this.getX() == node.getX() && this.getY() == node.getY() + 1) {
                return true;
            } else if (this.getX() == node.getX() && this.getY() == node.getY() - 1) {
                return true;
            } else if (this.getX() == node.getX() && this.getY() == node.getY()) {
                return true;
            }
        }
        return false;
    }

    public int getTotalDistance() {
        return distanceFromStart + distanceToEnd;
    }

    public boolean isObstacle() {
        return nodeType.equals("Obstacle");
    }

    public int getTime() {
        if (timeLayer == null) {
            //TODO: make exception
            throw new NullPointerException("You tried to get time from a node that is'nt in a time layer");
        }
        return timeLayer.getTime();
    }

    public NodeLayer getTimeLayer() {
        return timeLayer;
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

    public int getDistanceToEnd() {
        return distanceToEnd;
    }

    public void setTimeLayer(NodeLayer timeLayer) {
        this.timeLayer = timeLayer;
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
        int xDistance = Math.abs(endNode.getX() - this.getX());
        int yDistance = Math.abs(endNode.getY() - this.getY());

        this.distanceToEnd = xDistance + yDistance;
    }

    public void setNeighbourNodes(List<Node> possibleNeighbours) {
        neighbourNodes = new ArrayList<>();
        for (Node node : possibleNeighbours) {
            if (this.isNeighbour(node)) {
                neighbourNodes.add(node);
            }
        }
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Node node = (Node) o;
        return this.getTime() == node.getTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTime());
    }

}


