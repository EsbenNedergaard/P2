package WarehouseSimulation.GraphicalObjects;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.PathFinders.FastestPathFinder;
import Warehouse.Warehouse;
import Warehouse.Dexion;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderPickerGraphicsTest {
    private FastestPathFinder fastestPathFinder;
    private OrderPickerGraphic orderPicker;
    private Point2D startPoint;
    private Point2D endPoint;
    private final int PICK_TIME = 0;

    @BeforeEach
    void beforeEach() {
        Warehouse warehouse = new Dexion();
        SpaceTimeGrid timeGrid = new SpaceTimeGrid(warehouse.getBaseLayer(), 200);
        fastestPathFinder = new FastestPathFinder(timeGrid);
    }

    @Test
    void testPickerStartsAtExpectedPosition() {
        startPoint = new Point2D(0,0);
        endPoint = new Point2D(14, 1);
        List<Node> shortestRoute = fastestPathFinder.findFastestPath(startPoint, endPoint, 0, PICK_TIME).getRoute();
        orderPicker = new OrderPickerGraphic(shortestRoute);

        assertEquals(startPoint, orderPicker.getCurrentPosition());
    }

    @Test
    void testPickerEndsAtExpectedPosition() {
        startPoint = new Point2D(0,0);
        endPoint = new Point2D(14, 1);
        List<Node> shortestRoute = fastestPathFinder.findFastestPath(startPoint, endPoint, 0, PICK_TIME).getRoute();
        orderPicker = new OrderPickerGraphic(shortestRoute);

        int COUNTER = 0;
        while(orderPicker.move(COUNTER++));  // Move picker towards end of route

        assertEquals(endPoint, orderPicker.getCurrentPosition());
    }

}