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


    @Test
    void calculateBestRoute() {
        PickingRoute pickingRoute = testRouteFinder.calculateFastestRoute(pickingPoints);
        List<Node> expected = new ArrayList<>();

        //Walking to (3,0) which is ID 3.
        expected.add(spaceTimeGrid.getNodePointer(0, 0, 0));
        expected.add(spaceTimeGrid.getNodePointer(1, 0, 1));
        expected.add(spaceTimeGrid.getNodePointer(2, 0, 2));

        //Picking at ID = 3
        expected.add(spaceTimeGrid.getNodePointer(3, 0, 3));
        expected.add(spaceTimeGrid.getNodePointer(3, 0, 4));
        expected.add(spaceTimeGrid.getNodePointer(3, 0, 5));
        expected.add(spaceTimeGrid.getNodePointer(3, 0, 6));
        expected.add(spaceTimeGrid.getNodePointer(3, 0, 7));

        //Walking to (3,2) which is ID 6
        expected.add(spaceTimeGrid.getNodePointer(4, 0, 8));
        expected.add(spaceTimeGrid.getNodePointer(4, 1, 9));
        expected.add(spaceTimeGrid.getNodePointer(4, 2, 10));

        //Picking at ID=6
        expected.add(spaceTimeGrid.getNodePointer(3, 2, 11));
        expected.add(spaceTimeGrid.getNodePointer(3, 2, 12));
        expected.add(spaceTimeGrid.getNodePointer(3, 2, 13));
        expected.add(spaceTimeGrid.getNodePointer(3, 2, 14));
        expected.add(spaceTimeGrid.getNodePointer(3, 2, 15));

        //Moving back to the starting area
        expected.add(spaceTimeGrid.getNodePointer(2, 2, 16));
        expected.add(spaceTimeGrid.getNodePointer(1, 2, 17));
        expected.add(spaceTimeGrid.getNodePointer(0, 2, 18));
        expected.add(spaceTimeGrid.getNodePointer(0, 1, 19));
        expected.add(spaceTimeGrid.getNodePointer(0, 0, 20));

        assertEquals

    }
}