package Warehouse.Aisle;

import BackEnd.Geometry.Point2D;
import Warehouse.Racks.HorizontalRackRow;
import Warehouse.Racks.RackRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HorizontalAisleTest {
    private HorizontalAisle testAisle01;

    private ArrayList<RackRow> testRackRowList;

    @BeforeEach
    void setUp() {
        testAisle01 = new HorizontalAisle(28, new Point2D(0, 5));

        HorizontalRackRow testFirstRackRow = new HorizontalRackRow(new Point2D(0, 4), 28, 8);
        HorizontalRackRow testSecondRackRow = new HorizontalRackRow(new Point2D(0, 6), 28, 8);

        testRackRowList = new ArrayList<>();
        testRackRowList.add(testFirstRackRow);
        testRackRowList.add(testSecondRackRow);
    }

    @Test
    void getAisleLength01() {
        assertEquals(28, testAisle01.getAisleLength());
    }

    @Test
    void getStartPoint01() {
        assertEquals(new Point2D(0, 5), testAisle01.getStartPoint());
    }


    @Test
    void getRackRowList01() {
        assertEquals(testRackRowList, testAisle01.getRackRowList());
    }

    @Test
    void getPickingPoints01() {
    }
}
