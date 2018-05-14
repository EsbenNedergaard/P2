package BackEnd.Pathfinding.Heuristics;

import BackEnd.Geometry.Node;
import BackEnd.Graph.NodeLayer;
import BackEnd.Graph.SpaceTimeGrid;

import java.util.List;

public class TrueDistance implements Heuristic {
    @Override
    public void findDistanceToEndForAllNodes(SpaceTimeGrid spaceTimeGrid, Node endNode) {
        List<Node> baseLayerNodeList = spaceTimeGrid.getBaseLayer().getNodeListWithoutObstacles();
        //Køre truedistance på baseLayer i spaceTimeGrid, og finde deres distancer her og derefter går ned af i gennem spaceTimeGridet
        //og give punket 0,0 samme distance til end som den du fandt for 0,0 i baselayeret.

        //MODIFCIER DETTE SÅ DET ER TRUE DISTANCE OG IKKE MANHATTEN
        for(Node node : baseLayerNodeList) {
            int xDistance = Math.abs(endNode.getX() - node.getX());
            int yDistance = Math.abs(endNode.getY() - node.getY());

            int distanceToEnd = xDistance + yDistance;
            node.setDistanceToEnd(distanceToEnd);
        }


        int baseLayerSize = baseLayerNodeList.size();
        //BURDE SORTERE NODERNE FØRST FOR EN SIKKERHEDSSKYLD
        for(NodeLayer nodeLayer : spaceTimeGrid.getNodeLayerList()) {
            //We run through all the nodes in the layer and set the distance to end, to be the same as for the baseLayer
            for(int i = 0; i < baseLayerSize; i++) {
                nodeLayer.getNodeList().get(i).setDistanceToEnd(baseLayerNodeList.get(i).getDistanceToEnd());
            }
        }
    }
}
