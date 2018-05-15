package BackEnd.Graph;

import BackEnd.Geometry.Node;

import java.util.ArrayList;
import java.util.List;

public class BaseLayer {
    private List<Node> nodeList = new ArrayList<>();
    private List<Node> stationaryObstacles = new ArrayList<>();

    public BaseLayer(List<Node> nodeList) {
        for (Node n : nodeList) {
            if (n.isObstacle()) {
                stationaryObstacles.add(n);
            }
            else {
                this.nodeList.add(n);
            }
        }
        
        setBaseLayerNeighbours();
    }

    private void setBaseLayerNeighbours() {
        for (Node node : nodeList) {
            node.setBaseLayerNeighbours(nodeList);
        }
    }

    public List<Node> getNodeListWithoutObstacles() {
        return nodeList;
    }

    public List<Node> getStationaryObstacles() {
        return stationaryObstacles;
    }
}
