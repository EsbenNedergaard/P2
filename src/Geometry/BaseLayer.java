package Geometry;

import java.util.ArrayList;
import java.util.List;

public class BaseLayer {
    private List<Node> nodeList = new ArrayList<>();
    private List<Node> stationaryObstacles = new ArrayList<>();
    private int maxX;
    private int maxY;

    public BaseLayer(List<Node> nodeList) {
        maxX = 0;
        maxY = 0;
        for (Node n : nodeList) {
            /* We check if we have found a higher x or y value */
            if (maxX < n.getX()) {
                maxX = n.getX();
            }
            if (maxY < n.getY()) {
                maxY = n.getY();
            }

            if (n.isObstacle()) {
                stationaryObstacles.add(n);
            }
            else {
                this.nodeList.add(n);
            }
        }
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public List<Node> getStationaryObstacles() {
        return stationaryObstacles;
    }
}
