package BackEnd.Pathfinding;

import BackEnd.Geometry.Node;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Geometry.PickingPoint;

import java.util.ArrayList;
import java.util.List;


//TODO: måske skelne mellem WaitTime og TravelTime, og dette routeLenght op i disse 2.
public class PickingRoute {
    private int totalPickTime;
    private List<Node> route;
    private List<PickingPoint> pickingPoints;

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

    public void addNodeToRoute(Node n) {
        route.add(n);
    }

    public void addOtherRoute(List<Node> another) {
        route.addAll(another);
    }

    public void addPickPoint(PickingPoint pickingPoint) {

    }

    public void addPickingToRouteEnd(SpaceTimeGrid spaceTimeGrid, int pickTime) {
        Node pickPoint = route.get(route.size() - 1);
        for(int i = 0; i < pickTime; i++) {
            route.add(spaceTimeGrid.getNodePointer(pickPoint.getX(), pickPoint.getY(), (pickPoint.getTime() + i) + 1));
            totalPickTime++;
        }

    }
}
