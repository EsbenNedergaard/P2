package Geometry;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {

        int distanceDiff = o1.getTotalDistance() - o2.getTotalDistance();

        if(distanceDiff == 0){
            return o2.getY() - o1.getY();
        }

        return distanceDiff;
    }
}