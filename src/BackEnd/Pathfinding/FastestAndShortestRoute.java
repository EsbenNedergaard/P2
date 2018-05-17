package BackEnd.Pathfinding;

public class FastestAndShortestRoute {
    private PickingRoute fastestRoute;
    private PickingRoute shortestRoute;

    public FastestAndShortestRoute(PickingRoute fastestRoute, PickingRoute shortestRoute) {
        this.fastestRoute = fastestRoute;
        this.shortestRoute = shortestRoute;
    }

    public PickingRoute getFastestRoute() {
        return fastestRoute;
    }

    public PickingRoute getShortestRoute() {
        return shortestRoute;
    }
}
