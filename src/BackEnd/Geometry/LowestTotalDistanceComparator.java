package BackEnd.Geometry;

import java.util.Comparator;

public class LowestTotalDistanceComparator implements Comparator<Node> {

    // Compares two nodes by lowest total distance first, lowest distance to end secondly, and highest y value finally
    @Override
    public int compare(Node o1, Node o2) {
        int distanceDiff = o1.getTotalDistance() - o2.getTotalDistance();

        if (distanceDiff == 0) {
            int heuristicDiff = o1.getDistanceToEnd() - o2.getDistanceToEnd();

            if (heuristicDiff == 0) {
                return o2.getY() - o1.getY();
            }
            return heuristicDiff;
        }
        return distanceDiff;
    }
}