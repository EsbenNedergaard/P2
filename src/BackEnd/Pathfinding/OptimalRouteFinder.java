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
        List<Node> initialRoute = new ArrayList<>();
        bestRouteOfAllRoutes(routeStartPoint, pickingList, initialRoute);

        for(int i = 0; i < pathFinder.getPICK_TIME(); i++) {
            bestRoute.remove(bestRoute.size() -1);
        }
        List<Node> routeToRemove = new ArrayList<>(bestRoute);
        routeToRemove.remove(0);
        pathFinder.getSpaceTimeGrid().removeRoute(routeToRemove);

        List<Node> waitTime = new ArrayList<>();
        for(int i = 0; i < amountPickersInGraph *WAIT_TIME_BETWEEN_ROUTES; i++) {
            waitTime.add(new Node(routeStartPoint));
        }
        List<Node> fullRoute = new ArrayList<>();
        fullRoute.addAll(waitTime);
        fullRoute.addAll(bestRoute);
        amountPickersInGraph++;
        return fullRoute;
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
