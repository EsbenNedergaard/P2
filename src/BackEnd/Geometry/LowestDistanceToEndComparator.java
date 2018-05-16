package BackEnd.Geometry;

import java.util.Comparator;

public class LowestDistanceToEndComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        int heuristicDiff = o1.getDistanceToEnd() - o2.getDistanceToEnd();

        if (heuristicDiff != 0) {
            return heuristicDiff;
        }

        int distanceDiff = o1.getTotalDistance() - o2.getTotalDistance();
        if (distanceDiff != 0) {
            return distanceDiff;
        }
        return o2.getY() - o1.getY();
    }
}
