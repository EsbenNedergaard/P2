package BackEnd.Graph;

import Exceptions.NodeLayerDoesNotExistException;
import Exceptions.NodeDoesNotExistException;
import BackEnd.Geometry.Node;

import java.util.ArrayList;
import java.util.List;

public class SpaceTimeGrid {
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
            nodeLayerList.add(new NodeLayer(baseLayer.getNodeListWithoutObstacles(), i));
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
                try {
                    return nodeLayer.getNodePointer(x, y);
                } catch (NodeDoesNotExistException e) {
                    throw new NodeDoesNotExistException("The Node with x:" + x + ", y:" + y + ", does not exist in the time: " + time);
                }

            }
        }
        throw new NodeLayerDoesNotExistException("There is no NodeLayer with the time: " + time + " in this graph");
    }

    public NodeLayer getNodeLayerPointer(int time){
        for(NodeLayer nodeLayer : nodeLayerList) {
            if(time == nodeLayer.getTime()) {
                return nodeLayer;
            }
        }
        throw new NodeLayerDoesNotExistException("There is no NodeLayer with the time: " + time + " in this graph");
    }

    public void removeRoute(List<Node> route) {
        for (Node node : route) {
            try {
                if (node.getTime() + 1 != maxTime) {
                    //Here we remove the node in the next layer also so that we cant pass through each other
                    try {
                        Node nodeInNextLayer = this.getNodePointer(node.getX(), node.getY(), node.getTime() + 1);
                        this.removeNodeFromNeighbourLists(nodeInNextLayer);
                    } catch (NodeDoesNotExistException e) {
                        //We already removed the node in the next layer, so nothing should happen
                    }
                }
                this.removeNodeFromNeighbourLists(node);
            } catch (NodeDoesNotExistException e) {
                System.out.println("We could not remove the desired node in this layer");
            }
        }
    }

    public void removeNodeFromNeighbourLists(Node node){
        if(node.getTime() == 0) {
            return;
        }
        NodeLayer earlierNodeLayer = this.getNodeLayerPointer(node.getTime()-1);

        for(Node nodeInEarlierLayer : earlierNodeLayer.getNodeList()) {
            for (Node neighbour : nodeInEarlierLayer.getNeighbourNodes()) {
                if(node.equals(neighbour)) {
                    nodeInEarlierLayer.getNeighbourNodes().remove(neighbour);
                    /* We need this break because we remove an element from the Neighbour-loop that we are running through
                    otherwise the for-each loop will crash */
                    break;
                }
            }
        }
    }
}