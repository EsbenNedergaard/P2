package Geometry;

import Exceptions.NodeDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NodeLayer {
    private List<Node> nodeList = new ArrayList<>();
    private int time;

    public NodeLayer(List<Node> nodeList, int time) {
        if (nodeList.size() < 0) {
            throw new IllegalArgumentException();
        }
        for(Node element : nodeList) {
            this.nodeList.add(new Node(element));
        }
        this.time = time;
        setUpNodeReferences();
    }

    private void setUpNodeReferences(){
        for (Node element : this.nodeList) {
            element.setTimeLayer(this);
        }
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

    public Node getNodePointer(int x, int y) {
        for(Node n : nodeList) {
            if(x == n.getX() && y == n.getY()) {
                return n;
            }
        }
        throw new NodeDoesNotExistException("A node with x: " + x + " and y: " + y + ", does not exist");
    }

    public void removeNode(Node n){
        //TODO: check what happens when you try to remove an element that is'nt in the list
        nodeList.remove(n);
    }

    public void removeNodeFromNeighbourLists(Node n){
        for(Node element : nodeList) {
            for (Node neighbour : element.getNeighbourNodes()) {
                if(n.equals(neighbour)) {
                    element.getNeighbourNodes().remove(neighbour);
                    break;
                    /*We need this break because we remove an element from the Neighbour-loop that we are running through
                    otherwise the for-each loop will crash*/
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeLayer nodeLayer = (NodeLayer) o;

        return this.getTime() == nodeLayer.getTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTime());
    }
}