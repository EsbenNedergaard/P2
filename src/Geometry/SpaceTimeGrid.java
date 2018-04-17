package Geometry;

import Exceptions.NodeDoesNotExistException;

import java.util.ArrayList;
import java.util.List;

public class SpaceTimeGrid {
    //TODO: set something up so you can check if the end node or start node is set on top of a permanent obstacle with help of baselayer
    private BaseLayer baseLayer;
    private List<NodeLayer> nodeLayerList;
    private int maxTime;

    public SpaceTimeGrid(BaseLayer baseLayer, int maxTime) {
        this.baseLayer = baseLayer;
        this.nodeLayerList = new ArrayList<>();
        this.maxTime = maxTime;

        setupNodeLayerList();
    }

    private void setupNodeLayerList() {
        for (int i = 0; i < maxTime; i++) {
            NodeLayer tempNodeLayer = new NodeLayer(baseLayer.getNodeList(), i);
            nodeLayerList.add(tempNodeLayer);
            if (i != 0) {
                nodeLayerList.get(i-1).setAllNeighbourNodesForLayer(nodeLayerList.get(i));
            }
        }
    }

    public List<NodeLayer> getNodeLayerList() {
        return nodeLayerList;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public BaseLayer getBaseLayer() {
        return baseLayer;
    }

    public List<Node> getAllNodes(){
        List<Node> allNodes = new ArrayList<>();
        for(NodeLayer element : nodeLayerList){
            allNodes.addAll(element.getNodeList());
        }

        return allNodes;
    }

    public Node getNodePointer(int x, int y, int time) {
        for (NodeLayer nodeLayer : nodeLayerList) {
            if(time == nodeLayer.getTime()) {
                return nodeLayer.getNodePointer(x, y);
            }
        }
        throw new NodeDoesNotExistException("There was no NodeLayer in the graph with the time " + time);
    }

    public void removeNode(Node n){
        if(n.getTime() != 0) {
            this.removeNodeFromNeighbourLists(nodeLayerList.get(n.getTime() - 1), n);
        }
        n.getNodeLayerPointer().removeNode(n);
    }

    private void removeNodeFromNeighbourLists(NodeLayer earlierNodeLayer, Node neighbourToRemove){
        for(Node node : earlierNodeLayer.getNodeList()) {
            for (Node neighbour : node.getNeighbourNodes()) {
                if(neighbourToRemove.equals(neighbour)) {
                    node.getNeighbourNodes().remove(neighbour);
                    /*We need this break because we remove an element from the Neighbour-loop that we are running through
                    otherwise the for-each loop will crash*/
                    break;
                }
            }
        }
    }

}
