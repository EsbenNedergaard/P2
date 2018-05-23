package BackEnd.Pathfinding;

import BackEnd.Geometry.Node.Node;
import BackEnd.Geometry.PickingPoint;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import BackEnd.Pathfinding.RouteFinders.RouteFinder;
import Warehouse.SimpleNxNWarehouse;
import Warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RouteFinderTest {
    private RouteFinder testRouteFinder;
    private List<PickingPoint> pickingPoints;
    private Warehouse warehouse;
    private SpaceTimeGrid spaceTimeGrid;

    @BeforeEach
    void beforeEach() {
        int MAX_TIME = 100;
        warehouse = new SimpleNxNWarehouse(5);
        spaceTimeGrid = new SpaceTimeGrid(warehouse.getBaseLayer(), MAX_TIME);

        this.testRouteFinder = new RouteFinder(new PathFinder(spaceTimeGrid), warehouse.getRouteStartPoint(), warehouse.getRouteEndPoint());
        this.createPickingPoints();
    }

    private void createPickingPoints() {
        List<Integer> idList = new ArrayList<>();
        idList.add(3);
        idList.add(6);

        pickingPoints = warehouse.getPickingPoints(idList);
    }

    private void addPickingAtPoint(int currTime, int x, int y, List<Node> expected) {
        int pickTime = 5;
        for (int i = currTime; i < currTime + pickTime; i++) {
            expected.add(spaceTimeGrid.getNodePointer(x, y, i));
        }
    }


    @Test
    void calculateBestRoute() {
        PickingRoute pickingRoute = testRouteFinder.calculateFastestRoute(pickingPoints);
        List<Node> expected = new ArrayList<>();

        //Walking to (3,0) which is ID 3.
        expected.add(spaceTimeGrid.getNodePointer(0, 0, 0));
        expected.add(spaceTimeGrid.getNodePointer(1, 0, 1));
        expected.add(spaceTimeGrid.getNodePointer(2, 0, 2));
        this.addPickingAtPoint(3, 3, 0, expected);

        //Walking to (3,2) which is ID 6
        expected.add(spaceTimeGrid.getNodePointer(4, 0, 8));
        expected.add(spaceTimeGrid.getNodePointer(4, 1, 9));
        expected.add(spaceTimeGrid.getNodePointer(4, 2, 10));
        this.addPickingAtPoint(11, 3, 2, expected);

        //Moving back to the starting area
        expected.add(spaceTimeGrid.getNodePointer(2, 2, 16));
        expected.add(spaceTimeGrid.getNodePointer(1, 2, 17));
        expected.add(spaceTimeGrid.getNodePointer(0, 2, 18));
        expected.add(spaceTimeGrid.getNodePointer(0, 1, 19));
        expected.add(spaceTimeGrid.getNodePointer(0, 0, 20));

        assertEquals(expected, pickingRoute.getRoute());
    }

    @Test
    void calculateBestRouteWithMovingObstacles() {
        List<Node> nonPermanentObstacles = new ArrayList<>();
        //Adding an obstacle at 1,0 for 10 time units, which should not be worth waiting for
        for (int i = 0; i < 10; i++) {
            nonPermanentObstacles.add(spaceTimeGrid.getNodePointer(1, 0, i));
        }
        //Adding one at 1,2 for 5 time units, which should be worth for the algorithm to wait for
        for (int i = 0; i < 5; i++) {
            nonPermanentObstacles.add(spaceTimeGrid.getNodePointer(1, 2, i));
        }
        this.testRouteFinder.getPathFinder().removeRoute(nonPermanentObstacles);

        PickingRoute pickingRoute = testRouteFinder.calculateFastestRoute(pickingPoints);
        List<Node> expected = new ArrayList<>();

        //Moving to 0,2 and waiting for the obstacle to be removed
        expected.add(spaceTimeGrid.getNodePointer(0, 0, 0));
        expected.add(spaceTimeGrid.getNodePointer(0, 1, 1));
        expected.add(spaceTimeGrid.getNodePointer(0, 2, 2));
        expected.add(spaceTimeGrid.getNodePointer(0, 2, 3));
        expected.add(spaceTimeGrid.getNodePointer(0, 2, 4));
        expected.add(spaceTimeGrid.getNodePointer(0, 2, 5));

        //Then going to (3,2), which is ID = 6
        expected.add(spaceTimeGrid.getNodePointer(1, 2, 6));
        expected.add(spaceTimeGrid.getNodePointer(2, 2, 7));
        this.addPickingAtPoint(8, 3, 2, expected);

        //Moving to (3,0) which is at ID = 3.
        expected.add(spaceTimeGrid.getNodePointer(4, 2, 13));
        expected.add(spaceTimeGrid.getNodePointer(4, 1, 14));
        expected.add(spaceTimeGrid.getNodePointer(4, 0, 15));
        this.addPickingAtPoint(16, 3, 0, expected);

        //Moving back to the start
        expected.add(spaceTimeGrid.getNodePointer(2, 0, 21));
        expected.add(spaceTimeGrid.getNodePointer(1, 0, 22));
        expected.add(spaceTimeGrid.getNodePointer(0, 0, 23));

        assertEquals(expected, pickingRoute.getRoute());
    }
}