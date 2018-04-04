package Geometry;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        if (o1.getTotalDistance() - o2.getTotalDistance() != 0) {
            return o1.getTotalDistance() - o2.getTotalDistance();
        }
        if (o1.getX() - o2.getX() != 0) {
            return o1.getX() - o2.getX();
        }
        return o1.getY() - o2.getY();
    }
}
