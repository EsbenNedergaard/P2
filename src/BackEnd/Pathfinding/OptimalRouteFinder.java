package BackEnd.Pathfinding;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import Exceptions.RouteNotPossibleException;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Geometry.PickingPoint;

import java.util.ArrayList;
import java.util.List;

public class OptimalRouteFinder {
    private final int WAIT_TIME_BETWEEN_ROUTES = 3;
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

    public OptimalRouteFinder(SpaceTimeGrid grid, Point2D routeStartPoint, Point2D routeEndPoint) {
        this.spaceTimeGrid = grid;
        this.routeStartPoint = routeStartPoint;
        this.routeEndPoint = routeEndPoint;
        this.reset();
    }

    public void reset() {
        this.pathFinder = new PathFinder(new SpaceTimeGrid(this.spaceTimeGrid.getBaseLayer(), this.spaceTimeGrid.getMaxTime()));
        this.amountPickersInGraph = 0;
    }

    //Method that calculates the best route for the pickingList that it is given
    public List<Node> calculateBestRoute(List<PickingPoint> pickingList) {
        this.bestRoute = new PickingRoute();
        PickingRoute initialRoute = new PickingRoute();
        bestRouteOfAllRoutes(routeStartPoint, pickingList, initialRoute);

        PickingRoute routeToRemove = new PickingRoute(bestRoute);


        /*Here we remove the last 5 points that are added because the algorithm thinks it needs to pick
          a ware in the routeEndPoint, which is not right we remove one extra because for know we let
          the pickers stack up in the end point */
        //for(int i = 0; i < pathFinder.getPICK_TIME() + 1; i++) {
            routeToRemove.getRoute().remove(routeToRemove.getRouteLength() - 1);

        //}
        routeToRemove.getRoute().remove(0);
        //TODO: få lavet så SpaceTimeGrid tager en pickingRoute i stedet.
        pathFinder.getSpaceTimeGrid().removeRoute(routeToRemove.getRoute());


        PickingRoute waitTime = new PickingRoute();
        for(int i = 0; i < amountPickersInGraph *WAIT_TIME_BETWEEN_ROUTES; i++) {
            waitTime.addNodeToRoute(new Node(routeStartPoint));
        }


        //TODO: Få ændret herfra og ned og dermed ændret retur typen
        List<Node> fullRoute = new ArrayList<>();
        fullRoute.addAll(waitTime.getRoute());
        fullRoute.addAll(bestRoute.getRoute());
        amountPickersInGraph++;
        return fullRoute;
    }



    /*Our recursive function that calls itself with a smaller and smaller version of the list of remaining pick points
    * and a bigger currRoute plus a new start point*/
    private void bestRouteOfAllRoutes(Point2D currStart, List<PickingPoint> remainingPickingPoints, PickingRoute currRoute) {
        try {
            //Calculates the total time travelled so far
            int timeTravelledSinceStart = currRoute.getRouteLength() + amountPickersInGraph * WAIT_TIME_BETWEEN_ROUTES;

            //There are no more picking points to visit on the route
            if(remainingPickingPoints.isEmpty()) {
                //Adds the path from last picking point to delivery area
                currRoute.addOtherRoute(pathFinder.findShortestRoute(currStart, routeEndPoint, timeTravelledSinceStart).getRoute());

                //Checks if the new route is shorter than the previous found routes
                if(bestRoute.getRouteLength() == 0 || currRoute.getRouteLength() < bestRoute.getRouteLength()){
                    bestRoute = new PickingRoute(currRoute);
                }
            } else {
                //Iterates through all remaining picking points
                for (PickingPoint nextPickPoint : remainingPickingPoints) {
                    PickingRoute nextRoute = new PickingRoute(currRoute);
                    //Adds the path from current picking point to next picking point
                    nextRoute.addOtherRoute(pathFinder.findShortestRoute(currStart, nextPickPoint, timeTravelledSinceStart).getRoute());

                    //Adds time for picking on next pick point
                    nextRoute.addPickingToRouteEnd(pathFinder.getSpaceTimeGrid());

                    /*Creates a new list that doesn't include the (just added) next pick point, because we can't remove
                    * it from the list we are iterating through */
                    List<PickingPoint> nextList = new ArrayList<>(remainingPickingPoints);
                    nextList.remove(nextPickPoint);

                    //Function calls itself with remaining picking points to be visited
                    //Finds the next paths on the route
                    bestRouteOfAllRoutes(nextPickPoint, nextList, nextRoute);
                }
            }
        } catch (RouteNotPossibleException e) {
            System.out.println(e.getMessage());
        }
    }
}
