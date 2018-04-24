package BackEnd.Pathfinding;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.SpaceTimeGrid;

import java.util.ArrayList;
import java.util.List;

public class PickingRoute {
    private int travelTime,
        totalPickTime;
    private List<Node> route;
    private List<Point2D> productPoints;
    private int PICK_TIME = 5;

    public PickingRoute() {
        travelTime = 0;
        totalPickTime = 0;
        route = new ArrayList<>();
        productPoints = new ArrayList<>();
    }


    public PickingRoute(PickingRoute another) {
        travelTime = another.getTravelTime();
        totalPickTime = another.getTotalPickTime();
        route = new ArrayList<>(another.getRoute());
        productPoints = new ArrayList<>(another.getProductPoints());
    }

    public int getTravelTime() {
        return travelTime;
    }

    public int getTotalPickTime() {
        return totalPickTime;
    }

    public List<Point2D> getProductPoints() {
        return productPoints;
    }

    public List<Node> getRoute() {
        return route;
    }

    public void addNodeToRoute(Node n) {
        route.add(n);
    }

    public void addOtherRouteToRoute(PickingRoute another) {
        route.addAll(another.getRoute());
    }

    public void addProductPoint(Point2D productPoint) {
        productPoints.add(productPoint);
    }

    public void addPickingToRouteEnd(SpaceTimeGrid spaceTimeGrid) {
        Node pickPoint = route.get(route.size() - 1);

        for(int i = 0; i < PICK_TIME; i++) {
            route.add(spaceTimeGrid.getNodePointer(pickPoint.getX(), pickPoint.getY(), (pickPoint.getTime() + i) + 1));
            totalPickTime++;
        }

    }

    public int getRouteLength() {
        return route.size();
    }

}
