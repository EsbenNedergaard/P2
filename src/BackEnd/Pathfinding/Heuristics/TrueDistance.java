package BackEnd.Pathfinding.Heuristics;

import BackEnd.Geometry.LowestTotalDistanceComparator;
import BackEnd.Geometry.Node;
import BackEnd.Graph.NodeLayer;
import BackEnd.Graph.SpaceTimeGrid;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TrueDistance implements Heuristic {
    Node startNode;
    NodeLayer baseLayer;
    List<Node> baseLayerNodeList;
    PriorityQueue<Node> openSet;
    List<Node> closedSet;


    @Override
    public void findDistanceToEndForAllNodes(SpaceTimeGrid spaceTimeGrid, Node endNode) {
        this.baseLayer = new NodeLayer(spaceTimeGrid.getBaseLayer().getNodeListWithoutObstacles(), 0);
        endNode.setNodeLayer(baseLayer);
        this.baseLayerNodeList = baseLayer.getNodeList();
        this.startNode = endNode;
        this.openSet = new PriorityQueue<>(baseLayerNodeList.size(), new LowestTotalDistanceComparator());
        this.closedSet = new ArrayList<>();

        //Køre truedistance på baseLayer i spaceTimeGrid, og finde deres distancer her og derefter går ned af i gennem spaceTimeGridet
        //og give punket 0,0 samme distance til end som den du fandt for 0,0 i baselayeret.

        this.setBaseLayerNeighbours();
        this.setStartValues(baseLayerNodeList, startNode);
        this.calculateTrueDistances();
        this.setTrueDistancesToEndForBaseLayer();
        this.setTrueDistancesForAllLayers(spaceTimeGrid);
    }

    private void setTrueDistancesForAllLayers(SpaceTimeGrid spaceTimeGrid) {
        int baseLayerSize = baseLayerNodeList.size();
        //BURDE SORTERE NODERNE FØRST FOR EN SIKKERHEDSSKYLD
        for (NodeLayer nodeLayer : spaceTimeGrid.getNodeLayerList()) {
            //We run through all the nodes in the layer and set the distance to end, to be the same as for the baseLayer
            for (int i = 0; i < baseLayerSize; i++) {
                nodeLayer.getNodeList().get(i).setDistanceToEnd(baseLayerNodeList.get(i).getDistanceToEnd());
            }
        }
    }

    private void setTrueDistancesToEndForBaseLayer() {
        for (Node node : baseLayerNodeList) {
            node.setDistanceToEnd(node.getDistanceFromStart());
        }
    }

    private void setStartValues(List<Node> baseLayerNodeList, Node startNode) {
        //Makes sure the sets are empty before the algorithm begins
        openSet.clear();
        closedSet.clear();

        //First node gets distance 0, other nodes get distance infinity
        for (Node node : baseLayerNodeList) {
            if (node.equals(startNode)) {
                node.setDistanceFromStart(0);
                openSet.add(node);
            } else {
                node.setDistanceToInf();
            }
        }

        // All nodes get a heuristic distance of 0 to the end
        for (Node node : baseLayerNodeList) {
            node.setDistanceToEnd(0);
        }

    }

    private void calculateTrueDistances() {
        //Runs till all nodes have been visited
        while (!openSet.isEmpty()) {
            Node current = getNextNode();

            //Checks if there exists a better path through current node to its neighbours
            for (Node neighbour : current.getNeighbourNodes()) {
                //We check if the current node is in the already checked nodes (closed set)
                if (closedSet.contains(neighbour)) {
                    continue;
                }
                //Update distance to neighbour if the distance through current is shorter
                if (distanceThroughCurrentIsShorter(current, neighbour)) {
                    neighbour.setDistanceFromStart(current.getDistanceFromStart() + 1);
                }

                if (!openSet.contains(neighbour)) {
                    openSet.add(neighbour);
                }
            }
        }
    }

    //Checks if the distance from start to a neighbour node is shorter through the current node
    private boolean distanceThroughCurrentIsShorter(Node current, Node neighbour) {
        if (current.getDistanceFromStart() + 1 < neighbour.getDistanceFromStart()) {
            return true;
        }

        return false;
    }

    //Retrieves next node to visit and adds it to the closed set
    private Node getNextNode() {
        Node next = openSet.poll();
        closedSet.add(next);
        return next;
    }

    private void setBaseLayerNeighbours() {
        for (Node node : baseLayerNodeList) {
            setNeighboursForNode(node);
        }
    }

    private void setNeighboursForNode(Node node) {
        for (Node possibleNeighbour : baseLayerNodeList) {
            if (isNeighbours(node, possibleNeighbour)) {
                node.addNeighbour(possibleNeighbour);
            }
        }
    }

    private boolean isNeighbours(Node node1, Node node2) {
        if (node1.getX() == node2.getX() + 1 && node1.getY() == node2.getY()) {
            return true;
        } else if (node1.getX() == node2.getX() - 1 && node1.getY() == node2.getY()) {
            return true;
        } else if (node1.getX() == node2.getX() && node1.getY() == node2.getY() + 1) {
            return true;
        } else if (node1.getX() == node2.getX() && node1.getY() == node2.getY() - 1) {
            return true;
        }

        return false;
    }

}
