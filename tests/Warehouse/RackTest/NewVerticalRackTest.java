package Warehouse.RackTest;

import static Warehouse.GUIWarehouse.HEIGHT_WAREHOUSE;
import static Warehouse.GUIWarehouse.WIDTH_WAREHOUSE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import Exceptions.IllegalRackDimensionException;
import Warehouse.ProductContainer.Rack;
import Warehouse.ProductContainer.VerticalRack;
import Geometry.Point2D;
import org.junit.jupiter.api.Test;

public class NewVerticalRackTest {

    Rack testRack;

    // Testing a length that is one longer than the height of the warehouse.
    @Test
    void testNewVerticalRackWarehouseHeight01() {
        assertThrows(IllegalRackDimensionException.class, () -> {
            testRack = new VerticalRack("A", HEIGHT_WAREHOUSE+1, new Point2D(0, 0));
        });
    }

    // Testing a rack placed
    @Test
    void testNewVerticalRackWarehouseHeight02() {
        assertThrows(IllegalRackDimensionException.class, () -> {
            testRack = new VerticalRack("A", 1, new Point2D(0, HEIGHT_WAREHOUSE));
        });
        a
    }

    @Test
    void testNewVerticalRackOutOfWarehouseWindow01() {
        // Placing a rack out of the window
        assertThrows(IllegalRackDimensionException.class, () -> {
            testRack = new VerticalRack("A", HEIGHT_WAREHOUSE, new Point2D(-1, -1));
        });
    }



}
