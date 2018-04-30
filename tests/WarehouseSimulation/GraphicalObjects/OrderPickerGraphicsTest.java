package WarehouseSimulation.GraphicalObjects;

import BackEnd.Geometry.Point2D;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderPickerGraphicsTest {
    private GenerateTestPickingRoute route = new GenerateTestPickingRoute();
    private OrderPickerGraphic orderPicker1;
    private int UPDATER;


    @BeforeEach
    void beforeEach() {
        orderPicker1 = new OrderPickerGraphic(route.getRoute1());
        UPDATER = 0;
    }

    @Test
    void testStartPointInRouteMatchesOrderPicker01() {
        Point2D routeStartPoint = route.getRoute1().get(0);

        assertEquals(routeStartPoint.getXPixels(), orderPicker1.getCurrentPosition().getXPixels());
        assertEquals(routeStartPoint.getYPixels(), orderPicker1.getCurrentPosition().getYPixels());
    }

    @Test
    void testFinishPointMatchesOrderPicker01() {
        int routeListLength = route.getRoute1().size();
        Point2D routeFinishPoint = route.getRoute1().get(routeListLength - 1);

        // Finish the picker route
        while(orderPicker1.move(UPDATER++));

        assertEquals(routeFinishPoint.getXPixels(), orderPicker1.getCurrentPosition().getXPixels());
        assertEquals(routeFinishPoint.getYPixels(), orderPicker1.getCurrentPosition().getYPixels());
    }

}