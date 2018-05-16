package BackEnd.Pathfinding.RouteFinders;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Pathfinding.PickingRoute;

import java.util.List;

public interface RouteFinder {
    int WAIT_TIME_BETWEEN_PICKERS = 3;
    void reset();
    PickingRoute calculateRoute(List<PickingPoint> pickingList);
}
