package BackEnd.Pathfinding.RouteFinders;

import BackEnd.Geometry.Point2D;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.PathFinders.ShortestPathFinder;

public class ShortestRouteFinder extends RouteFinder {
    public ShortestRouteFinder(SpaceTimeGrid grid, Point2D routeStartPoint, Point2D routeEndPoint) {
        super(grid, routeStartPoint, routeEndPoint);
    }

    @Override
    PathFinder getPathFinder() {
        PathFinder shortestPathFinder = new ShortestPathFinder(new SpaceTimeGrid(super.getSpaceTimeGrid().getBaseLayer(), super.getSpaceTimeGrid().getMaxTime()));
        return shortestPathFinder;
    }
}
