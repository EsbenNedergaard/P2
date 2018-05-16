package BackEnd.Graph;

import BackEnd.Geometry.Node;
import Exceptions.NodeDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Every node has layers representing a time dimension
public class NodeLayer {
    private List<Node> nodeList = new ArrayList<>();
    private int time;

    public NodeLayer(List<Node> nodeList, int time) {
        if (nodeList == null || nodeList.size() < 0) {
            throw new IllegalArgumentException();
        }
        for (Node element : nodeList) {
            this.nodeList.add(new Node(element));
        }
        this.time = time;
        setUpNodeReferences();
    }

    private void setUpNodeReferences() {
        for (Node element : this.nodeList) {
            element.setNodeLayer(this);
        }
    }

    public Node getNodePointer(int x, int y) {
        for (Node n : nodeList) {
            if (x == n.getX() && y == n.getY()) {
                return n;
            }
        }
        throw new NodeDoesNotExistException("A node with x: " + x + " and y: " + y + ", does not exist");
    }

    public void setAllNeighbourNodesForLayer(NodeLayer nextNodeLayer) {
        for (Node node : this.getNodeList()) {
            node.setNeighbourNodes(nextNodeLayer.getNodeList());
        }
    }

    public int getTime() {
        return time;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void removeNode(Node n) {
        nodeList.remove(n);
    }

    //TODO: overveje om der skal være noget mere som gør NodeLayer ens
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeLayer nodeLayer = (NodeLayer) o;

        if (this.getTime() != nodeLayer.getTime()) {
            return false;
        }
        if (this.getNodeList().size() != nodeLayer.getNodeList().size()) {
            return false;
        }

        /*We check if there is a node in the layer with the same x and y coordinate for every node in our current layer*/
        try {
            for (Node node : this.getNodeList()) {
                nodeLayer.getNodePointer(node.getX(), node.getY());
            }
        } catch (NodeDoesNotExistException e) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTime()) + Objects.hash(getNodeList());
    }
}