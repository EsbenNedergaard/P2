package Geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NodeLayer {
    private List<Node> nodeList = new ArrayList<>();
    private int time;

    public NodeLayer(List<Node> nodeList, int time) {
        if (nodeList.size() <= 0) {
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
            //element.setTimeLayer(this);
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