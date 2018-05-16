package BackEnd.Pathfinding.RouteFinders;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Pathfinding.PickingRoute;

import java.util.List;

public interface RouteFinder {
    void reset();
    PickingRoute calculateRoute(List<PickingPoint> pickingList);
}
