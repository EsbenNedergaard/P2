package Geometry;

import Exceptions.RouteNotPossibleException;

import java.util.List;

public class FastestRoute {
    private final Node startNode = new Node(new Point2D(0, 0));
    private List<Point2D> pickingList;
    private SpaceTimeGrid grid;
    private List<Node> fastestRoute;
    private List<Node> tempFastestRoute;

    public FastestRoute(List<Point2D> pickingList, SpaceTimeGrid grid) {
        this.pickingList = pickingList;
        this.grid = grid;
        fastestRoute.add(startNode);
    }

    public List<Node> getFastestRoute(){

        PathFinder path = new PathFinder(grid);
        Node endNode = new Node(pickingList.get(0));



        try {
            int listSize = pickingList.size();
            for(int i = 0; i < listSize; i++) {
                tempFastestRoute = path.findShortestRoute(startNode, new Node(pickingList.get(i)), 0);
                if(tempFastestRoute.size() < fastestRoute.size()){
                    fastestRoute = tempFastestRoute;
                }
            }
            fastestRoute.add(startNode);

        } catch (RouteNotPossibleException e) {
            e.getMessage();
        }



        return fastestRoute;
    }
}
