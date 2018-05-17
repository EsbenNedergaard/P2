package BackEnd.Pathfinding;

import BackEnd.Geometry.Node.Node;
import BackEnd.Geometry.PickingPoint;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.SpaceTimeGrid;
import Exceptions.ShortestRouteNotCalculatedException;

import java.util.ArrayList;
import java.util.List;


//TODO: måske skelne mellem WaitTime og TravelTime, og dette routeLenght op i disse 2.
public class PickingRoute {
    private int totalPickTime;
    private List<Node> route;
    private List<PickingPoint> pickingPoints;
    private PickingRoute shortestRoute;


    public PickingRoute() {
        totalPickTime = 0;
        route = new ArrayList<>();
        pickingPoints = new ArrayList<>();
    }

    public PickingRoute(PickingRoute another) {
        totalPickTime = another.getTotalPickTime();
        route = new ArrayList<>(another.getRoute());
        pickingPoints = new ArrayList<>(another.getPickingPoints());
    }

    public int getRouteLength() {
        return route.size();
    }

    public int getTotalPickTime() {
        return totalPickTime;
    }

    public List<PickingPoint> getPickingPoints() {
        return pickingPoints;
    }

    public List<Node> getRoute() {
        return route;
    }

    public List<Point2D> getProductPoints() {
        List<Point2D> productPoints = new ArrayList<>();
        //We run through all the products and get the product positions.
        for (PickingPoint pickingPoint : this.getPickingPoints()) {
            productPoints.add(pickingPoint.getProduct().getProductPosition());
        }

        return productPoints;
    }

    public void addNodeToRoute(Node n) {
        route.add(n);
    }

    public void addOtherRoute(PickingRoute another) {
        route.addAll(another.getRoute());
    }

    public void addPickPoint(PickingPoint pickingPoint) {
        pickingPoints.add(pickingPoint);
    }

    public void addPickingToRouteEnd(SpaceTimeGrid spaceTimeGrid, int pickTime) {
        Node pickPoint = route.get(route.size() - 1);
        for (int i = 0; i < pickTime; i++) {
            route.add(spaceTimeGrid.getNodePointer(pickPoint.getX(), pickPoint.getY(), (pickPoint.getTime() + i) + 1));
            totalPickTime++;
        }
    }

    public void addStartTime(int startTime) {
        Node routeStartPoint = route.get(0);

        /*These are added as just Nodes, that are not part of the SpaceTimeGrid, because the GUI, needs them
          to start the pickers at different times because it does not look at their time but only x and y */
        List<Node> waitTime = new ArrayList<>();
        for (int i = 0; i < startTime; i++) {
            waitTime.add(new Node(routeStartPoint));
        }

        List<Node> fullRoute = new ArrayList<>();
        fullRoute.addAll(waitTime);
        fullRoute.addAll(this.route);

        this.route = fullRoute;
    }

    public PickingRoute getShortestRoute() {
        if(this.shortestRoute == null) {
            throw new ShortestRouteNotCalculatedException("We have not calculated the shortest route yet");
        }
        return shortestRoute;
    }

    public void setShortestRoute(PickingRoute shortestRoute) {
        this.shortestRoute = shortestRoute;
    }
}
