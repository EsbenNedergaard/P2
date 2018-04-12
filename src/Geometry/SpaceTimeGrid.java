package Geometry;

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


    public void removeNode(Node n){
        for (NodeLayer nodeLayerElement : nodeLayerList) {
            if (n.getTime() == nodeLayerElement.getTime()) {
                for(Node nodeElement : nodeLayerElement.getNodeList()) {
                    if (n.equals(nodeElement)) {
                        nodeLayerElement.getNodeList().remove(n);
                        return;
                    }
                }
                /*If there wasn't a node in the NodeLayer with the same time as the node, that was equal to the node,
                  then there wont be any so we might as well return*/
                return;
            }
        }
        throw new IllegalArgumentException("This node does not exist");
    }
}
