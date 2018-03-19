package Warehouse.RackTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import Exceptions.IllegalRackDimensionException;
import Warehouse.RackSome;
import org.junit.jupiter.api.Test;

public class NewRackTest {

    RackSome testRack;

    @Test
    void testNewRack01() {
        assertThrows(IllegalRackDimensionException.class, () -> {
            testRack = new RackSome("A", 2, 2, 0, 0);
        });
    }

    @Test
    void testNewRack02() {
        assertThrows(IllegalRackDimensionException.class, () -> {
            testRack = new RackSome("A", 10, 10, 1, 1);
        });
    }

}
