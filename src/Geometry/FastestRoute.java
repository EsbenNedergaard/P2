package Geometry;

import Exceptions.RouteNotPossibleException;

import java.util.ArrayList;
import java.util.List;

public class FastestRoute {
    private final Point2D startingPoint = new Node(new Point2D(0, 0));
    private List<Point2D> pickingList;
    private List<Node> bestRoute;
    private PathFinder path;


    public FastestRoute(List<Point2D> pickingList, SpaceTimeGrid grid) {
        this.pickingList = pickingList;
        this.path = new PathFinder(grid);
        this.bestRoute = new ArrayList<>();
    }

    public List<Node> getBestRoute(){
        return this.bestRoute;
    }

    public void calculateBestRoute() {
        List<Node> temp = new ArrayList<>();
        proc(startingPoint, pickingList, temp);
    }

    private void proc(Point2D currStart, List<Point2D> listRemaining, List<Node> currRoute) {
        try {
            if(listRemaining.isEmpty()) {
                int timeAfterRoute = currRoute.size();
                currRoute.addAll(path.findShortestRoute(currStart, startingPoint, timeAfterRoute));

                if(bestRoute.size() == 0 || currRoute.size() < bestRoute.size()){
                    bestRoute = currRoute;
                }
            } else {
                for (Point2D n : listRemaining) {
                    int timeAfterRoute = currRoute.size();
                    currRoute.addAll(path.findShortestRoute(currStart, n, timeAfterRoute));

                    List<Point2D> nextList = new ArrayList<>(listRemaining);
                    nextList.remove(0);
                    Point2D nextStart = currRoute.get(currRoute.size() - 1);
                    currRoute.remove(nextStart);
                    proc(nextStart, nextList, new ArrayList<>(currRoute));
                }
            }
        } catch (RouteNotPossibleException e) {
            System.out.println(e.getMessage());
        }
    }

     List<Node> getRouteThroughPickPoints(){
        List<Node> tempRoute = new ArrayList<>();
        Point2D currStart = startingPoint;
        try {
            for(Point2D endPoint : pickingList) {
                int timeAfterRoute = tempRoute.size();

                tempRoute.addAll(path.findShortestRoute(currStart, endPoint, timeAfterRoute));
                currStart = tempRoute.get(tempRoute.size() - 1);
                tempRoute.remove(currStart);
            }
        } catch (RouteNotPossibleException e) {
            System.out.println(e.getMessage());
        }
        return tempRoute;
    }
}
