package BackEnd.Pathfinding.Heuristics;

import BackEnd.Geometry.Node;
import BackEnd.Graph.SpaceTimeGrid;
import Exceptions.NodeDoesNotExistException;

public class TrueDistance implements Heuristic {
    @Override
    public void findDistanceToEndForAllNodes(SpaceTimeGrid spaceTimeGrid, Node endNode) {
        //Køre truedistance på baseLayer i spaceTimeGrid, og finde deres distancer her og derefter går ned af i gennem spaceTimeGridet
        //og give punket 0,0 samme distance til end som den du fandt for 0,0 i baselayeret.

        //MODIFCIER DETTE SÅ DET ER TRUE DISTANCE OG IKKE MANHATTEN
        for(Node node : spaceTimeGrid.getBaseLayer().getNodeListWithoutObstacles()) {
            int xDistance = Math.abs(endNode.getX() - node.getX());
            int yDistance = Math.abs(endNode.getY() - node.getY());

            int distanceToEnd = xDistance + yDistance;
            node.setDistanceToEnd(distanceToEnd);
        }

        //Denne del sørger for vi kopier baseLayerets distance to end
        for(Node baseLayerNode : spaceTimeGrid.getBaseLayer().getNodeListWithoutObstacles()) {
            for(int i = 0; i < spaceTimeGrid.getMaxTime(); i++) {
                try {
                    spaceTimeGrid.getNodePointer(baseLayerNode.getX(), baseLayerNode.getY(), i).setDistanceToEnd(baseLayerNode.getDistanceToEnd());
                } catch (NodeDoesNotExistException ignored) {
                    //This happens in cases where one of the nodes have been removed by an earlier route
                }
            }
        }
    }
}
