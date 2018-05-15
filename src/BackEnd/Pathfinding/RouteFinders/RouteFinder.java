package BackEnd.Pathfinding.RouteFinders;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.PathFinders.FastestPathFinder;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.PathFinders.ShortestPathFinder;
import BackEnd.Pathfinding.PickingRoute;
import Exceptions.RouteNotPossibleException;

import java.util.ArrayList;
import java.util.List;

public class RouteFinder {
    private final int WAIT_TIME_BETWEEN_PICKERS = 3;
    private int startTime;
    private Point2D routeStartPoint;
    private Point2D routeEndPoint;
    private PathFinder pathFinder;
    private int amountPickersInGraph;


    public RouteFinder(SpaceTimeGrid grid, Point2D routeStartPoint, Point2D routeEndPoint) {
        this.routeStartPoint = new Node(routeStartPoint);
        this.routeEndPoint = new Node(routeEndPoint);
        this.pathFinder = new FastestPathFinder(grid);
        this.amountPickersInGraph = 0;
        this.startTime = 0;
    }

    public RouteFinder(PathFinder pathFinder, Point2D routeStartPoint, Point2D routeEndPoint) {
        this.routeStartPoint = new Node(routeStartPoint);
        this.routeEndPoint = new Node(routeEndPoint);
        this.pathFinder = pathFinder;
        this.amountPickersInGraph = 0;
        this.startTime = 0;
    }

    public void reset() {
        this.pathFinder.resetSpaceTimeGrid();
        this.amountPickersInGraph = 0;
        this.startTime = 0;
    }

    //Method that calculates the best route for the pickingList that it is given
    public PickingRoute calculateBestRoute(List<PickingPoint> pickingList) {
        this.startTime = amountPickersInGraph * WAIT_TIME_BETWEEN_PICKERS;
        PickingRoute bestRoute = new PickingRoute();
        bestRoute = bestRouteOfAllRoutes(routeStartPoint, pickingList, bestRoute);


        //TODO: få lavet så SpaceTimeGrid tager en pickingRoute i stedet.
        pathFinder.getSpaceTimeGrid().removeRoute(bestRoute.getRoute());

        //Here we add the initial start time between the routes
        bestRoute.addStartTime(startTime);

        amountPickersInGraph++;
        return bestRoute;
    }


    /*Our recursive function that calls itself with a smaller and smaller version of the list of remaining pick points
     * and a bigger currRoute plus a new start point*/
    private PickingRoute bestRouteOfAllRoutes(Point2D currPosition, List<PickingPoint> remainingPickingPoints, PickingRoute currRoute) throws RouteNotPossibleException {
        PickingRoute bestRoute = new PickingRoute();

        if (remainingPickingPoints.isEmpty()) {
            //Adds the path from last picking point to delivery area
            addFinalPathToRoute(currRoute, currPosition);
            return currRoute;
        }
        for (PickingPoint nextPickPoint : remainingPickingPoints) { //Iterates through all remaining picking points
            PickingRoute newRoute = new PickingRoute(currRoute);
            //Adds the path from current picking point to next picking point
            addPathToRoute(newRoute, currPosition, nextPickPoint);
            addPickingTimeToRoute(newRoute, nextPickPoint);

            /*Creates a new list that doesn't include the (just added) next pick point, because we can't remove
             * it from the list we are iterating through */
            List<PickingPoint> nextList = getNextList(remainingPickingPoints, nextPickPoint);

            //Function calls itself with remaining picking points to be visited
            newRoute = bestRouteOfAllRoutes(nextPickPoint, nextList, newRoute);

            //Found route is the fastest found so far
            if (newRouteIsBestRoute(newRoute, bestRoute)) {
                bestRoute = new PickingRoute(newRoute);
            }
        }
        return bestRoute;
    }

    private boolean newRouteIsBestRoute (PickingRoute newRoute, PickingRoute bestRoute) {
        return newRoute.getRouteLength() < bestRoute.getRouteLength() || bestRoute.getRouteLength() == 0;
    }

    private void addPathToRoute (PickingRoute newRoute, Point2D currPosition, PickingPoint nextPickPoint){
        int timeTravelledSinceStart = newRoute.getRouteLength() + startTime;
        newRoute.addOtherRoute(pathFinder.findFastestPath(currPosition, nextPickPoint, timeTravelledSinceStart, nextPickPoint.getPickTime()));
    }

    private void addFinalPathToRoute (PickingRoute newRoute, Point2D currPosition){
        int timeTravelledSinceStart = newRoute.getRouteLength() + startTime;
        newRoute.addOtherRoute(pathFinder.findFastestPath(currPosition, routeEndPoint, timeTravelledSinceStart));
    }

    private void addPickingTimeToRoute(PickingRoute newRoute, PickingPoint nextPickPoint) {
        newRoute.addPickingToRouteEnd(pathFinder.getSpaceTimeGrid(), nextPickPoint.getPickTime());
        //Then we add the pick point to this routes list
        newRoute.addPickPoint(nextPickPoint);
    }

    private ArrayList<PickingPoint> getNextList (List<PickingPoint> remainingPickingPoints, PickingPoint nextPickPoint) {
        ArrayList<PickingPoint> nextList = new ArrayList<>(remainingPickingPoints);
        nextList.remove(nextPickPoint);
        return nextList;
    }
}

