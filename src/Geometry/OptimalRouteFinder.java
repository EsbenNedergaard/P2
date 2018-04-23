package Geometry;

import Exceptions.RouteNotPossibleException;

import java.util.ArrayList;
import java.util.List;

public class OptimalRouteFinder {
    private final int PICKING_TIME = 15;
    private final int WAIT_TIME_BETWEEN_ROUTES = 3;
    private SpaceTimeGrid spaceTimeGrid;
    private Point2D routeStartPoint;
    private Point2D routeEndPoint;
    private List<Node> bestRoute;
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
        this.bestRoute = new ArrayList<>();
        //TODO: FIX THIS RIGHT HERE, med at sætte punkter ind i starten er noget med vi skal tage, hvor meget vi fjerner til sidst
        //for(int i = 0; i < amountPickersInGraph * WAIT_TIME_BETWEEN_ROUTES; i++) {
            //bestRoute.add(pathFinder.getSpaceTimeGrid().getNodePointer(routeStartPoint.getX(), routeStartPoint.getY(), i));
        //}

        List<Node> temp = new ArrayList<>();

        bestRouteOfAllRoutes(routeStartPoint, pickingList, temp);

        List<Node> routeToRemove = new ArrayList<>(bestRoute);
        routeToRemove.remove(0);
        pathFinder.getSpaceTimeGrid().removeRoute(routeToRemove);

        List<Node> waitTime = new ArrayList<>();
        for(int i = 0; i < amountPickersInGraph *WAIT_TIME_BETWEEN_ROUTES; i++) {
            waitTime.add(new Node(routeStartPoint));
        }
        waitTime.addAll(bestRoute);
        amountPickersInGraph++;
        return waitTime;
    }



    /*Our recursive function that calls itself with a smaller and smaller version of the list of remaining pick points
    * and a bigger currRoute plus a new start point*/
    private void bestRouteOfAllRoutes(Point2D currStart, List<Point2D> remainingPickingPoints, List<Node> currRoute) {
        try {
            if(remainingPickingPoints.isEmpty()) {
                int timeAfterRoute = currRoute.size() + amountPickersInGraph * WAIT_TIME_BETWEEN_ROUTES;
                currRoute.addAll(pathFinder.findShortestRoute(currStart, routeEndPoint, timeAfterRoute));
                if(bestRoute.size() == 0 || currRoute.size() < bestRoute.size()){
                    bestRoute = new ArrayList<>(currRoute);
                }
            } else {
                for (Point2D n : remainingPickingPoints) {
                    int timeAfterRoute = currRoute.size() + amountPickersInGraph * WAIT_TIME_BETWEEN_ROUTES;
                    List<Node> nextRoute = new ArrayList<>(currRoute);
                    nextRoute.addAll(pathFinder.findShortestRoute(currStart, n, timeAfterRoute));

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
