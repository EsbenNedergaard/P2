package Warehouse.Aisle;

import Geometry.Point2D;
import Warehouse.Racks.HorizontalRackRow;
import Warehouse.Racks.Rack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalAisleTest {
    private HorizontalAisle testAisle01;
    private HorizontalRackRow testFirstRackRow01;
    private HorizontalRackRow testSecondRackRow01;

    @BeforeEach
    void setUp() {
        testAisle01 = new HorizontalAisle(30, new Point2D(0,5));
        testFirstRackRow01 = new HorizontalRackRow(new Point2D(1, 4), 28, 8);
        testSecondRackRow01 = new HorizontalRackRow(new Point2D(1, 6), 28, 8);
    }

    @Test
    void getAisleLength01() {
        assertEquals(30, testAisle01.getAisleLength());
    }

    @Test
    void getStartPoint01() {
        assertEquals(new Point2D(0, 5), testAisle01.getStartPoint());
    }

    @Test
    void getEndPoint01() {
        assertEquals(new Point2D(29, 5), testAisle01.getEndPoint());
    }

    @Test
    void getFirstRackRow01() {
        // Test if every rack in firstRackRow is as expected
        Rack[] rackArray = testAisle01.getFirstRackRow().getRackArray();
        int i = 0;
        for (Rack element : rackArray) {
            assertTrue(element.equals(new Rack(8, new Point2D(1 + i, 4))));
            i++;
        }
    }

    @Test
    void getSecondRackRow01() {
    }

    @Test
    void getRackRowList01() {
    }

    @Test
    void getPickingPoints01() {
    }

    @Test
    void doesItContainProductID01() {
    }
}
