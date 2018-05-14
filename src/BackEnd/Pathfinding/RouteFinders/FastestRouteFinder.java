package BackEnd.Pathfinding.RouteFinders;

import BackEnd.Geometry.Point2D;
import BackEnd.Pathfinding.PathFinders.FastestPathFinder;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Graph.SpaceTimeGrid;

public class FastestRouteFinder extends RouteFinder {
    public FastestRouteFinder(SpaceTimeGrid grid, Point2D routeStartPoint, Point2D routeEndPoint) {
        super(grid, routeStartPoint, routeEndPoint);
    }

    @Override
    PathFinder getPathFinder() {
        PathFinder fastestPathFinder = new FastestPathFinder(new SpaceTimeGrid(super.getSpaceTimeGrid().getBaseLayer(), super.getSpaceTimeGrid().getMaxTime()));
        return fastestPathFinder;
    }
}
