package Geometry;

import Exceptions.IsNotValidNodeTypeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*TODO: få lavet et nabo-system i forhold til vores varehus, som ved hvilke punkter man kan gå fra og til, da det gør vi kan være ligeglade
  med, hvilke punkter vi ikke kan gå igennem, hvis vi bare har ende punkterne af gangene, og kan afgøre, fra hvilke punkter man kan gå til andre punkter,
  nemmeste ville nok være at lave en liste af "right end points" og en liste af "left end points" også kan bare tjekke om varens y-koordinat stemmer overens med
  et endepunkt, så kører vi bare igennem et punkt efter et andet, og tager dem med lavest f-værdi som er naboer til vores nuværende punkt indtil vi når slutpunktet  */


public class Node extends Point2D {
    private static final int INFINITY = 1000000;
    private int distanceFromStart;
    private int distanceToEnd;
    private Node cameFrom;
    private ArrayList<Node> neighbourNodes;
    private NodeLayer nodeLayerPointer;
    private NodeLayer timeLayer;
    private NodeType nodeType;

    public Node(Point2D p) {
        super(p);
        this.nodeType = NodeType.WALKABLE;
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


    public boolean isObstacle() {
        return nodeType.equals(NodeType.OBSTACLE);
    }


    public int getTime() {
        if (nodeLayerPointer == null) {
            //TODO: make exception
            throw new NullPointerException("You tried to get time from a node that is no,,t in a time layer");
        }
        return nodeLayerPointer.getTime();
    }

    public NodeLayer getNodeLayerPointer() {
        return nodeLayerPointer;
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

    public void setNodeLayer(NodeLayer nodeLayerPointer) {
        this.nodeLayerPointer = nodeLayerPointer;
    }
  
    public NodeType getNodeType() {
        return nodeType;
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

    public void setNodeType(NodeType nodeType) {
        switch (nodeType) {
            case OBSTACLE:
                this.nodeType = NodeType.OBSTACLE;
                break;
            case WALKABLE:
                this.nodeType = NodeType.WALKABLE;
                break;
            default:
                throw new IsNotValidNodeTypeException();
        }
    }
}


