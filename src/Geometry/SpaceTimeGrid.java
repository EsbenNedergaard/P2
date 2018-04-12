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

    public List<Node> getAllNodes(){
        List<Node> allNodes = new ArrayList<>();
        for(NodeLayer element : nodeLayerList){
            allNodes.addAll(element.getNodeList());
        }

        return allNodes;
    }


    public void removeNode(Node n){
        //TODO: lave så man fjerner noten og tjekke om den bliver fjernet fra neighbour også
        /*for(Node node : nodeList){
            if (node.equals(n)){
                nodeList.remove(node);
            }
        }*/
    }
}
