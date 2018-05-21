package BackEnd.Geometry.Node;

import BackEnd.Geometry.Point2D;
import BackEnd.Graph.NodeLayer;
import BackEnd.Exceptions.IsNotValidNodeTypeException;
import BackEnd.Exceptions.UnplacedNodeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node extends Point2D {
    private static final int INFINITY = 1000000;
    private int distanceFromStart;
    private int distanceToEnd;
    private Node cameFrom;
    private ArrayList<Node> neighbourNodes = new ArrayList<>();
    private NodeLayer nodeLayerPointer;
    private NodeType nodeType;

    public Node(Point2D p) {
        super(p);
        this.nodeType = NodeType.WALKABLE;
    }

    private boolean isNeighbour(Node node) {
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
            throw new UnplacedNodeException();
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

    public void setDistanceToEnd(int distanceToEnd) {
        this.distanceToEnd = distanceToEnd;
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

    public void addNeighbour(Node neighbourNode) {
        this.neighbourNodes.add(neighbourNode);
    }
}


