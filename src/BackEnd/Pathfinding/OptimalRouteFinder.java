package BackEnd.Pathfinding;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import Exceptions.RouteNotPossibleException;
import BackEnd.Graph.SpaceTimeGrid;

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
    public List<Node> calculateBestRoute(List<Point2D> pickingList) {
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
    private void bestRouteOfAllRoutes(Point2D currStart, List<Point2D> remainingPickingPoints, PickingRoute currRoute) {
        try {
            if(remainingPickingPoints.isEmpty()) {
                int timeAfterRoute = currRoute.getRouteLength() + amountPickersInGraph * WAIT_TIME_BETWEEN_ROUTES;
                currRoute.addOtherRouteToRoute(pathFinder.findShortestRoute(currStart, routeEndPoint, timeAfterRoute));
                if(bestRoute.getRouteLength() == 0 || currRoute.getRouteLength() < bestRoute.getRouteLength()){
                    bestRoute = new PickingRoute(currRoute);
                }
            } else {
                for (Point2D n : remainingPickingPoints) {
                    int timeAfterRoute = currRoute.getRouteLength() + amountPickersInGraph * WAIT_TIME_BETWEEN_ROUTES;
                    PickingRoute nextRoute = new PickingRoute(currRoute);
                    nextRoute.addOtherRouteToRoute(pathFinder.findShortestRoute(currStart, n, timeAfterRoute));

                    nextRoute.addPickingToRouteEnd(pathFinder.getSpaceTimeGrid());

                    List<Point2D> nextList = new ArrayList<>(remainingPickingPoints);
                    nextList.remove(n);

                    bestRouteOfAllRoutes(n, nextList, nextRoute);
                }
            }
        } catch (RouteNotPossibleException e) {
            System.out.println(e.getMessage());
        }
    }
}
