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

    @Override
    PathFinder getPathFinder() {
        PathFinder fastestPathFinder = new FastestPathFinder(new SpaceTimeGrid(super.getSpaceTimeGrid().getBaseLayer(), super.getSpaceTimeGrid().getMaxTime()));
        return fastestPathFinder;
    }
}
