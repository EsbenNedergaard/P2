package BackEnd.Pathfinding.OptimalRouteFinders;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import BackEnd.Pathfinding.PathFinders.FastestPathFinder;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.PickingRoute;
import Exceptions.RouteNotPossibleException;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Geometry.PickingPoint;

import java.util.ArrayList;
import java.util.List;

public class FastestRouteFinder extends OptimalRouteFinder {
    public FastestRouteFinder(SpaceTimeGrid grid) {
        super(grid);
    }

    public FastestRouteFinder(SpaceTimeGrid grid, Point2D routeStartPoint, Point2D routeEndPoint) {
        super(grid, routeStartPoint, routeEndPoint);
    }

    @Override
    public void reset() {
        PathFinder fastestPathFinder = new FastestPathFinder(new SpaceTimeGrid(super.getSpaceTimeGrid().getBaseLayer(), super.getSpaceTimeGrid().getMaxTime()));
        super.setPathFinder(fastestPathFinder);
        super.setAmountPickersInGraph(0);
        super.setStartTime(0);
    }
}
