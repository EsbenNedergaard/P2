package BackEnd.Pathfinding.OptimalRouteFinders;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.PickingRoute;
import Exceptions.RouteNotPossibleException;

import java.util.ArrayList;
import java.util.List;

public abstract class OptimalRouteFinder {
    private final int WAIT_TIME_BETWEEN_PICKERS = 3;
    private int startTime;
    private SpaceTimeGrid spaceTimeGrid;
    private Point2D routeStartPoint;
    private Point2D routeEndPoint;
    private PickingRoute bestRoute;
    private PathFinder pathFinder;
    private int amountPickersInGraph;


    public OptimalRouteFinder(SpaceTimeGrid grid) {
        this.spaceTimeGrid = grid;
        this.routeStartPoint = new Node(new Point2D(0, 5));
        this.routeEndPoint = new Node(new Point2D(0, 6));
        this.reset();
    }

    public void reset() {
        this.pathFinder = this.getPathFinder();
        this.amountPickersInGraph = 0;
        this.startTime = 0;
    }

    abstract PathFinder getPathFinder();

    public SpaceTimeGrid getSpaceTimeGrid() {
        return spaceTimeGrid;
    }

    //TODO: problemer med picking points bliver lavet om når vi har 700 - 970
    //Method that calculates the best route for the pickingList that it is given
    public PickingRoute calculateBestRoute(List<PickingPoint> pickingList) {
        this.startTime = amountPickersInGraph * WAIT_TIME_BETWEEN_PICKERS;
        this.bestRoute = new PickingRoute();
        PickingRoute initialRoute = new PickingRoute();
        bestRouteOfAllRoutes(routeStartPoint, pickingList, initialRoute);

        //TODO: få lavet så SpaceTimeGrid tager en pickingRoute i stedet.
        pathFinder.getSpaceTimeGrid().removeRoute(bestRoute.getRoute());

        //Here we add the initial start time between the routes
        bestRoute.addStartTime(startTime);

        amountPickersInGraph++;
        return bestRoute;
    }



    /*Our recursive function that calls itself with a smaller and smaller version of the list of remaining pick points
     * and a bigger currRoute plus a new start point*/
    private void bestRouteOfAllRoutes(Point2D currStart, List<PickingPoint> remainingPickingPoints, PickingRoute currRoute) throws RouteNotPossibleException {
        int timeTravelledSinceStart = currRoute.getRouteLength() + startTime;

        //There are no more picking points to visit on the route
        if(remainingPickingPoints.isEmpty()) {
            //Adds the path from last picking point to delivery area
            currRoute.addOtherRoute(pathFinder.findFastestPath(currStart, routeEndPoint, timeTravelledSinceStart).getRoute());

            //Checks if the new route is shorter than the previous found routes
            if(bestRoute.getRouteLength() == 0 || currRoute.getRouteLength() < bestRoute.getRouteLength()){
                bestRoute = new PickingRoute(currRoute);
            }
        } else {
            //Iterates through all remaining picking points
            for (PickingPoint nextPickPoint : remainingPickingPoints) {
                PickingRoute nextRoute = new PickingRoute(currRoute);
                //Adds the path from current picking point to next picking point
                nextRoute.addOtherRoute(pathFinder.findFastestPath(currStart, nextPickPoint, timeTravelledSinceStart, nextPickPoint.getPickTime()).getRoute());

                //Adds time for picking on next pick point
                nextRoute.addPickingToRouteEnd(pathFinder.getSpaceTimeGrid(), nextPickPoint.getPickTime());
                //Then we add the pick point to this routes list
                nextRoute.addPickPoint(nextPickPoint);

                /*Creates a new list that doesn't include the (just added) next pick point, because we can't remove
                 * it from the list we are iterating through */
                List<PickingPoint> nextList = new ArrayList<>(remainingPickingPoints);
                nextList.remove(nextPickPoint);

                //Function calls itself with remaining picking points to be visited
                //Finds the next paths on the route
                bestRouteOfAllRoutes(nextPickPoint, nextList, nextRoute);
            }
        }
    }
}
