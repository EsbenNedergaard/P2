package Geometry;

import Exceptions.RouteNotPossibleException;

import java.util.ArrayList;
import java.util.List;

public class FastestRoute {
    private final Point2D startingPoint = new Node(new Point2D(0, 0));
    private List<Node> bestRoute;
    private PathFinder path;


    public FastestRoute(SpaceTimeGrid grid) {
        this.path = new PathFinder(grid);
    }

    public List<Node> calculateBestRoute(List<Point2D> pickingList) {
        this.bestRoute = new ArrayList<>();
        List<Node> temp = new ArrayList<>();

        proc(startingPoint, pickingList, temp);

        return this.bestRoute;
    }

    private void proc(Point2D currStart, List<Point2D> listRemaining, List<Node> currRoute) {
        try {
            if(listRemaining.isEmpty()) {
                int timeAfterRoute = currRoute.size();
                currRoute.addAll(path.findShortestRoute(currStart, startingPoint, timeAfterRoute));

                if(bestRoute.size() == 0 || currRoute.size() < bestRoute.size()){
                    bestRoute = new ArrayList<>(currRoute);
                }
            } else {
                for (Point2D n : listRemaining) {
                    int timeAfterRoute = currRoute.size();
                    List<Node> nextRoute = new ArrayList<>(currRoute);
                    nextRoute.addAll(path.findShortestRoute(currStart, n, timeAfterRoute));

                    List<Point2D> nextList = new ArrayList<>(listRemaining);
                    nextList.remove(n);
                    //Point2D nextStart = currRoute.get(currRoute.size() - 1);
                    //currRoute.remove(nextStart);
                    proc(n, nextList, nextRoute);
                }
            }
        } catch (RouteNotPossibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void testFakulitet(List<Integer> integerList){
        List<Integer> collectedList = new ArrayList<>();

        testProcedure(integerList, collectedList);
    }

    private void testProcedure(List<Integer> integerList, List<Integer> collectedList) {
        if(integerList.isEmpty()) {
            for(Integer i : collectedList) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }
        for(Integer i : integerList) {
            List<Integer> newList = new ArrayList<>(integerList);
            newList.remove(i);

            List<Integer> newCollectedList = new ArrayList<>();
            newCollectedList.addAll(collectedList);
            newCollectedList.add(i);
            testProcedure(newList, newCollectedList);
        }
    }

     List<Node> getRouteThroughPickPoints(List<Point2D> pickingList){
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
