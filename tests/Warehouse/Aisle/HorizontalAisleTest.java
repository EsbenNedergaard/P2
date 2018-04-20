package Warehouse.Aisle;

import Geometry.Point2D;
import Warehouse.Racks.HorizontalRackRow;
import Warehouse.Racks.RackRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalAisleTest {
    private HorizontalAisle testAisle01;
    private HorizontalRackRow testFirstRackRow01;
    private HorizontalRackRow testSecondRackRow01;
    private ArrayList<RackRow> testRackRowList;

    @BeforeEach
    void setUp() {
        testAisle01 = new HorizontalAisle(30, new Point2D(0,5));

        testFirstRackRow01 = new HorizontalRackRow(new Point2D(1, 4), 28, 8);
        testSecondRackRow01 = new HorizontalRackRow(new Point2D(1, 6), 28, 8);

        testRackRowList = new ArrayList<>();
        testRackRowList.add(testFirstRackRow01);
        testRackRowList.add(testSecondRackRow01);
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
    void getFirstRackRow01() {
        assertEquals(testFirstRackRow01, testAisle01.getTopRackRow());
    }

    @Test
    void getSecondRackRow01() {
        assertEquals(testSecondRackRow01, testAisle01.getBottomRackRow());
    }

    @Test
    void getRackRowList01() {
        assertEquals(testRackRowList, testAisle01.getRackRowList());
    }


    @Test
    void getPickingPoints01() {
    }

    @Test
    void doesItContainProductID01() {
    }
}
