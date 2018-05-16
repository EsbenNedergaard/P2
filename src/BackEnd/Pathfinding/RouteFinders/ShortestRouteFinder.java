package BackEnd.Pathfinding.RouteFinders;

import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.PickingRoute;

import java.util.ArrayList;
import java.util.List;

public class ShortestRouteFinder implements RouteFinder {
    private int startTime;
    private Point2D routeStartPoint;
    private Point2D routeEndPoint;
    private PathFinder pathFinder;
    private int amountPickersInGraph;

    public ShortestRouteFinder(Point2D routeStartPoint, Point2D routeEndPoint, PathFinder pathFinder) {
        this.routeStartPoint = routeStartPoint;
        this.routeEndPoint = routeEndPoint;
        this.pathFinder = pathFinder;
        this.amountPickersInGraph = 0;
        this.startTime = 0;
    }

    @Override
    public void reset() {
        this.pathFinder.resetSpaceTimeGrid();
        this.amountPickersInGraph = 0;
        this.startTime = 0;
    }

    @Override
    public PickingRoute calculateRoute(List<PickingPoint> pickingList) {
        //this.pathFinder.changeComparator(new LowestDistanceToEndComparator());


        //this.pathFinder.changeComparator(new LowestTotalDistanceComparator());
        return null;
    }

    private List<PickingPoint> findShortestPickList(List<PickingPoint> pickingPoints) {
        List<PickingPoint> pickingPointList = new ArrayList<>();


        return pickingPointList;
    }
}
