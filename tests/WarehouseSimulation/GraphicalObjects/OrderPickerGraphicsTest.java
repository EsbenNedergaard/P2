package WarehouseSimulation.GraphicalObjects;

import BackEnd.Geometry.Node.Node;
import BackEnd.Geometry.Point2D;
import BackEnd.Graph.SpaceTimeGrid;
import BackEnd.Pathfinding.PathFinders.PathFinder;
import Warehouse.Dexion;
import Warehouse.Warehouse;
import WarehouseSimulation.GraphicalObjects.OrderPicker.MovingObject;
import WarehouseSimulation.GraphicalObjects.OrderPicker.OrderPicker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovingObjectTest {
    private PathFinder pathFinder;
    private MovingObject orderPicker;
    private Point2D startPoint;
    private Point2D endPoint;
    private final int PICK_TIME = 0;

    @BeforeEach
    void beforeEach() {
        Warehouse warehouse = new Dexion();
        SpaceTimeGrid timeGrid = new SpaceTimeGrid(warehouse.getBaseLayer(), 200);
        pathFinder = new PathFinder(timeGrid);
    }

    @Test
    void testPickerStartsAtExpectedPosition() {
        startPoint = new Point2D(0, 0);
        endPoint = new Point2D(14, 1);
        List<Node> shortestRoute = pathFinder.findFastestPath(startPoint, endPoint, 0, PICK_TIME).getRoute();
        orderPicker = new OrderPicker(shortestRoute, "black");

        assertEquals(startPoint, orderPicker.getCurrentPosition());
    }

    @Test
    void testPickerEndsAtExpectedPosition() {
        startPoint = new Point2D(0, 0);
        endPoint = new Point2D(14, 1);
        List<Node> shortestRoute = pathFinder.findFastestPath(startPoint, endPoint, 0, PICK_TIME).getRoute();
        orderPicker = new OrderPicker(shortestRoute, "black");

        int COUNTER = 0;
        while (orderPicker.move(COUNTER++)) ;  // Move picker towards end of route

        assertEquals(endPoint, orderPicker.getCurrentPosition());
    }

    @Test
    void testPickerEndsAtExceptedPositionWithDoubleSpeed() {
        startPoint = new Point2D(1, 0);
        endPoint = new Point2D(12, 10);
        List<Node> shortestRoute = pathFinder.findFastestPath(startPoint, endPoint, 0, PICK_TIME).getRoute();
        orderPicker = new OrderPicker(shortestRoute, "red");
        orderPicker.setScaleSpeed(2);

        int COUNTER = 0;
        while (orderPicker.move(COUNTER++)) ;

        assertEquals(endPoint, orderPicker.getCurrentPosition());
    }

    @Test
    void testPickerEndsAtExceptedPositionWithFiveTimesSpeed() {
        startPoint = new Point2D(1, 0);
        endPoint = new Point2D(12, 7);
        List<Node> shortestRoute = pathFinder.findFastestPath(startPoint, endPoint, 0, PICK_TIME).getRoute();
        orderPicker = new OrderPicker(shortestRoute, "purple");
        orderPicker.setScaleSpeed(5);

        int COUNTER = 0;
        while (orderPicker.move(COUNTER++)) ;

        assertEquals(endPoint, orderPicker.getCurrentPosition());
    }

}