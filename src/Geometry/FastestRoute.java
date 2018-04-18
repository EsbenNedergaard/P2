package Geometry;

import Exceptions.RouteNotPossibleException;

import java.util.ArrayList;
import java.util.List;

public class FastestRoute {
    private final Node startNode = new Node(new Point2D(0, 0));
    private List<Point2D> pickingList;
    private PathFinder path;


    public FastestRoute(List<Point2D> pickingList, SpaceTimeGrid grid) {
        this.pickingList = pickingList;
        this.path = new PathFinder(grid);
    }

    public List<Node> getFastestRoute(){
        List<Node> fastestRoute = new ArrayList<>();

        List<Node> tempRoute = this.getRouteThroughPickPoints();
        if(fastestRoute.size() == 0 || tempRoute.size() < fastestRoute.size()){
            fastestRoute = tempRoute;
        }
        return fastestRoute;
    }

     List<Node> getRouteThroughPickPoints(){
        List<Node> tempRoute = new ArrayList<>();
        Node currStart = startNode;
        try {
            for(Point2D endPoint : pickingList) {
                tempRoute.addAll(path.findShortestRoute(currStart, new Node(endPoint), tempRoute.size()));
                currStart = tempRoute.get(tempRoute.size() - 1);
                tempRoute.remove(currStart);
            }
        } catch (RouteNotPossibleException e) {
            System.out.println(e.getMessage());
        }
        return tempRoute;
    }
}
