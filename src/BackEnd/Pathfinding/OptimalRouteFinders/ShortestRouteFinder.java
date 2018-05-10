package BackEnd.Pathfinding.OptimalRouteFinders;

import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.PathFinders.ShortestPathFinder;

public class ShortestRouteFinder extends OptimalRouteFinder {
    public ShortestRouteFinder(SpaceTimeGrid grid) {
        super(grid);
    }

    @Override
    PathFinder getPathFinder() {
        PathFinder shortestPathFinder = new ShortestPathFinder(new SpaceTimeGrid(super.getSpaceTimeGrid().getBaseLayer(), super.getSpaceTimeGrid().getMaxTime()));
        return shortestPathFinder;
    }
}
