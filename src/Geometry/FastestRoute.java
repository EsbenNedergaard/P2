package Geometry;

import Exceptions.RouteNotPossibleException;

import java.util.ArrayList;
import java.util.List;

public class FastestRoute {
    private final Point2D START_POINT = new Node(new Point2D(0, 5));
    private final Point2D END_POINT = new Node(new Point2D(0, 6));
    private List<Node> bestRoute;
    private PathFinder path;
    private final int PICKING_TIME = 5;


    public FastestRoute(SpaceTimeGrid grid) {
        this.path = new PathFinder(grid);
    }


    //Method that calculates the best route for the pickingList that it is given
    public List<Node> calculateBestRoute(List<Point2D> pickingList) {
        this.bestRoute = new ArrayList<>();
        List<Node> temp = new ArrayList<>();

        bestRouteOfAllRoutes(START_POINT, pickingList, temp);

        return this.bestRoute;
    }

    /*Our recursive function that calls itself with a smaller and smaller version of the list of remaining pick points
    * and a bigger currRoute plus a new start point*/
    private void bestRouteOfAllRoutes(Point2D currStart, List<Point2D> remainingPickingPoints, List<Node> currRoute) {
        try {
            if(remainingPickingPoints.isEmpty()) {
                int timeAfterRoute = currRoute.size();
                currRoute.addAll(path.findShortestRoute(currStart, END_POINT, timeAfterRoute));

                if(bestRoute.size() == 0 || currRoute.size() < bestRoute.size()){
                    bestRoute = new ArrayList<>(currRoute);
                }
            } else {
                for (Point2D n : remainingPickingPoints) {
                    int timeAfterRoute = currRoute.size();
                    List<Node> nextRoute = new ArrayList<>(currRoute);
                    nextRoute.addAll(path.findShortestRoute(currStart, n, timeAfterRoute));

                    List<Point2D> nextList = new ArrayList<>(remainingPickingPoints);
                    nextList.remove(n);

                    //Adding picking time
                    for(int i = 0; i < PICKING_TIME - 1; i++) {
                        Node previousNode = nextRoute.get(nextRoute.size() - 1);
                        Node waitPoint = path.getSpaceTimeGrid().getNodePointer(previousNode.getX(), previousNode.getY(), previousNode.getTime() + 1);
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
