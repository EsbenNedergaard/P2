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
    private int amtPickersInGraph;


    public OptimalRouteFinder(SpaceTimeGrid grid) {
        this.pathFinder = new PathFinder(grid);
        this.spaceTimeGrid = grid;
        this.routeStartPoint = new Node(new Point2D(0, 5));
        this.routeEndPoint = new Node(new Point2D(0, 6));
        this.amtPickersInGraph = 0;
    }


    public OptimalRouteFinder(SpaceTimeGrid grid, Point2D routeStartPoint, Point2D routeEndPoint) {
        this.pathFinder = new PathFinder(grid);
        this.spaceTimeGrid = grid;
        this.routeStartPoint = routeStartPoint;
        this.routeEndPoint = routeEndPoint;
        this.amtPickersInGraph = 0;
    }

    public void reset() {
        this.pathFinder = new PathFinder(spaceTimeGrid);
        this.amtPickersInGraph = 0;
    }

    //Method that calculates the best route for the pickingList that it is given
    public List<Node> calculateBestRoute(List<Point2D> pickingList) {
        this.bestRoute = new ArrayList<>();
        List<Node> temp = new ArrayList<>();

        bestRouteOfAllRoutes(routeStartPoint, pickingList, temp);
        pathFinder.getSpaceTimeGrid().removeRoute(this.bestRoute);
        amtPickersInGraph++;
        return this.bestRoute;
    }



    /*Our recursive function that calls itself with a smaller and smaller version of the list of remaining pick points
    * and a bigger currRoute plus a new start point*/
    private void bestRouteOfAllRoutes(Point2D currStart, List<Point2D> remainingPickingPoints, List<Node> currRoute) {
        try {
            if(remainingPickingPoints.isEmpty()) {
                int timeAfterRoute = currRoute.size() + amtPickersInGraph * WAIT_TIME_BETWEEN_ROUTES;
                currRoute.addAll(pathFinder.findShortestRoute(currStart, routeEndPoint, timeAfterRoute));
                if(bestRoute.size() == 0 || currRoute.size() < bestRoute.size()){
                    bestRoute = new ArrayList<>(currRoute);
                }
            } else {
                for (Point2D n : remainingPickingPoints) {
                    int timeAfterRoute = currRoute.size() + amtPickersInGraph * WAIT_TIME_BETWEEN_ROUTES;
                    List<Node> nextRoute = new ArrayList<>(currRoute);
                    nextRoute.addAll(pathFinder.findShortestRoute(currStart, n, timeAfterRoute));

                    List<Point2D> nextList = new ArrayList<>(remainingPickingPoints);
                    nextList.remove(n);

                    //Adding picking time
                    for(int i = 0; i < PICKING_TIME - 1; i++) {
                        Node previousNode = nextRoute.get(nextRoute.size() - 1);
                        Node waitPoint = pathFinder.getSpaceTimeGrid().getNodePointer(previousNode.getX(), previousNode.getY(), previousNode.getTime() + 1);
                        nextRoute.add(waitPoint);
                    }

                    bestRouteOfAllRoutes(n, nextList, nextRoute);
                }
            }
        } catch (RouteNotPossibleException e) {
            System.out.println(e.getMessage());
        }
    }
}
